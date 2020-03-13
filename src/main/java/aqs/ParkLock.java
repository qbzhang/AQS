package aqs;

import sun.misc.Contended;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.locks.LockSupport;

//原型锁需改造
public class ParkLock {
    @Contended
    private volatile int lockFlags = 0;
    private static AtomicIntegerFieldUpdater UPDATER = AtomicIntegerFieldUpdater.newUpdater(ParkLock.class, "lockFlags");
    private Queue<Thread> queue = new ConcurrentLinkedDeque<>();

    private boolean compareAndSet(int expect, int update) {
        return UPDATER.compareAndSet(this, expect, update);
    }

    public void lock() {
        boolean wasInterrupted = false;
        Thread current = Thread.currentThread();
        queue.offer(current);
        while ((current != queue.peek()) || !compareAndSet(0, 1)) {
            LockSupport.park();
            if (Thread.interrupted())
                wasInterrupted = true;
        }
        queue.remove();
        if (wasInterrupted)
            current.interrupt();
    }

    public void unlock() {
        compareAndSet(1, 0);
        LockSupport.unpark(queue.peek());
    }
}
