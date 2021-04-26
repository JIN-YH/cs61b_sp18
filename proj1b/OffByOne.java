public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        int a = x;
        int b = y;
        int c = a - b;
        if (c == 1 | c == -1) {
            return true;
        }
        return false;
    }
}
