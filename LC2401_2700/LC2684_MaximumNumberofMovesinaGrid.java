package LC2401_2700;

public class LC2684_MaximumNumberofMovesinaGrid {
    /**
     * You are given a 0-indexed m x n matrix grid consisting of positive integers.
     *
     * You can start at any cell in the first column of the matrix, and traverse the grid in the following way:
     *
     * From a cell (row, col), you can move to any of the cells: (row - 1, col + 1), (row, col + 1) and (row + 1, col + 1)
     * such that the value of the cell you move to, should be strictly bigger than the value of the current cell.
     * Return the maximum number of moves that you can perform.
     *
     * Input: grid = [[2,4,3,5],[5,4,9,3],[3,4,2,11],[10,9,13,15]]
     * Output: 3
     *
     * Input: grid = [[3,2,4],[2,1,9],[1,1,7]]
     * Output: 0
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 2 <= m, n <= 1000
     * 4 <= m * n <= 10^5
     * 1 <= grid[i][j] <= 10^6
     * @param grid
     * @return
     */
    // time = O(m * n), space = O(m * n)
    int[][] g;
    int m, n;
    boolean[][] st;
    int res;
    int[] dx = new int[]{-1, 0, 1}, dy = new int[]{1, 1, 1};
    public int maxMoves(int[][] grid) {
        g = grid;
        m = g.length;
        n = g[0].length;
        res = 0;

        for (int i = 0; i < m; i++) {
            st = new boolean[m][n];
            dfs(i, 0, 0);
        }
        return res;
    }

    private void dfs(int x, int y, int step) {
        st[x][y] = true;
        res = Math.max(res, step);
        for (int i = 0; i < 3; i++) {
            int a = x + dx[i], b = y + dy[i];
            if (a < 0 || a >= m || b < 0 || b >= n) continue;
            if (st[a][b]) continue;
            if (g[a][b] <= g[x][y]) continue;
            dfs(a, b, step + 1);
        }
    }
}