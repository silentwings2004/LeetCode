package LC001_300;
import java.util.*;
public class LC200_NumberofIslands {
    /**
     * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of
     * islands.
     *
     * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may
     * assume all four edges of the grid are all surrounded by water.
     *
     * Input: grid = [
     *   ["1","1","1","1","0"],
     *   ["1","1","0","1","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","0","0","0"]
     * ]
     * Output: 1
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 300
     * grid[i][j] is '0' or '1'.
     * @param grid
     * @return
     */
    // S1: dfs
    // time = O(m * n), space = O(m * n)
    char[][] g;
    int m, n;
    int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
    public int numIslands(char[][] grid) {
        g = grid;
        m = g.length;
        n = g[0].length;
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] == '1') {
                    dfs(i, j);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private void dfs(int x, int y) {
        g[x][y] = '0';
        for (int i = 0; i < 4; i++) {
            int a = x + dx[i];
            int b = y + dy[i];
            if (a < 0 || a >= m || b < 0 || b >= n) continue;
            if (g[a][b] == '0') continue;
            dfs(a, b);
        }
    }

    // S2: BFS
    // time = O(m * n), space = O(m * n)
    private int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public int numIslands2(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != '1') continue;
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i * n + j);
                grid[i][j] = 0;

                while (!queue.isEmpty()) {
                    int cur = queue.poll();
                    int x = cur / n, y = cur % n;
                    for (int[] dir : directions) {
                        int ii = x + dir[0];
                        int jj = y + dir[1];
                        if (ii < 0 || ii >= m || jj < 0 || jj >= n) continue;
                        if (grid[ii][jj] != '1') continue;
                        queue.offer(ii * n + jj);
                        grid[ii][jj] = 0;
                    }
                }
                count++;
            }
        }
        return count;
    }
}