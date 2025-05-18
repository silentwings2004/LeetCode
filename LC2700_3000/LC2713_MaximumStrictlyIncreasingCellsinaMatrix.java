package LC2700_3000;
import java.util.*;
public class LC2713_MaximumStrictlyIncreasingCellsinaMatrix {
    /**
     * Given a 1-indexed m x n integer matrix mat, you can select any cell in the matrix as your starting cell.
     *
     * From the starting cell, you can move to any other cell in the same row or column, but only if the value of the
     * destination cell is strictly greater than the value of the current cell. You can repeat this process as many
     * times as possible, moving from cell to cell until you can no longer make any moves.
     *
     * Your task is to find the maximum number of cells that you can visit in the matrix by starting from some cell.
     *
     * Return an integer denoting the maximum number of cells that can be visited.
     *
     * Input: mat = [[3,1],[3,4]]
     * Output: 2
     *
     * Input: mat = [[3,1,6],[-9,5,7]]
     * Output: 4
     *
     * Constraints:
     *
     * m == mat.length
     * n == mat[i].length
     * 1 <= m, n <= 10^5
     * 1 <= m * n <= 10^5
     * -10^5 <= mat[i][j] <= 10^5
     * @param mat
     * @return
     */
    // S1
    // time = O(m * n * log(m * n)), space = O(m * n)
    int[][] g;
    int m, n;
    int[][] dist;
    TreeMap<Integer, List<Integer>>[] r, c;
    public int maxIncreasingCells(int[][] mat) {
        g = mat;
        m = g.length;
        n = g[0].length;
        dist = new int[m][n];

        r = new TreeMap[m];
        c = new TreeMap[n];
        for (int i = 0; i < m; i++) r[i] = new TreeMap<>();
        for (int j = 0; j < n; j++) c[j] = new TreeMap<>();
        init();

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, dfs(i, j));
            }
        }
        return res;
    }

    private int dfs(int x, int y) {
        if (dist[x][y] != 0) return dist[x][y];

        dist[x][y] = 1;
        int t = 0;
        Integer rk = r[x].higherKey(g[x][y]), ck = c[y].higherKey(g[x][y]);
        if (rk != null) {
            for (int j : r[x].get(rk)) t = Math.max(t, dfs(x, j));
        }
        if (ck != null) {
            for (int i : c[y].get(ck)) t = Math.max(t, dfs(i, y));
        }
        dist[x][y] += t;
        return dist[x][y];
    }

    private void init() {
        for (int i = 0; i < m; i++) {
            int[][] w = new int[n][2];
            for (int j = 0; j < n; j++) {
                w[j] = new int[]{g[i][j], j};
            }
            Arrays.sort(w, (o1, o2) -> o1[0] - o2[0]);
            for (int j = 0; j < n; j++) {
                r[i].putIfAbsent(w[j][0], new ArrayList<>());
                r[i].get(w[j][0]).add(w[j][1]);
            }
        }

        for (int j = 0; j < n; j++) {
            int[][] w = new int[m][2];
            for (int i = 0; i < m; i++) {
                w[i] = new int[]{g[i][j], i};
            }
            Arrays.sort(w, (o1, o2) -> o1[0] - o2[0]);
            for (int i = 0; i < m; i++) {
                c[j].putIfAbsent(w[i][0], new ArrayList<>());
                c[j].get(w[i][0]).add(w[i][1]);
            }
        }
    }

    // S2: DP
    // time = O(m * n * log(m * n)), space = O(m * n)
    public int maxIncreasingCells2(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        List<int[]> q = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                q.add(new int[]{mat[i][j], i, j});
            }
        }
        Collections.sort(q, (o1, o2) -> o1[0] - o2[0]);
        TreeMap<Integer, Integer>[] rows = new TreeMap[m];
        TreeMap<Integer, Integer>[] cols = new TreeMap[n];
        for (int i = 0; i < m; i++) rows[i] = new TreeMap<>();
        for (int j = 0; j < n; j++) cols[j] = new TreeMap<>();

        int res = 1;
        for (int[] x : q) {
            int val = x[0], i = x[1], j = x[2];
            int len = 1;
            Integer lk = rows[i].lowerKey(val);
            if (lk != null) len = Math.max(len, rows[i].getOrDefault(lk, 0) + 1);

            lk = cols[j].lowerKey(val);
            if (lk != null) len = Math.max(len, cols[j].getOrDefault(lk, 0) + 1);

            rows[i].put(val, Math.max(len, rows[i].getOrDefault(val, 0)));
            cols[j].put(val, Math.max(len, cols[j].getOrDefault(val, 0)));
            res = Math.max(res, len);
        }
        return res;
    }
}