package LC3601_3900;

public class LC3750_MinimumNumberofFlipstoReverseBinaryString {
    /**
     * You are given a positive integer n.
     *
     * Let s be the binary representation of n without leading zeros.
     *
     * The reverse of a binary string s is obtained by writing the characters of s in the opposite order.
     *
     * You may flip any bit in s (change 0 → 1 or 1 → 0). Each flip affects exactly one bit.
     *
     * Return the minimum number of flips required to make s equal to the reverse of its original form.
     *
     * Input: n = 7
     * Output: 0
     *
     * Input: n = 10
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= n <= 10^9
     * @param n
     * @return
     */
    // S1
    // time = O(logn), space = O(logn)
    public int minimumFlips(int n) {
        String s = Integer.toBinaryString(n);
        String t = new StringBuilder(s).reverse().toString();
        int m = s.length(), res = 0;
        for (int i = 0; i < m; i++) {
            if (s.charAt(i) != t.charAt(i)) res++;
        }
        return res;
    }

    // S2
    // time = O(1), space = O(1)
    public int minimumFlips2(int n) {
        int r = Integer.reverse(n) >>> Integer.numberOfLeadingZeros(n);
        return Integer.bitCount(n ^ r);
    }
}