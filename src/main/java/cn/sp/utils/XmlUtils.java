package cn.sp.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Ship
 * @version 1.0.0
 * @description:
 * @date 2022/04/01 14:13
 */
public class XmlUtils {

    /**
     * XStream实例缓存，避免每次new造成性能问题
     */
    private static final Map<Class<?>, XStream> XSTREAM_MAP = new ConcurrentHashMap<>(64);

    /**
     * 获取XStream实例
     *
     * @return
     */
    private static XStream getXStreamInstance(Class<?> objClazz) {
        return XSTREAM_MAP.computeIfAbsent(objClazz, k -> {
            XStream xstream = new XStream(new DomDriver("UTF-8"));
            xstream.allowTypesByRegExp(new String[]{".*"});
            xstream.autodetectAnnotations(true);
            return xstream;
        });
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
        XStream xstream = getXStreamInstance(obj.getClass());
        xstream.processAnnotations(obj.getClass());
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
        XStream xstream = getXStreamInstance(obj.getClass());
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
        XStream xstream = getXStreamInstance(clazz);
        xstream.processAnnotations(clazz);
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
        XStream xstream = getXStreamInstance(clazz);
        xstream.alias(alias, clazz);
        T t = (T) xstream.fromXML(xml);
        return t;
    }


}
