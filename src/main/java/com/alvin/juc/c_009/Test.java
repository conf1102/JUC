package com.alvin.juc.c_009;

import java.util.concurrent.TimeUnit;

/**
 * 一个同步方法可以调用另外一个同步方法，一个线程已经拥有某个对象的锁，再次申请的时候仍然会得到该对象的锁.
 * 也就是说synchronized获得的锁是可重入的
 * @author mashibing
 */


public class Test {
    public static void main(String[] args) {
        new Test().testLock1();
    }

    public synchronized void testLock1() {
        System.out.println("Enter TestLock1");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        testLock2();
    }

    public synchronized void testLock2() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Enter TestLock2");
    }

}
