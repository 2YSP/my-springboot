package cn.sp.utils;


import com.alibaba.fastjson.JSON;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ship
 * @date 2020-08-05 14:53
 */
public class SerializingUtil {

    /**
     * 将目标类序列化为byte数组
     *
     * @param source
     * @param <T>
     * @return
     */
    public static <T> byte[] serialize(T source) {
        Schema<T> schema = RuntimeSchema.getSchema((Class<T>) source.getClass());
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        final byte[] result;
        try {
            result = ProtobufIOUtil.toByteArray(source, schema, buffer);
        } finally {
            buffer.clear();
        }
        return result;
    }

    /**
     * 将byte数组序列化为目标类
     *
     * @param source
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T deserialize(byte[] source, Class<T> clazz) {
        Schema<T> schema = RuntimeSchema.getSchema(clazz);
        T t = schema.newMessage();
        ProtobufIOUtil.mergeFrom(source, t, schema);
        return t;
    }

    public static void main(String[] args) throws IOException {
//        RpcRequest rpcRequest = createEntity();
//        byte[] bytes = serialize(rpcRequest);
//        System.out.println("数组大小:"+bytes.length);
//        RpcRequest result = deserialize(bytes, RpcRequest.class);
//        System.out.println(JSON.toJSONString(result));
        List<RpcRequest> list = new ArrayList<>(100000);
        for (int i = 0; i < 100000; i++) {
            list.add(createEntity());
        }

        long t1 = System.currentTimeMillis();
        int size = 0;
        for (RpcRequest request : list) {
            byte[] bytes = serialize(request);
            size += bytes.length;
        }

        long t2 = System.currentTimeMillis();

        System.out.println("protobuf序列化耗时：" + (t2 - t1) + "ms,数组大小:" + size);
        System.out.println("----------------------------");

        long t3 = System.currentTimeMillis();
        int num = 0;
        for (RpcRequest request : list) {
            byte[] bytes = javaSerialize(request);
            num += bytes.length;
        }

        long t4 = System.currentTimeMillis();

        System.out.println("java序列化耗时：" + (t4 - t3) + "ms,数组大小:" + num);
    }

    public static byte[] javaSerialize(Object o) throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bo);
        out.writeObject(o);
        return bo.toByteArray();
    }

    private static RpcRequest createEntity() {
        RpcRequest rpcRequest = new RpcRequest();
        Map<String, String> headers = new HashMap<>();
        headers.put("aa", "1");
        rpcRequest.setHeaders(headers);
        rpcRequest.setServiceName("serviceA");
        rpcRequest.setParameters(new Object[]{"hello", 10});
        rpcRequest.setParameterTypes(new Class[]{String.class, Integer.class});
        return rpcRequest;
    }
}
