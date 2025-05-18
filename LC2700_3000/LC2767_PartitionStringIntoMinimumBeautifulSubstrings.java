package LC2700_3000;
import java.util.*;
public class LC2767_PartitionStringIntoMinimumBeautifulSubstrings {
    /**
     * Given a binary string s, partition the string into one or more substrings such that each substring is beautiful.
     *
     * A string is beautiful if:
     *
     * It doesn't contain leading zeros.
     * It's the binary representation of a number that is a power of 5.
     * Return the minimum number of substrings in such partition. If it is impossible to partition the string s into
     * beautiful substrings, return -1.
     *
     * A substring is a contiguous sequence of characters in a string.
     *
     * Input: s = "1011"
     * Output: 2
     *
     * Input: s = "111"
     * Output: 3
     *
     * Input: s = "0"
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= s.length <= 15
     * s[i] is either '0' or '1'.
     * @param s
     * @return
     */
    // time O(2^n), space = O(2^n)
    int res = 20;
    HashSet<Long> set;
    public int minimumBeautifulSubstrings(String s) {
        if (s.charAt(0) == '0') return -1;
        set = new HashSet<>();
        for (long i = 1; i <= 2 << 15; i *= 5) set.add(i);
        dfs(s, 0, 0);
        return res == 20 ? -1 : res;
    }

    private void dfs(String s, int u, int cnt) {
        if (u == s.length()) {
            res = cnt;
            return;
        }
        if (cnt > res) return;

        for (int i = u; i < s.length(); i++) {
            if (i + 1 < s.length() && s.charAt(i + 1) == '0') continue;
            if (check(s, u, i)) dfs(s, i + 1, cnt + 1);
        }
    }

    private boolean check(String s, int a, int b) {
        long t = 0;
        for (int i = a; i <= b; i++) t = t * 2 + s.charAt(i) - '0';
        return set.contains(t);
    }
}