package LC3001_3300;
import java.util.*;
public class LC3286_FindaSafeWalkThroughaGrid {
    /**
     * You are given an m x n binary matrix grid and an integer health.
     *
     * You start on the upper-left corner (0, 0) and would like to get to the lower-right corner (m - 1, n - 1).
     *
     * You can move up, down, left, or right from one cell to another adjacent cell as long as your health remains
     * positive.
     *
     * Cells (i, j) with grid[i][j] = 1 are considered unsafe and reduce your health by 1.
     *
     * Return true if you can reach the final cell with a health value of 1 or more, and false otherwise.
     *
     * Input: grid = [[0,1,0,0,0],[0,1,0,1,0],[0,0,0,1,0]], health = 1
     * Output: true
     *
     * Input: grid = [[0,1,1,0,0,0],[1,0,1,0,0,0],[0,1,1,1,0,1],[0,0,1,0,1,0]], health = 3
     * Output: false
     *
     * Input: grid = [[1,1,1],[1,0,1],[1,1,1]], health = 5
     * Output: true
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 50
     * 2 <= m * n
     * 1 <= health <= m + n
     * grid[i][j] is either 0 or 1.
     * @param grid
     * @param health
     * @return
     */
    // S1: Dijkstra
    // time = O(m * n * log(m * n)), space = O(m * n)
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size(), n = grid.get(0).size();
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        if (health - grid.get(0).get(0) <= 0) return false;
        pq.offer(new int[]{health - grid.get(0).get(0), 0, 0});
        boolean[][] st = new boolean[m][n];

        int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
        while (!pq.isEmpty()) {
            int[] t = pq.poll();
            int h = t[0], x = t[1], y = t[2];
            if (st[x][y]) continue;
            st[x][y] = true;
            if (x == m - 1 && y == n - 1) return true;

            for (int i = 0; i < 4; i++) {
                int a = x + dx[i], b = y + dy[i];
                if (a < 0 || a >= m || b < 0 || b >= n) continue;
                if (st[a][b]) continue;
                if (h - grid.get(a).get(b) <= 0) continue;
                pq.offer(new int[]{h - grid.get(a).get(b), a, b});
            }
        }
        return false;
    }

    // S2: 0-1 bfs
    // time = O(m * n), space = O(m * n)
    public boolean findSafeWalk2(List<List<Integer>> grid, int health) {
        int m = grid.size(), n = grid.get(0).size(), inf = 0x3f3f3f3f;
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(dist[i], inf);
        dist[0][0] = grid.get(0).get(0);
        Deque<int[]> dq = new LinkedList<>();
        dq.offerLast(new int[]{0, 0});

        int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
        while (!dq.isEmpty()) {
            int[] t = dq.pollFirst();
            int x = t[0], y = t[1];

            for (int i = 0; i < 4; i++) {
                int a = x + dx[i], b = y + dy[i];
                if (a < 0 || a >= m || b < 0 || b >= n) continue;
                int v = grid.get(a).get(b);
                if (dist[a][b] > dist[x][y] + v) {
                    dist[a][b] = dist[x][y] + v;
                    if (v == 0) dq.offerFirst(new int[]{a, b});
                    else dq.offerLast(new int[]{a, b});
                }
            }
        }
        return dist[m - 1][n - 1] < health;
    }
}