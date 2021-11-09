package cn.sp.service.impl;

import cn.sp.dao.PersonDao;
import cn.sp.entity.Person;
import cn.sp.exception.BusinessException;
import cn.sp.service.TransactionTestService;
import cn.sp.utils.SpringContextHolder;
import com.github.rholder.retry.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.*;

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

    private static final Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
            .retryIfException()
            .retryIfResult(res -> res == false)
            .withStopStrategy(StopStrategies.stopAfterAttempt(3))
            .withWaitStrategy(WaitStrategies.incrementingWait(200, TimeUnit.MILLISECONDS, 1, TimeUnit.SECONDS))
            .build();

    public static void main(String[] args) {
        try {
            Boolean result = retryer.call(new Task(-1));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static class Task implements Callable<Boolean> {

        private int i;

        public Task(int i){
            this.i = i;
        }

        @Override
        public Boolean call() throws Exception {
            if (i < 0){
                throw new BusinessException("aaa");
            }
            return false;
        }
    }
}
