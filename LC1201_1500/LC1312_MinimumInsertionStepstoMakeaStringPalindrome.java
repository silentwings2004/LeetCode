package LC1201_1500;
import java.util.*;
public class LC1312_MinimumInsertionStepstoMakeaStringPalindrome {
    /**
     * Given a string s. In one step you can insert any character at any index of the string.
     *
     * Return the minimum number of steps to make s palindrome.
     *
     * A Palindrome String is one that reads the same backward as well as forward.
     *
     * Input: s = "zzazz"
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= s.length <= 500
     * All characters of s are lower case English letters.
     * @param s
     * @return
     */
    // S1: DP
    // time = O(n^2), space = O(n^2)
    public int minInsertions(String s) {
        int n = s.length();
        int[][] f = new int[n][n];
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                f[i][j] = Math.min(f[i + 1][j], f[i][j - 1]) + 1;
                if (s.charAt(i) == s.charAt(j)) f[i][j] = Math.min(f[i][j], f[i + 1][j - 1]);
            }
        }
        return f[0][n - 1];
    }

    // S2: SCS
    // time = O(n^2), space = O(n^2)
    public int minInsertions2(String s) {
        // corner case
        if (s == null || s.length() == 0) return 0;

        int n = s.length();
        StringBuilder sb = new StringBuilder(s);
        String t = sb.reverse().toString();

        s = "#" + s;
        t = "#" + t;

        int[][] dp = new int[n + 1][n + 1];

        // init
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
            dp[0][i] = i;
        }

        // SCS
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i) == t.charAt(j)) dp[i][j] = dp[i - 1][j - 1] + 1;
                else dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
            }
        }
        return dp[n][n] - n;
    }
}
/**
 * dp 区间型dp
 * XXXXX[XX]XXXXX
 * dp[i][j]: the minimum number of steps to make s[i:j] palindrome
 * dp[i+1][j], dp[i][j-1], dp[i+1][j-1], dp[...]
 * if (s[i] == s[j]) dp[i][j] = dp[i+1][j-1]
 * else dp[i][j] = min{dp[i+1][j]+1, dp[i][j-1]+1}
 * dp[0][n-1]
 *
 * t = s[0:-1}
 * s t the shortest common supersequence (ref: LC1092)
 * 得到的这个supersequence其实就是s通过最少的insertion得到的一个palindrome
 * s => palindrome
 * t => palindrome
 *
 * s: leetcode => leetcodocteel
 * t: edocteel => leetcodocteel
 * 如何使它最短呢？
 * 逆序，互相堆成
 * s的后半段与t的前半段能够越重合就越好 => supersequence尽量短
 * => 两个互逆的串，你得到的SCS一定是个回文串
 *
 * LCS variants:
 * dp[i][j]： the length of SCS for s[1:i] and t[1:j]
 *
 * dp
 * 状态表示：f(i,j)
 * 集合：所有将区间[i,j]变成回文串的方案的集合
 * 属性：方案的最小操作数
 * 状态计算：
 * 1.i与j匹配: f(i+1,j-1)
 * 2.i与新字母匹配: f(i+1,j)+1
 * 3.j与新字母匹配: f(i,j-1)+1
 */
