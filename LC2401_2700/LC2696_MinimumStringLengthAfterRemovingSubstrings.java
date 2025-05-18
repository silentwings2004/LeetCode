package LC2401_2700;

public class LC2696_MinimumStringLengthAfterRemovingSubstrings {
    /**
     * You are given a string s consisting only of uppercase English letters.
     *
     * You can apply some operations to this string where, in one operation, you can remove any occurrence of one of the
     * substrings "AB" or "CD" from s.
     *
     * Return the minimum possible length of the resulting string that you can obtain.
     *
     * Note that the string concatenates after removing the substring and could produce new "AB" or "CD" substrings.
     *
     * Input: s = "ABFCACDB"
     * Output: 2
     *
     * Input: s = "ACBBD"
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= s.length <= 100
     * s consists only of uppercase English letters.
     * @param s
     * @return
     */
    // time = O(n), space = O(n)
    public int minLength(String s) {
        int n = s.length();
        char[] stk = new char[n + 1];
        int tt = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (tt > 0 && (c == 'B' && stk[tt] == 'A' || c == 'D' && stk[tt] == 'C')) tt--;
            else stk[++tt] = c;
        }
        return tt;
    }
}