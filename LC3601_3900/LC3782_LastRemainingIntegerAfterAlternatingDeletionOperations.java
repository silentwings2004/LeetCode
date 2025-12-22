package LC3601_3900;

public class LC3782_LastRemainingIntegerAfterAlternatingDeletionOperations {
    /**
     * You are given an integer n.
     *
     * We write the integers from 1 to n in a sequence from left to right. Then, alternately apply the following two
     * operations until only one integer remains, starting with operation 1:
     *
     * Operation 1: Starting from the left, delete every second number.
     * Operation 2: Starting from the right, delete every second number.
     * Return the last remaining integer.
     *
     * Input: n = 8
     * Output: 3
     *
     * Input: n = 5
     * Output: 1
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
    // S1
    // time = O(logn), space = O(1)
    public long lastInteger(long n) {
        long st = 1, d = 1;
        while (n > 1) {
            st += (n - 2 + n % 2) * d;
            d *= -2;
            n = (n + 1) / 2;
        }
        return st;
    }

    // S2
    // time = O(1), space = O(1)
    public long lastInteger2(long n) {
        final long mask = 0xAAAAAAAAAAAAAAAL;
        return ((n - 1) & mask) + 1;
    }
}
/**
 * 等差数列，公差是2的幂
 * st = 1, d = 1, n = 8 =>
 * st = 7, d = -2, n = 4 (reverse) =>
 * st = 3, d = 4, n = 2 (re-reverse)
 * 位运算
 * 为方便计算，把初始序列改成从 0 开始，即 0,1,2,…,n−1。
 * 第一次操作，我们删除了所有的奇数，剩余的都是偶数。这意味着，（从 0 开始的）最终答案，二进制最低位一定是 0。
 * 把剩余元素 0,2,4,… 全部右移一位，我们又得到了序列 0,1,2,…。在此基础上，执行第二次操作。
 * 在第二次操作中，我们要从右往左删除：
 * 如果序列最后一个数是偶数，例如 0,1,2,3,4（对应着一开始 n−1=8 或者 9），那么我们会删除所有的奇数，剩余的都是偶数，
 * 这和序列最后一个数的奇偶性相同。注意序列最后一个数等于 n−1 右移一位
 * 如果序列最后一个数是奇数，例如 0,1,2,3,4,5（对应着一开始 n−1=10 或者 11），那么我们会删除所有的偶数，剩余的都是奇数，
 * 这和序列最后一个数的奇偶性相同。注意序列最后一个数等于 n−1 右移一位。
 * 这意味着，（从 0 开始的）最终答案，二进制从低到高第二位一定等于 n−1 从低到高第二位。
 * 依此类推
 * 一般地，（从 0 开始的）最终答案，二进制从低到高第 1,3,5,… 位一定是 0；第 2,4,6,… 位一定和 n−1 的第 2,4,6,… 位相同。
 * 算出答案后，把答案加一（因为原题的序列是从 1 开始的）。
 */