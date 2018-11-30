package com.yy.security;

/**
 * 售票方法类：死锁
 */
class SaleTicketLock implements Runnable {

    private int count = 100;

    public boolean flag = true;

    private Object mutex = new Object();    // 用于产生锁的对象

    @Override
    public void run() {
        if (flag) {
            while (true) {
                sale();             // 要求先获得this锁，再获得mutex锁
            }
        } else {
            synchronized (mutex) {  // 要求先获得mutex锁，再获得this锁
                System.out.println(Thread.currentThread().getName() + "拿到mutex锁");
                while (true) {
                    sale();
                }
            }
        }
    }

    //售票方法，要求先获得this锁，再获得mutex锁
    private synchronized void sale() {
        try {
            System.out.println(Thread.currentThread().getName() + "拿到this锁");
            Thread.sleep(100);  // 让拿到this锁的一号窗口线程休眠，给二号窗口线程执行机会
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (mutex) {
            System.out.println(Thread.currentThread().getName() + "拿到mutex锁");
            if (count > 0) {
                System.out.println(Thread.currentThread().getName() + "出售第" + (100-count+1) + "张票");
                count--;
            }
        }
    }
}

public class DeadLock {

    public static void main(String[] args) throws InterruptedException {
        SaleTicketLock locksale = new SaleTicketLock();
        Thread t1 = new Thread(locksale, "一号窗口");
        Thread t2 = new Thread(locksale, "二号窗口");
        t1.start();
        Thread.sleep(40);     //休眠当前线程（主线程）,让一号窗口线程先运行
        locksale.flag = false;
        t2.start();
    }

}
