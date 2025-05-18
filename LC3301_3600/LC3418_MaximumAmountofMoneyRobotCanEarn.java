package LC3301_3600;
import java.util.*;
public class LC3418_MaximumAmountofMoneyRobotCanEarn {
    /**
     * You are given an m x n grid. A robot starts at the top-left corner of the grid (0, 0) and wants to reach the
     * bottom-right corner (m - 1, n - 1). The robot can move either right or down at any point in time.
     *
     * The grid contains a value coins[i][j] in each cell:
     *
     * If coins[i][j] >= 0, the robot gains that many coins.
     * If coins[i][j] < 0, the robot encounters a robber, and the robber steals the absolute value of coins[i][j] coins.
     * The robot has a special ability to neutralize robbers in at most 2 cells on its path, preventing them from
     * stealing coins in those cells.
     *
     * Note: The robot's total coins can be negative.
     *
     * Return the maximum profit the robot can gain on the route.
     *
     * Input: coins = [[0,1,-1],[1,-2,3],[2,-3,4]]
     * Output: 8
     *
     * Input: coins = [[10,10,10],[10,10,10]]
     * Output: 40
     *
     * Constraints:
     *
     * m == coins.length
     * n == coins[i].length
     * 1 <= m, n <= 500
     * -1000 <= coins[i][j] <= 1000
     * @param coins
     * @return
     */
    // time = O(m * n), space = O(m * n)
    public int maximumAmount(int[][] coins) {
        final int inf = 0x3f3f3f3f;
        int m = coins.length, n = coins[0].length;
        int[][][] f = new int[m][n][3];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(f[i][j], -inf);
            }
        }
        f[0][0][0] = coins[0][0];
        f[0][0][1] = f[0][0][2] = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                for (int k = 0; k < 3; k++) {
                    if (i > 0) {
                        f[i][j][k] = Math.max(f[i][j][k], f[i - 1][j][k] + coins[i][j]);
                        if (k > 0) f[i][j][k] = Math.max(f[i][j][k], f[i - 1][j][k - 1]);
                    }
                    if (j > 0) {
                        f[i][j][k] = Math.max(f[i][j][k], f[i][j - 1][k] + coins[i][j]);
                        if (k > 0) f[i][j][k] = Math.max(f[i][j][k], f[i][j - 1][k - 1]);
                    }
                }
            }
        }

        int res = -inf;
        for (int i = 0; i < 3; i++) res = Math.max(res, f[m - 1][n - 1][i]);
        return res;
    }
}