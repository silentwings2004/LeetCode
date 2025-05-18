package LC1501_1800;
import java.util.*;
public class LC1547_MinimumCosttoCutaStick {
    /**
     * Given a wooden stick of length n units. The stick is labelled from 0 to n. For example, a stick of length 6 is
     * labelled as follows:
     *
     * Given an integer array cuts where cuts[i] denotes a position you should perform a cut at.
     *
     * You should perform the cuts in order, you can change the order of the cuts as you wish.
     *
     * The cost of one cut is the length of the stick to be cut, the total cost is the sum of costs of all cuts. When
     * you cut a stick, it will be split into two smaller sticks (i.e. the sum of their lengths is the length of the
     * stick before the cut). Please refer to the first example for a better explanation.
     *
     * Return the minimum total cost of the cuts.
     *
     * Input: n = 7, cuts = [1,3,4,5]
     * Output: 16
     *
     * Input: n = 9, cuts = [5,6,1,4,2]
     * Output: 22
     *
     * Constraints:
     *
     * 2 <= n <= 10^6
     * 1 <= cuts.length <= min(n - 1, 100)
     * 1 <= cuts[i] <= n - 1
     * All the integers in cuts array are distinct.
     * @param n
     * @param cuts
     * @return
     */
    // S1: dfs + memo
    // time = O(m^3), space = O(m^2)
    int[][] f;
    int[] c;
    public int minCost(int n, int[] cuts) {
        int m = cuts.length;
        c = new int[m + 2];
        for (int i = 0; i < m; i++) c[i] = cuts[i];
        c[m] = 0;
        c[m + 1] = n;
        Arrays.sort(c);
        f = new int[m + 2][m + 2];
        for (int i = 0; i <= m + 1; i++) Arrays.fill(f[i], -1);
        return dfs(0, c.length - 1);
    }

    private int dfs(int l, int r) {
        if (l + 1 == r) return 0;
        if (f[l][r] != -1) return f[l][r];

        int res = Integer.MAX_VALUE;
        for (int i = l + 1; i < r; i++) {
            res = Math.min(res, dfs(l, i) + dfs(i, r));
        }
        res += c[r] - c[l];
        f[l][r] = res;
        return res;
    }

    // S2
    // time = O(m^3), space = O(m^2)
    public int minCost2(int n, int[] cuts) {
        int m = cuts.length;
        int[] c = new int[m + 2];
        for (int i = 0; i < m; i++) c[i] = cuts[i];
        c[m] = 0;
        c[m + 1] = n;
        Arrays.sort(c);
        m = c.length;
        int[][] f = new int[m][m];
        for (int i = 0; i < m; i++) Arrays.fill(f[i], Integer.MAX_VALUE);
        for (int i = 0; i + 1 < m; i++) f[i][i + 1] = 0;

        for (int len = 3; len <= m; len++) {
            for (int i = 0; i + len - 1 < m; i++) {
                int j = i + len - 1;
                for (int k = i + 1; k < j; k++) {
                    f[i][j] = Math.min(f[i][j], f[i][k] + f[k][j] + c[j] - c[i]);
                }
            }
        }
        return f[0][m - 1];
    }
}