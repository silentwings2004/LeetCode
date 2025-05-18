package LC2401_2700;

public class LC2606_FindtheSubstringWithMaximumCost {
    /**
     * You are given a string s, a string chars of distinct characters and an integer array vals of the same length as
     * chars.
     *
     * The cost of the substring is the sum of the values of each character in the substring. The cost of an empty
     * string is considered 0.
     *
     * The value of the character is defined in the following way:
     *
     * If the character is not in the string chars, then its value is its corresponding position (1-indexed) in the
     * alphabet.
     * For example, the value of 'a' is 1, the value of 'b' is 2, and so on. The value of 'z' is 26.
     * Otherwise, assuming i is the index where the character occurs in the string chars, then its value is vals[i].
     * Return the maximum cost among all substrings of the string s.
     *
     * Input: s = "adaa", chars = "d", vals = [-1000]
     * Output: 2
     *
     * Input: s = "abc", chars = "abc", vals = [-1,-1,-1]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s consist of lowercase English letters.
     * 1 <= chars.length <= 26
     * chars consist of distinct lowercase English letters.
     * vals.length == chars.length
     * -1000 <= vals[i] <= 1000
     * @param s
     * @param chars
     * @param vals
     * @return
     */
    // time = O(1), space = O(1)
    public int maximumCostSubstring(String s, String chars, int[] vals) {
        int n = s.length(), m = chars.length();
        int[] cost = new int[26];
        for (int i = 0; i < 26; i++) cost[i] = i + 1;
        for (int i = 0; i < m; i++) cost[chars.charAt(i) - 'a'] = vals[i];

        int res = 0, last = 0;
        for (int i = 0; i < n; i++) {
            last += cost[s.charAt(i) - 'a'];
            res = Math.max(res, last);
            last = Math.max(last, 0);
        }
        return res;
    }
}