package com.alvin.juc.c_000;

import java.util.concurrent.TimeUnit;

public class T04_ThreadState {
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println(this.getState());
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.MICROSECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        Thread t = new MyThread();
        System.out.println(t.getState());
        t.start();
        try {
            //Purpose of t.join is let MyThread go firstly, then continue run the main Thread
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t.getState());
    }
}
