package LC3001_3300;
import java.util.*;
public class LC3148_MaximumDifferenceScoreinaGrid {
    /**
     * You are given an m x n matrix grid consisting of positive integers. You can move from a cell in the matrix to
     * any other cell that is either to the bottom or to the right (not necessarily adjacent). The score of a move from
     * a cell with the value c1 to a cell with the value c2 is c2 - c1.
     * You can start at any cell, and you have to make at least one move.
     *
     * Return the maximum total score you can achieve.
     *
     * Input: grid = [[9,5,7,3],[8,9,6,1],[6,7,14,3],[2,5,3,1]]
     * Output: 9
     *
     * Input: grid = [[4,3,2],[3,2,1]]
     * Output: -1
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 2 <= m, n <= 1000
     * 4 <= m * n <= 10^5
     * 1 <= grid[i][j] <= 10^5
     * @param grid
     * @return
     */
    // S1
    // time = O(m * n), space = O(m * n)
    public int maxScore(List<List<Integer>> grid) {
        final int inf = 0x3f3f3f3f;
        int m = grid.size(), n = grid.get(0).size();
        int[][] f = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(f[i], -inf);
        int[][] r = new int[m][2], c = new int[n][2];
        for (int i = 0; i < m; i++) r[i] = new int[]{i, 0};
        for (int i = 0; i < n; i++) c[i] = new int[]{0, i};

        int res = -inf;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                int c2 = grid.get(i).get(j);
                if (j > 0) {
                    int x = r[i][0], y = r[i][1], c1 = grid.get(x).get(y);
                    f[i][j] = Math.max(f[i][j], Math.max(0, f[x][y]) + c2 - c1);
                }
                if (i > 0) {
                    int x = c[j][0], y = c[j][1], c1 = grid.get(x).get(y);
                    f[i][j] = Math.max(f[i][j], Math.max(0, f[x][y]) + c2 - c1);
                }
                res = Math.max(res, f[i][j]);
                if (j > 0) {
                    int x = r[i][0], y = r[i][1], c1 = grid.get(x).get(y);
                    if (Math.max(f[i][j], 0) - c2 > Math.max(f[x][y], 0) - c1) r[i] = new int[]{i, j};
                    else r[i] = new int[]{x, y};
                }
                if (i > 0) {
                    int x = c[j][0], y = c[j][1], c1 = grid.get(x).get(y);
                    if (Math.max(f[i][j], 0) - c2 > Math.max(f[x][y], 0) - c1) c[j] = new int[]{i, j};
                    else c[j] = new int[]{x, y};
                }
            }
        }
        return res;
    }

    // S2
    // time = O(m * n), space = O(m * n)
    public int maxScore2(List<List<Integer>> grid) {
        int m = grid.size(), n = grid.get(0).size(), res = Integer.MIN_VALUE;
        int[][] s = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) Arrays.fill(s[i], Integer.MAX_VALUE);
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int minv = Math.min(s[i - 1][j], s[i][j - 1]);
                res = Math.max(res, grid.get(i - 1).get(j - 1) - minv);
                s[i][j] = Math.min(grid.get(i - 1).get(j - 1), minv);
            }
        }
        return res;
    }
}
/**
 * 类似二维前缀和的做法
 * 海拔高度
 */