package cn.sp.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * @author Ship
 * @version 1.0.0
 * @description: xml工具类
 * @date 2022/04/01 14:13
 */
public class XmlUtils {

    private static XStream xstream = new XStream(new DomDriver("UTF-8"));

    static {
        xstream.allowTypesByRegExp(new String[]{".*"});
        xstream.autodetectAnnotations(true);
    }

    /**
     * 对象转xml
     *
     * @param obj
     * @return
     */
    public static String toXml(Object obj) {
        if (obj == null) {
            return "";
        }
        return xstream.toXML(obj);
    }

    /**
     * 对象转xml
     *
     * @param obj
     * @param alias
     * @return
     */
    public static String toXml(Object obj, String alias) {
        if (obj == null) {
            return "";
        }
        xstream.alias(alias, obj.getClass());
        return xstream.toXML(obj);
    }


    /**
     * xml转对象（有别名注解的情况）
     *
     * @param xml
     * @param clazz
     * @param <T>   类上必须有别名注解
     * @return
     */
    public static <T> T fromXml(String xml, Class<T> clazz) {
        T t = (T) xstream.fromXML(xml);
        return t;
    }

    /**
     * xml转对象
     *
     * @param xml
     * @param clazz
     * @param alias
     * @param <T>   类上不需要有别名注解
     * @return
     */
    public static <T> T fromXml(String xml, Class<T> clazz, String alias) {
        xstream.alias(alias, clazz);
        T t = (T) xstream.fromXML(xml);
        return t;
    }

    public static void main(String[] args) {
//        QimenRequest request = new QimenRequest();
//        request.setCode("0");
//        request.setEntryOrderId("EID1234");
//        String xml = toXml(request, "response");
//        System.out.println(xml);
//        QimenRequest qimenRequest = XmlUtils.fromXml(xml, QimenRequest.class, "response");
//        System.out.println(qimenRequest);

    }


}
