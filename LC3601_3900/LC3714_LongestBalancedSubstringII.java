package LC3601_3900;
import java.util.*;
public class LC3714_LongestBalancedSubstringII {
    /**
     * You are given a string s consisting only of the characters 'a', 'b', and 'c'.
     *
     * A substring of s is called balanced if all distinct characters in the substring appear the same number of times.
     *
     * Return the length of the longest balanced substring of s.
     *
     * A substring is a contiguous non-empty sequence of characters within a string.
     *
     * Input: s = "abbac"
     * Output: 4
     *
     * Input: s = "aabcc"
     * Output: 3
     *
     * Input: s = "aba"
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s contains only the characters 'a', 'b', and 'c'.
     * @param s
     * @return
     */
    // time = O(n), space = O(n)
    public int longestBalanced(String s) {
        int n = s.length(), res = 0;
        // Case 1: 1 char
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && s.charAt(j) == s.charAt(j - 1)) j++;
            res = Math.max(res, j - i);
            i = j - 1;
        }

        // Case 2: 2 chars
        res = Math.max(res, f(s, 'a', 'b'));
        res = Math.max(res, f(s, 'a', 'c'));
        res = Math.max(res, f(s, 'b', 'c'));

        // Case 3: 3 chars
        HashMap<Long, Integer> map = new HashMap<>();
        map.put((1L * n) << 32 | n, -1);
        int[] cnt = new int[3];
        for (int i = 0; i < n; i++) {
            cnt[s.charAt(i) - 'a']++;
            long p = (1L * cnt[0] - cnt[1] + n) << 32 | (cnt[1] - cnt[2] + n);
            if (map.containsKey(p)) res = Math.max(res, i - map.get(p));
            else map.put(p, i);
        }
        return res;
    }

    private int f(String s, char x, char y) {
        int n = s.length(), res = 0;
        for (int i = 0; i < n; i++) {
            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(0, i - 1);
            int d = 0;
            for (; i < n && (s.charAt(i) == x || s.charAt(i) == y); i++) {
                d += s.charAt(i) == x ? 1 : -1;
                if (map.containsKey(d)) res = Math.max(res, i - map.get(d));
                else map.put(d, i);
            }
        }
        return res;
    }
}