package LC3601_3900;

public class LC3675_MinimumOperationstoTransformString {
    /**
     * You are given a string s consisting only of lowercase English letters.
     *
     * You can perform the following operation any number of times (including zero):
     *
     * Choose any character c in the string and replace every occurrence of c with the next lowercase letter in the
     * English alphabet.
     *
     * Return the minimum number of operations required to transform s into a string consisting of only 'a' characters.
     *
     * Note: Consider the alphabet as circular, thus 'a' comes after 'z'.
     *
     * Input: s = "yz"
     * Output: 2
     *
     * Input: s = "a"
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= s.length <= 5 * 10^5
     * s consists only of lowercase English letters.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public int minOperations(String s) {
        int n = s.length(), res = 0;
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            res = Math.max(res, (26 - u) % 26);
        }
        return res;
    }
}