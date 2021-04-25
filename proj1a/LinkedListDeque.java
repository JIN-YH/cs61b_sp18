public class LinkedListDeque<T> {
    private IntNode sentinel;
    private int size;

    public class IntNode {
        public IntNode prev;
        public T item;
        public IntNode next;
        public IntNode(IntNode pre, T i, IntNode nex) {
            prev = pre;
            item = i;
            next = nex;
        }
    }

    public LinkedListDeque() {
        sentinel = new IntNode(null ,null , null);
        size = 0;
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public void addLast(T item) {
        sentinel.prev = new IntNode(sentinel.prev, item, sentinel);
        if (sentinel.next == sentinel) {
            sentinel.next = sentinel.prev;
        }
        size += 1;
    }

    public void addFirst(T item) {
        sentinel.next = new IntNode(sentinel, item, sentinel.next);
        if (sentinel.prev == sentinel) {
            sentinel.prev = sentinel.next;
        }
        size += 1;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }else return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (size == 0) return;
        IntNode p = sentinel;
        for (int i=0; i<size; i++) {
            System.out.print(p.next.item+" ");
            p = p.next;
        }
    }

    public T removeFirst() {
        if (this.isEmpty()) return null;
        T p = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return p;
    }

    public T removeLast() {
        if (this.isEmpty()) return null;
        T p = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return p;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        IntNode p = sentinel;
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
