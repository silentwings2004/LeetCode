package LC001_300;
import java.util.*;
public class LC132_PalindromePartitioningII {
    /**
     * Given a string s, partition s such that every substring of the partition is a palindrome.
     *
     * Return the minimum cuts needed for a palindrome partitioning of s.
     *
     * Input: s = "aab"
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= s.length <= 2000
     * s consists of lower-case English letters only.
     *
     * @param s
     * @return
     */
    // S1
    // time = O(n^2), space = O(n^2)
    public int minCut(String s) {
        int n = s.length();
        boolean[][] g = new boolean[n + 1][n + 1];
        for (int len = 1; len <= n; len++) {
            for (int i = 1; i + len - 1 <= n; i++) {
                int j = i + len - 1;
                if (s.charAt(i - 1) == s.charAt(j - 1) && (len <= 2 || g[i + 1][j - 1])) g[i][j] = true;
            }
        }

        int[] f = new int[n + 1];
        Arrays.fill(f, Integer.MAX_VALUE);
        f[0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                if (g[j][i]) f[i] = Math.min(f[i], f[j - 1] + 1);
            }
        }
        return f[n] - 1;
    }

    // S1.2
    // time = O(n^2), space = O(n^2)
    public int minCut2(String s) {
        int n = s.length();
        s = '#' + s;
        boolean[][] g = new boolean[n + 1][n + 1];
        int[] f = new int[n + 1];
        Arrays.fill(f, (int) 1e8);

        for (int j = 1; j <= n; j++) {
            for (int i = 1; i <= j; i++) {
                if (i == j) g[i][j] = true;
                else if (s.charAt(i) == s.charAt(j)) {
                    if (i + 1 > j - 1 || g[i + 1][j - 1]) g[i][j] = true;
                }
            }
        }

        f[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                if (g[j][i]) f[i] = Math.min(f[i], f[j - 1] + 1);
            }
        }
        return f[n] - 1;
    }
}
/**
 * f(i):
 * 1. 集合：s[1~i]的所有分割方案
 * 2. 属性：最小值
 * 状态计算：f(i) = f(k-1) + 1
 */
