package cn.sp.service;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import cn.sp.dao.PersonDao;
import cn.sp.entity.Person;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {

	@Autowired
	private PersonDao personDao;
	
	@Autowired
	private RedisTemplate<String,String> redisTemplate;//处理对象


	public void save(Person person){
		personDao.save(person);
	}
	
	@Cacheable(value="personinfo")
	public Person queryById(Integer id){
		System.out.println("PersonService.queryById()=========从数据库中进行获取的....id="+id);


		return personDao.queryById(id);
	}
	
	@CacheEvict(value="personinfo")
	public void deleteFromCache(){
		System.out.println("PersonService.delete().从缓存中删除");
	}

	public void add(Person person) {
		Person person2 = new Person();
		person2.setName("fadf");
		person2.setAge(19);
		person2.setSex("男");
		personDao.save(person2);
		((PersonService)AopContext.currentProxy()).doAdd(person);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void doAdd(Person person) {
		personDao.save(person);
		int i = 1/0;
	}
}
