public class ArrayDeque<Item> implements Deque<Item> {
    private Item[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    private int getlen() {
        return items.length;
    }

    public ArrayDeque() {
        items = (Item[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    private void resize(int s) {
        Item[] a = (Item[]) new Object[s];
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
        if (i > 0) {
            return i - 1;
        }
        return items.length - 1;
    }

    private int movetoright(int i) {
        if (i < items.length - 1) {
            return i + 1;
        }
        return 0;
    }

    @Override
    public void addFirst(Item item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst = movetoleft(nextFirst);
        size += 1;
    }

    @Override
    public void addLast(Item item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast = movetoright(nextLast);
        size += 1;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        if (isEmpty()) {
            return;
        }
        int j = 0;
        for (int i = movetoright(nextFirst); j < size; i = movetoright(i)) {
            System.out.print(items[i] + " ");
            j++;
        }
    }


    private boolean ratio() {
        if (getlen() < 16) {
            return false;
        }
        int len = getlen();
        int s = size;
        float r = (float) s / (float) len;
        if (r < 0.25) {
            return true;
        }
        return false;
    }

    @Override
    public Item removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Item temp = items[movetoright(nextFirst)];
        items[movetoright(nextFirst)] = null;
        nextFirst = movetoright(nextFirst);
        size -= 1;
        if (ratio()) {
            resize(getlen() / 2);
        }
        return temp;
    }

    @Override
    public Item removeLast() {
        if (isEmpty()) {
            return null;
        }
        Item temp = items[movetoleft(nextLast)];
        items[movetoleft(nextLast)] = null;
        nextLast = movetoleft(nextLast);
        size -= 1;
        if (ratio()) {
            resize(getlen() / 2);
        }
        return temp;
    }

    @Override
    public Item get(int index) {
        if (isEmpty()) {
            return null;
        }
        int i = movetoright(nextFirst);
        while (index != 0) {
            index -= 1;
            i = movetoright(i);
        }
        return items[i];
    }

}
