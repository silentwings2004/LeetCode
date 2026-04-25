package LC3901_4200;
import java.util.*;
public class LC3906_CountGoodIntegersonaGridPath {
    /**
     * You are given two integers l and r, and a string directions consisting of exactly three 'D' characters and three
     * 'R' characters.
     *
     * For each integer x in the range [l, r] (inclusive), perform the following steps:
     *
     * If x has fewer than 16 digits, pad it on the left with leading zeros to obtain a 16-digit string.
     * Place the 16 digits into a 4 × 4 grid in row-major order (the first 4 digits form the first row from left to
     * right, the next 4 digits form the second row, and so on).
     * Starting at the top-left cell (row = 0, column = 0), apply the 6 characters of directions in order:
     * 'D' increments the row by 1.
     * 'R' increments the column by 1.
     * Record the sequence of digits visited along the path (including the starting cell), producing a sequence of
     * length 7.
     * The integer x is considered good if the recorded sequence is non-decreasing.
     *
     * Return an integer representing the number of good integers in the range [l, r].
     *
     * Input: l = 8, r = 10, directions = "DDDRRR"
     * Output: 2
     *
     * Input: l = 123456789, r = 123456790, directions = "DDRRDR"
     * Output: 1
     *
     * Input: l = 1288561398769758, r = 1288561398769758, directions = "RRRDDD"
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= l <= r <= 9 × 10^15
     * directions.length == 6
     * directions consists of exactly three 'D' characters and three 'R' characters.
     * @param l
     * @param r
     * @param directions
     * @return
     */
    // time = O(D^2 * logr), space = O(D * logr)
    String s;
    boolean[] st;
    long[][] f;
    public long countGoodIntegersOnPath(long l, long r, String directions) {
        return cal(r, directions) - cal(l - 1, directions);
    }

    private long cal(long v, String d) {
        s = String.format("%016d", v);
        st = new boolean[16];
        int x = 0, y = 0;
        st[0] = true;
        for (int i = 0; i < 6; i++) {
            char c = d.charAt(i);
            if (c == 'D') x++;
            else y++;
            st[x * 4 + y] = true;
        }
        f = new long[16][10];
        for (int i = 0; i < 16; i++) Arrays.fill(f[i], -1);
        return dfs(0, 0, true);
    }

    private long dfs(int u, int last, boolean isLimit) {
        if (u == 16) return 1;
        if (!isLimit && f[u][last] != -1) return f[u][last];

        int up = isLimit ? s.charAt(u) - '0' : 9;
        long res = 0;
        for (int i = 0; i <= up; i++) {
            if (st[u]) {
                if (i < last) continue;
                res += dfs(u + 1, i, isLimit && i == up);
            } else res += dfs(u + 1, last, isLimit && i == up);
        }
        if (!isLimit) f[u][last] = res;
        return res;
    }
}