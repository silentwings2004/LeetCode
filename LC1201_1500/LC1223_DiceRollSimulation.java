package LC1201_1500;

public class LC1223_DiceRollSimulation {
    /**
     * A die simulator generates a random number from 1 to 6 for each roll. You introduced a constraint to the generator
     * such that it cannot roll the number i more than rollMax[i] (1-indexed) consecutive times.
     *
     * Given an array of integers rollMax and an integer n, return the number of distinct sequences that can be
     * obtained with exact n rolls. Since the answer may be too large, return it modulo 10^9 + 7.
     *
     * Two sequences are considered different if at least one element differs from each other.
     *
     * Input: n = 2, rollMax = [1,1,2,2,2,3]
     * Output: 34
     *
     * Constraints:
     *
     * 1 <= n <= 5000
     * rollMax.length == 6
     * 1 <= rollMax[i] <= 15
     * @param n
     * @param rollMax
     * @return
     */
    // S1
    // time = O(n * m^2 * k), space = O(n * m * k)
    public int dieSimulator(int n, int[] rollMax) {
        int[][][] dp = new int[n][6][16];
        int M = (int)(1e9 + 7);

        // init
        for (int j = 0; j < 6; j++) dp[0][j][1] = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 6; j++) {
                for (int k = 1; k <= rollMax[j]; k++) {
                    if (k > 1) dp[i][j][k] = dp[i - 1][j][k - 1];
                    else {
                        for (int jj = 0; jj < 6; jj++) {
                            if (jj == j) continue; // excluding dp[i-1][j][?]
                            for (int kk = 1; kk <= rollMax[jj]; kk++) {
                                dp[i][j][k] += dp[i - 1][jj][kk];
                                dp[i][j][k] %= M;
                            }
                        }
                    }
                }
            }
        }

        int res = 0;
        for (int j = 0; j < 6; j++) {
            for (int k = 1; k <= rollMax[j]; k++) {
                res += dp[n - 1][j][k];
                res %= M;
            }
        }
        return res;
    }

    // S1.2
    // time = O(n * m^2 * k), space = O(n * m * k)
    public int dieSimulator2(int n, int[] rollMax) {
        int mod = (int)(1e9 + 7);
        int[][][] f = new int[n + 1][6][16];
        for (int i = 0; i < 6; i++) f[1][i][1] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 6; j++) {
                for (int k = 1; k <= rollMax[j]; k++) {
                    for (int u = 0; u < 6; u++) {
                        int len = 1;
                        if (u == j) {
                            len = k + 1;
                            if (len > rollMax[j]) continue;
                        }
                        f[i + 1][u][len] = (f[i + 1][u][len] + f[i][j][k]) % mod;
                    }
                }
            }
        }

        int res = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 1; j <= rollMax[i]; j++) {
                res = (res + f[n][i][j]) % mod;
            }
        }
        return res;
    }
}
/**
 * dp[i][j] = sum(dp[i-1][j']) j'!=j
 * dp[i][j][k]: 第i个元素是j，并且j已经连续出现k次
 * dp[i][6][2] = dp[i-1][6][1]
 * dp[i][6][1] = dp[i-1][1][?] ...dp[i-1][5][?] excluding dp[i-1][6][?]
 *
 * f(i,j,k):所有长度为i的，最后一位是j，并且j出现k次的所有方案
 * 5000 * 6 * 15 = 5000 * 90 = 450000
 * 枚举下一个数字是什么
 * 状态转移 * 6 = 4.5 * 10^5 * 6 = 2.7 * 10^6 可以过
 */