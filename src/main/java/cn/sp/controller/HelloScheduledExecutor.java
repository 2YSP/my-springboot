package cn.sp.controller;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Ship
 * @date 2020-01-13 19:46
 */
public class HelloScheduledExecutor {

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
        System.out.println(System.currentTimeMillis());
        // 延迟1秒执行一次任务
        executor.schedule(new HelloTask(), 1, TimeUnit.SECONDS);

        // 初始化延迟1秒后，按照固定的2秒频率执行
        executor.scheduleAtFixedRate(new HelloTask(), 1, 2, TimeUnit.SECONDS);
        // 初始化延迟1秒后，按照固定的2秒延迟执行任务
        executor.scheduleWithFixedDelay(new HelloTask(), 1, 2, TimeUnit.SECONDS);
    }

    public static class HelloTask implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis() + "  hello world  " + Thread.currentThread().getName());
        }
    }
}
