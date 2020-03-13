package thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class ThreadInterruptedTest {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            while (true) {
                if (Thread.interrupted()) {
                    System.out.println("线程被中断了");
                    break;
                }
                /*try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }*/
                System.out.println("开始");
                LockSupport.park();
                System.out.println("等待:" + Thread.currentThread().isInterrupted());
            }
        });
        t.start();
        try {
            TimeUnit.SECONDS.sleep(1);
            t.interrupt();
            TimeUnit.SECONDS.sleep(5);
            LockSupport.unpark(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
