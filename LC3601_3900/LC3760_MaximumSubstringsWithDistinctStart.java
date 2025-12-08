package LC3601_3900;

public class LC3760_MaximumSubstringsWithDistinctStart {
    /**
     * You are given a string s consisting of lowercase English letters.
     *
     * Return an integer denoting the maximum number of substrings you can split s into such that each substring starts
     * with a distinct character (i.e., no two substrings start with the same character).
     *
     * Input: s = "abab"
     * Output: 2
     *
     * Input: s = "abcd"
     * Output: 4
     *
     * Input: s = "aaaa"
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s consists of lowercase English letters.
     * @param s
     * @return
     */
    // time = O(n), sapce = O(1)
    public int maxDistinct(String s) {
        int n = s.length(), res = 0;
        boolean[] st = new boolean[26];
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            st[u] = true;
            int j = i + 1;
            while (j < n && st[s.charAt(j) - 'a']) j++;
            res++;
            i = j - 1;
        }
        return res;
    }
}