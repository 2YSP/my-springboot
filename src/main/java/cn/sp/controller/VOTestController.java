package cn.sp.controller;

import cn.sp.vo.BaseVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 2YSP
 * @Description:
 * @Date: Created in 2017/12/18
 */
@RestController
public class VOTestController {

    @GetMapping("vo/test")
    public BaseVo test(){
        BaseVo baseVo = new BaseVo();
        //设置为Null
        baseVo.setResult(null);
        return baseVo;
    }
}
