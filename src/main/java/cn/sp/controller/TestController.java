package cn.sp.controller;

import cn.sp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 2YSP on 2017/11/3.
 */
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "set",method = RequestMethod.GET)
    public String set(){
        stringRedisTemplate.opsForValue().set("you","123");
        return stringRedisTemplate.opsForValue().get("you");
    }

    @RequestMapping(value = "setUser",method = RequestMethod.GET)
    public String setUser(){
        User user = new User();
        user.setId(1);
        user.setName("dada");
        user.setAge(20);
        redisTemplate.opsForValue().set("user",user);
        return "ok";

    }

    @RequestMapping(value = "getUser",method = RequestMethod.GET)
    public User getUser(){

        return (User) redisTemplate.opsForValue().get("user");

    }
}
