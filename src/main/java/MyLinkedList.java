import java.util.Iterator;

public class MyLinkedList<T> implements Iterable<T> {
    private Node<T> head = new Node<T>(null);
    private Node<T> tail = new Node<T>(null);
    private int size = 0;

    public MyLinkedList() {
        head.next = tail;
        tail.prev = head;
    }

    public boolean addFirst(T t) {
        Node<T> newNode = new Node<T>(t);
        newNode.prev = head;
        newNode.next = head.next;
        newNode.next.prev = newNode;
        head.next = newNode;
        size++;
        return true;
    }

    public T getFirst() {
        if (size > 0)
            return head.next.getT();
        else return null;
    }

    public T removeFirst() {
        Node<T> resultNode = head.next;
        head.next = resultNode.next;
        resultNode.next.prev = head;
        resultNode.next = resultNode = null;
        size--;
        return resultNode.getT();
    }

    public boolean addLast(T t) {
        Node<T> newNode = new Node<T>(t);
        newNode.next = tail;
        newNode.prev = tail.prev;
        newNode.prev.next = newNode;
        tail.prev = newNode;
        size++;
        return true;
    }

    public T removeLast() {
        Node<T> resultNode = tail.prev;
        resultNode.prev.next = tail;
        tail.prev = resultNode.prev;
        resultNode.prev = resultNode.next = null;
        size--;
        return resultNode.getT();
    }

    public T getLast() {
        if (size > 0)
            return tail.prev.getT();
        else
            return null;
    }

    public int getSize() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    class MyIterator implements Iterator<T> {
        private Node<T> currentNode = head;

        @Override
        public boolean hasNext() {
            return currentNode.next != tail;
        }

        @Override
        public T next() {
            currentNode = currentNode.next;
            return currentNode.getT();
        }
    }

    class Node<T> {
        private Node<T> prev;
        private Node<T> next;
        private T t;

        public T getT() {
            return t;
        }

        public Node(T t) {
            this.t = t;
        }
    }
}
