package LC1201_1500;

public class LC1254_NumberofClosedIslands {
    /**
     * Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group
     * of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.
     *
     * Return the number of closed islands.
     *
     * Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
     * Output: 2
     *
     * Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
     * Output: 1
     *
     * Input: grid = [[1,1,1,1,1,1,1],
     *                [1,0,0,0,0,0,1],
     *                [1,0,1,1,1,0,1],
     *                [1,0,1,0,1,0,1],
     *                [1,0,1,1,1,0,1],
     *                [1,0,0,0,0,0,1],
     *                [1,1,1,1,1,1,1]]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= grid.length, grid[0].length <= 100
     * 0 <= grid[i][j] <=1
     * @param grid
     * @return
     */
    // time = O(m * n), space = O(m * n)
    int[][] g;
    int m, n;
    int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
    public int closedIsland(int[][] grid) {
        g = grid;
        m = g.length;
        n = g[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] == 0) res += dfs(i, j);
            }
        }
        return res;
    }

    private int dfs(int x, int y) {
        int res = 1;
        g[x][y] = 1;
        for (int i = 0; i < 4; i++) {
            int a = x + dx[i], b = y + dy[i];
            if (a < 0 || a >= m || b < 0 || b >= n) {
                res = 0;
                continue;
            }
            if (g[a][b] == 0 && dfs(a, b) == 0) res = 0;
        }
        return res;
    }
}
