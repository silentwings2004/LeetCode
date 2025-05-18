package LC1201_1500;
import java.util.*;
public class LC1397_FindAllGoodStrings {
    /**
     * Given the strings s1 and s2 of size n and the string evil, return the number of good strings.
     *
     * A good string has size n, it is alphabetically greater than or equal to s1, it is alphabetically smaller than or
     * equal to s2, and it does not contain the string evil as a substring. Since the answer can be a huge number,
     * return this modulo 10^9 + 7.
     *
     * Input: n = 2, s1 = "aa", s2 = "da", evil = "b"
     * Output: 51
     *
     * Input: n = 8, s1 = "leetcode", s2 = "leetgoes", evil = "leet"
     * Output: 0
     *
     * Input: n = 2, s1 = "gx", s2 = "gz", evil = "x"
     * Output: 2
     *
     * Constraints:
     *
     * s1.length == n
     * s2.length == n
     * s1 <= s2
     * 1 <= n <= 500
     * 1 <= evil.length <= 50
     * All strings consist of lowercase English letters.
     * @param n
     * @param s1
     * @param s2
     * @param evil
     * @return
     */
    // time = O(n * m * s), space = O(m * n)
    final int mod = (int)1e9 + 7;
    int n, m;
    int[][] f;
    int[] ne;
    String s1, s2, t;
    public int findGoodStrings(int n, String s1, String s2, String evil) {
        this.n = n;
        this.s1 = s1;
        this.s2 = s2;
        t = evil;
        m = t.length();
        t = " " + t;
        f = new int[n][m + 1];
        for (int i = 0; i < n; i++) Arrays.fill(f[i], -1);
        ne = new int[m + 1];

        for (int i = 2, j = 0; i <= m; i++) {
            while (j > 0 && t.charAt(i) != t.charAt(j + 1)) j = ne[j];
            if (t.charAt(i) == t.charAt(j + 1)) j++;
            ne[i] = j;
        }
        return dfs(0, 0, true, true);
    }

    private int dfs(int i, int j, boolean downLimit, boolean upLimit) {
        if (j == m) return 0;
        if (i == n) return 1;
        if (!downLimit && !upLimit && f[i][j] != -1) return f[i][j];

        long res = 0;
        int down = downLimit ? s1.charAt(i) - 'a' : 0;
        int up = upLimit ? s2.charAt(i) - 'a' : 25;
        for (int u = down; u <= up; u++) {
            int k = j;
            while (k > 0 && u != t.charAt(k + 1) - 'a') k = ne[k];
            if (u == t.charAt(k + 1) - 'a') k++;
            res = (res + dfs(i + 1, k, downLimit && u == down, upLimit && u == up)) % mod;
        }
        if (!downLimit && !upLimit) f[i][j] = (int)res;
        return (int)res;
    }
}