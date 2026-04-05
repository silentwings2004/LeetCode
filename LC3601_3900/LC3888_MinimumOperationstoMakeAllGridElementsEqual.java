package LC3601_3900;

public class LC3888_MinimumOperationstoMakeAllGridElementsEqual {
    /**
     * You are given a 2D integer array grid of size m × n, and an integer k.
     *
     * In one operation, you can:
     *
     * Select any k x k submatrix of grid, and
     * Increment all elements inside that submatrix by 1.
     * Return the minimum number of operations required to make all elements in the grid equal. If it is not possible,
     * return -1.
     *
     * A submatrix (x1, y1, x2, y2) is a matrix that forms by choosing all cells matrix[x][y] where x1 <= x <= x2 and
     * y1 <= y <= y2.
     *
     * Input: grid = [[3,3,5],[3,3,5]], k = 2
     * Output: 2
     *
     * Input: grid = [[1,2],[2,3]], k = 1
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= m == grid.length <= 1000
     * 1 <= n == grid[i].length <= 1000
     * -10^5 <= grid[i][j] <= 10^5
     * 1 <= k <= min(m, n)
     * @param grid
     * @param k
     * @return
     */
    // time = O(m * n), space = O(m * n)
    public long minOperations(int[][] grid, int k) {
        final long inf = (long)1E18;
        int m = grid.length, n = grid[0].length;
        long[][] baseOps = build(grid, k, 0);
        int[][] zero = new int[m][n];
        long[][] coefOps = build(zero, k, 1);
        long[][] baseInc = apply(baseOps, m, n, k);
        long[][] coefInc = apply(coefOps, m, n, k);

        long g0 = grid[0][0], fixedP = -inf;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                long coeff = coefInc[i][j] - 1;
                long rhs = g0 - grid[i][j] - baseInc[i][j];
                if (coeff == 0) {
                    if (rhs != 0) return -1;
                } else {
                    if (rhs % coeff != 0) return -1;
                    long p = rhs / coeff;
                    if (fixedP == -inf) fixedP = p;
                    else if (fixedP != p) return -1;
                }
            }
        }

        long p = 0;
        if (fixedP != -inf) p = fixedP;
        else {
            for (int i = 0; i < m - k + 1; i++) {
                for (int j = 0; j < n - k + 1; j++) {
                    long a = baseOps[i][j];
                    long b = coefOps[i][j];
                    if (b == 0) {
                        if (a < 0) return -1;
                    } else {
                        if (a < 0) {
                            long need = (-a + b - 1) / b;
                            if (need > p) p = need;
                        }
                    }
                }
            }
        }
        if (p < 0) return -1;

        long res = 0;
        for (int i = 0; i < m - k + 1; i++) {
            for (int j = 0; j < n - k + 1; j++) {
                long v = baseOps[i][j] + p * coefOps[i][j];
                if (v < 0) return -1;
                res += v;
            }
        }
        return res;
    }

    private long[][] build(int[][] grid, int k, long p) {
        int m = grid.length, n = grid[0].length;
        long[][] ops = new long[m - k + 1][n - k + 1];
        ops[0][0] = p;
        for (int i = 0; i < m - k + 1; i++) {
            for (int j = 0; j < n - k + 1; j++) {
                if (i == 0 && j == 0) continue;
                long val = 0;
                if (i > 0) val += grid[i - 1][j];
                if (j > 0) val += grid[i][j - 1];
                if (i > 0 && j > 0) val -= grid[i - 1][j - 1];
                val -= grid[i][j];
                if (i >= k) val += ops[i - k][j];
                if (j >= k) val += ops[i][j - k];
                if (i >= k && j >= k) val -= ops[i - k][j - k];
                ops[i][j] = val;
            }
        }
        return ops;
    }

    private long[][] apply(long[][] ops, int m, int n, int k) {
        long[][] diff = new long[m + 1][n + 1];
        for (int i = 0; i < ops.length; i++) {
            for (int j = 0; j < ops[0].length; j++) {
                long v = ops[i][j];
                if (v == 0) continue;
                diff[i][j] += v;
                diff[i + k][j] -= v;
                diff[i][j + k] -= v;
                diff[i + k][j + k] += v;
            }
        }

        long[][] inc = new long[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                long v = diff[i][j];
                if (i > 0) v += inc[i - 1][j];
                if (j > 0) v += inc[i][j - 1];
                if (i > 0 && j > 0) v -= inc[i - 1][j - 1];
                inc[i][j] = v;
            }
        }
        return inc;
    }
}