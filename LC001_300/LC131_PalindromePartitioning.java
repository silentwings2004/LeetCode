package LC001_300;
import java.util.*;
public class LC131_PalindromePartitioning {
    /**
     * Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible
     * palindrome partitioning of s.
     *
     * A palindrome string is a string that reads the same backward as forward.
     *
     * Input: s = "aab"
     * Output: [["a","a","b"],["aa","b"]]
     *
     * Constraints:
     *
     * 1 <= s.length <= 16
     * s contains only lowercase English letters.
     * @param s
     * @return
     */
    // time = O(n * 2^n), space = O(n^2)
    List<List<String>> res;
    List<String> path;
    boolean[][] f;
    public List<List<String>> partition(String s) {
        int n = s.length();
        f = new boolean[n][n];

        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                if (i == j) f[i][j] = true;
                else if (s.charAt(i) == s.charAt(j)) {
                    if (i + 1 > j - 1 || f[i + 1][j - 1]) f[i][j] = true;
                }
            }
        }

        res = new ArrayList<>();
        path = new ArrayList<>();
        dfs(s, 0);
        return res;
    }

    private void dfs(String s, int u) {
        if (u == s.length()) res.add(new ArrayList<>(path));
        else {
            for (int i = u; i < s.length(); i++) {
                if (f[u][i]) {
                    path.add(s.substring(u, i + 1));
                    dfs(s, i + 1);
                    path.remove(path.size() - 1);
                }
            }
        }
    }
}
/**
 * 大框架：dfs
 * 挨个向后找 -> 确定回文 how? 双指针 * n
 * dp[i][j] -> 区间型dp  s[i:j] is palindrome
 * s[i] = s[j] && dp[i+1][j-1] is palindrome
 * 大区间依赖小区间
 * 考查2点：
 * 1. dfs
 * 2. 预处理回文串，判断回文的时间不会叠加在dfs的时间上
 */
