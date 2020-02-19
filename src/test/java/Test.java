import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Test {
    @org.junit.Test
    public void test1() {
        Queue<Long> queue = new ConcurrentLinkedDeque<>();
        queue.offer(1L);
        queue.offer(2L);
        queue.offer(3L);
        queue.offer(4L);
        queue.offer(5L);
        Long tmp = null;
        while ((tmp = queue.poll()) != null)
            System.out.println(tmp);
    }
}
