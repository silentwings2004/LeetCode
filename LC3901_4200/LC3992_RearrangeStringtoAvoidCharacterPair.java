package LC3901_4200;

public class LC3992_RearrangeStringtoAvoidCharacterPair {
    /**
     * You are given a string s and two distinct lowercase English letters x and y.
     *
     * Rearrange the characters of s to construct a new string t such that:
     *
     * t is a permutation of s.
     * Every occurrence of y appears before every occurrence of x in t.
     * Return any valid string t.
     *
     * Input: s = "aabc", x = "a", y = "c"
     * Output: "cbaa"
     *
     * Input: s = "dcab", x = "d", y = "b"
     * Output: "cabd"
     *
     * Input: s = "axe", x = "o", y = "x"
     * Output: "axe"
     *
     * Constraints:
     *
     * 1 <= s.length <= 100
     * s consists of lowercase English letters.
     * x and y are lowercase English letters.
     * x != y
     * @param s
     * @param x
     * @param y
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public String rearrangeString(String s, char x, char y) {
        int n = s.length(), a = 0, b = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == x) a++;
            else if (c == y) b++;
            else sb.append(c);
        }
        return String.valueOf(y).repeat(b) + String.valueOf(x).repeat(a) + sb.toString();
    }

    // S2
    // time = O(n), space = O(n)
    public String rearrangeString2(String s, char x, char y) {
        char[] t = s.toCharArray();
        int n = t.length, l = 0, r = n - 1;
        while (l < r) {
            if (t[l] != x) l++;
            else if (t[r] != y) r--;
            else {
                char tmp = t[l];
                t[l] = t[r];
                t[r] = tmp;
                l++;
                r--;
            }
        }
        return new String(t);
    }
}