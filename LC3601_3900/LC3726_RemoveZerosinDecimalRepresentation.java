package LC3601_3900;

public class LC3726_RemoveZerosinDecimalRepresentation {
    /**
     * You are given a positive integer n.
     *
     * Return the integer obtained by removing all zeros from the decimal representation of n.
     *
     * Input: n = 1020030
     * Output: 123
     *
     * Input: n = 1
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= n <= 10^15
     * @param n
     * @return
     */
    // time = O(k), space = O(1)
    public long removeZeros(long n) {
        long res = 0, i = 1;
        while (n > 0) {
            long x = n % 10;
            if (x > 0) {
                res = x * i + res;
                i *= 10;
            }
            n /= 10;
        }
        return res;
    }
}