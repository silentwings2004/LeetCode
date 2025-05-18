package LC3001_3300;
import java.util.*;
public class LC3039_ApplyOperationstoMakeStringEmpty {
    /**
     * You are given a string s.
     *
     * Consider performing the following operation until s becomes empty:
     *
     * For every alphabet character from 'a' to 'z', remove the first occurrence of that character in s (if it exists).
     * For example, let initially s = "aabcbbca". We do the following operations:
     *
     * Remove the underlined characters s = "aabcbbca". The resulting string is s = "abbca".
     * Remove the underlined characters s = "abbca". The resulting string is s = "ba".
     * Remove the underlined characters s = "ba". The resulting string is s = "".
     * Return the value of the string s right before applying the last operation. In the example above, answer is "ba".
     *
     * Input: s = "aabcbbca"
     * Output: "ba"
     *
     * Input: s = "abcd"
     * Output: "abcd"
     *
     * Constraints:
     *
     * 1 <= s.length <= 5 * 10^5
     * s consists only of lowercase English letters.
     * @param s
     * @return
     */
    // time = O(n), space = O(n)
    public String lastNonEmptyString(String s) {
        int[] cnt = new int[26], pos = new int[26];
        int n = s.length(), mc = 0;
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            cnt[u]++;
            pos[u] = i;
            mc = Math.max(mc, cnt[u]);
        }

        List<int[]> q = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (cnt[i] == mc) q.add(new int[]{pos[i], i});
        }
        Collections.sort(q, (o1, o2) -> o1[0] - o2[0]);
        StringBuilder sb = new StringBuilder();
        for (int[] x : q) {
            sb.append((char)('a' + x[1]));
        }
        return sb.toString();
    }
}