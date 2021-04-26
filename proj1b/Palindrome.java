public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        int len = word.length();
        Deque<Character> d = new ArrayDeque<Character>();
        for (int i = 0; i < len; i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    private boolean isPalindrome(Deque<Character> D) {
        if (D.size() < 2) {
            return true;
        }
        if (D.removeFirst() == D.removeLast()) {
            return isPalindrome(D);
        }
        return false;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> D = wordToDeque(word);
        return isPalindrome(D);
    }

    private boolean isPalindrome(Deque<Character> D, CharacterComparator cc) {
        if (D.size() < 2) {
            return true;
        }
        if (cc.equalChars(D.removeFirst(), D.removeLast())) {
            return isPalindrome(D, cc);
        }
        return false;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> D = wordToDeque(word);
        return isPalindrome(D, cc);
    }
}
