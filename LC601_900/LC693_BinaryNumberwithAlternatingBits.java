package LC601_900;

public class LC693_BinaryNumberwithAlternatingBits {
    /**
     * Given a positive integer, check whether it has alternating bits: namely, if two adjacent bits will always have
     * different values.
     *
     * Input: n = 5
     * Output: true
     *
     * Constraints:
     *
     * 1 <= n <= 2^31 - 1
     * @param n
     * @return
     */
    // S1
    // time = O(1), space = O(1)
    public boolean hasAlternatingBits(int n) {
        String s = Integer.toBinaryString(n);
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) return false;
        }
        return true;
    }

    // S2
    // time = O(1), space = O(1)
    public boolean hasAlternatingBits2(int n) {
        for (int i = 1; 1L << i < n; i++) {
            int a = n >> i - 1 & 1;
            int b = n >> i & 1;
            if (a == b) return false;
        }
        return true;
    }
}