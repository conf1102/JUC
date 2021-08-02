package com.alvin.juc.c_010;

import java.util.concurrent.TimeUnit;

/**
 * 一个同步方法可以调用另外一个同步方法，一个线程已经拥有某个对象的锁，再次申请的时候仍然会得到该对象的锁.
 * 也就是说synchronized获得的锁是可重入的
 * 这里是继承中有可能发生的情形，子类调用父类的同步方法
 *
 * @author mashibing
 */

public class Test {
    public synchronized void test() {
        System.out.println("Parent Test Started");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Parent Test Ended");
    }

    public static void main(String[] args) {
        SubTest t = new SubTest();
        t.test();
    }
}

class SubTest extends Test {
    @Override
    public synchronized void test() {
        System.out.println("Sub Test Started");
        super.test();
        System.out.println("Sub Test Ended");
    }
}
