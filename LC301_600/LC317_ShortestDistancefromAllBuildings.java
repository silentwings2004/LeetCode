package LC301_600;
import java.util.*;
public class LC317_ShortestDistancefromAllBuildings {
    /**
     * You are given an m x n grid grid of values 0, 1, or 2, where:
     *
     * each 0 marks an empty land that you can pass by freely,
     * each 1 marks a building that you cannot pass through, and
     * each 2 marks an obstacle that you cannot pass through.
     * You want to build a house on an empty land that reaches all buildings in the shortest total travel distance.
     * You can only move up, down, left, and right.
     *
     * Return the shortest travel distance for such a house. If it is not possible to build such a house according to
     * the above rules, return -1.
     *
     * The total travel distance is the sum of the distances between the houses of the friends and the meeting point.
     *
     * The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
     *
     * Input: grid = [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
     * Output: 7
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 100
     * grid[i][j] is either 0, 1, or 2.
     * There will be at least one building in the grid.
     * @param grid
     * @return
     */
    // time = O(m^2 * n^2), space = O(m * n)
    final int inf = 0x3f3f3f3f;
    int[][] grid, dist, cnt;
    int m, n;
    public int shortestDistance(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        dist = new int[m][n];
        cnt = new int[m][n];
        int tot = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    tot++;
                    bfs(i, j);
                }
            }
        }

        int res = inf;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && cnt[i][j] == tot) {
                    res = Math.min(res, dist[i][j]);
                }
            }
        }
        return res == inf ? -1 : res;
    }

    private void bfs(int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, i, j});
        boolean[][] st = new boolean[m][n];
        st[i][j] = true;
        int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
        while (!q.isEmpty()) {
            int sz = q.size();
            while (sz-- > 0) {
                int[] t = q.poll();
                int d = t[0], x = t[1], y = t[2];
                for (int u = 0; u < 4; u++) {
                    int a = x + dx[u], b = y + dy[u];
                    if (a < 0 || a >= m || b < 0 || b >= n) continue;
                    if (grid[a][b] != 0) continue;
                    if (st[a][b]) continue;
                    dist[a][b] += d + 1;
                    cnt[a][b]++;
                    st[a][b] = true;
                    q.offer(new int[]{d + 1, a, b});
                }
            }
        }
    }
}