package LC2401_2700;

public class LC2556_DisconnectPathinaBinaryMatrixbyatMostOneFlip {
    /**
     * You are given a 0-indexed m x n binary matrix grid. You can move from a cell (row, col) to any of the cells
     * (row + 1, col) or (row, col + 1) that has the value 1. The matrix is disconnected if there is no path from (0, 0)
     * to (m - 1, n - 1).
     *
     * You can flip the value of at most one (possibly none) cell. You cannot flip the cells (0, 0) and (m - 1, n - 1).
     *
     * Return true if it is possible to make the matrix disconnect or false otherwise.
     *
     * Note that flipping a cell changes its value from 0 to 1 or from 1 to 0.
     *
     * Input: grid = [[1,1,1],[1,0,0],[1,1,1]]
     * Output: true
     *
     * Input: grid = [[1,1,1],[1,0,1],[1,1,1]]
     * Output: false
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 1000
     * 1 <= m * n <= 10^5
     * grid[i][j] is either 0 or 1.
     * grid[0][0] == grid[m - 1][n - 1] == 1
     * @param grid
     * @return
     */
    // S1: double dfs
    // time = O(m * n), space = O(m * n)
    int[][] g;
    int m, n;
    boolean[][] st;
    int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
    public boolean isPossibleToCutPath(int[][] grid) {
        g = grid;
        m = g.length;
        n = g[0].length;
        st = new boolean[m][n];
        if (m == 1 && n == 1 || m == 2 && n == 1 || m == 1 && n == 2) return false;
        return dfs(0, 0) && dfs(0, 0) ? false : true;
    }

    private boolean dfs(int x, int y) {
        if (x == m - 1 && y == n - 1) return true;
        if (st[x][y]) return false;

        g[x][y] = 0;
        for (int i = 0; i < 4; i++) {
            int a = x + dx[i], b = y + dy[i];
            if (a < 0 || a >= m || b < 0 || b >= n) continue;
            if (g[a][b] == 0) continue;
            if (dfs(a, b)) return true;
        }
        g[x][y] = 1;
        st[x][y] = true;
        return false;
    }

    // S2
    // time = O(m * n), space = O(1)
    public boolean isPossibleToCutPath2(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j == n - 1) continue;
                if ((i + 1 >= m || grid[i + 1][j] == 0) && (j + 1 >= n || grid[i][j + 1] == 0)) {
                    grid[i][j] = 0;
                }
            }
        }
        if (grid[0][0] == 0) return true;

        int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
        for (int k = 0; k < m + n - 3; k++) {
            if (y1 + 1 < n && grid[x1][y1 + 1] == 1) y1++;
            else x1++;
            if (x2 + 1 < m && grid[x2 + 1][y2] == 1) x2++;
            else y2++;
            if (x1 == x2 && y1 == y2) return true;
        }
        return false;
    }
}
/**
 * 询问连通图里是否存在一个割点
 */