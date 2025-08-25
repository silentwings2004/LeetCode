package LC3601_3900;

public class LC3662_FilterCharactersbyFrequency {
    /**
     * You are given a string s consisting of lowercase English letters and an integer k.
     *
     * Your task is to construct a new string that contains only those characters from s which appear fewer than k times
     * in the entire string. The order of characters in the new string must be the same as their order in s.
     *
     * Return the resulting string. If no characters qualify, return an empty string.
     *
     * Note: Every occurrence of a character that occurs fewer than k times is kept.
     *
     * Input: s = "aadbbcccca", k = 3
     * Output: "dbb"
     *
     * Input: s = "xyz", k = 2
     * Output: "xyz"
     *
     * Constraints:
     *
     * 1 <= s.length <= 100
     * s consists of lowercase English letters.
     * 1 <= k <= s.length
     * @param s
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    public String filterCharacters(String s, int k) {
        int n = s.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            cnt[u]++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            if (cnt[u] < k) sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}