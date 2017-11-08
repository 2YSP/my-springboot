package cn.sp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 2YSP on 2017/11/8.
 */
@RestController
@RequestMapping("hello")
public class HelloController {

    final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("")
    public String sayHello(){
        logger.debug("访问hello.....");
        return "hello";
    }
}
