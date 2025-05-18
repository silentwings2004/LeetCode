package LC3001_3300;
import java.util.*;
public class LC3225_MaximumScoreFromGridOperations {
    /**
     * You are given a 2D matrix grid of size n x n. Initially, all cells of the grid are colored white. In one
     * operation, you can select any cell of indices (i, j), and color black all the cells of the jth column starting
     * from the top row down to the ith row.
     *
     * The grid score is the sum of all grid[i][j] such that cell (i, j) is white and it has a horizontally adjacent
     * black cell.
     *
     * Return the maximum score that can be achieved after some number of operations.
     *
     * Input: grid = [[0,0,0,0,0],[0,0,3,0,0],[0,1,0,0,0],[5,0,0,3,0],[0,0,0,0,2]]
     * Output: 11
     *
     * Input: grid = [[10,9,0,0,15],[7,1,0,8,0],[5,20,0,11,0],[0,0,0,1,2],[8,12,1,10,3]]
     * Output: 94
     *
     * Constraints:
     *
     * 1 <= n == grid.length <= 100
     * n == grid[i].length
     * 0 <= grid[i][j] <= 10^9
     * @param grid
     * @return
     */
    // time = O(n^3), space = O(n^2)
    final int N = 110;
    final long inf = Long.MAX_VALUE;
    public long maximumScore(int[][] grid) {
        long[][] f = new long[N][N];
        long[][] pre = new long[N][N];
        long[][] suf = new long[N][N];
        long[] s = new long[N];
        for (int i = 0; i < N; i++) Arrays.fill(f[i], -inf);

        int n = grid.length;
        for (int i = 0; i <= n; i++) {
            f[i][0] = 0;
            for (int j = 1; j <= n; j++) {
                f[i][j] = -inf;
            }
        }

        for (int j = 0; j < n; j++) {
            s[0] = 0;
            for (int i = 1; i <= n; i++) {
                s[i] = s[i - 1] + grid[i - 1][j];
            }

            for (int i = 0; i <= n; i++) {
                pre[i][0] = f[i][0];
                for (int k = 1; k <= n; k++) {
                    pre[i][k] = Math.max(pre[i][k - 1], f[i][k]);
                }
                suf[i][n + 1] = 0;
                for (int k = n; k >= 0; k--) {
                    suf[i][k] = Math.max(suf[i][k + 1], f[i][k] + (i < k ? s[k] - s[i] : 0));
                }
            }

            for (int i = 0; i <= n; i++) {
                for (int k = 0; k <= n; k++) {
                    f[i][k] = Math.max(pre[k][i] + (k < i ? s[i] - s[k] : 0), suf[k][i + 1]);
                }
            }
        }

        long res = -inf;
        for (int i = 0; i <= n; i++) res = Math.max(res, f[0][i]);
        return res;
    }
}
/**
 * dp + 优化
 * 结论: 从贪心的角度上说，有一种情况 凹形 是不可能的
 * 优化前：需要知道4个信息：当前在第 j 列，第 j 列的高度, 第 j + 1 列的高度, 第 j - 1 列的高度 => O(n^4)
 * 优化后: 需要知道3个大信息 + 1个 bool 值：当前在第 j 列，第 j 列的高度, 第 j + 1 列的高度
 *        去掉：第 j - 1 列的高度 (不枚举)
 * 目标：不重不漏地计算答案
 */