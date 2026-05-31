package LC3901_4200;

public class LC3938_MaximumPathIntersectionSuminaGrid {
    /**
     * You are given an m x n integer matrix grid.
     *
     * Two players move across the grid:
     *
     * Player 1 starts at the top-left cell (0, 0) and can move only right or down. Their destination is the
     * bottom-right cell (m - 1, n - 1).
     * Player 2 starts at the bottom-left cell (m - 1, 0) and can move only right or up. Their destination is the
     * top-right cell (0, n - 1).
     * Each player must choose a valid path from their respective starting cell to their destination.
     *
     * A cell is called shared if it belongs to both chosen paths.
     *
     * Return an integer denoting the maximum possible sum of values of all shared cells.
     *
     * Input: grid = [[1,2,0,-3],[1,-2,1,0],[-4,2,-1,3],[3,-3,3,-2],[-1,-5,0,1]]
     * Output: 4
     *
     * Input: grid = [[4,-2,-3],[-1,-3,-1],[-4,2,-1]]
     * Output: 3
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 2 <= m, n <= 1000
     * 4 <= m * n <= 5 * 10^5
     * -100 <= grid[i][j] <= 100
     * @param grid
     * @return
     */
    // S1：DP
    // time = O(m * n), space = O(1)
    public int maxScore(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int res = Integer.MIN_VALUE;

        // 每一行：最大连续子数组和，长度至少为 2
        for (int i = 0; i < m; i++) {
            int best = grid[i][0]; // 长度至少为 1，且以 j-1 结尾
            for (int j = 1; j < n; j++) {
                int x = grid[i][j];
                res = Math.max(res, best + x);
                best = Math.max(x, best + x);
            }
        }
        // 每一列：最大连续子数组和，长度至少为 2
        for (int j = 0; j < n; j++) {
            int best = grid[0][j];
            for (int i = 1; i < m; i++) {
                int x = grid[i][j];
                res = Math.max(res, best + x);
                best = Math.max(x, best + x);
            }
        }
        // 严格内部的单格也可以作为唯一共享格
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                res = Math.max(res, grid[i][j]);
            }
        }
        return res;
    }

    // S2: 前缀和 (更一般的做法)
    // time = O(m * n), space = O(m + n)
    public int maxScore2(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] sr = new int[n + 1], sc = new int[m + 1];
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sr[j + 1] = sr[j] + grid[i][j];
            }
            int l1 = 0, l2 = 0;
            for (int c = 1; c <= n; c++) {
                if (c - l1 > 1) res = Math.max(res, sr[c] - sr[l1]);
                else if (c - l2 > 1) res = Math.max(res, sr[c] - sr[l2]);
                if (sr[c] < sr[l1]) {
                    l2 = l1;
                    l1 = c;
                }
            }
        }

        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                sc[i + 1] = sc[i] + grid[i][j];
            }
            int l1 = 0, l2 = 0;
            for (int r = 1; r <= m; r++) {
                if (r - l1 > 1) res = Math.max(res, sc[r] - sc[l1]);
                else if (r - l2 > 1) res = Math.max(res, sc[r] - sc[l2]);
                if (sc[r] < sc[l1]) {
                    l2 = l1;
                    l1 = r;
                }
            }
        }

        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                res = Math.max(res, grid[i][j]);
            }
        }
        return res;
    }
}