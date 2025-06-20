package LC3301_3600;
import java.util.*;
public class LC3565_SequentialGridPathCover {
    /**
     * You are given a 2D array grid of size m x n, and an integer k. There are k cells in grid containing the values
     * from 1 to k exactly once, and the rest of the cells have a value 0.
     *
     * You can start at any cell, and move from a cell to its neighbors (up, down, left, or right). You must find a path
     * in grid which:
     *
     * Visits each cell in grid exactly once.
     * Visits the cells with values from 1 to k in order.
     * Return a 2D array result of size (m * n) x 2, where result[i] = [xi, yi] represents the ith cell visited in the
     * path. If there are multiple such paths, you may return any one.
     *
     * If no such path exists, return an empty array.
     *
     * Input: grid = [[0,0,0],[0,1,2]], k = 2
     *
     * Output: [[0,0],[1,0],[1,1],[1,2],[0,2],[0,1]]
     *
     * Input: grid = [[1,0,4],[3,0,2]], k = 4
     *
     * Output: []
     *
     * Constraints:
     *
     * 1 <= m == grid.length <= 6
     * 1 <= n == grid[i].length <= 6
     * 1 <= k <= m * n
     * 0 <= grid[i][j] <= k
     * grid contains all integers between 1 and k exactly once.
     * @param grid
     * @param k
     * @return
     */
    // time = O(m * n * 4^(m * n)), space = O(m * n)
    int[][] g;
    int m, n;
    boolean[][] st;
    List<List<Integer>> res;
    int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
    public List<List<Integer>> findPath(int[][] grid, int k) {
        g = grid;
        m = g.length;
        n = g[0].length;
        res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] <= 1) {
                    st = new boolean[m][n];
                    List<int[]> path = new ArrayList<>();
                    if (dfs(i, j, g[i][j], path)) {
                        for (int[] x : path) {
                            res.add(Arrays.asList(x[0], x[1]));
                        }
                        return res;
                    }
                }
            }
        }
        return res;
    }

    private boolean dfs(int x, int y, int last, List<int[]> path) {
        st[x][y] = true;
        path.add(new int[]{x, y});
        if (path.size() == m * n) return true;

        for (int i = 0; i < 4; i++) {
            int a = x + dx[i], b = y + dy[i];
            if (a < 0 || a >= m || b < 0 || b >= n) continue;
            if (st[a][b]) continue;
            if (g[a][b] == 0 || g[a][b] - last == 1) {
                if (dfs(a, b, g[a][b] == 0 ? last : g[a][b], path)) return true;
            }
        }
        st[x][y] = false;
        path.remove(path.size() - 1);
        return false;
    }
}
