package cn.sp.controller;

import cn.sp.exception.BusinessException;
import java.util.Date;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
	
	
	@ApiOperation(value = "创建用户",notes = "1")
	@PostMapping("save")
	public IResult savePerson(@RequestBody @Validated Person person){
//		Person person = new Person();
//		person.setName("lisi");
//		person.setAge(12);
//		person.setSex("女");
//		person.setBirthday(new Date());
		try{
			personService.save(person);
		}catch(Exception e){
			e.printStackTrace();
			return IResult.ERROR;
		}
		return IResult.SUCCESS;
	}

	@ApiOperation(value = "redis操作测试接口")
	@GetMapping("test")
	public String test(){
		stringRedisTemplate.opsForValue().set("you", "andme");
		return stringRedisTemplate.opsForValue().get("you");
	}

	@ApiOperation(value = "查询用户信息")
	@GetMapping("/person/{uid}")
	public Person get(@PathVariable int uid){
		Person p1 = personService.queryById(uid);
		System.out.println("p1:"+p1);
		if (uid == 7){
			throw new BusinessException(525,"查询用户信息异常");
		}
		Person p2 = personService.queryById(2);
		System.out.println("p2"+p2);
		return p1;
	}


	@PostMapping("")
	public String add(@RequestBody Person person){
		personService.add(person);
		return "ok";
	}
}
