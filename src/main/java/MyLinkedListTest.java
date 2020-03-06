import java.util.Iterator;

public class MyLinkedListTest {
    public static void main(String[] args) {
        MyLinkedList<String> lists = new MyLinkedList<>();
        lists.addFirst("abc1");
        lists.addFirst("abc2");
        lists.addFirst("abc3");
        lists.addFirst("abc4");

        lists.addLast("1");
        lists.addLast("2");
        lists.addLast("3");
        lists.addLast("4");
//        System.out.println(lists.getSize());
        Iterator<String> it = lists.iterator();
        for (; it.hasNext(); ) {
            String tmp = it.next();
            System.out.println(tmp);
        }
        System.out.println("************************");
        for (String temp : lists)
            System.out.println(temp);
    }
}
