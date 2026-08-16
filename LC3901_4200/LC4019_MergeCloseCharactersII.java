package LC3901_4200;

import java.util.Arrays;

public class LC4019_MergeCloseCharactersII {
    /**
     * You are given a string s consisting of lowercase English letters and an integer k.
     *
     * Two equal characters s[i] and s[j], where 0 <= i < j < s.length, are considered close if j - i <= k. All indices
     * refer to the current string.
     *
     * Repeatedly perform the following operation until no close pair remains:
     *
     * Among all close pairs (i, j), choose the pair with the smallest i. If multiple pairs have the same i, choose the
     * one with the smallest j.
     * Merge the right character into the left character by removing s[j] from s. The character s[i] remains unchanged,
     * and the remaining characters are reindexed.
     * Return the resulting string after performing all possible merges.
     *
     * Input: s = "abca", k = 3
     * Output: "abc"
     *
     * Input: s = "aabca", k = 2
     * Output: "abca"
     *
     * Input: s = "yybyzybz", k = 2
     * Output: "ybzybz"
     *
     * Constraints:
     *
     * 1 <= s.length <= 5 * 10^5
     * 1 <= k <= s.length
     * s consists of lowercase English letters.
     * @param s
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    public String mergeCharacters(String s, int k) {
        int n = s.length();
        int[] stk = new int[n + 1];
        int[] pos = new int[26];
        Arrays.fill(pos, -1);
        int tt = 0;
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            if (pos[u] == -1 || tt + 1 - pos[u] > k) {
                stk[++tt] = i;
                pos[u] = tt;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= tt; i++) sb.append(s.charAt(stk[i]));
        return sb.toString();
    }
}