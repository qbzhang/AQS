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
        System.out.println(lists.getSize());
        /*while (lists.getSize()>0 && lists) {

        }*/
        System.out.println("首节点:" + lists.getFirst());
        System.out.println("尾节点:" + lists.getLast());
    }
}
