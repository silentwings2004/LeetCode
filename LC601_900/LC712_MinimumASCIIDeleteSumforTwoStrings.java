package LC601_900;

public class LC712_MinimumASCIIDeleteSumforTwoStrings {
    /**
     * Given two strings s1 and s2, return the lowest ASCII sum of deleted characters to make two strings equal.
     *
     * Input: s1 = "sea", s2 = "eat"
     * Output: 231
     *
     * Input: s1 = "delete", s2 = "leet"
     * Output: 403
     *
     * Constraints:
     *
     * 1 <= s1.length, s2.length <= 1000
     * s1 and s2 consist of lowercase English letters.
     * @param s1
     * @param s2
     * @return
     */
    // time = O(m * n), space = O(m * n)
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        s1 = "#" + s1;
        s2 = "#" + s2;
        int[][] f = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) f[i][0] = f[i - 1][0] + s1.charAt(i);
        for (int j = 1; j <= n; j++) f[0][j] = f[0][j - 1] + s2.charAt(j);
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char c1 = s1.charAt(i), c2 = s2.charAt(j);
                f[i][j] = Math.min(f[i - 1][j] + c1, f[i][j - 1] + c2);
                f[i][j] = Math.min(f[i][j], f[i - 1][j - 1] + c1 + c2);
                if (c1 == c2) f[i][j] = Math.min(f[i][j], f[i - 1][j - 1]);
            }
        }
        return f[m][n];
    }
}
/**
 * f(i,j): 将S1的前i个字符和S2的前j个字符匹配
 * 1.只删i => f(i-1,j) + S1(i)
 * 2.只删j => f(i,j-1) + S2(j)
 * 3.删除i,j => f(i-1,j-1) + S1(i) + S2(j)
 * 4.i，j都不删  => f(i-1,j-1)   S1(i) == S2(j)
 * => f(n,m)
 */