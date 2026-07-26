package LC3901_4200;
import java.util.*;
public class LC4003_MinimumCostPathwithAlternatingDirectionsIII {
    /**
     * You are given two integers m and n representing the number of rows and columns of a grid. Your goal is to reach
     * cell (m - 1, n - 1). You are also given a 2D integer array penalty.
     *
     * The cost to enter cell (i, j) is (i + 1) * (j + 1).
     *
     * You begin at cell (0, 0) and initially pay its entrance cost. Actions performed after entering (0, 0) are
     * numbered starting from 1.
     *
     * On each action, you may move to an adjacent cell or wait in the current cell. A move follows the parity rule if:
     *
     * On an odd-numbered action, you move right or down.
     * On an even-numbered action, you move left or up.
     * The cost of an action is determined as follows:
     *
     * If you move according to the parity rule, pay only the entrance cost of the destination cell.
     * If you move in a direction that violates the parity rule, pay the entrance cost of the destination cell plus
     * penalty[i][j], where (i, j) is the cell you move from.
     * If you wait in cell (i, j), pay penalty[i][j].
     * After every move or wait, the action number increases by 1. Therefore, the required parity alternates after every
     * action, regardless of whether a penalty was paid.
     *
     * Return the minimum total cost required to reach (m - 1, n - 1).
     *
     * Input: m = 2, n = 2, penalty = [[5,3],[1,4]]
     * Output: 8
     *
     * Input: m = 2, n = 2, penalty = [[0,7],[3,2]]
     * Output: 7
     *
     * Input: m = 2, n = 3, penalty = [[8,0,9],[7,4,1]]
     * Output: 12
     *
     * Constraints:
     *
     * 1 <= m, n <= 10^5
     * 2 <= m * n <= 10^5
     * penalty.length == m
     * penalty[i].length == n
     * 0 <= penalty[i][j] <= 10^5
     * @param m
     * @param n
     * @param penalty
     * @return
     */
    // time = O(m * n * log(m * n)), space = O(m * n)
    final long inf = (long)1E18;
    int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
    public long minCost(int m, int n, int[][] penalty) {
        long[][][] dist = new long[m][n][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 2; k++) {
                    dist[i][j][k] = inf;
                }
            }
        }

        PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1[3], o2[3]));
        pq.offer(new long[]{0, 0, 0, 1});
        dist[0][0][0] = 1;

        while (!pq.isEmpty()) {
            long[] t = pq.poll();
            int x = (int)t[0], y = (int)t[1], p = (int)t[2];
            long cost = t[3];
            if (cost != dist[x][y][p]) continue;

            // wait
            long nc = cost + penalty[x][y];
            if (nc < dist[x][y][p ^ 1]) {
                dist[x][y][p ^ 1] = nc;
                pq.offer(new long[]{x, y, p ^ 1, nc});
            }

            // move
            boolean isOdd = p == 0;
            for (int i = 0; i < 4; i++) {
                int a = x + dx[i], b = y + dy[i];
                if (a < 0 || a >= m || b < 0 || b >= n) continue;
                boolean ok = isOdd ? i == 1 || i == 2 : i == 0 || i == 3;
                nc = cost + 1L * (a + 1) * (b + 1) + (ok ? 0 : penalty[x][y]);
                if (nc < dist[a][b][p ^ 1]) {
                    dist[a][b][p ^ 1] = nc;
                    pq.offer(new long[]{a, b, p ^ 1, nc});
                }
            }
        }
        return Math.min(dist[m - 1][n - 1][0], dist[m - 1][n - 1][1]);
    }
}