package aqs;

public class MyLockTest {
    private MyReentrantLock lock = new MyReentrantLock();

    public void a() {
        lock.lock();
        System.out.println("a");
        b();
        lock.unlock();
    }

    public void b() {
        lock.lock();
        System.out.println("b");
        lock.unlock();
    }

    public static void main(String[] args) {
        MyLockTest test = new MyLockTest();
        test.a();
    }
}
