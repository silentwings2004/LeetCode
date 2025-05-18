package LC1501_1800;

public class LC1790_CheckifOneStringSwapCanMakeStringsEqual {
    /**
     * You are given two strings s1 and s2 of equal length. A string swap is an operation where you choose two indices
     * in a string (not necessarily different) and swap the characters at these indices.
     *
     * Return true if it is possible to make both strings equal by performing at most one string swap on exactly one of
     * the strings. Otherwise, return false.
     *
     * Input: s1 = "bank", s2 = "kanb"
     * Output: true
     *
     * Input: s1 = "attack", s2 = "defend"
     * Output: false
     *
     *
     Constraints:

     1 <= s1.length, s2.length <= 100
     s1.length == s2.length
     s1 and s2 consist of only lowercase English letters.
     * @param s1
     * @param s2
     * @return
     */
    // time = O(n), space = O(1)
    public boolean areAlmostEqual(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        if (m != n) return false;

        int cnt = 0, idx1 = -1, idx2 = -1;
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                cnt++;
                if (cnt > 2) return false;
                if (idx1 == -1) idx1 = i;
                else idx2 = i;
            }
        }
        if (cnt == 0) return true;
        if (cnt == 2) {
            if (s1.charAt(idx1) == s2.charAt(idx2) && s1.charAt(idx2) == s2.charAt(idx1)) return true;
        }
        return false;
    }
}
