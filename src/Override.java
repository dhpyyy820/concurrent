
public class Override {

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @java.lang.Override
            public void run() {
                for (int i=0; i<20; i++) {
                    System.out.println("线程t1运行中：" + i);
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @java.lang.Override
            public void run() {
                try {
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i=0; i<20; i++) {
                        System.out.println("线程t2运行中：" + i);
                }
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @java.lang.Override
            public void run() {
                try {
                    t2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i=0; i<20; i++) {
                    System.out.println("线程t3运行中：" + i);
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }

}
