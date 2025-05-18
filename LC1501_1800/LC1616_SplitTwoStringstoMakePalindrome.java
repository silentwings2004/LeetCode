package LC1501_1800;

public class LC1616_SplitTwoStringstoMakePalindrome {
    /**
     * You are given two strings a and b of the same length. Choose an index and split both strings at the same index,
     * splitting a into two strings: aprefix and asuffix where a = aprefix + asuffix, and splitting b into two strings:
     * bprefix and bsuffix where b = bprefix + bsuffix. Check if aprefix + bsuffix or bprefix + asuffix forms a palindrome.
     *
     * When you split a string s into sprefix and ssuffix, either ssuffix or sprefix is allowed to be empty. For
     * example, if s = "abc", then "" + "abc", "a" + "bc", "ab" + "c" , and "abc" + "" are valid splits.
     *
     * Return true if it is possible to form a palindrome string, otherwise return false.
     *
     * Notice that x + y denotes the concatenation of strings x and y.
     *
     * Input: a = "x", b = "y"
     * Output: true
     *
     * Input: a = "xbdef", b = "xecab"
     * Output: false
     *
     * Input: a = "ulacfd", b = "jizalu"
     * Output: true
     *
     * Constraints:
     *
     * 1 <= a.length, b.length <= 10^5
     * a.length == b.length
     * a and b consist of lowercase English letters
     * @param a
     * @param b
     * @return
     */
    // time = O(n), space = O(1)
    public boolean checkPalindromeFormation(String a, String b) {
        int n = a.length();
        if (n == 1) return true;
        int i, j;
        for (i = 0, j = n - 1; i < j; i++, j--) {
            if (a.charAt(i) == b.charAt(j)) continue;
            else {
                if (check(a, i, j) || check(b, i, j)) return true;
                else break;
            }
        }
        if (i >= j) return true;
        for (i = n - 1, j = 0; j < i; i--, j++) {
            if (a.charAt(i) == b.charAt(j)) continue;
            else {
                if (check(a, j, i) || check(b, j, i)) return true;
                else break;
            }
        }
        return j >= i;
    }

    private boolean check(String s, int l, int r) {
        for (int i = l, j = r; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) return false;
        }
        return true;
    }
}