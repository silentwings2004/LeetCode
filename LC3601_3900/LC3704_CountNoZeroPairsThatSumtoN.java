package LC3601_3900;
import java.util.*;
public class LC3704_CountNoZeroPairsThatSumtoN {
    /**
     * A no-zero integer is a positive integer that does not contain the digit 0 in its decimal representation.
     *
     * Given an integer n, count the number of pairs (a, b) where:
     *
     * a and b are no-zero integers.
     * a + b = n
     * Return an integer denoting the number of such pairs.
     *
     * Input: n = 2
     * Output: 1
     *
     * Input: n = 3
     * Output: 2
     *
     * Constraints:
     *
     * 2 <= n <= 10^15
     * @param n
     * @return
     */
    // time = O(m^3), space = O(m)
    public long countNoZeroPairs(long n) {
        String s = String.valueOf(n);
        int m = s.length();
        int[] w = new int[m];
        for (int i = 0; i < m; i++) w[i] = s.charAt(m - 1 - i) - '0';
        long res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                long[][] f = new long[m + 1][2];
                for (int k = 0; k <= m; k++) Arrays.fill(f[k], -1);
                res += dfs(0, 0, i, j, m, w, f);
            }
        }
        return res;
    }

    private long dfs(int u, int carry, int la, int lb, int m, int[] w, long[][] f) {
        if (u == m) return carry == 0 ? 1 : 0;
        if (f[u][carry]!= -1) return f[u][carry];

        long cnt = 0;
        boolean al = u > la, bl = u > lb;
        int as = al ? 0 : 1, ae = al ? 0 : 9;
        int bs = bl ? 0 : 1, be = bl ? 0 : 9;

        int need = w[u];
        for (int i = as; i <= ae; i++) {
            for (int j = bs; j <= be; j++) {
                int t = i + j + carry;
                if (t % 10 == need) {
                    cnt += dfs(u + 1, t / 10, la, lb, m, w, f);
                }
            }
        }
        return f[u][carry] = cnt;
    }
}