package LC1201_1500;

public class LC1391_CheckifThereisaValidPathinaGrid {
    /**
     * You are given an m x n grid. Each cell of grid represents a street. The street of grid[i][j] can be:
     *
     * 1 which means a street connecting the left cell and the right cell.
     * 2 which means a street connecting the upper cell and the lower cell.
     * 3 which means a street connecting the left cell and the lower cell.
     * 4 which means a street connecting the right cell and the lower cell.
     * 5 which means a street connecting the left cell and the upper cell.
     * 6 which means a street connecting the right cell and the upper cell.
     *
     * You will initially start at the street of the upper-left cell (0, 0). A valid path in the grid is a path that
     * starts from the upper left cell (0, 0) and ends at the bottom-right cell (m - 1, n - 1). The path should only
     * follow the streets.
     *
     * Notice that you are not allowed to change any street.
     *
     * Return true if there is a valid path in the grid or false otherwise.
     *
     * Input: grid = [[2,4,3],[6,5,2]]
     * Output: true
     *
     * Input: grid = [[1,2,1],[1,2,1]]
     * Output: false
     *
     * Input: grid = [[1,1,2]]
     * Output: false
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 300
     * 1 <= grid[i][j] <= 6
     * @param grid
     * @return
     */
    // time = O(m * n), space = O(m * n)
    int[][] g;
    int m, n;
    int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
    int[][] state = new int[][]{
            {0, 1, 0, 1},
            {1, 0, 1, 0},
            {0, 0, 1, 1},
            {0, 1, 1, 0},
            {1, 0, 0, 1},
            {1, 1, 0, 0}
    };
    public boolean hasValidPath(int[][] grid) {
        g = grid;
        m = g.length;
        n = g[0].length;
        return dfs(0, 0);
    }

    private boolean dfs(int x,int y) {
        if (x == m - 1 && y == n - 1) return true;
        int t = g[x][y] - 1;
        g[x][y] = 0;
        for (int i = 0; i < 4; i++) {
            int a = x + dx[i], b = y + dy[i];
            if (a < 0 || a >= m || b < 0 || b >= n) continue;
            if (g[a][b] == 0) continue;
            if (state[t][i] == 0 || state[g[a][b] - 1][i ^ 2] == 0) continue;
            if (dfs(a, b)) return true;
        }
        return false;
    }
}
/**
 * (x,y)的街道的i方向是否为1
 * (a,b)的街道的i方向的反方向是否为1 => i ^ 2
 */