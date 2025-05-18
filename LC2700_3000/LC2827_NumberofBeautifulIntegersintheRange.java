package LC2700_3000;
import java.util.*;
public class LC2827_NumberofBeautifulIntegersintheRange {
    /**
     * You are given positive integers low, high, and k.
     *
     * A number is beautiful if it meets both of the following conditions:
     *
     * The count of even digits in the number is equal to the count of odd digits.
     * The number is divisible by k.
     * Return the number of beautiful integers in the range [low, high].
     *
     * Input: low = 10, high = 20, k = 3
     * Output: 2
     *
     * Input: low = 1, high = 10, k = 1
     * Output: 1
     *
     * Input: low = 5, high = 5, k = 2
     * Output: 0
     *
     * Constraints:
     *
     * 0 < low <= high <= 10^9
     * 0 < k <= 20
     * @param low
     * @param high
     * @param k
     * @return
     */
    // time = O(2^n), space = O(n)
    char[] s;
    int[][][][] f;
    int k;
    public int numberOfBeautifulIntegers(int low, int high, int k) {
        this.k = k;
        int res = work(high) - work(low);
        if (low % k != 0) return res;
        String s = String.valueOf(low);
        int odd = 0, even = 0;
        for (int i = 0; i < s.length(); i++) {
            int d = s.charAt(i) - '0';
            if (d % 2 == 0) even++;
            else odd++;
        }
        if (odd == even) res++;
        return res;
    }

    private int work(int num) {
        s = String.valueOf(num).toCharArray();
        int n = s.length;
        f = new int[n][11][11][20];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 11; j++) {
                for (int u = 0; u < 11; u++) {
                    Arrays.fill(f[i][j][u], -1);
                }
            }
        }
        return dp(0, 0, 0, 0, true, false);
    }

    private int dp(int u, int odd, int even, int cur, boolean isLimit, boolean isNum) {
        if (u == s.length) {
            if (isNum && odd == even && cur % k == 0) return 1;
            return 0;
        }
        if (!isLimit && isNum && f[u][odd][even][cur % k] != -1) return f[u][odd][even][cur % k];

        int res = 0;
        if (!isNum) res += dp(u + 1, odd, even, cur, false, false);
        int up = isLimit ? s[u] - '0' : 9;
        for (int i = isNum ? 0 : 1; i <= up; i++) {
            res += dp(u + 1, i % 2 == 0 ? odd : odd + 1, i % 2 == 0 ? even + 1 : even, cur * 10 + i, isLimit && i == up, true);
        }
        if (!isLimit && isNum) f[u][odd][even][cur % k] = res;
        return res;
    }
}
/**
 * diff = 奇数数位的数目 - 偶数数位的数目 = 0
 */