package LC3001_3300;

public class LC3197_FindtheMinimumAreatoCoverAllOnesII {
    /**
     * You are given a 2D binary array grid. You need to find 3 non-overlapping rectangles having non-zero areas with
     * horizontal and vertical sides such that all the 1's in grid lie inside these rectangles.
     *
     * Return the minimum possible sum of the area of these rectangles.
     *
     * Note that the rectangles are allowed to touch.
     *
     * Input: grid = [[1,0,1],[1,1,1]]
     *
     * Output: 5
     *
     * Input: grid = [[1,0,1,0],[0,1,0,1]]
     *
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= grid.length, grid[i].length <= 30
     * grid[i][j] is either 0 or 1.
     * The input is generated such that there are at least three 1's in grid.
     * @param grid
     * @return
     */
    // time = O((m * n)^2), space = O(m * n)
    public int minimumSum(int[][] grid) {
        return Math.min(helper(grid), helper(rotate(grid)));
    }

    private int helper(int[][] g) {
        int res = Integer.MAX_VALUE;
        int m = g.length, n = g[0].length;
        // case 1
        if (m >= 3) {
            for (int i = 1; i < m; i++) {
                for (int j = i + 1; j < m; j++) {
                    int area = cal(g, 0, i, 0, n);
                    area += cal(g, i, j, 0, n);
                    area += cal(g, j, m, 0, n);
                    res = Math.min(res, area);
                }
            }
        }

        if (m >= 2 && n >= 2) {
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    int area = cal(g, 0, i, 0, n);
                    area += cal(g, i, m, 0, j);
                    area += cal(g, i, m, j, n);
                    res = Math.min(res, area);

                    area = cal(g, 0, i, 0, j);
                    area += cal(g, 0, i, j, n);
                    area += cal(g, i, m, 0, n);
                    res = Math.min(res, area);
                }
            }
        }
        return res;
    }

    private int cal(int[][] g, int rl, int rr, int cl, int cr) {
        int xl = g.length, xr = 0;
        int yl = g[0].length, yr = 0;
        for (int i = rl; i < rr; i++) {
            for (int j = cl; j < cr; j++) {
                if (g[i][j] == 1) {
                    xl = Math.min(xl, i);
                    xr = Math.max(xr, i);
                    yl = Math.min(yl, j);
                    yr = Math.max(yr, j);
                }
            }
        }
        return (xr - xl + 1) * (yr - yl + 1);
    }

    private int[][] rotate(int[][] g) {
        int m = g.length, n = g[0].length;
        int[][] f = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                f[j][m - 1 - i] = g[i][j];
            }
        }
        return f;
    }

    // S2
    // time = O(m^2 * n^2), space = O(m * n)
    public int minimumSum2(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int res = Integer.MAX_VALUE;
        for (int i = 1; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                int A1 = process(grid, 0, 0, i - 1, n - 1);
                int A2 = process(grid, i, 0, j - 1, n - 1);
                int A3 = process(grid, j, 0, m - 1, n - 1);
                res = Math.min(A1 + A2 + A3, res);
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int A1 = process(grid, 0, 0, i - 1, j - 1);
                int A2 = process(grid, 0, j, i - 1, n - 1);
                int A3 = process(grid, i, 0, m - 1, n - 1);
                res = Math.min(A1 + A2 + A3, res);
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int A1 = process(grid, 0, 0, i - 1, n - 1);
                int A2 = process(grid, i, 0, m - 1, j - 1);
                int A3 = process(grid, i, j, m - 1, n - 1);
                res = Math.min(A1 + A2 + A3, res);
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int A1 = process(grid, 0, 0, m - 1, i - 1);
                int A2 = process(grid, 0, i, m - 1, j - 1);
                int A3 = process(grid, 0, j, m - 1, n - 1);
                res = Math.min(A1 + A2 + A3, res);
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int A1 = process(grid, 0, 0, m - 1, j - 1);
                int A2 = process(grid, 0, j, i - 1, n - 1);
                int A3 = process(grid, i, j, m - 1, n - 1);
                res = Math.min(A1 + A2 + A3, res);
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int A1 = process(grid, 0, 0, i - 1, j - 1);
                int A2 = process(grid, i, 0, m - 1, j - 1);
                int A3 = process(grid, 0, j, m - 1, n - 1);
                res = Math.min(A1 + A2 + A3, res);
            }
        }
        return res;
    }

    private int process(int[][] grid, int a, int b, int c, int d) {
        if (a > c || b > d) return Integer.MAX_VALUE / 3;
        int l = Integer.MAX_VALUE, r = Integer.MIN_VALUE, u = Integer.MAX_VALUE, v = Integer.MIN_VALUE;
        for (int i = a; i <= c; i++) {
            for (int j = b; j <= d; j++) {
                if (grid[i][j] == 0) continue;
                l = Math.min(l, j);
                r = Math.max(r, j);
                u = Math.min(u, i);
                v = Math.max(v, i);
            }
        }
        if (v >= u && r >= l) return (v - u + 1) * (r - l + 1);
        return Integer.MAX_VALUE / 3;
    }
}
/**
 * 2个矩形怎么做？
 * 3个矩形能否变成2个矩形？
 * 4个矩形怎么做？只能 回溯 + 暴搜!
 * 事实上将一个矩阵分成三个互不相交的子矩形，只有如下六种形式：
 *         1.        2.      3.
 *
 *         ┌－┐      ┌┐┌┐    ┌－┐
 *         └－┘      └┘└┘    └－┘
 *         ┌－┐      ┌－┐    ┌┐┌┐
 *         └－┘      └－┘    └┘└┘
 *         ┌－┐
 *         └－┘
 *
 *         4.       5.      6.
 *         ┌┐┌┐┌┐   ┌ ┐┌┐    ┌┐┌ ┐
 *         └┘└┘└┘   │ │└┘    └┘│ │
 *                  │ │┌┐    ┌┐│ │
 *                  └ ┘└┘    └┘└ ┘
 * 于每种形式，只有两条分割线。我们可以用o(MN)的时间遍历分割线的位置，就可以确定三个子矩阵的边界。
 * 对于每一个子矩阵，我们再遍历其中的元素，确定包含所有元素1的最小矩阵即可（同3135）。
 */