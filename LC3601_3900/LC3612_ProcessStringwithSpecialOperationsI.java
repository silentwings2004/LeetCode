package LC3601_3900;

public class LC3612_ProcessStringwithSpecialOperationsI {
    /**
     * You are given a string s consisting of lowercase English letters and the special characters: *, #, and %.
     *
     * Build a new string result by processing s according to the following rules from left to right:
     *
     * If the letter is a lowercase English letter append it to result.
     * A '*' removes the last character from result, if it exists.
     * A '#' duplicates the current result and appends it to itself.
     * A '%' reverses the current result.
     * Return the final string result after processing all characters in s.
     *
     * Input: s = "a#b%*"
     * Output: "ba"
     *
     * Input: s = "z*#"
     * Output: ""
     *
     * Constraints:
     *
     * 1 <= s.length <= 20
     * s consists of only lowercase English letters and special characters *, #, and %.
     * @param s
     * @return
     */
    // time = O(2^n), space = O(n)
    public String processStr(String s) {
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isLowerCase(c)) sb.append(c);
            else if (c == '*') {
                if (sb.length() > 0) sb.setLength(sb.length() - 1);
            } else if (c == '#') sb.append(sb.toString());
            else if (c == '%') sb.reverse();
        }
        return sb.toString();
    }
}