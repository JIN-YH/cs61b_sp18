public class ArrayDeque<T> {
    T[] items;
    int size;
    int nextFirst;
    int nextLast;

    public ArrayDeque() {
        items = (T []) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    private void resize(int s) {
        T[] a = (T []) new Object[s];
        //System.arraycopy(items,movefirst(nextFirst), a, 1, s);
        int j = 1;
        for (int i = movetoright(nextFirst); j <= size; i = movetoright(i)) {
            a[j] = items[i];
            j += 1;
        }
        nextFirst = 0;
        nextLast = size + 1;
        items = a;
    }

    private int movetoleft(int i) {
        if (i > 0) return i - 1;
        return items.length - 1;
    }

    private int movetoright(int i) {
        if (i < items.length - 1) return i + 1;
        return 0;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst = movetoleft(nextFirst);
        size += 1;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast = movetoright(nextLast);
        size += 1;
    }

    public boolean isEmpty() {
        if (size == 0) return true;
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (isEmpty()) return;
        for(int i = movetoright(nextFirst); i!=nextLast; i= movetoright(i)) {
            System.out.print(items[i] + " ");
        }
    }

    private boolean ratio() {
        if (size < 16) return false;
        if (size/items.length < 0.25) return true;
        return false;
    }

    public T removeFirst() {
        if (isEmpty()) return null;
        T temp = items[movetoright(nextFirst)];
        items[movetoright(nextFirst)] = null;
        nextFirst = movetoright(nextFirst);
        size -= 1;
        if (ratio()) {
            resize(items.length / 2);
        }
        return temp;
    }

    public T removeLast() {
        if (isEmpty()) return null;
        T temp = items[movetoleft(nextLast)];
        items[movetoleft(nextLast)] = null;
        nextLast = movetoleft(nextLast);
        size -= 1;
        if (ratio()) {
            resize(items.length / 2);
        }
        return temp;
    }

    public T get(int index) {
        if (isEmpty()) return null;
        int i = movetoright(nextFirst);
        while(index != 0) {
            index -= 1;
            i = movetoright(i);
        }
        return items[i];
    }

}
