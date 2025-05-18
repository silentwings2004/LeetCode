package LC2401_2700;

public class LC2596_CheckKnightTourConfiguration {
    /**
     * There is a knight on an n x n chessboard. In a valid configuration, the knight starts at the top-left cell of the
     * board and visits every cell on the board exactly once.
     *
     * You are given an n x n integer matrix grid consisting of distinct integers from the range [0, n * n - 1] where
     * grid[row][col] indicates that the cell (row, col) is the grid[row][col]th cell that the knight visited. The moves
     * are 0-indexed.
     *
     * Return true if grid represents a valid configuration of the knight's movements or false otherwise.
     *
     * Note that a valid knight move consists of moving two squares vertically and one square horizontally, or two
     * squares horizontally and one square vertically. The figure below illustrates all the possible eight moves of a
     * knight from some cell.
     *
     * Input: grid = [[0,11,16,5,20],[17,4,19,10,15],[12,1,8,21,6],[3,18,23,14,9],[24,13,2,7,22]]
     * Output: true
     *
     * Input: grid = [[0,3,6],[5,8,1],[2,7,4]]
     * Output: false
     *
     * Constraints:
     *
     * n == grid.length == grid[i].length
     * 3 <= n <= 7
     * 0 <= grid[row][col] < n * n
     * All integers in grid are unique.
     */
    // time = O(n^2), space = O(n^2)
    int[][] g;
    int n;
    int[] dx = new int[]{-2, -1, 1, 2, 2, 1, -1, -2}, dy = new int[]{1, 2, 2, 1, -1, -2, -2, -1};
    public boolean checkValidGrid(int[][] grid) {
        g = grid;
        n = g.length;
        return dfs(0, 0, 0);
    }

    private boolean dfs(int x, int y, int v) {
        if (v == n * n - 1) return true;
        for (int i = 0; i < 8; i++) {
            int a = x + dx[i], b = y + dy[i];
            if (a < 0 || a >= n || b < 0 || b >= n) continue;
            if (g[a][b] != v + 1) continue;
            if (dfs(a, b, v + 1)) return true;
        }
        return false;
    }
}