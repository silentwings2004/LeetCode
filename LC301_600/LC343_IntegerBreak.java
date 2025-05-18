package LC301_600;

public class LC343_IntegerBreak {
    /**
     * Given an integer n, break it into the sum of k positive integers, where k >= 2, and maximize the product of those
     * integers.
     *
     * Return the maximum product you can get.
     *
     * Input: n = 2
     * Output: 1
     *
     * Constraints:
     *
     * 2 <= n <= 58
     * @param n
     * @return
     */
    // S1: dp
    // time = O(n^2), space = O(n)
    public int integerBreak(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;

        int[] dp = new int[n + 1];

        // init
        dp[2] = 2;
        dp[3] = 3;

        for (int i = 4; i <= n; i++) {
            for (int j = 2; j <= i / 2; j++) {
                dp[i] = Math.max(dp[i], dp[j] * dp[i - j]);
            }
        }
        return dp[n];
    }

    // S2: Math
    // time = O(1), space = O(1)
    public int integerBreak2(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;

        if (n % 3 == 0) return (int) Math.pow(3, n / 3);
        else if (n % 3 == 1) return (int) Math.pow(3, n / 3 - 1) * 4; // 乘以1永远都是不合算的！
        return (int) Math.pow(3, n / 3) * 2;
    }

    // S3: Math
    // time = O(1), space = O(1)
    public int integerBreak3(int n) {
        if (n <= 3) return 1 * (n - 1);
        int p = 1;
        while (n >= 5) {
            n -= 3;
            p *= 3;
        }
        return p * n;
    }
}
/**
 * M = (x1+x2+x3) + (x4+x5+...)
 * 先考虑2个数加和的乘积，最后break down就是最优解
 * 一开始没有必要考虑分很多份
 * S2: Math
 * M -> M/2 * M/2
 * M, n => pow(M/n, n)
 * M / n = e = 2.73
 *
 * 尽可能分成3和2，最多只能有2个2
 * n = a1 + a2 + ... + ak
 * 1. ai >= 5 => ai = 3 + (ai - 3),
 * 2. 不包含1
 * 3. ai = 4 = 2 + 2 => 不包含4
 * 如果多于2个2，随意找出3个2， 2 + 2 + 2 = 6 = 3 + 3，分成2个3更好
 */