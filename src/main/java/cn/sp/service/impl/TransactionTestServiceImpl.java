package cn.sp.service.impl;

import cn.sp.dao.PersonDao;
import cn.sp.entity.Person;
import cn.sp.service.TransactionTestService;
import cn.sp.utils.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Ship
 * @version 1.0.0
 * @description
 * @date 2021/10/22 11:22
 */
@Service
public class TransactionTestServiceImpl implements TransactionTestService {

    @Autowired
    private PersonDao personDao;

    @Override
    public void batchAdd(List<Person> personList) {
        // 异步调用也会导致事务失效
//        personList.parallelStream().forEach(person -> add(person));

        // 这种不会失效
        TransactionTestServiceImpl testService = SpringContextHolder.getBean(TransactionTestServiceImpl.class);
        personList.forEach(person -> {
            testService.add(person);
        });
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void add(Person person) {
        System.out.println("add person================");
        personDao.save(person);
        int i = 1 / 0;
    }
}
