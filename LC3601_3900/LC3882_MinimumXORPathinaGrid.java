package LC3601_3900;

public class LC3882_MinimumXORPathinaGrid {
    /**
     * You are given a 2D integer array grid of size m * n.
     *
     * You start at the top-left cell (0, 0) and want to reach the bottom-right cell (m - 1, n - 1).
     *
     * At each step, you may move either right or down.
     *
     * The cost of a path is defined as the bitwise XOR of all the values in the cells along that path, including the
     * start and end cells.
     *
     * Return the minimum possible XOR value among all valid paths from (0, 0) to (m - 1, n - 1).
     *
     * Input: grid = [[1,2],[3,4]]
     * Output: 6
     *
     * Input: grid = [[6,7],[5,8]]
     * Output: 9
     *
     * Input: grid = [[2,7,5]]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= m == grid.length <= 1000
     * 1 <= n == grid[i].length <= 1000
     * m * n <= 1000
     * 0 <= grid[i][j] <= 1023
     * @param grid
     * @return
     */
    // time = O(m * n * k), space = O(m * n * k)  k: 2^11
    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int k = 1 << 11;
        boolean[][] f = new boolean[m * n][k];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int u = i * n + j, x = grid[i][j];
                if (i == 0 && j == 0) f[u][x] = true;
                else {
                    if (i > 0) {
                        int v = (i - 1) * n + j;
                        for (int t = 0; t < k; t++) {
                            if (f[v][t]) f[u][t ^ x] = true;
                        }
                    }
                    if (j > 0) {
                        int v = i * n + j - 1;
                        for (int t = 0; t < k; t++) {
                            if (f[v][t]) f[u][t ^ x] = true;
                        }
                    }
                }
            }
        }
        int res = grid[m - 1][n - 1], v = (m - 1) * n + (n - 1);
        for (int t = 0; t < k; t++) {
            if (f[v][t]) {
                res = t;
                break;
            }
        }
        return res;
    }

    // S2
    // time = O(m * n * U), space = O(m * n * U)  U: max(grid)
    int res;
    public int minCost2(int[][] grid) {
        res = Integer.MAX_VALUE;
        int m = grid.length, n = grid[0].length;
        int t = 0;
        for (int[] r : grid) {
            for (int x : r) {
                t |= x;
            }
        }

        boolean[][][] st = new boolean[m][n][t + 1];
        dfs(m - 1, n - 1, 0, grid, st);
        return res;
    }

    private void dfs(int i, int j, int xor, int[][] g, boolean[][][] st) {
        if (res == 0 || i < 0 || j < 0 || st[i][j][xor]) return;
        st[i][j][xor] = true;
        xor ^= g[i][j];
        if (i == 0 && j == 0) {
            res = Math.min(res, xor);
            return;
        }
        dfs(i - 1, j, xor, g, st);
        dfs(i, j - 1, xor, g, st);
    }
}