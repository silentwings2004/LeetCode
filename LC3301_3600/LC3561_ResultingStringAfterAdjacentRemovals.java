package LC3301_3600;

public class LC3561_ResultingStringAfterAdjacentRemovals {
    /**
     * You are given a string s consisting of lowercase English letters.
     *
     * You must repeatedly perform the following operation while the string s has at least two consecutive characters:
     *
     * Remove the leftmost pair of adjacent characters in the string that are consecutive in the alphabet, in either
     * order (e.g., 'a' and 'b', or 'b' and 'a').
     * Shift the remaining characters to the left to fill the gap.
     * Return the resulting string after no more operations can be performed.
     *
     * Note: Consider the alphabet as circular, thus 'a' and 'z' are consecutive.
     *
     * Input: s = "abc"
     * Output: "c"
     *
     * Input: s = "adcb"
     * Output: ""
     *
     * Input: s = "zadb"
     * Output: "db"
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s consists only of lowercase English letters.
     * @param s
     * @return
     */
    // time = O(n), space = O(n)
    public String resultingString(String s) {
        int n = s.length();
        char[] stk = new char[n + 1];
        int tt = 0;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (tt > 0 && check(stk[tt], c)) tt--;
            else stk[++tt] = c;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= tt; i++) sb.append(stk[i]);
        return sb.toString();
    }

    private boolean check(char a, char b) {
        return Math.abs(a - b) == 1 || Math.abs(a - b) == 25;
    }
}