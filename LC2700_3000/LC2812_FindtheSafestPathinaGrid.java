package LC2700_3000;
import java.util.*;
public class LC2812_FindtheSafestPathinaGrid {
    /**
     * You are given a 0-indexed 2D matrix grid of size n x n, where (r, c) represents:
     *
     * A cell containing a thief if grid[r][c] = 1
     * An empty cell if grid[r][c] = 0
     * You are initially positioned at cell (0, 0). In one move, you can move to any adjacent cell in the grid,
     * including cells containing thieves.
     *
     * The safeness factor of a path on the grid is defined as the minimum manhattan distance from any cell in the path
     * to any thief in the grid.
     *
     * Return the maximum safeness factor of all paths leading to cell (n - 1, n - 1).
     *
     * An adjacent cell of cell (r, c), is one of the cells (r, c + 1), (r, c - 1), (r + 1, c) and (r - 1, c) if it
     * exists.
     *
     * The Manhattan distance between two cells (a, b) and (x, y) is equal to |a - x| + |b - y|, where |val| denotes the
     * absolute value of val.
     *
     * Input: grid = [[1,0,0],[0,0,0],[0,0,1]]
     * Output: 0
     *
     * Input: grid = [[0,0,1],[0,0,0],[0,0,0]]
     * Output: 2
     *
     * Input: grid = [[0,0,0,1],[0,0,0,0],[0,0,0,0],[1,0,0,0]]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= grid.length == n <= 400
     * grid[i].length == n
     * grid[i][j] is either 0 or 1.
     * There is at least one thief in the grid.
     * @param grid
     * @return
     */
    // S1
    // time = O(n^2*logn), space = O(n^2)
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size(), inf = 0x3f3f3f3f;
        if (grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1) return 0;
        Queue<int[]> q = new LinkedList<>();
        int[][] d = new int[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(d[i], inf);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    q.offer(new int[]{i, j});
                    d[i][j] = 0;
                }
            }
        }

        int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
        while (!q.isEmpty()) {
            int[] t = q.poll();
            int x = t[0], y = t[1];
            for (int i = 0; i < 4; i++) {
                int a = x + dx[i], b = y + dy[i];
                if (a < 0 || a >= n || b < 0 || b >= n) continue;
                if (grid.get(a).get(b) == 1) continue;
                if (d[a][b] != inf) continue;
                d[a][b] = d[x][y] + 1;
                q.offer(new int[]{a, b});
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        pq.offer(new int[]{d[0][0], 0, 0});
        boolean[][] st = new boolean[n][n];

        while (!pq.isEmpty()) {
            int[] t = pq.poll();
            int v = t[0], x = t[1], y = t[2];
            if (st[x][y]) continue;
            st[x][y] = true;
            if (x == n - 1 && y == n - 1) return v;

            for (int i = 0; i < 4; i++) {
                int a = x + dx[i], b = y + dy[i];
                if (a < 0 || a >= n || b < 0 || b >= n) continue;
                if (grid.get(a).get(b) == 1) continue;
                if (st[a][b]) continue;
                pq.offer(new int[]{Math.min(v, d[a][b]), a, b});
            }
        }
        return 0;
    }

    // S2
    // time = O(n^2*logn), space = O(n^2)
    int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
    public int maximumSafenessFactor2(List<List<Integer>> grid) {
        int n = grid.size();
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) q.offer(new int[]{i, j});
            }
        }

        while (!q.isEmpty()) {
            int[] t = q.poll();
            int x = t[0], y = t[1];
            for (int i = 0; i < 4; i++) {
                int a = x + dx[i], b = y + dy[i];
                if (a < 0 || a >= n || b < 0 || b >= n) continue;
                if (grid.get(a).get(b) != 0) continue;
                grid.get(a).set(b, grid.get(x).get(y) + 1);
                q.offer(new int[]{a, b});
            }
        }

        int l = 0, r = 2 * n;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (check(grid, mid)) l = mid;
            else r = mid - 1;
        }
        return r;
    }

    private boolean check(List<List<Integer>> g, int mid) {
        int n = g.size();
        if (g.get(0).get(0) <= mid) return false;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        boolean[][] st = new boolean[n][n];
        st[0][0] = true;

        while (!q.isEmpty()) {
            int[] t = q.poll();
            int x = t[0], y = t[1];
            if (x == n - 1 && y == n - 1) return true;
            for (int i = 0; i < 4; i++) {
                int a = x + dx[i], b = y + dy[i];
                if (a < 0 || a >= n || b < 0 || b >= n) continue;
                if (st[a][b]) continue;
                if (g.get(a).get(b) <= mid) continue;
                q.offer(new int[]{a, b});
                st[a][b] = true;
            }
        }
        return false;
    }
}
/**
 * grid[i][j]: the distance from (i,j) to its nearest thief + 1
 * 如何让d最大 => 猜一个d，看看能不能找出这样的一条路径来
 * 满足经过的格子 grid[i][j] > d
 * 我们预先处理grid，通过多源BFS，求出每个格子到离其最近的thief的距离grid[i][j]。
 * 为了便于处理grid里已经存在数值为1的格子，在这里我们填充grid[i][j]表示该点"离最近的thief的距离+1".
 * 然后我们二分搜值这个safety factor。
 * 假设是d，那么我们尝试寻找一条从左上到右下的通路，
 * 使得该路径不能包含有grid[i][j]<=d的格子，再走一次bfs即可判断。然后根据判断值，不断调整d的大小直至收敛。
 */