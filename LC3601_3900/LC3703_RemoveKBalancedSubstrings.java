package LC3601_3900;

public class LC3703_RemoveKBalancedSubstrings {
    /**
     * You are given a string s consisting of '(' and ')', and an integer k.
     *
     * A string is k-balanced if it is exactly k consecutive '(' followed by k consecutive ')', i.e., '(' * k + ')' * k.
     *
     * For example, if k = 3, k-balanced is "((()))".
     *
     * You must repeatedly remove all non-overlapping k-balanced substrings from s, and then join the remaining parts.
     * Continue this process until no k-balanced substring exists.
     *
     * Return the final string after all possible removals.
     *
     * A substring is a contiguous non-empty sequence of characters within a string.
     *
     * Input: s = "(())", k = 1
     * Output: ""
     *
     * Input: s = "(()(", k = 1
     * Output: "(("
     *
     * Input: s = "((()))()()()", k = 3
     * Output: "()()()"
     *
     * Constraints:
     *
     * 2 <= s.length <= 10^5
     * s consists only of '(' and ')'.
     * 1 <= k <= s.length / 2
     * @param s
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    public String removeSubstring(String s, int k) {
        int n = s.length();
        int[][] stk = new int[n + 1][2];
        int tt = 0;

        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) == '(' ? 0 : 1;
            if (tt == 0 || stk[tt][0] != u) stk[++tt] = new int[]{u, 1};
            else stk[tt][1]++;

            if (u == 1 && tt > 1 && stk[tt][1] == k && stk[tt - 1][1] >= k) {
                tt--;
                stk[tt][1] -= k;
                if (stk[tt][1] == 0) tt--;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= tt; i++) {
            char c = stk[i][0] == 0 ? '(' : ')';
            int cnt = stk[i][1];
            for (int j = 0; j < cnt; j++) sb.append(c);
        }
        return sb.toString();
    }
}