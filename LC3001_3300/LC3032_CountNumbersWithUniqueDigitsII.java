package LC3001_3300;
import java.util.*;
public class LC3032_CountNumbersWithUniqueDigitsII {
    /**
     * Given two positive integers a and b, return the count of numbers having unique digits in the range [a, b]
     * (inclusive).
     *
     * Input: a = 1, b = 20
     * Output: 19
     *
     * Input: a = 9, b = 19
     * Output: 10
     *
     * Input: a = 80, b = 120
     * Output: 27
     *
     * Constraints:
     *
     * 1 <= a <= b <= 1000
     * @param a
     * @param b
     * @return
     */
    // S1: brute-force
    // time = O(n * D), space = O(1)
    public int numberCount(int a, int b) {
        int res = 0;
        for (int i = a; i <= b; i++) {
            if (check(i)) res++;
        }
        return res;
    }

    private boolean check(int x) {
        boolean[] st = new boolean[10];
        while (x > 0) {
            int d = x % 10;
            x /= 10;
            if (st[d]) return false;
            st[d] = true;
        }
        return true;
    }

    // S2: digit dp
    // time = O(n * D^2), space = O(n * D)
    int[][] f;
    public int numberCount2(int a, int b) {
        int res = cal(b) - cal(a);
        String s = String.valueOf(a);
        boolean[] st = new boolean[10];
        boolean flag = true;
        for (int i = 0; i < s.length(); i++) {
            int u = s.charAt(i) - '0';
            if (st[u]) {
                flag = false;
                break;
            } else st[u] = true;
        }
        if (flag) res++;
        return res;
    }

    private int cal(int x) {
        String s = String.valueOf(x);
        int n = s.length();
        f = new int[n][1 << 10];
        for (int i = 0; i < n; i++) Arrays.fill(f[i], -1);
        return dfs(s, 0, 0, true, false);
    }

    private int dfs(String s, int u, int state, boolean isLimit, boolean isNum) {
        if (u == s.length()) return isNum ? 1 : 0;
        if (isNum && !isLimit && f[u][state] != -1) return f[u][state];

        int t = 0;
        if (!isNum) t = dfs(s, u + 1, state, false, false);
        int up = isLimit ? s.charAt(u) - '0' : 9;
        for (int i = isNum ? 0 : 1; i <= up; i++) {
            if ((state >> i & 1) == 0) {
                t += dfs(s, u + 1, state | 1 << i, i == up && isLimit, true);
            }
        }
        f[u][state] = t;
        return t;
    }
}