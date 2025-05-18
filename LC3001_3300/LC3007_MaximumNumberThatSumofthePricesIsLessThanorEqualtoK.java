package LC3001_3300;
import java.util.*;
public class LC3007_MaximumNumberThatSumofthePricesIsLessThanorEqualtoK {
    /**
     * You are given an integer k and an integer x.
     *
     * Consider s is the 1-indexed binary representation of an integer num. The price of a number num is the number of
     * i's such that i % x == 0 and s[i] is a set bit.
     *
     * Return the greatest integer num such that the sum of prices of all numbers from 1 to num is less than or equal to
     * k.
     *
     * Note:
     *
     * In the binary representation of a number set bit is a bit of value 1.
     * The binary representation of a number will be indexed from right to left. For example, if s == 11100, s[4] == 1
     * and s[2] == 0.
     *
     * Input: k = 9, x = 1
     * Output: 6
     *
     * Input: k = 7, x = 2
     * Output: 9
     *
     * Constraints:
     *
     * 1 <= k <= 10^15
     * 1 <= x <= 8
     * @param k
     * @param x
     * @return
     */
    // time = O((xlogk)^3), space = O((xlogk)^2)
    final int N = 65;
    long[][] f;
    char[] s;
    int x, n;
    public long findMaximumNumber(long k, int x) {
        f = new long[N][N];
        this.x = x;
        long l = 1, r = (long)1e15;
        while (l < r) {
            long mid = l + r + 1 >> 1;
            if (check(mid, k, x)) l = mid;
            else r = mid - 1;
        }
        return r;
    }

    private boolean check(long mid, long k, int x) {
        s = Long.toBinaryString(mid).toCharArray();
        n = s.length;
        for (int i = 0; i < N; i++) Arrays.fill(f[i], -1);
        return dfs(0, 0, true) <= k;
    }

    private long dfs(int u, int cnt, boolean isLimit) {
        if (u == n) return cnt;
        if (!isLimit && f[u][cnt] != -1) return f[u][cnt];

        long res = 0;
        int up = isLimit ? s[u] - '0' : 1;
        for (int i = 0; i <= up; i++) {
            res += dfs(u + 1, cnt + ((n - u) % x == 0 ? i : 0), isLimit && i == up);
        }
        f[u][cnt] = res;
        return res;
    }
}
