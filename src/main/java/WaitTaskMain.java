import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class WaitTaskMain {
    public static void main(String[] args) {
        WaitTask waitTask = new WaitTask();
        new Thread(() -> {
            IntStream.range(1, 100).forEach(x -> {
                if (x != 50)
                    System.out.println(x);
                else {
                    try {
                        waitTask.await();//暂停线程
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }).start();
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitTask.signalAll();//唤醒线程
    }
}
