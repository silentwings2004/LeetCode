package LC2101_2400;

public class LC2320_CountNumberofWaystoPlaceHouses {
    /**
     * There is a street with n * 2 plots, where there are n plots on each side of the street. The plots on each side
     * are numbered from 1 to n. On each plot, a house can be placed.
     *
     * Return the number of ways houses can be placed such that no two houses are adjacent to each other on the same
     * side of the street. Since the answer may be very large, return it modulo 109 + 7.
     *
     * Note that if a house is placed on the ith plot on one side of the street, a house can also be placed on the ith
     * plot on the other side of the street.
     *
     * Input: n = 1
     * Output: 4
     *
     * Input: n = 2
     * Output: 9
     *
     * Constraints:
     *
     * 1 <= n <= 10^4
     * @param n
     * @return
     */
    // S1: DP
    // time = O(n), space = O(n)
    public int countHousePlacements(int n) {
        long[][] f = new long[n + 1][2];
        f[1][0] = f[1][1] = 1;
        long M = (long)(1e9 + 7);
        for (int i = 2; i <= n; i++) {
            f[i][0] = (f[i - 1][0] + f[i - 1][1]) % M;
            f[i][1] = f[i - 1][0];
        }

        long res = f[n][0] + f[n][1];
        return (int)(res * res % M);
    }

    // S2: Fib
    // time = O(n), space = O(1)
    public int countHousePlacements12(int n) {
        long a = 1, b = 1;
        long M = (long)(1e9 + 7);
        while (-- n > 0) {
            long c = (a + b) % M;
            a = b;
            b = c;
        }
        a += b;
        return (int)(a * a % M);
    }
}
/**
 * dp或者 递推
 * 搞2种状态
 * f(i,0), f(i,1)
 * f(i,0) = f(i-1,0) + f(i-1,1)
 * f(i,1) = f(i-1,0)
 * (f(n,0) + f(n,1))^2
 * time = O(n) = O(10000)
 *
 * x o x o x x o
 * house robber: the max sum for a plan where no two houses are adjacent
 * this problem: the total numbers for a plan where no two houses are adjacent
 * dp[i][0]: the total # of valid plans where there is no house in the i-th place
 * dp[i][1]: the total # of valid plans where there is a house in the i-th place
 * dp[i] -> dp[i-1]
 * dp[i][0] = dp[i-1][1] + dp[i-1][0]
 * dp[1][1] = dp[i-1][0]
 *
 * 在n个位置里放置k个球，问有多少种方案，使得任意2个球不相邻
 * C(n - (k - 1), k)
 * 我们将n个位置里拿走k-1个位置。剩下的位置里任意放置k个元素。
 * 然后再将拿走的k-1个位置插在已经放置的k个元素中间，就完美地实现了题目要求，即没有任何两个元素相邻。
 * k = 0, 1, 2, ...(2k - 1 <= n)
 * 用组合数公式求
 * C(m,n) = C(m-1,n) + C(m-1,n-1)
 */
