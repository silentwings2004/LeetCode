package LC2700_3000;

public class LC2930_NumberofStringsWhichCanBeRearrangedtoContainSubstring {
    /**
     * You are given an integer n.
     *
     * A string s is called good if it contains only lowercase English characters and it is possible to rearrange the
     * characters of s such that the new string contains "leet" as a substring.
     *
     * For example:
     *
     * The string "lteer" is good because we can rearrange it to form "leetr" .
     * "letl" is not good because we cannot rearrange it to contain "leet" as a substring.
     * Return the total number of good strings of length n.
     *
     * Since the answer may be large, return it modulo 109 + 7.
     *
     * A substring is a contiguous sequence of characters within a string.
     *
     * Input: n = 4
     * Output: 12
     *
     * Input: n = 10
     * Output: 83943898
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * @param n
     * @return
     */
    // time = O(1), space = O(1)
    final int mod = (int)1e9 + 7;
    public int stringCount(int n) {
        long t1 = qmi(26, n);
        long t2 = qmi(25, n);
        long t3 = qmi(24, n);
        long t4 = qmi(23, n);
        long t5 = qmi(25, n - 1);
        long t6 = qmi(24, n - 1);
        long t7 = qmi(23, n - 1);

        long t = ((((t1 - 3 * t2 % mod + mod) % mod + 3 * t3 % mod) % mod - t4 + mod) % mod - n * ((t5 - 2 * t6 % mod + mod) % mod + t7) % mod + mod) % mod;
        return (int)t;
    }

    private long qmi(long a, long k) {
        long res = 1;
        while (k > 0) {
            if ((k & 1) == 1) res = res * a % mod;
            a = a * a % mod;
            k >>= 1;
        }
        return res;
    }
}
/**
 * 至少装满型 背包
 * 背包大小：负数和0是一样的
 */