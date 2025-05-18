package LC901_1200;
import java.util.*;
public class LC994_RottingOranges {
    /**
     * You are given an m x n grid where each cell can have one of three values:
     *
     * 0 representing an empty cell,
     * 1 representing a fresh orange, or
     * 2 representing a rotten orange.
     * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
     *
     * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible,
     * return -1.
     *
     * Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
     * Output: 4
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 10
     * grid[i][j] is 0, 1, or 2.
     * @param grid
     * @return
     */
    // time = O(m * n), space = O(m * n)
    int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int cnt = 0;
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) q.offer(new int[]{i, j});
                else if (grid[i][j] == 1) cnt++;
            }
        }
        if (cnt == 0) return 0;

        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int[] t = q.poll();
                int x = t[0], y = t[1];
                for (int i = 0; i < 4; i++) {
                    int a = x + dx[i], b = y + dy[i];
                    if (a < 0 || a >= m || b < 0 || b >= n) continue;
                    if (grid[a][b] != 1) continue;
                    grid[a][b] = 2;
                    cnt--;
                    q.offer(new int[]{a, b});
                }
            }
            step++;
        }
        return cnt > 0 ? -1 : step - 1;
    }
}