package com.yy.security;

/**
 * 售票方法类
 */
class SaleTicketRun implements Runnable {

    //总票数
    private int count=100;

    @Override
    public void run() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sale_unsecuri();
        //sale_synchroBlock();
        //sale_synchroMethod();
    }

    //不安全的方法
    private void sale_unsecuri() {
        while (count > 0) {
            System.out.println(Thread.currentThread().getName() + "出售第" + (100-count+1) + "张票");
            count--;
        }
    }

    //同步代码块方法
    private void sale_synchroBlock() {
        synchronized (this) {
            while (count > 0) {
                System.out.println(Thread.currentThread().getName() + "出售第" + (100-count+1) + "张票");
                count--;
            }
        }
    }

    //同步方法
    private synchronized void sale_synchroMethod() {
        while (count > 0) {
            System.out.println(Thread.currentThread().getName() + "出售第" + (100-count+1) + "张票");
            count--;
        }
    }
}

public class ThreadTicket {

    public static void main(String[] args) {
        SaleTicketRun salerun = new SaleTicketRun();
        Thread t1 = new Thread(salerun, "1号窗口");
        Thread t2 = new Thread(salerun, "2号窗口");
        t1.start();
        t2.start();
    }
}
