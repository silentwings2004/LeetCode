package LC3601_3900;
import java.util.*;
public class LC3651_MinimumCostPathwithTeleportations {
    /**
     * You are given a m x n 2D integer array grid and an integer k. You start at the top-left cell (0, 0) and your goal
     * is to reach the bottom‐right cell (m - 1, n - 1).
     *
     * There are two types of moves available:
     *
     * Normal move: You can move right or down from your current cell (i, j), i.e. you can move to (i, j + 1) (right) or
     * (i + 1, j) (down). The cost is the value of the destination cell.
     *
     * Teleportation: You can teleport from any cell (i, j), to any cell (x, y) such that grid[x][y] <= grid[i][j]; the
     * cost of this move is 0. You may teleport at most k times.
     *
     * Return the minimum total cost to reach cell (m - 1, n - 1) from (0, 0).
     *
     * Input: grid = [[1,3,3],[2,5,4],[4,3,5]], k = 2
     * Output: 7
     *
     * Input: grid = [[1,2],[2,3],[3,4]], k = 1
     * Output: 9
     *
     * Constraints:
     *
     * 2 <= m, n <= 80
     * m == grid.length
     * n == grid[i].length
     * 0 <= grid[i][j] <= 104
     * 0 <= k <= 10
     * @param grid
     * @param k
     * @return
     */
    // S1
    // time = O(m * n * k + log(m * n)), space = O(m * n * k)
    public int minCost(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        long inf = (long)1E18;

        long[][][] f = new long[m][n][k + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(f[i][j], inf);
            }
        }
        f[0][0][0] = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i + 1 < m) f[i + 1][j][0] = Math.min(f[i + 1][j][0], f[i][j][0] + grid[i + 1][j]);
                if (j + 1 < n) f[i][j + 1][0] = Math.min(f[i][j + 1][0], f[i][j][0] + grid[i][j + 1]);
            }
        }

        TreeMap<Integer, List<int[]>> map = new TreeMap<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map.putIfAbsent(grid[i][j], new ArrayList<>());
                map.get(grid[i][j]).add(new int[]{i, j});
            }
        }

        for (int u = 1; u <= k; u++) {
            long minv = inf;
            for (List<int[]> v : map.values()) {
                for (int[] x : v) minv = Math.min(minv, f[x[0]][x[1]][u - 1]);
                for (int[] x : v) f[x[0]][x[1]][u] = minv;
            }

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i > 0) f[i][j][u] = Math.min(f[i][j][u], f[i - 1][j][u] + grid[i][j]);
                    if (j > 0) f[i][j][u] = Math.min(f[i][j][u], f[i][j - 1][u] + grid[i][j]);
                }
            }
        }

        long res = inf;
        for (int i = 0; i <= k; i++) res = Math.min(res, f[m - 1][n - 1][i]);
        return (int)res;
    }

    // S2
    // time = O((mn + U) * k), space = O(n + U)
    public int minCost2(int[][] grid, int k) {
        final int inf = 0x3f3f3f3f;
        int m = grid.length, n = grid[0].length;
        if (k > 0 && grid[0][0] >= grid[m - 1][n - 1]) return 0;

        int mx = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mx = Math.max(mx, grid[i][j]);
            }
        }

        int[] suf = new int[mx + 2];
        Arrays.fill(suf, inf);
        int[] minf = new int[mx + 1];
        int[] f = new int[n + 1];

        for (int t = 0; t <= k; t++) {
            Arrays.fill(minf, inf);
            Arrays.fill(f, inf);
            f[1] = -grid[0][0];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int x = grid[i][j];
                    f[j + 1] = Math.min(Math.min(f[j], f[j + 1]) + x, suf[x]);
                    minf[x] = Math.min(minf[x], f[j + 1]);
                }
            }

            boolean flag = true;
            for (int i = mx; i >= 0; i--) {
                int mn = Math.min(suf[i + 1], minf[i]);
                if (mn < suf[i]) {
                    suf[i] = mn;
                    flag = false;
                }
            }
            if (flag) break;
        }
        return f[n];
    }
}