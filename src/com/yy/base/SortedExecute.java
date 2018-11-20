package com.yy.base;

public class SortedExecute {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<20; i++) {
                    System.out.println("t1线程执行中......." + i);
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i=0; i<20; i++) {
                    System.out.println("t2线程执行中......." + i);
                }
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i=0; i<20; i++) {
                    System.out.println("t3线程执行中......." + i);
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }
}
