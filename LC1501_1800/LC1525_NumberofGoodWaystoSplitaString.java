package LC1501_1800;
import java.util.*;
public class LC1525_NumberofGoodWaystoSplitaString {
    /**
     * You are given a string s.
     *
     * A split is called good if you can split s into two non-empty strings sleft and sright where their concatenation
     * is equal to s (i.e., sleft + sright = s) and the number of distinct letters in sleft and sright is the same.
     *
     * Return the number of good splits you can make in s.
     *
     * Input: s = "aacaba"
     * Output: 2
     *
     * Input: s = "abcd"
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s consists of only lowercase English letters.
     * @param s
     * @return
     */
    // time = O(n), space = O(n)
    public int numSplits(String s) {
        int n = s.length();
        int[] pre = new int[n + 1], suf = new int[n + 1];
        boolean[] st = new boolean[26];
        for (int i = 1; i <= n; i++) {
            int u = s.charAt(i - 1) - 'a';
            pre[i] = pre[i - 1] + (st[u] ? 0 : 1);
            st[u] = true;
        }
        Arrays.fill(st, false);
        for (int i = n - 1; i >= 0; i--) {
            int u = s.charAt(i) - 'a';
            suf[i] = suf[i + 1] + (st[u] ? 0 : 1);
            st[u] = true;
        }

        int res = 0;
        for (int i = 0; i < n - 1; i++) {
            if (pre[i + 1] == suf[i + 1]) res++;
        }
        return res;
    }
}