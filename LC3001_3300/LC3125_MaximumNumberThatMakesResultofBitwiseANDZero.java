package LC3001_3300;

public class LC3125_MaximumNumberThatMakesResultofBitwiseANDZero {
    /**
     * Given an integer n, return the maximum integer x such that x <= n, and the bitwise AND of all the numbers in the
     * range [x, n] is 0.
     *
     * Input: n = 7
     * Output: 3
     *
     * Input: n = 9
     * Output: 7
     *
     * Input: n = 17
     * Output: 15
     *
     * Constraints:
     *
     * 1 <= n <= 10^15
     * @param n
     * @return
     */
    // time = O(logn), space = O(1)
    public long maxNumber(long n) {
        long res = n - 1;
        while (n > 0) {
            n -= n & -n;
            if (n > 0) res = Math.min(res, n - 1);
        }
        return res;
    }
}