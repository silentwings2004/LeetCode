package LC901_1200;

public class LC1034_ColoringABorder {
    /**
     * You are given an m x n integer matrix grid, and three integers row, col, and color. Each value in the grid
     * represents the color of the grid square at that location.
     *
     * Two squares belong to the same connected component if they have the same color and are next to each other in
     * any of the 4 directions.
     *
     * The border of a connected component is all the squares in the connected component that are either 4-directionally
     * adjacent to a square not in the component, or on the boundary of the grid (the first or last row or column).
     *
     * You should color the border of the connected component that contains the square grid[row][col] with color.
     *
     * Return the final grid.
     *
     * Input: grid = [[1,1],[1,2]], row = 0, col = 0, color = 3
     * Output: [[3,3],[3,2]]
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 50
     * 1 <= grid[i][j], color <= 1000
     * 0 <= row < m
     * 0 <= col < n
     * @param grid
     * @param row
     * @param col
     * @param color
     * @return
     */
    // time = O(m * n), space = O(m * n)
    int[][] g;
    int m, n;
    boolean[][] st;
    int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        g = grid;
        m = g.length;
        n = g[0].length;
        st = new boolean[m][n];
        dfs(row, col, color);
        return g;
    }

    private void dfs(int x, int y, int c) {
        st[x][y] = true;
        boolean is_border = false;
        for (int i = 0; i < 4; i++) {
            int a = x + dx[i], b = y + dy[i];
            if (a < 0 || a >= m || b < 0 || b >= n) {
                is_border = true;
                continue;
            } else if (st[a][b]) continue;
            else if (g[a][b] != g[x][y]) {
                is_border = true;
                continue;
            }
            dfs(a, b, c);
        }
        if (is_border) g[x][y] = c;
    }
}