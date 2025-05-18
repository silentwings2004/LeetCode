package LC301_600;

public class LC361_BombEnemy {
    /**
     * Given an m x n matrix grid where each cell is either a wall 'W', an enemy 'E' or empty '0', return the maximum
     * enemies you can kill using one bomb. You can only place the bomb in an empty cell.
     *
     * The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since it
     * is too strong to be destroyed.
     *
     * Input: grid = [["0","E","0","0"],["E","0","W","E"],["0","E","0","0"]]
     * Output: 3
     *
     * Input: grid = [["W","W","W"],["0","0","0"],["E","E","E"]]
     * Output: 1
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 500
     * grid[i][j] is either 'W', 'E', or '0'.
     * @param grid
     * @return
     */
    // time = O(m * n), space = O(m * n)
    public int maxKilledEnemies(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][][] f = new int[4][m][n];

        // case 1 : up
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'W') continue;
                f[0][i][j] = (i == m - 1 ? 0 : f[0][i + 1][j]) + (grid[i][j] == 'E' ? 1 : 0);
            }
        }

        // case 2 / 4: down / right
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'W') continue;
                f[1][i][j] = (i == 0 ? 0 : f[1][i - 1][j]) + (grid[i][j] == 'E' ? 1 : 0);
                f[3][i][j] = (j == 0 ? 0 : f[3][i][j - 1]) + (grid[i][j] == 'E' ? 1 : 0);
            }
        }

        // case 3: left
        for (int i = 0; i < m; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 'W') continue;
                f[2][i][j] = (j == n - 1 ? 0 : f[2][i][j + 1]) + (grid[i][j] == 'E' ? 1 : 0);
            }
        }

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') {
                    res = Math.max(res, f[0][i][j] + f[1][i][j] + f[2][i][j] + f[3][i][j]);
                }
            }
        }
        return res;
    }
}
