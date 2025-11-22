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
    // time = O(logn), space = O(logn)
    char[] s;
    long[][][][] f, g;
    public long totalWaviness(long num1, long num2) {
        return cal(num2) - cal(num1 - 1);
    }

    private long cal(long x) {
        s = String.valueOf(x).toCharArray();
        int n = s.length;
        f = new long[n][11][11][2];
        g = new long[n][11][11][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 11; j++) {
                for (int k = 0; k < 11; k++) {
                    Arrays.fill(f[i][j][k], -1);
                    Arrays.fill(g[i][j][k], -1);
                }
            }
        }
        return dfs(0, 10, 10, true, true)[1];
    }

    private long[] dfs(int u, int pp, int p, boolean isNum, boolean isLimit) {
        if (u == s.length) return new long[]{1, 0};
        int t = isNum ? 1 : 0;
        if (!isLimit && f[u][pp][p][t] != -1) {
            return new long[]{f[u][pp][p][t], g[u][pp][p][t]};
        }

        long cnt = 0, tw = 0;
        int up = isLimit ? s[u] - '0' : 9;
        for (int i = 0; i <= up; i++) {
            boolean newLimit = isLimit && i == up;
            boolean newNum = isNum && i == 0;
            int npp = 10, np = 10;
            if (newNum) {
                npp = 10;
                np = 10;
            } else if (isNum) {
                npp = 10;
                np = i;
            } else {
                npp = p;
                np = i;
            }
            int v = 0;
            if (!isNum && pp != 10) {
                if (p > pp && p > i || p < pp && p < i) v = 1;
            }
            long[] res = dfs(u + 1, npp, np, newNum, newLimit);
            cnt += res[0];
            tw += res[1] + res[0] * v;
        }
        if (!isLimit) {
            f[u][pp][p][t] = cnt;
            g[u][pp][p][t] = tw;
        }
        return new long[]{cnt, tw};
    }
}