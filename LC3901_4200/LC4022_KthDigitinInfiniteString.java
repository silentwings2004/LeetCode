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
        k--; // k 改成从 0 开始，方便计算
        // 十进制长为 length 的正整数有 cnt = 9 * 10^(length-1) 个
        long cnt = 9, length = 1;
        while (cnt * length <= k) {
            k -= cnt * length; // 这里减小了 k
            cnt *= 10;
            length++;
        }
        long x = cnt / 9 + k / length; // 答案在正整数 x 中
        if (x / 10 % 2 == 1) x += 9 - x % 10 * 2; // 改成递减顺序，例如 10 变成 19，11 变成 18 ……
        // 计算 x 从高到低第 k%length（从 0 开始）个数字
        for (int i = 0; i < length - k % length - 1; i++) x /= 10;
        return (int)(x % 10);
    }
}
/**
 * Ref: LC400
 * 1. 答案在哪个整数里
 * 2. 答案在整数里的第几位
 *
 * 长度为 L 的正整数有多少个 => 9 * 10^(L-1)
 */