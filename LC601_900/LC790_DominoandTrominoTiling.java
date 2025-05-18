package LC601_900;

public class LC790_DominoandTrominoTiling {
    /**
     * You have two types of tiles: a 2 x 1 domino shape and a tromino shape. You may rotate these shapes.
     *
     * Given an integer n, return the number of ways to tile an 2 x n board. Since the answer may be very large, return
     * it modulo 109 + 7.
     *
     * In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are two
     * 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a
     * tile.
     *
     * Input: n = 3
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= n <= 1000
     * @param n
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public int numTilings(int n) {
        long M = (long)(1e9 + 7);
        long[][] dp = new long[n + 1][2];
        dp[0][0] = 1;
        dp[1][0] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 2][0] + 2 * dp[i - 1][1]) % M;
            dp[i][1] = (dp[i - 2][0] + dp[i - 1][1]) % M;
        }
        return (int) dp[n][0];
    }

    // S2: dp
    // time = O(n), space = O(n)
    final int mod = (int) 1e9 + 7;
    public int numTilings2(int n) {
        int[][] w = {
                {1, 1, 1, 1},
                {0, 0, 1, 1},
                {0, 1, 0, 1},
                {1, 0, 0, 0}
        };
        int[][] f = new int[n + 1][4];
        f[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    f[i + 1][k] = (f[i + 1][k] + f[i][j] * w[j][k]) % mod;
                }
            }
        }
        return f[n][0];
    }

    // S3
    // time = O(n), space = O(n)
    public int numTilings3(int n) {
        int mod = (int)1E9 + 7;
        if (n == 1) return 1;
        long[] f = new long[n + 1];
        f[0] = f[1] = 1;
        f[2] = 2;
        for (int i = 3; i <= n; i++) {
            f[i] = (f[i - 1] * 2 + f[i - 3]) % mod;
        }
        return (int)f[n];
    }
}
/**
 * how many ways that we can cover a n*2 board using one type of domino (1x2)
 * dp[i]: ways to cover i cols
 * dp[i] = dp[i-1] + dp[i-2]      Fib sequence
 * dp[i][j]:
 * dp[i][0]: ways to cover i cols, both rows of col i are covered 平放
 * dp[i][1]: ways to cover i cols, top rows of col i are covered  上凸
 * dp[i][2]: ways to cover i cols, bottom rows of col i are covered  下凸
 *
 * init: dp[0][0] = dp[1][0] = 1
 * dp[i][0] = dp[i-1][0] + dp[i-2][0] + dp[i-1][1] + dp[i-1][2]
 * dp[i][1] = dp[i-2][0] + dp[i-1][2]
 * dp[i][2] = dp[i-2][0] + dp[i-1][1]
 *
 * dp[i][1] == dp[i][2] due to symmetry
 * =>
 * dp[i][0] = dp[i-1][0] + dp[i-2][0] + 2 * dp[i-1][1]
 * dp[i][1] = dp[i-2][0] + dp[i-1][1]
 * time = O(n), space = O(n) -> O(1)
 *
 * dp[2][0] = dp[1][0] + dp[0][0] - 2 * dp[1][1] = 2
 * dp[1][1] = 0 (no way to put one line and has only top row covered), dp[1][0] = 1 (just lay down one flat piece)
 * => dp[0][0] = 2 - 1 = 1
 *
 * f(i,j): 铺完前i-1列，且i列被占用的状态为j的方案数
 *      00   01   10   11
 * 00   1    1    1    1
 * 01             1    1
 * 10        1         1
 * 11   1
 * 手推一下4种状态的转移方式：
 */