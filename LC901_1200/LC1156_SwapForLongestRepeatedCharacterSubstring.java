package LC901_1200;
import java.util.*;
public class LC1156_SwapForLongestRepeatedCharacterSubstring {
    /**
     * You are given a string text. You can swap two of the characters in the text.
     *
     * Return the length of the longest substring with repeated characters.
     *
     * Input: text = "ababa"
     * Output: 3
     *
     * Input: text = "aaabaaa"
     * Output: 6
     *
     * Input: text = "aaaaa"
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= text.length <= 2 * 10^4
     * text consist of lowercase English characters only.
     * @param text
     * @return
     */
    // time = O(n), space = O(n)
    public int maxRepOpt1(String text) {
        int n = text.length();
        List<Integer>[] p = new List[26];
        for (int i = 0; i < 26; i++) p[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) p[text.charAt(i) - 'a'].add(i);

        int res = 0;
        for (List<Integer> q : p) {
            int m = q.size();
            for (int i = 0, j = 0; i < m; i++) {
                while (q.get(i) - q.get(j) > i - j + 1) j++;
                if (i + 1 < m || j > 0) res = Math.max(res, q.get(i) - q.get(j) + 1);
            }
            for (int i = 0, j = 0; i < m; i++) {
                while (q.get(i) - q.get(j) > i - j) j++;
                int t = q.get(i) - q.get(j) + 1;
                if (i + 1 < m || j > 0) t++;
                res = Math.max(res, t);
            }
        }
        return res;
    }

    // S2
    // time = O(n), space = O(n)
    public int maxRepOpt12(String text) {
        int n = text.length(), res = 0;
        int[] cnt = new int[26], last = new int[26];
        for (int i = 0; i < n; i++) {
            char c = text.charAt(i);
            int j = i + 1;
            while (j < n && text.charAt(j) == c) j++;
            cnt[c - 'a']++;
            i = j - 1;
        }

        for (int i = 0; i < n; i++) {
            char c = text.charAt(i);
            int j = i + 1;
            while (j < n && text.charAt(j) == c) j++;
            int len = j - i;
            if (i >= 2 && text.charAt(i - 2) == c) res = Math.max(res, len + last[c - 'a'] + (cnt[c - 'a'] == 2 ? 0 : 1));
            else res = Math.max(res, len + (cnt[c - 'a'] == 1 ? 0 : 1));
            last[c - 'a'] = len;
            i = j - 1;
        }
        return res;
    }
}