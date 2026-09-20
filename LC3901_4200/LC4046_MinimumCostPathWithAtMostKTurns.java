package LC3901_4200;
import java.util.*;
public class LC4046_MinimumCostPathWithAtMostKTurns {
    /**
     * You are given a 2D integer array grid of size m x n, where grid[i][j] represents the cost of visiting cell
     * (i, j), and an integer k.
     *
     * You start at the top-left cell (0, 0) and want to reach the bottom-right cell (m - 1, n - 1).
     *
     * From each cell, you may move one step in any of the four directions: up, down, left, or right.
     *
     * The cost of a path is the sum of the values of all visited cells, including the starting and ending cells. If
     * a cell is visited more than once, its value is included each time it is visited.
     *
     * Return the minimum possible path cost to reach (m - 1, n - 1) using at most k turns. If no such path exists,
     * return -1.
     *
     * A turn occurs when the direction changes between two consecutive moves. For example, moving right and then down
     * counts as one turn, while moving right and then right does not.
     *
     * Input: grid = [[2,7,3],[1,4,5]], k = 1
     * Output: 12
     *
     * Input: grid = [[4,1,9],[3,2,5],[4,8,6]], k = 2
     * Output: 20
     *
     * Input: grid = [[1,9],[3,4]], k = 0
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= m == grid.length <= 75
     * 1 <= n == grid[i].length <= 75
     * 0 <= grid[i][j] <= 1000
     * 0 <= k < min(m, n)
     * @param grid
     * @param k
     * @return
     */
    // time = O(m * n * k * log(m * n * k)), space = O(m * n * k)
    public int minCost(int[][] grid, int k) {
        final int inf = 0x3f3f3f3f;
        int m = grid.length, n = grid[0].length;
        int[][][][] dist = new int[m][n][5][k + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int d = 0; d < 5; d++) {
                    Arrays.fill(dist[i][j][d], inf);
                }
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[4] - o2[4]); // {x, y, dir, turn, cost}
        pq.offer(new int[]{0, 0, 4, 0, grid[0][0]});
        dist[0][0][4][0] = grid[0][0];

        int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
        while (!pq.isEmpty()) {
            int[] t = pq.poll();
            int x = t[0], y = t[1], dir = t[2], turn = t[3], cost = t[4];
            if (dist[x][y][dir][turn] != cost) continue;
            if (x == m - 1 && y == n - 1) return cost;

            for (int i = 0; i < 4; i++) {
                int a = x + dx[i], b = y + dy[i];
                if (a < 0 || a >= m || b < 0 || b >= n) continue;
                int new_turn = dir == 4 || dir == i ? turn : turn + 1;
                if (new_turn > k) continue;
                int new_cost = cost + grid[a][b];
                if (dist[a][b][i][new_turn] > new_cost) {
                    dist[a][b][i][new_turn] = new_cost;
                    pq.offer(new int[]{a, b, i, new_turn, new_cost});
                }
            }
        }
        return -1;
    }
}