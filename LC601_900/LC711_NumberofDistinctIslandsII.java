package LC601_900;
import java.util.*;
public class LC711_NumberofDistinctIslandsII {
    /**
     * You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected
     * 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
     *
     * An island is considered to be the same as another if they have the same shape, or have the same shape after
     * rotation (90, 180, or 270 degrees only) or reflection (left/right direction or up/down direction).
     *
     * Return the number of distinct islands.
     *
     * Input: grid = [[1,1,0,0,0],[1,0,0,0,0],[0,0,0,0,1],[0,0,0,1,1]]
     * Output: 1
     *
     * Input: grid = [[1,1,0,0,0],[1,1,0,0,0],[0,0,0,1,1],[0,0,0,1,1]]
     * Output: 1
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 50
     * grid[i][j] is either 0 or 1.
     * @param grid
     * @return
     */
    // S1: Canonical Conversion
    // time = O(m * n * log(m * n)), space = O(m * n)
    int[][] g;
    int m, n;
    List<Integer> shape;
    int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
    public int numDistinctIslands2(int[][] grid) {
        g = grid;
        m = g.length;
        n = g[0].length;
        HashSet<String> set = new HashSet<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] == 1) {
                    shape = new ArrayList<>();
                    dfs(i, j);
                    if (shape.size() > 0) set.add(convert());
                }
            }
        }
        return set.size();
    }

    private String convert() {
        String res = "";
        int lift = m + n, len = shape.size();
        int[] out = new int[len];
        int[] xs = new int[len];
        int[] ys = new int[len];

        for (int c = 0; c < 8; c++) {
            int t = 0;
            for (int z : shape) {
                int x = z / n, y = z % n;
                // (x, y), (x, -y), (-x, y), (-x, -y)
                // (y, x), (y, -x), (-y, x), (-y, -x)
                xs[t] = c <= 1 ? x : (c <= 3 ? -x : (c <= 5 ? y : -y));
                ys[t++] = c <= 3 ? (c % 2 == 0 ? y : -y) : (c % 2 == 0 ? x : -x);
            }

            int mx = xs[0], my = ys[0];
            for (int x : xs) mx = Math.min(mx, x);
            for (int y : ys) my = Math.min(my, y);

            for (int i = 0; i < len; i++) {
                out[i] = (xs[i] - mx) * lift + (ys[i] - my);
            }
            Arrays.sort(out);
            String candidate = Arrays.toString(out);
            if (res.compareTo(candidate) < 0) res = candidate;
        }
        return res;
    }

    private void dfs(int i, int j) {
        g[i][j] = 0;
        shape.add(i * n + j);
        for (int k = 0; k < 4; k++) {
            int x = i + dx[k], y = j + dy[k];
            if (x < 0 || x >= m || y < 0 || y >= n) continue;
            if (g[x][y] == 0) continue;
            dfs(x, y);
        }
    }

    // S2: Cal distance between point pairs
    // time = O(m * n * k^2), space = O(m * n)
    class Solution {
        int[][] g;
        int m, n;
        List<int[]> pos;
        int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
        public int numDistinctIslands2(int[][] grid) {
            g = grid;
            m = g.length;
            n = g[0].length;
            HashSet<HashMap<Integer, Integer>> set = new HashSet<>();

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (g[i][j] == 1) {
                        pos = new ArrayList<>();
                        dfs(i, j);
                        HashMap<Integer, Integer> map = new HashMap<>();
                        int len = pos.size();
                        for (int a = 0; a < len; a++) {
                            for (int b = a + 1; b < len; b++) {
                                int dist = get(a, b);
                                map.put(dist, map.getOrDefault(dist, 0) + 1);
                            }
                        }
                        set.add(map);
                    }
                }
            }
            return set.size();
        }

        private int get(int i, int j) {
            int[] p1 = pos.get(i), p2 = pos.get(j);
            int x1 = p1[0], y1 = p1[1], x2 = p2[0], y2 = p2[1];
            return (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
        }

        private void dfs(int i, int j) {
            pos.add(new int[]{i, j});
            g[i][j] = 0;
            for (int k = 0; k < 4; k++) {
                int x = i + dx[k];
                int y = j + dy[k];
                if (x < 0 || x >= m || y < 0 || y >= n) continue;
                if (g[x][y] == 0) continue;
                dfs(x, y);
            }
        }
    }
}