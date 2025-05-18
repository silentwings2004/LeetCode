package LC1801_2100;
import java.util.*;
public class LC1931_PaintingaGridWithThreeDifferentColors {
    /**
     * You are given two integers m and n. Consider an m x n grid where each cell is initially white. You can paint each
     * cell red, green, or blue. All cells must be painted.
     *
     * Return the number of ways to color the grid with no two adjacent cells having the same color. Since the answer
     * can be very large, return it modulo 10^9 + 7.
     *
     * Input: m = 1, n = 1
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= m <= 5
     * 1 <= n <= 1000
     * @param m
     * @param n
     * @return
     */
    // S1
    // time = O(3^m + n * m * k^2), space = O(k),  k: number of possible states
    public int colorTheGrid(int m, int n) {
        long M = (long)(1e9 + 7);
        List<Integer> cand = new ArrayList<>();
        for (int state = 0; state < (int)Math.pow(3, m); state++) { // O(3^m)
            int state0 = state;
            List<Integer> temp = new ArrayList<>();
            boolean flag = true;
            for (int i = 0; i < m; i++) {
                int color = state0 % 3;
                if (!temp.isEmpty() && temp.get(temp.size() - 1) == color) {
                    flag = false;
                    break;
                }
                temp.add(color);
                state0 /= 3;
            }
            if (flag) cand.add(state);
        }
        int k = cand.size();

        long[] dp = new long[k];
        Arrays.fill(dp, 1);
        long[] dp_new = new long[k];

        for (int i = 1; i < n; i++) {   // O(n)
            for (int s = 0; s < k; s++) { // state of col i
                dp_new[s] = 0; // dp_new must reset to 0!!!!
                for (int t = 0; t < k; t++) { // state of col i-1
                    if (checkOK(cand.get(s), cand.get(t), m)) { // O(m)
                        dp_new[s] = (dp_new[s] + dp[t]) % M;
                    }
                }
            }
            // swap(dp, dp_new)
            long[] dp_temp = dp;
            dp = dp_new;
            dp_new = dp_temp;
        }
        long res = 0;
        for (int s = 0; s < k; s++) {
            res = (res + dp[s]) % M;
        }
        return (int)res;
    }

    private boolean checkOK(int s, int t, int m) {
        for (int i = 0; i < m; i++) {
            if (s % 3 == t % 3) { // 有任意一位相等，则表示相邻2位颜色相同 -> false
                return false;
            }
            s /= 3;
            t /= 3;
        }
        return true;
    }

    // S2
    // time = O(n * 4^m), space = O(4^m + n * 2^m)
    final long mod = (long)(1e9 + 7);
    List<List<Integer>> p;
    public int colorTheGrid2(int m, int n) {
        p = new ArrayList<>();
        init(m);
        int k = p.size();
        if (k == 0) return 0;
        boolean[][] g = new boolean[k][k];
        for (int i = 0; i < k; i++) {
            List<Integer> a = p.get(i);
            for (int j = 0; j < k; j++) {
                List<Integer> b = p.get(j);
                boolean flag = true;
                for (int u = 0; u < m; u++) {
                    if (a.get(u) == b.get(u)) {
                        flag = false;
                        break;
                    }
                }
                g[i][j] = flag;
            }
        }

        long[][] f = new long[n][k];
        for (int i = 0; i < k; i++) f[0][i] = 1;
        for (int i = 1; i < n; i++) {
            for (int a = 0; a < k; a++) {
                for (int b = 0; b < k; b++) {
                    if (g[b][a]) {
                        f[i][a] = (f[i][a] + f[i - 1][b]) % mod;
                    }
                }
            }
        }

        long res = 0;
        for (int i = 0; i < k; i++) res = (res + f[n - 1][i]) % mod;
        return (int)res;
    }

    private void init(int m) {
        dfs(new ArrayList<>(), m, -1);
    }

    private void dfs(List<Integer> q, int m, int last) {
        if (q.size() == m) {
            p.add(new ArrayList<>(q));
            return;
        }
        for (int i = 0; i < 3; i++) {
            if (i == last) continue;
            q.add(i);
            dfs(q, m, i);
            q.remove(q.size() - 1);
        }
    }
}
/**
 * ref LC1411
 * state compression dp
 * 1 <= m <= 5  ->  3^5=243    3种颜色，3进制数
 * dp[i][01210]=dp[i-1][10101]
 *              +dp[i-1][20121]
 *              +...
 * dp[i][xxxxx]=?
 * 第i-1列与第i列不能有重复
 * time = O(1000 * 243 * 243)
 * 相当多的组合不合法 => 把243种先过滤一遍，筛选出那些合法的列状态 => [48]种
 */

