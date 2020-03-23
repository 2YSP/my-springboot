package cn.sp.service;

/**
 * Created by 2YSP on 2020/3/23.
 */
public interface RedisService {

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    boolean set(String key, String value);

    /**
     * 写入缓存并设置时间
     *
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    boolean setEx(final String key, String value, Long expireTime);


    boolean exists(final String key);


    Object get(String key);

    boolean remove(String key);

}
