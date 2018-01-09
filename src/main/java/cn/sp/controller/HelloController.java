package cn.sp.controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 2YSP on 2017/11/8.
 */
//@RestController
@Controller
@RequestMapping("hello")
public class HelloController {

    final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @ApiOperation(value = "拦截器测试接口")
    @RequestMapping("/h")
    public String sayHello(ModelMap model){
        logger.debug("访问hello.....");
        model.addAttribute("title","访问hello");
        return "hello";
    }

    @RequestMapping("illegalAccess")
    public String illegalAccess(){
        return "illegalAccess";
    }
}
