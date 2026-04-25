package LC3901_4200;
import java.util.*;
public class LC3905_MultiSourceFloodFill {
    /**
     * You are given two integers n and m representing the number of rows and columns of a grid, respectively.
     *
     * You are also given a 2D integer array sources, where sources[i] = [ri, ci, colori]
     * indicates that the cell (ri, ci) is initially colored with colori. All other cells are initially uncolored and
     * represented as 0.
     *
     * At each time step, every currently colored cell spreads its color to all adjacent uncolored cells in the four
     * directions: up, down, left, and right. All spreads happen simultaneously.
     *
     * If multiple colors reach the same uncolored cell at the same time step, the cell takes the color with the maximum
     * value.
     *
     * The process continues until no more cells can be colored.
     *
     * Return a 2D integer array representing the final state of the grid, where each cell contains its final color.
     *
     * Input: n = 3, m = 3, sources = [[0,0,1],[2,2,2]]
     * Output: [[1,1,2],[1,2,2],[2,2,2]]
     *
     * Input: n = 3, m = 3, sources = [[0,1,3],[1,1,5]]
     * Output: [[3,3,3],[5,5,5],[5,5,5]]
     *
     * Input: n = 2, m = 2, sources = [[1,1,5]]
     * Output: [[5,5],[5,5]]
     *
     * Constraints:
     *
     * 1 <= n, m <= 10^5
     * 1 <= n * m <= 10^5
     * 1 <= sources.length <= n * m
     * sources[i] = [ri, ci, colori]
     * 0 <= ri <= n - 1
     * 0 <= ci <= m - 1
     * 1 <= colori <= 10^6
     * All (ri, ci) in sources are distinct.
     * @param n
     * @param m
     * @param sources
     * @return
     */
    // S1: PQ
    // time = O(m * n * log(m * n)), space = O(m * n)
    public int[][] colorGrid(int n, int m, int[][] sources) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o2[1] - o1[1]);
        for (int[] s : sources) {
            int x = s[0], y = s[1], c = s[2];
            pq.offer(new int[]{0, c, x, y});
        }

        int[][] res = new int[n][m];
        int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
        while (!pq.isEmpty()) {
            int[] t = pq.poll();
            int d = t[0], c = t[1], x = t[2], y = t[3];
            if (res[x][y] != 0) continue;
            res[x][y] = c;
            for (int i = 0; i < 4; i++) {
                int a = x + dx[i], b = y + dy[i];
                if (a < 0 || a >= n || b < 0 || b >= m) continue;
                if (res[a][b] != 0) continue;
                pq.offer(new int[]{d + 1, c, a, b});
            }
        }
        return res;
    }

    // S2: BFS
    // time = O(m * n), space = O(m * n)
    final int inf = 0x3f3f3f3f;
    public int[][] colorGrid2(int n, int m, int[][] sources) {
        int[][] g = new int[n][m];
        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) Arrays.fill(dist[i], inf);
        Queue<int[]> q = new LinkedList<>();
        for (int[] s : sources) {
            int x = s[0], y = s[1], c = s[2];
            g[x][y] = c;
            dist[x][y] = 0;
            q.offer(new int[]{x, y});
        }

        int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
        while (!q.isEmpty()) {
            int sz = q.size();
            while (sz-- > 0) {
                int[] t = q.poll();
                int x = t[0], y = t[1];
                int d = dist[x][y], c = g[x][y];
                for (int i = 0; i < 4; i++) {
                    int a = x + dx[i], b = y + dy[i];
                    if (a < 0 || a >= n || b < 0 || b >= m) continue;
                    if (dist[a][b] > d + 1) {
                        dist[a][b] = d + 1;
                        g[a][b] = c;
                        q.offer(new int[]{a, b});
                    } else if (dist[a][b] == d + 1) {
                        if (c > g[a][b]) g[a][b] = c;
                    }
                }
            }
        }
        return g;
    }
}