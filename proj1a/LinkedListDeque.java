public class LinkedListDeque<T> {
    private IntNode sentinel;
    private int size;

    private class IntNode {
        private IntNode prev;
        private T item;
        private IntNode next;
        private IntNode(IntNode pre, T i, IntNode nex) {
            prev = pre;
            item = i;
            next = nex;
        }
    }

    public LinkedListDeque() {
        sentinel = new IntNode(null, null, null);
        size = 0;
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public void addLast(T item) {
        sentinel.prev = new IntNode(sentinel.prev, item, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    public void addFirst(T item) {
        sentinel.next = new IntNode(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }else {
            return false;
        }
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (size == 0) {
            return;
        }
        IntNode p = sentinel;
        for (int i = 0; i < size; i++) {
            System.out.print(p.next.item + " ");
            p = p.next;
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        IntNode p = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        p.next = null;
        p.prev = null;
        size -= 1;
        return p.item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        IntNode p = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        p.next = null;
        p.prev = null;
        return p.item;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        IntNode p = sentinel.next;
        for (int i=0; i<index; i++) {
            p = p.next;
        }
        return p.item;
    }

    private T getRecursive(int index, IntNode p) {
        if (index == 0) {
            return p.item;
        }
        return getRecursive(index - 1, p.next);
    }

    public  T getRecursive(int index) {
        if (index >= size) return null;
        return getRecursive(index, sentinel.next);
    }

}
