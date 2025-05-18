package LC2401_2700;
import java.util.*;
public class LC2577_MinimumTimetoVisitaCellInaGrid {
    /**
     * You are given a m x n matrix grid consisting of non-negative integers where grid[row][col] represents the minimum
     * time required to be able to visit the cell (row, col), which means you can visit the cell (row, col) only when
     * the time you visit it is greater than or equal to grid[row][col].
     *
     * You are standing in the top-left cell of the matrix in the 0th second, and you must move to any adjacent cell in
     * the four directions: up, down, left, and right. Each move you make takes 1 second.
     *
     * Return the minimum time required in which you can visit the bottom-right cell of the matrix. If you cannot visit
     * the bottom-right cell, then return -1.
     *
     * Input: grid = [[0,1,3,2],[5,1,2,5],[4,3,8,6]]
     * Output: 7
     *
     * Input: grid = [[0,2,4],[3,2,1],[1,0,4]]
     * Output: -1
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 2 <= m, n <= 1000
     * 4 <= m * n <= 10^5
     * 0 <= grid[i][j] <= 10^5
     * grid[0][0] == 0
     * @param grid
     * @return
     */
    // time = O(m * n * log(m * n)), space = O(m * n)
    public int minimumTime(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        if (grid[0][1] <= 1) pq.offer(new int[]{1, 0, 1});
        if (grid[1][0] <= 1) pq.offer(new int[]{1, 1, 0});
        boolean[][] st = new boolean[m][n];

        int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
        while (!pq.isEmpty()) {
            int[] t = pq.poll();
            int time = t[0], x = t[1], y = t[2];
            if (x == m - 1 && y == n - 1) return time;
            if (st[x][y]) continue;
            st[x][y] = true;

            for (int i = 0; i < 4; i++) {
                int a = x + dx[i], b = y + dy[i];
                if (a < 0 || a >= m || b < 0 || b >= n) continue;
                if (st[a][b]) continue;
                if (time + 1 >= grid[a][b]) pq.offer(new int[]{time + 1, a, b});
                else {
                    int delta = grid[a][b] - time;
                    if (delta % 2 == 0) pq.offer(new int[]{grid[a][b] + 1, a, b});
                    else pq.offer(new int[]{grid[a][b], a, b});
                }
            }
        }
        return -1;
    }
}
/**
 * 朝4个方向走 => 不可能是dp，因为dp要求无后效性
 * 最短路 => Dijkstra bfs + pq
 *
 */