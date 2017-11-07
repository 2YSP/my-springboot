package cn.sp.dao;


import cn.sp.entity.Person;


public interface PersonDao {
	 void save(Person person);
	 
	 Person queryById(Integer id);
}
