package LC3601_3900;
import java.util.*;
public class LC3665_TwistedMirrorPathCount {
    /**
     * Given an m x n binary grid grid where:
     *
     * grid[i][j] == 0 represents an empty cell, and
     * grid[i][j] == 1 represents a mirror.
     * A robot starts at the top-left corner of the grid (0, 0) and wants to reach the bottom-right corner
     * (m - 1, n - 1). It can move only right or down. If the robot attempts to move into a mirror cell, it is reflected
     * before entering that cell:
     *
     * If it tries to move right into a mirror, it is turned down and moved into the cell directly below the mirror.
     * If it tries to move down into a mirror, it is turned right and moved into the cell directly to the right of the
     * mirror.
     * If this reflection would cause the robot to move outside the grid boundaries, the path is considered invalid and
     * should not be counted.
     *
     * Return the number of unique valid paths from (0, 0) to (m - 1, n - 1).
     *
     * Since the answer may be very large, return it modulo 109 + 7.
     *
     * Note: If a reflection moves the robot into a mirror cell, the robot is immediately reflected again based on the
     * direction it used to enter that mirror: if it entered while moving right, it will be turned down; if it entered
     * while moving down, it will be turned right. This process will continue until either the last cell is reached, the
     * robot moves out of bounds or the robot moves to a non-mirror cell.
     *
     * Input: grid = [[0,1,0],[0,0,1],[1,0,0]]
     * Output: 5
     *
     * Input: grid = [[0,0],[0,0]]
     * Output: 2
     *
     * Input: grid = [[0,1,1],[1,1,0]]
     * Output: 1
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 2 <= m, n <= 500
     * grid[i][j] is either 0 or 1.
     * grid[0][0] == grid[m - 1][n - 1] == 0
     * @param grid
     * @return
     */
    // S1
    // time = O(m * n), space = O(m * n)
    public int uniquePaths(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long mod = (long)(1e9 + 7);
        long[][][] f = new long[m][n][2]; // 0: from up, 1: from left
        f[0][0][1] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (f[i][j][0] == 0 && f[i][j][1] == 0) continue;
                long tot = (f[i][j][0] + f[i][j][1]) % mod;

                // from (i, j) to go down
                if (i + 1 < m) {
                    int x = i + 1, y = j, d = 0; // 0: go down, 1: go right
                    while (x < m && y < n && grid[x][y] == 1) {
                        if (d == 0) y++;
                        else x++;
                        d ^= 1;
                    }
                    if (x < m && y < n) f[x][y][d] = (f[x][y][d] + tot) % mod;
                }

                // from (i, j) to go right
                if (j + 1 < n) {
                    int x = i, y = j + 1, d = 1;
                    while (x < m && y < n && grid[x][y] == 1) {
                        if (d == 0) y++;
                        else x++;
                        d ^= 1;
                    }
                    if (x < m && y < n) f[x][y][d] = (f[x][y][d] + tot) % mod;
                }
            }
        }
        return (int)((f[m - 1][n - 1][0] + f[m - 1][n - 1][1]) % mod);
    }

    // S2
    // time = O(m * n), space = O(m * n)
    long mod = (long)(1e9 + 7);
    public int uniquePaths2(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][][] f = new int[m][n][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(f[i][j], -1);
            }
        }
        return dfs(m - 1, n - 1, 0, f, grid);
    }

    private int dfs(int i, int j, int k, int[][][] f, int[][] grid) {
        if (i < 0 || j < 0) return 0;
        if (i == 0 && j == 0) return 1;
        if (f[i][j][k] != -1) return f[i][j][k];

        long res = 0;
        if (grid[i][j] == 0) {
            res = (dfs(i, j - 1, 0, f, grid) + dfs(i - 1, j, 1, f, grid)) % mod;
        } else if (k == 0) {  // 从右边过来
            res = dfs(i - 1, j, 1, f, grid); // 反射到上边
        } else { // 从下边过来
            res = dfs(i, j - 1, 0, f, grid); // 反射到左边
        }
        return f[i][j][k] = (int)res;
    }
}