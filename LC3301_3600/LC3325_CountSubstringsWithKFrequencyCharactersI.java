package LC3301_3600;

public class LC3325_CountSubstringsWithKFrequencyCharactersI {
    /**
     * Given a string s and an integer k, return the total number of substrings of s where at least one character
     * appears at least k times.
     *
     * Input: s = "abacb", k = 2
     * Output: 4
     *
     * Input: s = "abcde", k = 1
     * Output: 15
     *
     * Constraints:
     *
     * 1 <= s.length <= 3000
     * 1 <= k <= s.length
     * s consists only of lowercase English letters.
     * @param s
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public int numberOfSubstrings(String s, int k) {
        int n = s.length(), res = 0;
        int[] cnt = new int[26];
        for (int i = 0, j = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            cnt[u]++;
            while (cnt[u] >= k) {
                res += n - i;
                cnt[s.charAt(j++) - 'a']--;
            }
        }
        return res;
    }
}