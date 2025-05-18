package LC1201_1500;
import java.util.*;
public class LC1463_CherryPickupII {
    /**
     * You are given a rows x cols matrix grid representing a field of cherries where grid[i][j] represents the number
     * of cherries that you can collect from the (i, j) cell.
     *
     * You have two robots that can collect cherries for you:
     *
     * Robot #1 is located at the top-left corner (0, 0), and
     * Robot #2 is located at the top-right corner (0, cols - 1).
     * Return the maximum number of cherries collection using both robots by following the rules below:
     *
     * From a cell (i, j), robots can move to cell (i + 1, j - 1), (i + 1, j), or (i + 1, j + 1).
     * When any robot passes through a cell, It picks up all cherries, and the cell becomes an empty cell.
     * When both robots stay in the same cell, only one takes the cherries.
     * Both robots cannot move outside of the grid at any moment.
     * Both robots should reach the bottom row in grid.
     *
     * Input: grid = [[3,1,1],[2,5,1],[1,5,5],[2,1,1]]
     * Output: 24
     *
     * Constraints:
     *
     * rows == grid.length
     * cols == grid[i].length
     * 2 <= rows, cols <= 70
     * 0 <= grid[i][j] <= 100
     * @param grid
     * @return
     */
    // time = O(m * n^2), space = O(n^2)
    public int cherryPickup(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], Integer.MIN_VALUE);
        dp[0][n - 1] = grid[0][0] + grid[0][n - 1];

        for (int r = 1; r < m; r++) {
            int[][] dp_old = new int[n][n];
            for (int i = 0; i < n; i++) dp_old[i] = dp[i].clone();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int a = i - 1; a <= i + 1; a++) {
                        for (int b = j - 1; b <= j + 1; b++) {
                            if (a < 0 || a >= n || b < 0 || b >= n) continue;
                            if (i != j) dp[i][j] = Math.max(dp[i][j], dp_old[a][b] + grid[r][i] + grid[r][j]);
                            else dp[i][j] = Math.max(dp[i][j], dp_old[a][b] + grid[r][i]);
                        }
                    }
                }
            }
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }

    // S1.2: dp
    // time = O(m * n^2 * 9), space = O(m * n^2)
    public int cherryPickup2(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][][] f = new int[m][n][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    f[i][j][k] = -1;
                }
            }
        }
        f[0][0][n - 1] = grid[0][0] + grid[0][n - 1];

        int res = 0;
        for (int k = 1; k < m; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int a = i - 1; a <= i + 1; a++) {
                        for (int b = j - 1; b <= j + 1; b++) {
                            if (a < 0 || a >= n || b < 0 || b >= n) continue;
                            int t = f[k - 1][a][b];
                            if (t == -1) continue;
                            if (i == j) t += grid[k][i];
                            else t += grid[k][i] + grid[k][j];
                            f[k][i][j] = Math.max(f[k][i][j], t);
                            res = Math.max(res, f[k][i][j]);
                        }
                    }
                }
            }
        }
        return res;
    }
}
/**
 * 时间序列中，dp
 * 因为这题有非常显著的“轮次”的特点：每走一步就下降一行。
 * 这一行（两个机器人）的选择，取决于上一行（两个机器人）的选择。
 * dp[i][j]：for a given row, the maximum number of cherries collection using both robots at i and at j
 * dp[r][i][j] = dp[r-1][a][b] + grid[r][i] + grid[r][j];
 * ref: LC741 cherry pick 1
 * dp[i][j][p][q] = dp[a][b][x][y] 可以省掉一维
 *
 * 状态表示：f(k,i,j)
 * 集合：所有走了k步，第一个机器人位于第i列，第2个机器人位于第j列的所有走法
 * 属性：最大值
 * 状态计算：最后一步9种走法
 */