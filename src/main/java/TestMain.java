public class TestMain {
    public static void main(String[] args) {
        int len = 1000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < len; i++)
            test();
        System.out.println("---------------------");
        System.out.println("平均：" + (System.currentTimeMillis() - start) / len);
        System.out.println("总耗时：" + (System.currentTimeMillis() - start));
    }

    public static void test() {
        long start = System.currentTimeMillis();
        final User u = new User();
        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++)
                u.add();
        };
        Thread t1 = new Thread(runnable, "th1");
        Thread thread = new Thread(runnable, "th2");
        Thread thread2 = new Thread(runnable, "th3");
        Thread thread3 = new Thread(runnable, "th4");
        Thread thread4 = new Thread(runnable, "th5");
        Thread thread5 = new Thread(runnable, "th6");
        t1.start();
        thread.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        try {
            t1.join();
            thread.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(u.getCount());
        System.out.println(System.currentTimeMillis() - start);
    }
}
