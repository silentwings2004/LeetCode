package LC601_900;
import java.util.*;
public class LC604_DesignCompressedStringIterator {
    /**
     * Design and implement a data structure for a compressed string iterator. The given compressed string will be in
     * the form of each letter followed by a positive integer representing the number of this letter existing in the
     * original uncompressed string.
     *
     * Implement the StringIterator class:
     *
     * next() Returns the next character if the original string still has uncompressed characters, otherwise returns a
     * white space.
     * hasNext() Returns true if there is any letter needs to be uncompressed in the original string, otherwise returns
     * false.
     *
     * Input
     * ["StringIterator", "next", "next", "next", "next", "next", "next", "hasNext", "next", "hasNext"]
     * [["L1e2t1C1o1d1e1"], [], [], [], [], [], [], [], [], []]
     * Output
     * [null, "L", "e", "e", "t", "C", "o", true, "d", true]
     *
     * Constraints:
     *
     * 1 <= compressedString.length <= 1000
     * compressedString consists of lower-case an upper-case English letters and digits.
     * The number of a single character repetitions in compressedString is in the range [1, 10^9]
     * At most 100 calls will be made to next and hasNext.
     * @param compressedString
     */
    // S1
    TreeMap<Long, Character> map;
    long t, p;
    public LC604_DesignCompressedStringIterator(String compressedString) {
        map = new TreeMap<>();
        t = 0;
        p = 0;
        int n = compressedString.length();
        for (int i = 0; i < n; i++) {
            char c = compressedString.charAt(i);
            int j = i + 1;
            while (j < n && Character.isDigit(compressedString.charAt(j))) j++;
            int k = Integer.parseInt(compressedString.substring(i + 1, j));
            map.put(p, c);
            p += k;
            i = j - 1;
        }
    }
    // time = O(logn), space = O(n)
    public char next() {
        if (!hasNext()) return ' ';
        Long fk = map.floorKey(t++);
        return map.get(fk);
    }
    // time = O(1), space = O(n)
    public boolean hasNext() {
        return t < p;
    }

    // S2
    class StringIterator {
        String s;
        int pos, left;
        char cur;
        public StringIterator(String compressedString) {
            s = compressedString;
            pos = 0;
        }
        // time = O(n), space = O(n)
        public char next() {
            if (hasNext()) {
                if (inc() || (parseNext() && inc())) return cur;
            }
            return ' ';
        }
        // time = O(1), space = O(n)
        public boolean hasNext() {
            return pos < s.length() || left > 0;
        }

        private boolean inc() {
            return left-- > 0;
        }

        private boolean parseNext() {
            int n = s.length();
            if (pos >= n) return false;
            cur = s.charAt(pos++);
            left = 0;
            int i = pos;
            while (i < n && Character.isDigit(s.charAt(i))) i++;
            left = Integer.parseInt(s.substring(pos, i));
            pos = i;
            return true;
        }
    }
}