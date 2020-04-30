package cn.sp.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.sp.service.IAService;

/**
 * @author Ship
 * @date 2020-04-30 21:04
 */
@Component
public class IAServiceHolder {

    public static final Map<String, IAService> SERVICE_MAP = new ConcurrentHashMap<>();

//    @Autowired
//    public IAServiceHolder(Map<String, IAService> serviceMap) {
//        System.out.println(serviceMap.size());
//        serviceMap.forEach((key, value) -> {
//            SERVICE_MAP.put(key, value);
//        });
//    }

    @Autowired
    public IAServiceHolder(List<IAService> serviceList) {
        System.out.println(serviceList.size());
        serviceList.forEach(s -> s.sayHello());
    }
}
