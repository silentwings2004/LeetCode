package LC1201_1500;
import java.util.*;
public class LC1219_PathwithMaximumGold {
    /**
     * In a gold mine grid of size m x n, each cell in this mine has an integer representing the amount of gold in that
     * cell, 0 if it is empty.
     *
     * Return the maximum amount of gold you can collect under the conditions:
     *
     * Every time you are located in a cell you will collect all the gold in that cell.
     * From your position, you can walk one step to the left, right, up, or down.
     * You can't visit the same cell more than once.
     * Never visit a cell with 0 gold.
     * You can start and stop collecting gold from any position in the grid that has some gold.
     *
     * Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
     * Output: 24
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 15
     * 0 <= grid[i][j] <= 100
     * There are at most 25 cells containing gold.
     * @param grid
     * @return
     */
    // time = O(m * n), space = O(m * n)
    int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
    int[][] g;
    int m, n, res;
    public int getMaximumGold(int[][] grid) {
        g = grid;
        m = g.length;
        n = g[0].length;
        res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] > 0) dfs(i, j, 0);
            }
        }
        return res;
    }

    private void dfs(int x, int y, int t) {
        t += g[x][y];
        res = Math.max(res, t);
        int v = g[x][y];
        g[x][y] = 0;
        for (int i = 0; i < 4; i++) {
            int a = x + dx[i], b = y + dy[i];
            if (a < 0 || a >= m || b < 0 || b >= n) continue;
            if (g[a][b] == 0) continue;
            dfs(a, b, t);
        }
        g[x][y] = v;
    }
}