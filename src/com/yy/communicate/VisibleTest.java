package com.yy.communicate;

class VisibleThread extends Thread {

    public boolean flag = true;
    //public volatile boolean flag = true;

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        System.out.println("子线程执行中  。。。");
        while (flag) {

        }
        System.out.println("子线程停止");
    }
}

public class VisibleTest {

    public static void main(String[] args) throws InterruptedException {
        VisibleThread tson = new VisibleThread();
        tson.start();
        Thread.sleep(100);   //休眠主线程，让子线程先执行
        tson.setFlag(false);       // 修改共享全局变量flag为false
        System.out.println("共享全局变量flag已设置为false");
        Thread.sleep(1000);  //休眠主线程，让子线程继续执行一段时间
        System.out.println("共享全局变量flag在主线程看到的值为" + tson.flag);
    }

}
