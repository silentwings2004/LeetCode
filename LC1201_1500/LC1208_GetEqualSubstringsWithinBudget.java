package LC1201_1500;

public class LC1208_GetEqualSubstringsWithinBudget {
    /**
     * You are given two strings s and t of the same length and an integer maxCost.
     *
     * You want to change s to t. Changing the ith character of s to ith character of t costs |s[i] - t[i]| (i.e., the
     * absolute difference between the ASCII values of the characters).
     *
     * Return the maximum length of a substring of s that can be changed to be the same as the corresponding substring
     * of t with a cost less than or equal to maxCost. If there is no substring from s that can be changed to its
     * corresponding substring from t, return 0.
     *
     * Input: s = "abcd", t = "bcdf", maxCost = 3
     * Output: 3
     *
     * Input: s = "abcd", t = "cdef", maxCost = 3
     * Output: 1
     *
     * Input: s = "abcd", t = "acde", maxCost = 0
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * t.length == s.length
     * 0 <= maxCost <= 10^6
     * s and t consist of only lowercase English letters.
     * @param s
     * @param t
     * @param maxCost
     * @return
     */
    // time = O(n), space = O(n)
    public int equalSubstring(String s, String t, int maxCost) {
        int n = s.length();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) w[i] = Math.abs(s.charAt(i) - t.charAt(i));
        int res = 0;
        for (int i = 0, j = 0, cost = 0; i < n; i++) {
            cost += w[i];
            while (cost > maxCost) cost -= w[j++];
            res = Math.max(res, i - j + 1);
        }
        return res;
    }
}