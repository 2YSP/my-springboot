package cn.sp.dao;


import cn.sp.entity.Person;
import java.util.List;


public interface PersonDao {
	 void save(Person person);
	 
	 Person queryById(Integer id);

	 List<Person> queryPage();
}
