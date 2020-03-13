package aqs;

import java.util.concurrent.locks.ReentrantLock;

public class User {
    private int count;
    //    private aqs.ParkLock lock = new aqs.ParkLock();
//    private Lock2 lock = new Lock2();
    private ReentrantLock lock = new ReentrantLock(true);

    public void add() {
        try {
            lock.lock();
            a();
//            count++;
//            TimeUnit.MILLISECONDS.sleep(5);
        } finally {
            lock.unlock();
        }
    }

    public void a() {
        try {
            lock.lock();
            count++;
        } finally {
            lock.unlock();
        }
    }

    /*public synchronized void add() {
        count++;
//        try {
//            TimeUnit.SECONDS.sleep(5);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//            e.printStackTrace();
//        }
    }*/

    public int getCount() {
        return count;
    }
}
