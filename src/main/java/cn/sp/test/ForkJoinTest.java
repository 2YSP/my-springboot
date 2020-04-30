package cn.sp.test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 相关博客http://blog.dyngr.com/blog/2016/09/15/java-forkjoinpool-internals/
 * Created by 2YSP on 2019/9/13.
 */
public class ForkJoinTest {

  public static void main(String[] args) {
    long t1 = System.currentTimeMillis();
//    testWithOutForkJoinPool();
    testWithForkJoinPool();
    long t2 = System.currentTimeMillis();
    System.out.println("耗时：" + (t2 - t1) + " ms");
  }

  /**
   * 使用ForkJoin框架测试  1274ms
   */
  private static void testWithForkJoinPool() {
    final int SIZE = 10000000;
    double[] numbers = new double[SIZE];
    for (int i = 0; i < numbers.length; i++) {
      numbers[i] = Math.random();
    }
    Counter counter = new Counter(numbers, 0, numbers.length, new Filter() {
      @Override
      public boolean accept(double t) {
        return t > 0.5;
      }
    });
    ForkJoinPool forkJoinPool = new ForkJoinPool();
    forkJoinPool.invoke(counter);
    System.out.println(counter.join());
  }

  /**
   * 单线程测试 319ms  这就尴尬了，为什么单线程的还快些
   */
  private static void testWithOutForkJoinPool() {
    final int SIZE = 10000000;
    double[] numbers = new double[SIZE];
    for (int i = 0; i < numbers.length; i++) {
      numbers[i] = Math.random();
    }
    int count = 0;
    for (int i = 0; i < numbers.length; i++) {
      if (numbers[i] > 0.5) {
        count++;
      }
    }
    System.out.println(count);
  }

}

interface Filter {

  boolean accept(double t);
}

class Counter extends RecursiveTask<Integer> {

  public static final int THRESHOLD = 1000;

  private double[] values;
  private int to;
  private int from;
  private Filter filter;

  public Counter(double[] values, int from, int to, Filter filter) {
    this.values = values;
    this.filter = filter;
    this.from = from;
    this.to = to;
  }


  @Override
  protected Integer compute() {
    if (to - from < THRESHOLD) {
      // calculate directly
      int count = 0;
      for (int i = from; i < to; i++) {
        if (filter.accept(values[i])) {
          count++;
        }
        return count;
      }
    } else {
      int mid = (from + to) / 2;
      Counter leftCounter = new Counter(values, from, mid, filter);
      Counter rightCounter = new Counter(values, mid, to, filter);
      invokeAll(leftCounter, rightCounter);
      return leftCounter.join() + rightCounter.join();
    }
    return null;
  }
}
