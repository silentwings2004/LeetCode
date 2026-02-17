package LC3601_3900;

public class LC3844_LongestAlmostPalindromicSubstring {
    /**
     * You are given a string s consisting of lowercase English letters.
     *
     * A substring is almost-palindromic if it becomes a palindrome after removing exactly one character from it.
     *
     * Return an integer denoting the length of the longest almost-palindromic substring in s.
     *
     * A substring is a contiguous non-empty sequence of characters within a string.
     *
     * A palindrome is a non-empty string that reads the same forward and backward.
     *
     * Input: s = "abca"
     * Output: 4
     *
     * Input: s = "abba"
     * Output: 4
     *
     * Input: s = "zzabba"
     * Output: 5
     *
     * Constraints:
     *
     * 2 <= s.length <= 2500
     * s consists of only lowercase English letters.
     * @param s
     * @return
     */
    // S1
    // time = O(n^2), space = O(n^2)
    public int almostPalindromic(String s) {
        int n = s.length();
        boolean[][] f = new boolean[n][n];
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) f[i][j] = len <= 2 || f[i + 1][j - 1];
            }
        }

        int[][] g = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (i + 1 < n && j - 1 >= 0) g[i][j] = 1 + g[i + 1][j - 1];
                    else g[i][j] = 1;
                }
            }
        }

        for (int len = n; len >= 2; len--) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                int k = g[i][j];
                if (k >= len / 2 || f[i + k + 1][j - k] || f[i + k][j - k - 1]) return len;
            }
        }
        return 0;
    }

    // S2
    // time = O(n^2), space = O(1)
    public int almostPalindromic2(String s) {
        int n = s.length(), res = 0;
        for (int i = 0; i < 2 * n - 1 && res < n; i++) {
            int l = i / 2, r = (i + 1) / 2;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
            }
            res = Math.max(res, expand(s, l - 1, r));
            res = Math.max(res, expand(s, l, r + 1));
        }
        return Math.min(res, n);
    }

    private int expand(String s, int l, int r) {
        int n = s.length();
        while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return r - l - 1;
    }
}
/**
 * O(n^2) 暴力做法
 * O(nlogn) 的非暴力做法(但常数比较大)
 * 1. 计算每个位置为回文中心的最长子串的长度 —— Manacher 算法 O(n)
 * 2. 后缀数组，计算 s + '#' + reverse(s) 的两个后缀的最长公共前缀 (LCP)
 *    ST 表预处理 O(nlogn)
 * O(nlogn)
 *     后缀数组可以用字符串哈希 + 二分代替，时间复杂度也是 O(nlogn), 但无法保证 100% 正确
 */