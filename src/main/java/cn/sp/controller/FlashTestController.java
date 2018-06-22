package cn.sp.controller;

import cn.sp.entity.Person;
import cn.sp.service.PersonService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author ship
 * @Description 重定向传参数的两种方式：1.URL路径和查询参数 2.利用flash属性
 * @Date: 2018-06-22 13:47
 */
@Controller
@RequestMapping("flash")
public class FlashTestController {

    @Autowired
    private PersonService personService;


    @ApiOperation(value = "flash属性练习")
    @GetMapping("/{uid}")
    public String query(@PathVariable int uid, RedirectAttributes model){
        Person p1 = personService.queryById(uid);
        model.addFlashAttribute("person",p1);
        //springMvc会将 name自动填充到重定向的url中
        model.addAttribute("name",p1.getName());
        return "redirect:/flash/name/{name}";
    }


    @GetMapping("/name/{name}")
    public String receive(@PathVariable String name, Model model){
        System.out.println("名字叫："+name);
        return "/flash";
    }

}
