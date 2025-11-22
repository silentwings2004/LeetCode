package LC3601_3900;

public class LC3746_MinimumStringLengthAfterBalancedRemovals {
    /**
     * You are given a string s consisting only of the characters 'a' and 'b'.
     *
     * You are allowed to repeatedly remove any substring where the number of 'a' characters is equal to the number of
     * 'b' characters. After each removal, the remaining parts of the string are concatenated together without gaps.
     *
     * Return an integer denoting the minimum possible length of the string after performing any number of such operations.
     *
     * A substring is a contiguous non-empty sequence of characters within a string.
     *
     * Input: s = "aabbab"
     * Output: 0
     *
     * Input: s = "aaaa"
     * Output: 4
     *
     * Input: s = "aaabb"
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s[i] is either 'a' or 'b'.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public int minLengthAfterRemovals(String s) {
        int n = s.length(), res = 0;
        for (int i = 0; i < n; i++) res += s.charAt(i) == 'a' ? 1 : -1;
        return Math.abs(res);
    }
}