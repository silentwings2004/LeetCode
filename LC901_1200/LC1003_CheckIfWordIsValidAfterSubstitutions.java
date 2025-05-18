package LC901_1200;

public class LC1003_CheckIfWordIsValidAfterSubstitutions {
    /**
     * Given a string s, determine if it is valid.
     *
     * A string s is valid if, starting with an empty string t = "", you can transform t into s after performing the
     * following operation any number of times:
     *
     * Insert string "abc" into any position in t. More formally, t becomes tleft + "abc" + tright, where t == tleft +
     * tright. Note that tleft and tright may be empty.
     * Return true if s is a valid string, otherwise, return false.
     *
     * Input: s = "aabcbc"
     * Output: true
     *
     * Input: s = "abcabcababcc"
     * Output: true
     *
     * Input: s = "abccba"
     * Output: false
     *
     * Constraints:
     *
     * 1 <= s.length <= 2 * 10^4
     * s consists of letters 'a', 'b', and 'c'
     * @param s
     * @return
     */
    // S1: brute-force
    // time = O(n), space = O(n)
    public boolean isValid(String s) {
        String t = "abc";
        while (s.length() > 0) {
            int pos = s.indexOf(t);
            if (pos == -1) return false;
            s = s.substring(0, pos) + s.substring(pos + 3);
        }
        return s.length() == 0;
    }

    // S2: stack
    // time = O(n), space = O(n)
    final int N = 20010;
    public boolean isValid2(String s) {
        char[] stk = new char[N];
        int n = s.length(), tt = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            stk[++tt] = c;
            if (tt >= 3 && stk[tt] == 'c' && stk[tt - 1] == 'b' && stk[tt - 2] == 'a') tt -= 3;
        }
        return tt == 0;
    }
}
/**
 * 本质就是寻找合法括号序列 => stack
 * 3个字母，递推
 */