package LC901_1200;

public class LC1100_FindKLengthSubstringsWithNoRepeatedCharacters {
    /**
     * Given a string s and an integer k, return the number of substrings in s of length k with no repeated characters.
     *
     * Input: s = "havefunonleetcode", k = 5
     * Output: 6
     *
     * Input: s = "home", k = 5
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^4
     * s consists of lowercase English letters.
     * 1 <= k <= 10^4
     * @param s
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public int numKLenSubstrNoRepeats(String s, int k) {
        int[] cnt = new int[26];
        int n = s.length(), res = 0;
        for (int i = 0, j = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            cnt[u]++;
            while (cnt[u] > 1) cnt[s.charAt(j++) - 'a']--;
            if (i - j + 1 == k) {
                res++;
                cnt[s.charAt(j++) - 'a']--;
            }
        }
        return res;
    }
}