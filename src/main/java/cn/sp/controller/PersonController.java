package cn.sp.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.sp.entity.Person;
import cn.sp.service.PersonService;
import cn.sp.vo.IResult;

@RestController
@RequestMapping("person")
public class PersonController {

	@Autowired
	private PersonService personService;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;//处理字符串
	
	
	
	@GetMapping("save")
	public IResult savePerson(){
		Person person = new Person();
		person.setName("lisi");
		person.setAge(12);
		person.setSex("女");
		person.setBirthday(new Date());
		try{
			personService.save(person);
		}catch(Exception e){
			e.printStackTrace();
			return IResult.ERROR;
		}
		return IResult.SUCCESS;
	}
	
	@GetMapping("test")
	public String test(){
		stringRedisTemplate.opsForValue().set("you", "andme");
		return stringRedisTemplate.opsForValue().get("you");
	}
	
	@GetMapping("/get")
	public String get(){
		Person p1 = personService.queryById(2);
		System.out.println("p1"+p1);
		Person p2 = personService.queryById(2);
		System.out.println("p2"+p2);
		return "ok";
	}
	
}
