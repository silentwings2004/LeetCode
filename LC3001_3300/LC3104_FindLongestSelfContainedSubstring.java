package LC3001_3300;
import java.util.*;
public class LC3104_FindLongestSelfContainedSubstring {
    /**
     * Given a string s, your task is to find the length of the longest self-contained
     * substring
     *  of s.
     *
     * A substring t of a string s is considered self-contained if t != s and for every character in t, it doesn't exist
     * in the rest of s.
     *
     * Return the length of the longest self-contained substring of s if it exists, otherwise, return -1.
     *
     * Input: s = "abba"
     * Output: 2
     *
     * Input: s = "abab"
     * Output: -1
     *
     * Input: s = "abacd"
     * Output: 4
     *
     * Constraints:
     *
     * 2 <= s.length <= 5 * 10^4
     * s consists only of lowercase English letters.
     * @param s
     * @return
     */
    // time = O(n + 26^3), space = O(26 * n)
    public int maxSubstringLength(String s) {
        int n = s.length();
        int[][] cnt = new int[n + 1][26], pos = new int[26][2];
        for (int i = 0; i < 26; i++) Arrays.fill(pos[i], -1);
        for (int i = 1; i <= n; i++) {
            int u = s.charAt(i - 1) - 'a';
            cnt[i] = cnt[i - 1].clone();
            cnt[i][u] = cnt[i - 1][u] + 1;
            pos[u][1] = i - 1;
            if (pos[u][0] == -1) pos[u][0] = i - 1;
        }

        int res = -1;
        for (int i = 0; i < 26; i++) {
            if (pos[i][0] == -1) continue;
            int l = pos[i][0];
            for (int j = 0; j < 26; j++) {
                if (pos[j][0] == -1 || pos[j][0] < l) continue;
                int r = Math.max(pos[i][1], pos[j][1]);
                if (r - l + 1 == n) continue;
                if (check(cnt, l, r)) res = Math.max(res, r - l + 1);
            }
        }
        return res;
    }

    private boolean check(int[][] cnt, int l, int r) {
        int n = cnt.length - 1;
        for (int i = 0; i < 26; i++) {
            int t = cnt[r + 1][i] - cnt[l][i];
            if (t == 0 || t == cnt[n][i]) continue;
            return false;
        }
        return true;
    }
}