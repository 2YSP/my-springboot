package cn.sp.controller;

import cn.sp.component.IP;
import cn.sp.vo.BaseVo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author: 2YSP
 * @Description:
 * @Date: Created in 2017/12/18
 */
@Slf4j
@RestController
public class VOTestController {

    @ApiOperation(value = "jackson注解测试接口")
    @GetMapping("vo/test")
    public BaseVo test(@IP String ip){

        log.info("请求的ip地址为:{}",ip);
        BaseVo baseVo = new BaseVo();
        //设置为Null
        baseVo.setResult(null);
        return baseVo;
    }
}
