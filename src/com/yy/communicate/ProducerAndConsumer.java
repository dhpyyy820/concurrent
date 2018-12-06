package com.yy.communicate;

class Info {

    private String name;

    private String content;

    private boolean flag = true;

    // 生产者生产方法
    public synchronized void set(String name, String content) {
        if(!flag) {
            try {
                wait();     //生产者阻塞
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.name = name;
        try {
            Thread.sleep(40);       //name与content的设置需要保证原子性
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.content = content;
        System.out.println("生产者生产了：" + name + ":" +content);    //生产者生产完毕
        flag = false;   //设置标志：生产者不可生产，消费者可消费
        notify();   //唤醒消费者
    }

    // 消费者消费方法
    public synchronized void get() {
        if(flag) {
            try {
                wait();     //消费者阻塞
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("消费者消费了：" + name + ":" +content);    //消费者消费完毕
        flag = true;    //设置标志：消费者不可消费，生产者可生产
        notify();     //唤醒生产者
    }
}

class Producer implements Runnable {

    private Info info;

    public Producer(Info info) {
        this.info = info;
    }

    @Override
    public void run() {
        boolean exchange = true;
        for(int i=0; i<10; i++) {
            if(exchange) {
                info.set("李雷", "班长");
                exchange = false;
            } else {
                info.set("韩梅梅", "学习委员");
                exchange = true;
            }
        }
    }
}

class Consumer implements Runnable {

    private Info info;

    public Consumer(Info info) {
        this.info = info;
    }

    @Override
    public void run() {
        for (int i=0; i<10; i++) {
            info.get();
        }
    }
}

public class ProducerAndConsumer {

    public static void main(String[] args) throws InterruptedException {
        Info info = new Info();
        Producer producer = new Producer(info);
        Consumer consumer = new Consumer(info);
        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(consumer);
        t1.start();
        Thread.sleep(200);  //一开始让生产者先进行生产
        t2.start();
    }
}
