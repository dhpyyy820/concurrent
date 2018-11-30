package com.yy.security;

import java.util.concurrent.atomic.AtomicInteger;

class countAtomThread extends Thread {

    //private static int count;
    private static AtomicInteger count = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i=0; i<1000; i++)
            count.incrementAndGet();
        System.out.println("当前计数为："+count);
    }
}

public class AtomTest {

    public static void main(String[] args) {
        countAtomThread[] arr = new countAtomThread[10];
        for (int i=0; i<10; i++) {
            arr[i] = new countAtomThread();
        }
        for (int i=0; i<10; i++) {
            arr[i].start();
        }
    }
}
