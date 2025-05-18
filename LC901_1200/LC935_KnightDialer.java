package LC901_1200;
import java.util.*;
public class LC935_KnightDialer {
    /**
     * The chess knight has a unique movement, it may move two squares vertically and one square horizontally, or two
     * squares horizontally and one square vertically (with both forming the shape of an L). The possible movements of
     * chess knight are shown in this diagaram:
     *
     * A chess knight can move as indicated in the chess diagram below:
     *
     * We have a chess knight and a phone pad as shown below, the knight can only stand on a numeric cell (i.e. blue cell).
     *
     * Given an integer n, return how many distinct phone numbers of length n we can dial.
     *
     * You are allowed to place the knight on any numeric cell initially and then you should perform n - 1 jumps to dial
     * a number of length n. All jumps should be valid knight jumps.
     *
     * As the answer may be very large, return the answer modulo 10^9 + 7.
     *
     * Input: n = 1
     * Output: 10
     *
     * Constraints:
     *
     * 1 <= n <= 5000
     * @param n
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public int knightDialer(int n) {
        int[] dp = new int[10];
        Arrays.fill(dp, 1);
        int M = (int)(1e9 + 7);
        for (int i = 2; i <= n; i++) {
            int[] dp_old = dp.clone();
            dp[0] = dp_old[4] + dp_old[6];
            dp[1] = dp_old[6] + dp_old[8];
            dp[2] = dp_old[7] + dp_old[9];
            dp[3] = dp_old[4] + dp_old[8];
            dp[4] = (dp_old[3] + dp_old[9]) % M + dp_old[0];
            dp[5] = 0;
            dp[6] = (dp_old[1] + dp_old[7]) % M + dp_old[0];
            dp[7] = dp_old[2] + dp_old[6];
            dp[8] = dp_old[1] + dp_old[3];
            dp[9] = dp_old[4] + dp_old[2];

            for (int k = 0; k <= 9; k++) {
                dp[k] %= M;
            }
        }

        int res = 0;
        for (int i = 0; i <= 9; i++) {
            res += dp[i];
            res %= M;
        }
        return res;
    }

    // S2
    // time = O(n), space = O(n)
    public int knightDialer2(int n) {
        int mod = (int)1e9 + 7;
        int[][] f = new int[n][10];
        int[][] tr = new int[][]{
                {4, 6},
                {6, 8},
                {7, 9},
                {4, 8},
                {3, 9, 0},
                {},
                {1, 7, 0},
                {2, 6},
                {1, 3},
                {2, 4},
        };
        for (int i = 0; i < 10; i++) f[0][i] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k : tr[j]) {
                    f[i][j] = (f[i][j] + f[i - 1][k]) % mod;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < 10; i++) res = (res + f[n - 1][i]) % mod;
        return res;
    }
}
/**
 * 1 <- 6 / 8
 * 2 <- 7 / 9
 * dp[1] = dp[6] + dp[8]
 * dp[n][1] = dp[n-1][6] + dp[n-1][8]
 * dp[n][2] =
 * ...
 * dp[n][0]
 *
 * f(i,j): 跳了i次，最后位于j的方案数
 * 4->0 => f(i-1,4)
 * 6->0 => f(i-1,6)
 * 递推
 * time = O(10*n*3) = O(10n)
 */
