package LC901_1200;

public class LC1021_RemoveOutermostParentheses {
    /**
     * A valid parentheses string is either empty "", "(" + A + ")", or A + B, where A and B are valid parentheses
     * strings, and + represents string concatenation.
     *
     * For example, "", "()", "(())()", and "(()(()))" are all valid parentheses strings.
     * A valid parentheses string s is primitive if it is nonempty, and there does not exist a way to split it into
     * s = A + B, with A and B nonempty valid parentheses strings.
     *
     * Given a valid parentheses string s, consider its primitive decomposition: s = P1 + P2 + ... + Pk, where Pi are
     * primitive valid parentheses strings.
     *
     * Return s after removing the outermost parentheses of every primitive string in the primitive decomposition of s.
     *
     * Input: s = "(()())(())"
     * Output: "()()()"
     *
     * Input: s = "(()())(())(()(()))"
     * Output: "()()()()(())"
     *
     * Input: s = "()()"
     * Output: ""
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s[i] is either '(' or ')'.
     * s is a valid parentheses string.
     * @param s
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public String removeOuterParentheses(String s) {
        int delta = 0;
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                if (delta != 0) sb.append(c);
                delta++;
            } else if (c == ')') {
                if (delta != 1) sb.append(c);
                delta--;
            }
        }
        return sb.toString();
    }

    // S2
    // time = O(n), space = O(n)
    public String removeOuterParentheses2(String s) {
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        for (int i = 0, j = 0, cnt = 0; i < n; i++) {
            if (s.charAt(i) == '(') cnt++;
            else if (--cnt == 0) {
                sb.append(s.substring(j + 1, i));
                j = i + 1;
            }
        }
        return sb.toString();
    }
}