package LC3001_3300;

public class LC3090_MaximumLengthSubstringWithTwoOccurrences {
    /**
     * Given a string s, return the maximum length of a substring such that it contains at most two occurrences of each
     * character.
     *
     * Input: s = "bcbbbcba"
     * Output: 4
     *
     * Input: s = "aaaa"
     * Output: 2
     *
     * Constraints:
     *
     * 2 <= s.length <= 100
     * s consists only of lowercase English letters.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public int maximumLengthSubstring(String s) {
        int n = s.length(), res = 0;
        int[] cnt = new int[26];
        for (int i = 0, j = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            cnt[u]++;
            while (cnt[u] > 2) {
                cnt[s.charAt(j++) - 'a']--;
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }
}