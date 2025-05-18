package LC2700_3000;
import java.util.*;
public class LC2839_CheckifStringsCanbeMadeEqualWithOperationsI {
    /**
     * You are given two strings s1 and s2, both of length 4, consisting of lowercase English letters.
     *
     * You can apply the following operation on any of the two strings any number of times:
     *
     * Choose any two indices i and j such that j - i = 2, then swap the two characters at those indices in the string.
     * Return true if you can make the strings s1 and s2 equal, and false otherwise.
     *
     * Input: s1 = "abcd", s2 = "cdab"
     * Output: true
     *
     * Input: s1 = "abcd", s2 = "dacb"
     * Output: false
     *
     * Constraints:
     *
     * s1.length == s2.length == 4
     * s1 and s2 consist only of lowercase English letters.
     * @param s1
     * @param s2
     * @return
     */
    // time = O(1), space = O(1)
    public boolean canBeEqual(String s1, String s2) {
        char[] s = s1.toCharArray(), t = s2.toCharArray();
        if (s[0] > s[2]) swap(s, 0, 2);
        if (s[1] > s[3]) swap(s, 1, 3);
        if (t[0] > t[2]) swap(t, 0, 2);
        if (t[1] > t[3]) swap(t, 1, 3);
        return Arrays.equals(s, t);
    }

    private void swap(char[] s, int i, int j) {
        char t = s[i];
        s[i] = s[j];
        s[j] = t;
    }
}