package LC3301_3600;

public class LC3546_EqualSumGridPartitionI {
    /**
     * You are given an m x n matrix grid of positive integers. Your task is to determine if it is possible to make
     * either one horizontal or one vertical cut on the grid such that:
     *
     * Each of the two resulting sections formed by the cut is non-empty.
     * The sum of the elements in both sections is equal.
     * Return true if such a partition exists; otherwise return false.
     *
     * Input: grid = [[1,4],[2,3]]
     * Output: true
     *
     * Input: grid = [[1,3],[2,4]]
     * Output: false
     *
     * Constraints:
     *
     * 1 <= m == grid.length <= 10^5
     * 1 <= n == grid[i].length <= 10^5
     * 2 <= m * n <= 10^5
     * 1 <= grid[i][j] <= 10^5
     * @param grid
     * @return
     */
    // S1: 二维前缀和
    // time = O(m * n), space = O(m * n)
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long[][] s = new long[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                s[i][j] = s[i - 1][j] + s[i][j - 1] - s[i - 1][j - 1] + grid[i - 1][j - 1];
            }
        }

        for (int i = 1; i < m; i++) {
            if (get(s, 0, 0, i - 1, n - 1) == get(s, i, 0, m - 1, n - 1)) return true;
        }

        for (int j = 1; j < n; j++) {
            if (get(s, 0, 0, m - 1, j - 1) == get(s, 0, j, m - 1, n - 1)) return true;
        }
        return false;
    }

    private long get(long[][] s, int x1, int y1, int x2, int y2) {
        x1++;
        y1++;
        x2++;
        y2++;
        return s[x2][y2] - s[x1 - 1][y2] - s[x2][y1 - 1] + s[x1 - 1][y1 - 1];
    }

    // S2: 暴力枚举
    // time = O(m * n), space = O(1)
    public boolean canPartitionGrid2(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long tot = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                tot += grid[i][j];
            }
        }

        long s = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                s += grid[i][j];
            }
            if (s * 2 == tot) return true;
        }

        s = 0;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                s += grid[i][j];
            }
            if (s * 2 == tot) return true;
        }
        return false;
    }

    // S3: matrix transpose
    // time = O(m * n), space = O(m * n)
    public boolean canPartitionGrid3(int[][] grid) {
        long tot = 0;
        for (int[] row : grid) {
            for (int x : row) {
                tot += x;
            }
        }

        return check(grid, tot) || check(transpose(grid), tot);
    }

    private boolean check(int[][] g, long tot) {
        long s = 0;
        for (int i = 0; i < g.length - 1; i++) {
            for (int x : g[i]) {
                s += x;
            }
            if (s * 2 == tot) return true;
        }
        return false;
    }

    private int[][] transpose(int[][] a) {
        int m = a.length, n = a[0].length;
        int[][] b = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                b[j][i] = a[i][j];
            }
        }
        return b;
    }
}
/**
 * 先讨论水平分割的情况。
 * 设整个 grid 的元素和为 total。
 * 设第一部分的元素和为 s，那么第二部分的元素和为 total−s，题目要求 s=total−s，即 2s=total.
 * 据此，做法是：一边遍历 grid，一边计算第一部分的元素和 s。每一行遍历结束后，判断 2s=total 是否成立
 * 对于垂直分割，可以把 grid 旋转 90 度，复用上述代码
 */