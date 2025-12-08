package LC3601_3900;
import java.util.*;
public class LC3753_TotalWavinessofNumbersinRangeII {
    /**
     * You are given two integers num1 and num2 representing an inclusive range [num1, num2].
     *
     * The waviness of a number is defined as the total count of its peaks and valleys:
     *
     * A digit is a peak if it is strictly greater than both of its immediate neighbors.
     * A digit is a valley if it is strictly less than both of its immediate neighbors.
     * The first and last digits of a number cannot be peaks or valleys.
     * Any number with fewer than 3 digits has a waviness of 0.
     * Return the total sum of waviness for all numbers in the range [num1, num2].
     *
     * Input: num1 = 120, num2 = 130
     * Output: 3
     *
     * Input: num1 = 198, num2 = 202
     * Output: 3
     *
     * Input: num1 = 4848, num2 = 4848
     * Output: 2
     *
     * onstraints:
     *
     * 1 <= num1 <= num2 <= 10^15
     * @param num1
     * @param num2
     * @return
     */
    // time = O(D^2 * n^2), space = O(D * n^2), D = O(log(num2))
    char[] ls, hs;
    long[][][][] f;
    public long totalWaviness(long num1, long num2) {
        ls = String.valueOf(num1).toCharArray();
        hs = String.valueOf(num2).toCharArray();
        int n = hs.length;
        f = new long[n][n - 1][3][10];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                for (int k = 0; k < 3; k++) {
                    Arrays.fill(f[i][j][k], -1);
                }
            }
        }
        return dfs(0, 0, 0, 0, true, true);
    }

    private long dfs(int u, int w, int lcmp, int last, boolean ll, boolean lh) {
        if (u == hs.length) return w;
        if (!ll && !lh && f[u][w][lcmp + 1][last] != -1) return f[u][w][lcmp + 1][last];

        int dl = hs.length - ls.length;
        int lo = ll && u >= dl ? ls[u - dl] - '0' : 0;
        int hi = lh ? hs[u] - '0' : 9;

        long res = 0;
        boolean isNum = !ll || u > dl; // 前面是否填过数字
        for (int i = lo; i <= hi; i++) {
            // 当前填的数不是最高位，cmp 才有意义
            int cmp = isNum ? (i == last ? 0 : (i > last ? 1 : -1)) : 0;
            int nw = w + (cmp * lcmp < 0 ? 1 : 0);
            res += dfs(u + 1, nw, cmp, i, ll && i == lo, lh && i == hi);
        }
        if (!ll && !lh) f[u][w][lcmp + 1][last] = res;
        return res;
    }
}