package LC2700_3000;

public class LC2734_LexicographicallySmallestStringAfterSubstringOperation {
    /**
     * You are given a string s consisting of only lowercase English letters. In one operation, you can do the following:
     *
     * Select any non-empty substring of s, possibly the entire string, then replace each one of its characters with the
     * previous character of the English alphabet. For example, 'b' is converted to 'a', and 'a' is converted to 'z'.
     * Return the lexicographically smallest string you can obtain after performing the above operation exactly once.
     *
     * A substring is a contiguous sequence of characters in a string.
     *
     * A string x is lexicographically smaller than a string y of the same length if x[i] comes before y[i] in
     * alphabetic order for the first position i such that x[i] != y[i].
     *
     * Input: s = "cbabc"
     * Output: "baabc"
     *
     * Input: s = "acbbc"
     * Output: "abaab"
     *
     * Input: s = "leetcode"
     * Output: "kddsbncd"
     *
     * Constraints:
     *
     * 1 <= s.length <= 3 * 10^5
     * s consists of lowercase English letters
     * @param s
     * @return
     */
    // time = O(n), space = O(n)
    public String smallestString(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'a') continue;
            flag = true;
            int j = i;
            while (j < n && chars[j] > 'a') {
                chars[j] = (char)(chars[j] - 1);
                j++;
            }
            break;
        }
        if (!flag) chars[n - 1] = 'z';
        return String.valueOf(chars);
    }
}