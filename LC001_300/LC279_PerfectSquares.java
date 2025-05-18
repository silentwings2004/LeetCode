package LC001_300;
import java.util.*;
public class LC279_PerfectSquares {
    /**
     * Given an integer n, return the least number of perfect square numbers that sum to n.
     *
     * A perfect square is an integer that is the square of an integer; in other words, it is the product of some
     * integer with itself. For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.
     *
     * Input: n = 12
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= n <= 10^4
     * @param n
     * @return
     */
    // S1: DP
    // time = O(n * sqrt(n)), space = O(n)
    public int numSquares(int n) {
        int[] f = new int[n + 1];
        Arrays.fill(f, Integer.MAX_VALUE);
        f[0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= (int) Math.sqrt(i); j++) {
                f[i] = Math.min(f[i], f[i - j * j] + 1);
            }
        }
        return f[n];
    }

    // S2: Math
    // time = O(sqrt(n) + logn), space = O(1)
    public int numSquares2(int n) {
        if (check(n)) return 1;

        for (int i = 1; i <= n / i; i++) {
            if (check(n - i * i)) return 2;
        }

        while (n % 4 == 0) n /= 4;
        if (n % 8 != 7) return 3;
        return 4;
    }

    private boolean check(int x) {
        int r = (int) Math.sqrt(x);
        return r * r == x;
    }
}
/**
 * n 是若干个平方数相加
 * n = a^2 + b^2 + ... + x^2
 * dp[i]: the least number of perfect square numbers which sum to i
 * dp[n] = Math.min{}dp[n - x^2] + 1} for all x => O(n^2)
 *
 * 当成完全背包来做
 * Math
 * 1. ans <= 4
 * 2. n 能表示成3个整数的平方和，当且仅当 n != 4^a * (8b + 7)
 */
