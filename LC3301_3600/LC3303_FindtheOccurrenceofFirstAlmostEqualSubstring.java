package LC3301_3600;
import java.util.*;
public class LC3303_FindtheOccurrenceofFirstAlmostEqualSubstring {
    /**
     * You are given two strings s and pattern.
     *
     * A string x is called almost equal to y if you can change at most one character in x to make it identical to y.
     *
     * Return the smallest starting index of a
     * substring
     *  in s that is almost equal to pattern. If no such index exists, return -1.
     *
     * A substring is a contiguous non-empty sequence of characters within a string.
     *
     * Input: s = "abcdefg", pattern = "bcdffg"
     * Output: 1
     *
     * Input: s = "ababbababa", pattern = "bacaba"
     * Output: 4
     *
     * Input: s = "abcd", pattern = "dba"
     * Output: -1
     *
     * Input: s = "dde", pattern = "d"
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= pattern.length < s.length <= 3 * 10^5
     * s and pattern consist only of lowercase English letters.
     * @param s
     * @param pattern
     * @return
     */
    // time = O(n + m), space = O(n + m)
    public int minStartingIndex(String s, String pattern) {
        int[] pre = exkmp(pattern + s);
        int[] suf = exkmp(reverse(pattern) + reverse(s));
        int m = pattern.length(), n = s.length();
        for (int i = 0, j = m - 1; j < n; i++, j++) {
            if (pre[m + i] + suf[m + (n - 1 - j)] >= m - 1) return i;
        }
        return -1;
    }

    private int[] exkmp(String s) {
        int n = s.length();
        int[] z = new int[n];
        int l = 0, r = 0;
        for (int i = 1; i < n; i++) {
            if (i <= r) z[i] = Math.min(z[i - l], r - i + 1);
            while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i])) {
                l = i;
                r = i + z[i];
                z[i]++;
            }
        }
        return z;
    }

    private String reverse(String s) {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }
}
/**
 * 题目最下面有个进阶问题（连续改至多 k 个），把上式中的 ≥ m − 1 改成 ≥ m − k 即可
 */