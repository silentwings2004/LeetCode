package LC1201_1500;
import java.util.*;
public class LC1278_PalindromePartitioningIII {
    /**
     * You are given a string s containing lowercase letters and an integer k. You need to :
     *
     * First, change some characters of s to other lowercase English letters.
     * Then divide s into k non-empty disjoint substrings such that each substring is a palindrome.
     * Return the minimal number of characters that you need to change to divide the string.
     *
     * Input: s = "abc", k = 2
     * Output: 1
     *
     * Input: s = "aabbc", k = 3
     * Output: 0
     *
     * Input: s = "leetcode", k = 8
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= k <= s.length <= 100.
     * s only contains lowercase English letters.
     * @param s
     * @param k
     * @return
     */
    // S1: dp
    // time = O(n^3), space = O(n^2)
    public int palindromePartition(String s, int k) {
        int n = s.length(), INF = (int)1e8;
        int[][] f = new int[n][k + 1], cost = new int[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(f[i], INF);
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                for (int a = i, b = j; a < b; a++, b--) {
                    if (s.charAt(a) != s.charAt(b)) cost[i][j]++;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            f[i][1] = cost[0][i]; // 单独处理 j = 1 的情况
            for (int j = 2; j <= k; j++) {
                for (int u = i; u > 0; u--) { // 枚举最后一段的起点
                    f[i][j] = Math.min(f[i][j], f[u - 1][j - 1] + cost[u][i]);
                }
            }
        }
        return f[n - 1][k];
    }

    // S2: memoization
    // time = O(n^2 * k), space = O(n^2)
    final int inf = 0x3f3f3f3f;
    int[][] f, g;
    public int palindromePartition2(String s, int k) {
        int n = s.length();
        f = new int[n][k];
        g = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(f[i], -1);
            for (int j = i + 1; j < n; j++) {
                g[i][j] = get(s, i, j);
            }
        }
        return dp(s, 0, k - 1);
    }

    private int dp(String s, int u, int k) {
        if (u == s.length()) return inf;
        if (k == 0) return g[u][s.length() - 1];
        if (f[u][k] != -1) return f[u][k];

        int res = inf;
        for (int i = u; i < s.length(); i++) {
            res = Math.min(res, dp(s, i + 1, k - 1) + g[u][i]);
        }
        return f[u][k] = res;
    }

    private int get(String s, int i, int j) {
        int t = 0;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) t++;
            i++;
            j--;
        }
        return t;
    }
}
/**
 * dp
 * 1. 状态表示 f(i,j)
 * 集合：前i个字符分割为j个回文串的所有方案的集合
 * 属性：最小值
 * 2. 状态计算：
 * 枚举下最后一段的长度 1,2,...k,i
 * 最后一段长度为k，代价固定 => f(i-k,j-1) + cost[i-k+1~i]
 * O(n^3)
 * 预处理下cost
 * cost[i,j]直接暴力算
 */