package cn.sp.service.impl;

import org.springframework.stereotype.Component;

import cn.sp.service.IAService;

/**
 * @author Ship
 * @date 2020-04-30 21:03
 */
@Component
public class AServiceImpl implements IAService {


    @Override
    public void sayHello() {
        System.out.println("A say hello");
    }
}
