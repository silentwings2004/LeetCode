package LC3901_4200;

public class LC4022_KthDigitinInfiniteString {
    /**
     * You are given an integer k.
     *
     * An infinite string is formed by concatenating the decimal representations of the positive integers, without
     * separators.
     *
     * For every nonnegative integer b, block b contains the positive integers from 10 * b through 10 * b + 9. The
     * integers in each block are appended as follows:
     *
     * If b is even, append the integers in increasing order.
     * If b is odd, append the integers in decreasing order.
     * Therefore, the string starts with the integers 1 through 9, followed by 19 through 10, then 20 through 29, then
     * 39 through 30, and so on.
     *
     * Return the kth digit (1-indexed) of this string.
     *
     * Input: k = 4
     * Output: 4
     *
     * Input: k = 15
     * Output: 7
     *
     * Input: k = 11
     * Output: 9
     *
     * Constraints:
     *
     * 1 <= k <= 10^15
     * @param k
     * @return
     */
    // time = O(logk), space = O(1)
    public int kthDigit(long k) {
        int l = 1;
        while (l * 9L * Math.pow(10, l - 1) < k) {
            k -= l * 9L * Math.pow(10, l - 1);
            l++;
        }
        k--;
        long d = (long)Math.pow(10, l - 1) + k / l;
        k %= l;
        int res = String.valueOf(d).charAt((int)k) - '0';
        return k < l - 1 || d / 10 % 2 == 0 ? res : 9 - res;
    }
}