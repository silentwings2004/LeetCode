package LC3601_3900;

public class LC3754_ConcatenateNonZeroDigitsandMultiplybySumI {
    /**
     * You are given an integer n.
     *
     * Form a new integer x by concatenating all the non-zero digits of n in their original order. If there are no
     * non-zero digits, x = 0.
     *
     * Let sum be the sum of digits in x.
     *
     * Return an integer representing the value of x * sum.
     *
     * Input: n = 10203004
     * Output: 12340
     *
     * Input: n = 1000
     * Output: 1
     *
     * Constraints:
     *
     * 0 <= n <= 10^9
     * @param n
     * @return
     */
    // time = O(logn), space = O(logn)
    public long sumAndMultiply(int n) {
        int x = 0, sum = 0;
        String s = String.valueOf(n);
        for (int i = 0; i < s.length(); i++) {
            int u = s.charAt(i) - '0';
            if (u > 0) {
                x = x * 10 + u;
                sum += u;
            }
        }
        return 1L * x * sum;
    }
}