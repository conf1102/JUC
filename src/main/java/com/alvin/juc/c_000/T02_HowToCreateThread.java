package com.alvin.juc.c_000;

import java.util.concurrent.*;

public class T02_HowToCreateThread {
    static class MyThread extends Thread {
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hello MyThread");
        }
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("Hello MyRunnable");
        }
    }

    static class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("Hello MyCallable");
            return "success";
        }
    }

    // 5 ways to start thread
    public static void main(String[] args) {
        new MyThread().start();
        new Thread(new MyRunnable()).start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hello Lambda");
        }).start();

        Thread thread = new Thread(new FutureTask<String>(new MyCallable()));
        thread.start();

        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(() -> {
            System.out.println("Hello ThreadPool");
        });
        service.shutdown();
    }
}
