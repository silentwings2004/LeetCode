package LC3001_3300;
import java.util.*;
public class LC3276_SelectCellsinGridWithMaximumScore {
    /**
     * You are given a 2D matrix grid consisting of positive integers.
     *
     * You have to select one or more cells from the matrix such that the following conditions are satisfied:
     *
     * No two selected cells are in the same row of the matrix.
     * The values in the set of selected cells are unique.
     * Your score will be the sum of the values of the selected cells.
     *
     * Return the maximum score you can achieve.
     *
     * Input: grid = [[1,2,3],[4,3,2],[1,1,1]]
     *
     * Output: 8
     *
     * Input: grid = [[8,7,6],[8,3,2]]
     *
     * Output: 15
     *
     * Constraints:
     *
     * 1 <= grid.length, grid[i].length <= 10
     * 1 <= grid[i][j] <= 100
     * @param grid
     * @return
     */
    // S1: dfs + pruning
    // time = O(m * n * 2^m), space = O(m * n + 2^m)
    List<List<Integer>> grid;
    int[] suf;
    int m, n, res;
    HashSet<String> set;
    public int maxScore(List<List<Integer>> grid) {
        this.grid = grid;
        m = grid.size();
        n = grid.get(0).size();
        set = new HashSet<>();
        suf = new int[m + 1];
        for (int i = m - 1; i >= 0; i--) {
            int mx = grid.get(i).get(0);
            for (int j = 0; j < n; j++) {
                mx = Math.max(mx, grid.get(i).get(j));
            }
            suf[i] = suf[i + 1] + mx;
        }

        dfs(0, 0, 0, 0);
        return res;
    }

    private void dfs(int u, int sum, long used1, long used2) {
        if (u == m) {
            res = Math.max(res, sum);
            return;
        }
        if (sum + suf[u] <= res) return;
        String h = u + "#" + sum + "#" + used1 + "#" + used2;
        if (!set.add(h)) return;

        dfs(u + 1, sum, used1, used2);
        for (int i = 0; i < n; i++) {
            int x = grid.get(u).get(i);
            long bit = 0;
            if (x <= 50) bit = 1L << x;
            else bit = 1L << (x - 50);
            if (x <= 50 && (used1 & bit) != 0 || x > 50 && (used2 & bit) != 0) continue;
            if (x <= 50) dfs(u + 1, sum + x, used1 | bit, used2);
            else dfs(u + 1, sum + x, used1, used2 | bit);
        }
    }

    // S2: DFS + 值域DP
    // time = O(m * n * 2^m), space = O(m * n + 2^m)
    class Solution {
        List<List<Integer>> g;
        int[][] f;
        List<Integer>[] pos;
        int m, n;
        public int maxScore(List<List<Integer>> grid) {
            this.g = grid;
            m = g.size();
            n = g.get(0).size();
            int mx = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    mx = Math.max(mx, g.get(i).get(j));
                }
            }
            pos = new List[mx + 1];
            for (int i = 0; i <= mx; i++) pos[i] = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int v = g.get(i).get(j);
                    pos[v].add(i);
                }
            }
            f = new int[mx + 1][1 << m];
            for (int i = 0; i <= mx; i++) Arrays.fill(f[i], -1);
            return dfs(mx, 0);
        }

        private int dfs(int i, int j) {
            if (i == 0) return 0;
            if (f[i][j] != -1) return f[i][j];

            int res = dfs(i - 1, j); // not choose
            for (int k : pos[i]) {
                if ((j >> k & 1) == 0) {
                    res = Math.max(res, dfs(i - 1, j | 1 << k) + i);
                }
            }
            f[i][j] = res;
            return res;
        }
    }
}