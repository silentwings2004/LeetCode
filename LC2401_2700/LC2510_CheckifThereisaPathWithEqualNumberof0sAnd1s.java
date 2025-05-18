package LC2401_2700;

public class LC2510_CheckifThereisaPathWithEqualNumberof0sAnd1s {
    /**
     * You are given a 0-indexed m x n binary matrix grid. You can move from a cell (row, col) to any of the cells
     * (row + 1, col) or (row, col + 1).
     *
     * Return true if there is a path from (0, 0) to (m - 1, n - 1) that visits an equal number of 0's and 1's.
     * Otherwise return false.
     *
     * Input: grid = [[0,1,0,0],[0,1,0,0],[1,0,1,0]]
     * Output: true
     *
     * Input: grid = [[1,1,0],[0,0,1],[1,0,0]]
     * Output: false
     *
     * Input: grid = [[1,1,0],[0,0,1],[1,0,0]]
     * Output: false
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 2 <= m, n <= 100
     * grid[i][j] is either 0 or 1.
     * @param grid
     * @return
     */
    // S1: dp
    // time = O(m * n * (m + n)), space = O(m * n * (m + n))
    public boolean isThereAPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if ((m + n) % 2 == 0) return false;
        long[][][] f = new long[m][n][m + n + 1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    f[i][j][grid[i][j]] = 1;
                    continue;
                }
                for (int k = 0; k <= i + j + 1; k++) {
                    if (i > 0 && k >= grid[i][j]) f[i][j][k] += f[i - 1][j][k - grid[i][j]];
                    if (j > 0 && k >= grid[i][j]) f[i][j][k] += f[i][j - 1][k - grid[i][j]];
                }
            }
        }
        return f[m - 1][n - 1][(m + n - 1) / 2] >= 1;
    }

    // S2: dfs (最优解!)
    // time = O(m * n * (m + n)), space = O(m * n * (m + n))
    int[][] g;
    int m, n;
    public boolean isThereAPath2(int[][] grid) {
        g = grid;
        m = grid.length;
        n = grid[0].length;
        if ((m + n) % 2 == 0) return false;
        return dfs(m - 1, n - 1, (m + n - 1) / 2);
    }

    private boolean dfs(int x, int y, int v) { // v: how many ones required in the path
        if (x < 0 || x >= m || y < 0 || y >= n) return false;
        if (v < 0) return false;

        if (x == 0 && y == 0) return v == 1 && g[0][0] == 1 || v == 0 && g[0][0] == 0;
        if (g[x][y] == 0) return dfs(x - 1, y, v) || dfs(x, y - 1, v);
        else return dfs(x - 1, y, v -1) || dfs(x, y - 1, v - 1);
    }
}