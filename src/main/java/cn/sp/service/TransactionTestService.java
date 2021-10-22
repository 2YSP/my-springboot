package cn.sp.service;

import cn.sp.entity.Person;

import java.util.List;

/**
 * @author Ship
 * @version 1.0.0
 * @description
 * @date 2021/10/22 11:22
 */
public interface TransactionTestService {

    void batchAdd(List<Person> personList);

    void add(Person person);
}
