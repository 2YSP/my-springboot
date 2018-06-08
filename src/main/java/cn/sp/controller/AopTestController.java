package cn.sp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 2YSP on 2018/6/7.
 */
@RestController
@RequestMapping("aop")
public class AopTestController {

    @GetMapping("testBeforeService")
    public String testBeforeService(String key,String value){

        return "key="+key+"  value="+value;
    }

    @RequestMapping("/testAfterReturning")
    public String testAfterReturning(String key){

        return "key=: "+key;
    }

    @RequestMapping("/testAfterReturning01")
    public Integer testAfterReturning01(Integer key){

        return key;
    }

    @RequestMapping("/testAfterThrowing")
    public String testAfterThrowing(String key){

        throw new NullPointerException();
    }

    @RequestMapping("/testAfter")
    public String testAfter(String key){

        throw new NullPointerException();
    }

    @RequestMapping("/testAfter02")
    public String testAfter02(String key){

        return key;
    }

    @RequestMapping("/testAroundService")
    public String testAroundService(String key){

        return "环绕通知："+key;
    }
}
