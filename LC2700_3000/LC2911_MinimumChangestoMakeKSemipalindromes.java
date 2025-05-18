package LC2700_3000;
import java.util.*;
public class LC2911_MinimumChangestoMakeKSemipalindromes {
    /**
     * Given a string s and an integer k, partition s into k substrings such that the sum of the number of letter
     * changes required to turn each substring into a semi-palindrome is minimized.
     *
     * Return an integer denoting the minimum number of letter changes required.
     *
     * Notes
     *
     * A string is a palindrome if it can be read the same way from left to right and right to left.
     * A string with a length of len is considered a semi-palindrome if there exists a positive integer d such that
     * 1 <= d < len and len % d == 0, and if we take indices that have the same modulo by d, they form a palindrome.
     * For example, "aa", "aba", "adbgad", and, "abab" are semi-palindrome and "a", "ab", and, "abca" are not.
     * A substring is a contiguous sequence of characters within a string.
     *
     * Input: s = "abcac", k = 2
     * Output: 1
     *
     * Input: s = "abcdef", k = 2
     * Output: 2
     *
     * Input: s = "aabbaa", k = 3
     * Output: 0
     *
     * Constraints:
     *
     * 2 <= s.length <= 200
     * 1 <= k <= s.length / 2
     * s consists only of lowercase English letters.
     * @param s
     * @param k
     * @return
     */
    // time = O(n^2 * k), space = O(n^2 + n * k)
    final int INF = (int)1e9;
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int[][] f = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) Arrays.fill(f[i], INF);
        for (int i = 0; i <= n; i++) f[i][0] = 0;

        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                cost[i][j] = get(s, i, j);
            }
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= Math.min(i / 2, k); j++) {
                if (j == 1) f[i][j] = cost[0][i - 1];
                else {
                    for (int u = 1; u < i - 1; u++) {
                        f[i][j] = Math.min(f[i][j], f[u][j - 1] + cost[u][i - 1]);
                    }
                }
            }
        }
        return f[n][k];
    }

    private int get(String s, int l, int r) {
        int n = s.length(), res = INF;
        int len = r - l + 1;
        for (int i = 1; i < len; i++) {
            if (len % i != 0) continue;
            HashMap<Integer, StringBuilder> map = new HashMap<>();
            for (int j = l; j <= r; j++) {
                int rem = (j - l) % i;
                map.putIfAbsent(rem, new StringBuilder());
                map.get(rem).append(s.charAt(j));
            }
            int t = 0;
            for (StringBuilder sb : map.values()) t += check(sb.toString());
            res = Math.min(res, t);
        }
        return res;
    }

    private int check(String s) {
        int cnt = 0;
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) cnt++;
        }
        return cnt;
    }
}