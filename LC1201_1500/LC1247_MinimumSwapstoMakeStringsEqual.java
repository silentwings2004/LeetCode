package LC1201_1500;

public class LC1247_MinimumSwapstoMakeStringsEqual {
    /**
     * You are given two strings s1 and s2 of equal length consisting of letters "x" and "y" only. Your task is to make
     * these two strings equal to each other. You can swap any two characters that belong to different strings, which
     * means: swap s1[i] and s2[j].
     *
     * Return the minimum number of swaps required to make s1 and s2 equal, or return -1 if it is impossible to do so.
     *
     * Input: s1 = "xx", s2 = "yy"
     * Output: 1
     *
     * Input: s1 = "xy", s2 = "yx"
     * Output: 2
     *
     * Input: s1 = "xx", s2 = "xy"
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= s1.length, s2.length <= 1000
     * s1, s2 only contain 'x' or 'y'.
     * @param s1
     * @param s2
     * @return
     */
    // time = O(n), space = O(1)
    public int minimumSwap(String s1, String s2) {
        int n = s1.length(), a = 0, b = 0;
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) == s2.charAt(i)) continue;
            if (s1.charAt(i) == 'x') a++;
            else b++;
        }
        if ((a + b) % 2 == 1) return -1;
        if (a % 2 == 1) return (a + b) / 2 + 1;
        return (a + b) / 2;
    }
}