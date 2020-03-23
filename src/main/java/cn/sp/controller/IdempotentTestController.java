package cn.sp.controller;

import cn.sp.annotation.AutoIdempotent;
import cn.sp.service.TokenService;
import cn.sp.vo.CommonResult;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 2YSP on 2020/3/23.
 */
@RestController
@RequestMapping("/token")
public class IdempotentTestController {

    @Autowired
    private TokenService tokenService;

    /**
     * 获取token
     *
     * @return
     */
    @GetMapping("")
    public String getToken() {
        return tokenService.createToken();
    }

    @AutoIdempotent
    @PostMapping("/good")
    public CommonResult addGood() {
        System.out.println("向数据库添加商品。。。");
        return CommonResult.success("添加商品成功");
    }
}
