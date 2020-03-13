import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WaitTask {
    private Lock lock = new ReentrantLock(true);
    private Condition condition = lock.newCondition();

    public void await() throws InterruptedException {
        try {
            lock.lock();
            condition.await();
        } catch (InterruptedException e) {
            throw e;
        } finally {
            lock.unlock();
        }
    }

    public void signalAll() {
        try {
            lock.lock();
            condition.signalAll();
        } catch (Exception e) {
            throw e;
        } finally {
            lock.unlock();
        }
    }
}
