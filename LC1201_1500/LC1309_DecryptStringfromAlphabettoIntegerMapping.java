package LC1201_1500;

public class LC1309_DecryptStringfromAlphabettoIntegerMapping {
    /**
     * You are given a string s formed by digits and '#'. We want to map s to English lowercase characters as follows:
     *
     * Characters ('a' to 'i') are represented by ('1' to '9') respectively.
     * Characters ('j' to 'z') are represented by ('10#' to '26#') respectively.
     * Return the string formed after mapping.
     *
     * The test cases are generated so that a unique mapping will always exist.
     *
     * Input: s = "10#11#12"
     * Output: "jkab"
     *
     * Input: s = "1326#"
     * Output: "acz"
     *
     * Constraints:
     *
     * 1 <= s.length <= 1000
     * s consists of digits and the '#' letter.
     * s will be a valid string such that mapping is always possible.
     * @param s
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public String freqAlphabets(String s) {
        int n = s.length();
        int[] stk = new int[n + 1];
        int tt = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c != '#') stk[++tt] = c - '0';
            else {
                int b = stk[tt], a = stk[tt - 1];
                tt -= 2;
                stk[++tt] = a * 10 + b;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= tt; i++) sb.append((char)(stk[i] - 1 + 'a'));
        return sb.toString();
    }

    // S2
    // time = O(n), space = O(n)
    public String freqAlphabets2(String s) {
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (i + 2 < n && s.charAt(i + 2) == '#') {
                sb.append((char)('j' + Integer.parseInt(s.substring(i, i + 2)) - 10));
                i += 2;
            } else {
                sb.append((char)('a' + s.charAt(i) - '1'));
            }
        }
        return sb.toString();
    }
}