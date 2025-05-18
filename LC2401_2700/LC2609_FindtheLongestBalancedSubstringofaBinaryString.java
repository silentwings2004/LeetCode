package LC2401_2700;

public class LC2609_FindtheLongestBalancedSubstringofaBinaryString {
    /**
     * You are given a binary string s consisting only of zeroes and ones.
     *
     * A substring of s is considered balanced if all zeroes are before ones and the number of zeroes is equal to the
     * number of ones inside the substring. Notice that the empty substring is considered a balanced substring.
     *
     * Return the length of the longest balanced substring of s.
     *
     * A substring is a contiguous sequence of characters within a string.
     *
     * Input: s = "01000111"
     * Output: 6
     *
     * Input: s = "00111"
     * Output: 4
     *
     * Input: s = "111"
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= s.length <= 50
     * '0' <= s[i] <= '1'
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public int findTheLongestBalancedSubstring(String s) {
        int n = s.length(), res = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                int j = i;
                while (j < n && s.charAt(j) == '0') j++;
                int zero = j - i;
                if (j == n) break;
                int k = j;
                while (k < n && s.charAt(k) == '1') k++;
                int one = k - j;
                res = Math.max(res, Math.min(zero, one) * 2);
                i = k - 1;
            }
        }
        return res;
    }
}