package LC2101_2400;
import java.util.*;
public class LC2370_LongestIdealSubsequence {
    /**
     * You are given a string s consisting of lowercase letters and an integer k. We call a string t ideal if the
     * following conditions are satisfied:
     *
     * t is a subsequence of the string s.
     * The absolute difference in the alphabet order of every two adjacent letters in t is less than or equal to k.
     * Return the length of the longest ideal string.
     *
     * A subsequence is a string that can be derived from another string by deleting some or no characters without
     * changing the order of the remaining characters.
     *
     * Note that the alphabet order is not cyclic. For example, the absolute difference in the alphabet order of 'a'
     * and 'z' is 25, not 1.
     *
     * Input: s = "acfgbd", k = 2
     * Output: 4
     *
     * Input: s = "abcd", k = 3
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * 0 <= k <= 25
     * s consists of lowercase English letters.
     * @param s
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public int longestIdealString(String s, int k) {
        int n = s.length(), res = 0;
        int[] f = new int[26];

        for (char c : s.toCharArray()) {
            int l = Math.max(0, c - 'a' - k);
            int r = Math.min(25, c - 'a' + k);
            int max = 1;
            for (int i = l; i <= r; i++) {
                max = Math.max(max, f[i] + 1);
            }
            f[c - 'a'] = Math.max(f[c - 'a'], max);
            res = Math.max(res, f[c - 'a']);
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public int longestIdealString2(String s, int k) {
        int n = s.length();
        int[] f = new int[n];
        Arrays.fill(f, 1);
        int[] prev = new int[26];
        Arrays.fill(prev, -1);

        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = Math.max(0, s.charAt(i) - 'a' - k); j <= Math.min(25, s.charAt(i) - 'a' + k); j++) {
                if (prev[j] != -1) {
                    f[i] = Math.max(f[i], f[prev[j]] + 1);
                }
            }
            prev[s.charAt(i) - 'a'] = i;
            res = Math.max(res, f[i]);
        }
        return res;
    }
}
/**
 * [xxxxxx j x j x i] xxxx
 *         a   c   b
 * int[] prev[26]
 * j = prev['c']
 * j = prev['a']
 * dp[j] = max{dp[j} + 1
 */