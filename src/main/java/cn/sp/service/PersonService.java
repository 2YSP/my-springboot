package cn.sp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import cn.sp.dao.PersonDao;
import cn.sp.entity.Person;

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
}
