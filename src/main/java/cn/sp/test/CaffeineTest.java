package cn.sp.test;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.TimeUnit;

/**
 * Created by 2YSP on 2019/6/1.
 */
public class CaffeineTest {

    public static LoadingCache<String, String> cache = CacheBuilder.newBuilder().refreshAfterWrite(10, TimeUnit.SECONDS)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    return System.currentTimeMillis() + " : "+key;
                }
            });

    public static void main(String[] args)throws Exception {
//        cache.put("test","hello");
//        for(int i=0;i<11;i++){
//            System.out.println(cache.get("test"));
//            Thread.sleep(2000);
//        }

        ZkClient zkClient = new ZkClient("192.168.136.128:2181");

//        zkClient.createPersistent("/rpc/wang.leisure.api.UserService/service",true);
        zkClient.deleteRecursive("/rpc");
    }
}
