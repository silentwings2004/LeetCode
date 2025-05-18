package LC901_1200;
import java.util.*;
public class LC934_ShortestBridge {
    /**
     * You are given an n x n binary matrix grid where 1 represents land and 0 represents water.
     *
     * An island is a 4-directionally connected group of 1's not connected to any other 1's. There are exactly two
     * islands in grid.
     *
     * You may change 0's to 1's to connect the two islands to form one island.
     *
     * Return the smallest number of 0's you must flip to connect the two islands.
     *
     * Input: grid = [[0,1],[1,0]]
     * Output: 1
     *
     * Input: grid = [[0,1,0],[0,0,0],[0,0,1]]
     * Output: 2
     *
     * Input: grid = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
     * Output: 1
     *
     * Constraints:
     *
     * n == grid.length == grid[i].length
     * 2 <= n <= 100
     * grid[i][j] is either 0 or 1.
     * There are exactly two islands in grid.
     * @param grid
     * @return
     */
    int[][] g, dist;
    int n;
    int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
    Queue<int[]> queue;
    public int shortestBridge(int[][] grid) {
        g = grid;
        n = grid.length;
        dist = new int[n][n];
        for (int[] x : dist) Arrays.fill(x, (int) 1e8);
        queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] == 1) {
                    dfs(i, j);
                    return bfs();
                }
            }
        }
        return -1;
    }

    private void dfs(int x, int y) {
        g[x][y] = 0;
        dist[x][y] = 0;
        queue.offer(new int[]{x, y});
        for (int i = 0; i < 4; i++) {
            int a = x + dx[i], b = y + dy[i];
            if (a >= 0 && a < n && b >= 0 && b < n && g[a][b] == 1) {
                dfs(a, b);
            }
        }
    }

    private int bfs() {
        while (!queue.isEmpty()) {
            int[] t = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = t[0] + dx[i], y = t[1] + dy[i];
                if (x >= 0 && x < n && y >= 0 && y < n && dist[x][y] > dist[t[0]][t[1]] + 1) {
                    dist[x][y] = dist[t[0]][t[1]] + 1;
                    if (g[x][y] == 1) return dist[x][y] - 1;
                    queue.offer(new int[]{x, y});
                }
            }
        }
        return -1;
    }
}
/**
 * 求2个连通块之间的最短距离 =>
 * 相当于求一个块里的所有点到另一个块的最短距离
 * 第一个块的点作为起点，第二个块的点作为终点 => 多源最短路问题
 */