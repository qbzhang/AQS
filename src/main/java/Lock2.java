import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

public class Lock2 {
    private static AtomicBoolean busy = new AtomicBoolean(false);
    private static Queue<Thread> waiters = new ConcurrentLinkedDeque<>();
    private int reentrantCount = 0;

    public void unlock() {
        if (reentrantCount > 0) {
            reentrantCount--;
        } else {
            reentrantCount = 0;
            waiters.remove();
            busy.set(false);
            LockSupport.unpark(waiters.peek());
        }
    }

    public void lock() {
//        boolean wasInterrupted = false;   //线程是否已被中断标志
        Thread current = Thread.currentThread();
        if (current == waiters.peek()) {
            reentrantCount++;
        } else {
            waiters.offer(current);  //添加当前线程到并发队列
            while (current != waiters.peek() || !busy.compareAndSet(false, true)) {
                //当前线程，如果不是队首元素则进行park操作。如果没有线程进入区域，则退出循环，让那线程执行。
                LockSupport.park();
            /*if (Thread.interrupted()) { //获取当前线程中断状态
                wasInterrupted = true;
            }*/
            }
        }
//        waiters.remove();
        /*if (wasInterrupted) {
            current.interrupt();
        }*/
    }
}
