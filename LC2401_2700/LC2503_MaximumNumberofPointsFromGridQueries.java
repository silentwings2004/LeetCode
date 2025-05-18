package LC2401_2700;
import java.util.*;
public class LC2503_MaximumNumberofPointsFromGridQueries {
    /**
     * You are given an m x n integer matrix grid and an array queries of size k.
     *
     * Find an array answer of size k such that for each integer queres[i] you start in the top left cell of the matrix
     * and repeat the following process:
     *
     * If queries[i] is strictly greater than the value of the current cell that you are in, then you get one point if
     * it is your first time visiting this cell, and you can move to any adjacent cell in all 4 directions: up, down,
     * left, and right.
     * Otherwise, you do not get any points, and you end this process.
     * After the process, answer[i] is the maximum number of points you can get. Note that for each query you are
     * allowed to visit the same cell multiple times.
     *
     * Return the resulting array answer.
     *
     * Input: grid = [[1,2,3],[2,5,7],[3,5,1]], queries = [5,6,2]
     * Output: [5,8,1]
     *
     * Input: grid = [[5,2,1],[1,1,2]], queries = [3]
     * Output: [0]
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 2 <= m, n <= 1000
     * 4 <= m * n <= 10^5
     * k == queries.length
     * 1 <= k <= 10^4
     * 1 <= grid[i][j], queries[i] <= 10^6
     * @param grid
     * @param queries
     * @return
     */
    // S1: PQ
    // time = O(m * n * log(m * n)), space = O(m * n)
    private int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public int[] maxPoints(int[][] grid, int[] queries) {
        int m = grid.length, n = grid[0].length;
        int len = queries.length;
        int[][] qs = new int[len][2];
        for (int i = 0; i < len; i++) qs[i] = new int[]{queries[i], i};
        Arrays.sort(qs, (o1, o2) -> o1[0] - o2[0]);

        int[] res = new int[len];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]); // {grid value, x, y};
        boolean[][] st = new boolean[m][n];
        pq.offer(new int[]{grid[0][0], 0, 0});
        st[0][0] = true;
        int cnt = 0;

        for (int k = 0; k < len; k++) {
            int q = qs[k][0], idx = qs[k][1];
            while (!pq.isEmpty() && pq.peek()[0] < q) {
                int[] t = pq.poll();
                int i = t[1], j = t[2];
                cnt++;

                for (int[] dir : directions) {
                    int x = i + dir[0];
                    int y = j + dir[1];
                    if (x < 0 || x >= m || y < 0 || y >= n) continue;
                    if (st[x][y]) continue;

                    pq.offer(new int[]{grid[x][y], x, y});
                    st[x][y] = true;
                }
            }
            res[idx] = cnt;
        }
        return res;
    }

    // S2: Union Find
    // time = O(m * n * log(m * n)), space = O(m * n)
    int n, m;
    int[] p, sz;
    public int[] maxPoints2(int[][] grid, int[] queries) {
        m = grid.length;
        n = grid[0].length;
        p = new int[m * n];
        sz = new int[m * n];
        Integer[] pos = new Integer[queries.length];
        for (int i = 0; i < queries.length; i++) pos[i] = i;
         Arrays.sort(pos, (o1, o2) -> queries[o1] - queries[o2]);

        int[][] node = new int[m * n][3];
        for (int i = 0, k = 0; i < m; i++) {
            for (int j = 0; j < n; j++, k++) {
                node[k] = new int[]{grid[i][j], i, j};
            }
        }
        Arrays.sort(node, (o1, o2) -> o1[0] - o2[0]);

        for (int i = 0; i < m * n; i++) {
            p[i] = i;
            sz[i] = 1;
        }

        int[] res = new int[queries.length];
        int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
        int i = 0;
        for (int k : pos) {
            int query = queries[k];
            while (i < m * n && node[i][0] < query) {
                int x = node[i][1], y = node[i][2];
                i++;

                for (int j = 0; j < 4; j++) {
                    int a = x + dx[j], b = y + dy[j];
                    if (a < 0 || a >= m || b < 0 || b >= n) continue;
                    if (grid[a][b] >= query) continue;
                    int u = get(x, y), v = get(a, b);
                    u = find(u);
                    v = find(v);
                    if (u != v) {
                        sz[v] += sz[u];
                        p[u] = v;
                    }
                }
            }
            if (grid[0][0] < query) res[k] = sz[find(0)];
        }
        return res;
    }

    private int get(int x, int y) {
        return x * n + y;
    }

    private int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }
}
/**
 * 连通块2种算法：
 * 1. flood fill: bfs / dfs
 * 2. 并查集
 * 连通块逐步扩展，而不会减小
 *
 * BFS => queue -> pq
 * q [x x x x x x]
 * ref: LC778
 */