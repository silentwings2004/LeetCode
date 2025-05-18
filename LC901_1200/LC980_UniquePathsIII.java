package LC901_1200;
import java.util.*;
public class LC980_UniquePathsIII {
    /**
     * You are given an m x n integer array grid where grid[i][j] could be:
     *
     * 1 representing the starting square. There is exactly one starting square.
     * 2 representing the ending square. There is exactly one ending square.
     * 0 representing empty squares we can walk over.
     * -1 representing obstacles that we cannot walk over.
     * Return the number of 4-directional walks from the starting square to the ending square, that walk over every
     * non-obstacle square exactly once.
     *
     * Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
     * Output: 2
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 20
     * 1 <= m * n <= 20
     * -1 <= grid[i][j] <= 2
     * There is exactly one starting cell and one ending cell.
     * @param grid
     * @return
     */
    // time = O(3^n), space = O(n)
    int n, m, k;
    int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
    int[][] g;
    public int uniquePathsIII(int[][] grid) {
        g = grid;
        n = g.length;
        m = g[0].length;
        k = 0;
        int sx = 0, sy = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (g[i][j] == 1) {
                    sx = i;
                    sy = j;
                    k++;
                } else if (g[i][j] == 0) k++;
            }
        }
        return dfs(sx, sy);
    }

    private int dfs(int x, int y) {
        if (g[x][y] == 2) {
            if (k == 0) return 1;
            return 0;
        }

        g[x][y] = -1;
        k--;
        int res = 0;
        for (int i = 0; i < 4; i++) {
            int a = x + dx[i], b = y + dy[i];
            if (a >= 0 && a < n && b >= 0 && b < m && g[a][b] != -1) {
                res += dfs(a, b);
            }
        }
        g[x][y] = 0;
        k++;
        return res;
    }
}
/**
 * 求哈密顿路径数
 * 正解：连通性dp问题 -> 插头dp
 */
