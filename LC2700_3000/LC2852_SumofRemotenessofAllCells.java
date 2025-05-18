package LC2700_3000;

public class LC2852_SumofRemotenessofAllCells {
    /**
     * You are given a 0-indexed matrix grid of order n * n. Each cell in this matrix has a value grid[i][j], which is
     * either a positive integer or -1 representing a blocked cell.
     *
     * You can move from a non-blocked cell to any non-blocked cell that shares an edge.
     *
     * For any cell (i, j), we represent its remoteness as R[i][j] which is defined as the following:
     *
     * If the cell (i, j) is a non-blocked cell, R[i][j] is the sum of the values grid[x][y] such that there is no path
     * from the non-blocked cell (x, y) to the cell (i, j).
     * For blocked cells, R[i][j] == 0.
     * Return the sum of R[i][j] over all cells.
     *
     * Input: grid = [[-1,1,-1],[5,-1,4],[-1,3,-1]]
     * Output: 39
     *
     * Input: grid = [[-1,3,4],[-1,-1,-1],[3,-1,-1]]
     * Output: 13
     *
     * Input: grid = [[1]]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= n <= 300
     * 1 <= grid[i][j] <= 10^6 or grid[i][j] == -1
     * @param grid
     * @return
     */
    // time = O(n^2 * logn), space = O(n^2)
    int[] p, sz;
    long[] s;
    boolean[][] st;
    int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
    int[][] grid;
    int n;
    public long sumRemoteness(int[][] grid) {
        this.grid = grid;
        n = grid.length;
        p = new int[n * n];
        sz = new int[n * n];
        s = new long[n * n];
        for (int i = 0; i < n * n; i++) {
            p[i] = i;
            sz[i] = 1;
        }

        st = new boolean[n][n];
        long sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum += Math.max(0, grid[i][j]);
                if (grid[i][j] == -1 || st[i][j]) continue;
                dfs(i, j);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == -1) continue;
                int fa = find(i * n + j);
                s[fa] += grid[i][j];
            }
        }

        long res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == -1) continue;
                res += sum - s[find(i * n + j)];
            }
        }
        return res;
    }

    private void dfs(int x, int y) {
        st[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int a = x + dx[i], b = y + dy[i];
            if (a < 0 || a >= n || b < 0 || b >= n) continue;
            if (grid[a][b] == -1) continue;
            if (st[a][b]) continue;
            st[a][b] = true;
            int u = x * n + y, v = a * n + b;
            if (find(u) != find(v)) p[find(u)] = find(v);
            dfs(a, b);
        }
    }

    private int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }
}