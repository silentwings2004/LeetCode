package LC1201_1500;
import java.util.*;
public class LC1297_MaximumNumberofOccurrencesofaSubstring {
    /**
     * Given a string s, return the maximum number of occurrences of any substring under the following rules:
     *
     * The number of unique characters in the substring must be less than or equal to maxLetters.
     * The substring size must be between minSize and maxSize inclusive.
     *
     * Input: s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4
     * Output: 2
     *
     * Input: s = "aaaa", maxLetters = 1, minSize = 3, maxSize = 3
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * 1 <= maxLetters <= 26
     * 1 <= minSize <= maxSize <= min(26, s.length)
     * s consists of only lowercase English letters.
     * @param s
     * @param maxLetters
     * @param minSize
     * @param maxSize
     * @return
     */
    // S1
    // time = O(26 * n), space = O(n)
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int n = s.length(), res = 0;
        int[] cnt = new int[26];
        int t = 0;
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0, j = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            cnt[u]++;
            if (cnt[u] == 1) t++;
            if (i - j + 1 == minSize) {
                if (t <= maxLetters) {
                    String str = s.substring(j, i + 1);
                    map.put(str, map.getOrDefault(str, 0) + 1);
                }
                cnt[s.charAt(j) - 'a']--;
                if (cnt[s.charAt(j) - 'a'] == 0) t--;
                j++;
            }
        }
        for (int v : map.values()) res = Math.max(res, v);
        return res;
    }

    // S2: string hash
    // time = O(n), space = O(n)
    public int maxFreq2(String s, int maxLetters, int minSize, int maxSize) {
        int n = s.length();
        final int P = 131;
        long key = 0, p = 1;
        int m = minSize;
        for (int i = 0; i < minSize; i++) p = p * P;

        HashMap<Long, Integer> map = new HashMap<>();
        int[] cnt = new int[26];
        int res = 0;
        for (int i = 0, t = 0; i < n; i++) {
            key = key * P + s.charAt(i);
            if (cnt[s.charAt(i) - 'a'] == 0) t++;
            cnt[s.charAt(i) - 'a']++;
            if (i >= m) {
                cnt[s.charAt(i - m) - 'a']--;
                if (cnt[s.charAt(i - m) - 'a'] == 0) t--;
                key -= s.charAt(i - m) * p;
            }
            map.put(key, map.getOrDefault(key, 0) + 1);
            if (i >= m - 1 && t <= maxLetters) res = Math.max(res, map.get(key));
        }
        return res;
    }
}