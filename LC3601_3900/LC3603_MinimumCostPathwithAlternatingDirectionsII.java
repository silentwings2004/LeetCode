package LC3601_3900;
import java.util.*;
public class LC3603_MinimumCostPathwithAlternatingDirectionsII {
    /**
     * You are given two integers m and n representing the number of rows and columns of a grid, respectively.
     *
     * The cost to enter cell (i, j) is defined as (i + 1) * (j + 1).
     *
     * You are also given a 2D integer array waitCost where waitCost[i][j] defines the cost to wait on that cell.
     *
     * You start at cell (0, 0) at second 1.
     *
     * At each step, you follow an alternating pattern:
     *
     * On odd-numbered seconds, you must move right or down to an adjacent cell, paying its entry cost.
     * On even-numbered seconds, you must wait in place, paying waitCost[i][j].
     * Return the minimum total cost required to reach (m - 1, n - 1).
     *
     * Input: m = 1, n = 2, waitCost = [[1,2]]
     * Output: 3
     *
     * Input: m = 2, n = 2, waitCost = [[3,5],[2,4]]
     * Output: 9
     *
     * Input: m = 2, n = 3, waitCost = [[6,1,4],[3,2,5]]
     * Output: 16
     *
     * Constraints:
     *
     * 1 <= m, n <= 10^5
     * 2 <= m * n <= 10^5
     * waitCost.length == m
     * waitCost[0].length == n
     * 0 <= waitCost[i][j] <= 10^5
     * @param m
     * @param n
     * @param waitCost
     * @return
     */
    // S1: Dijkstra
    // time = O(m * n * log(m * n)), space = O(m * n)
    public long minCost(int m, int n, int[][] waitCost) {
        PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1[0], o2[0]));
        boolean[][][] st = new boolean[m][n][2];
        pq.offer(new long[]{1, 1, 0, 0});

        int[] dx = new int[]{0, 1}, dy = new int[]{1, 0};
        while (!pq.isEmpty()) {
            long[] t = pq.poll();
            long cost = t[0];
            int ts = (int)t[1], x = (int)t[2], y = (int)t[3];
            if (st[x][y][ts]) continue;
            st[x][y][ts] = true;
            if (x == m - 1 && y == n - 1) return cost;

            int nt = (ts + 1) % 2;
            if (ts % 2 == 0) {
                long nc = cost + waitCost[x][y];
                if (st[x][y][nt]) continue;
                pq.offer(new long[]{nc, nt, x, y});
            } else {
                for (int i = 0; i < 2; i++) {
                    int a = x + dx[i], b = y + dy[i];
                    if (a < 0 || a >= m || b < 0 || b >= n) continue;
                    if (st[a][b][nt]) continue;
                    long nc = cost + 1L * (a + 1) * (b + 1);
                    pq.offer(new long[]{nc, nt, a, b});
                }
            }
        }
        return -1;
    }

    // S2: DP
    // time = O(m * n), space = O(m * n)
    public long minCost2(int m, int n, int[][] waitCost) {
        long[][] f = new long[m][n];
        f[0][0] = 1;
        for (int j = 1; j < n; j++) {
            f[0][j] = f[0][j - 1] + (j == 1 ? 0 : waitCost[0][j - 1]) + (j + 1);
        }
        for (int i = 1; i < m; i++) {
            f[i][0] = f[i - 1][0] + (i == 1 ? 0 : waitCost[i - 1][0]) + (i + 1);
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                f[i][j] = Math.min(f[i - 1][j] + waitCost[i - 1][j], f[i][j - 1] + waitCost[i][j - 1]) + (i + 1) * (j + 1);
            }
        }
        return f[m - 1][n - 1];
    }
}