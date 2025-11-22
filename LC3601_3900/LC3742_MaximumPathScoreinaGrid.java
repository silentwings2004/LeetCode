package LC3601_3900;
import java.util.*;
public class LC3742_MaximumPathScoreinaGrid {
    /**
     * You are given an m x n grid where each cell contains one of the values 0, 1, or 2. You are also given an integer
     * k.
     *
     * You start from the top-left corner (0, 0) and want to reach the bottom-right corner (m - 1, n - 1) by moving only
     * right or down.
     *
     * Each cell contributes a specific score and incurs an associated cost, according to their cell values:
     *
     * 0: adds 0 to your score and costs 0.
     * 1: adds 1 to your score and costs 1.
     * 2: adds 2 to your score and costs 1.
     * Return the maximum score achievable without exceeding a total cost of k, or -1 if no valid path exists.
     *
     * Note: If you reach the last cell but the total cost exceeds k, the path is invalid.
     *
     * Input: grid = [[0, 1],[2, 0]], k = 1
     * Output: 2
     *
     * Input: grid = [[0, 1],[1, 2]], k = 1
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= m, n <= 200
     * 0 <= k <= 10^3
     * grid[0][0] == 0
     * 0 <= grid[i][j] <= 2
     * @param grid
     * @param k
     * @return
     */
    // time = O(m * n * k), space = O(n * k)
    final int inf = 0x3f3f3f3f;
    public int maxPathScore(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[][] f = new int[n][k + 1];
        for (int i = 0; i < n; i++) Arrays.fill(f[i], -inf);

        for (int i = 0; i < m; i++) {
            int[][] g = new int[n][k + 1];
            for (int j = 0; j < n; j++) Arrays.fill(g[j], -inf);
            for (int j = 0; j < n; j++) {
                int v = grid[i][j];
                int cost = v == 0 ? 0 : 1;
                if (i == 0 && j == 0) {
                    if (cost <= k) g[0][cost] = v;
                    continue;
                }

                // left
                if (j > 0) {
                    int[] l = g[j - 1];
                    for (int c = 0; c + cost <= k; c++) {
                        if (l[c] != -inf) {
                            int nc = c + cost;
                            g[j][nc] = Math.max(g[j][nc], l[c] + v);
                        }
                    }
                }

                // top
                if (i > 0) {
                    int[] t = f[j];
                    for (int c = 0; c + cost <= k; c++) {
                        if (t[c] != -inf) {
                            int nc = c + cost;
                            g[j][nc] = Math.max(g[j][nc], t[c] + v);
                        }
                    }
                }
            }
            f = g;
        }

        int res = -inf;
        for (int c = 0; c <= k; c++) res = Math.max(res, f[n - 1][c]);
        return res < 0 ? -1 : res;
    }
}