package LC1201_1500;
import java.util.*;
public class LC1433_CheckIfaStringCanBreakAnotherString {
    /**
     * Given two strings: s1 and s2 with the same size, check if some permutation of string s1 can break some
     * permutation of string s2 or vice-versa. In other words s2 can break s1 or vice-versa.
     *
     * A string x can break string y (both of size n) if x[i] >= y[i] (in alphabetical order) for all i between 0 and n-1.
     *
     * Input: s1 = "abc", s2 = "xya"
     * Output: true
     *
     * Input: s1 = "abe", s2 = "acd"
     * Output: false
     *
     * Input: s1 = "leetcodee", s2 = "interview"
     * Output: true
     *
     * Constraints:
     *
     * s1.length == n
     * s2.length == n
     * 1 <= n <= 10^5
     * All strings consist of lowercase English letters.
     * @param s1
     * @param s2
     * @return
     */
    // time = O(nlogn), space = O(n)
    public boolean checkIfCanBreak(String s1, String s2) {
        char[] s = s1.toCharArray(), t = s2.toCharArray();
        Arrays.sort(s);
        Arrays.sort(t);
        return check(s, t) || check(t, s);
    }

    private boolean check(char[] s, char[] t) {
        int n = s.length;
        for (int i = 0; i < n; i++) {
            if (s[i] < t[i]) return false;
        }
        return true;
    }
}