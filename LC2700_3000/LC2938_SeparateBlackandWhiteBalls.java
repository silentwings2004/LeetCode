package LC2700_3000;

public class LC2938_SeparateBlackandWhiteBalls {
    /**
     * There are n balls on a table, each ball has a color black or white.
     *
     * You are given a 0-indexed binary string s of length n, where 1 and 0 represent black and white balls, respectively.
     *
     * In each step, you can choose two adjacent balls and swap them.
     *
     * Return the minimum number of steps to group all the black balls to the right and all the white balls to the left.
     *
     * Input: s = "101"
     * Output: 1
     *
     * Input: s = "100"
     * Output: 2
     *
     * Input: s = "0111"
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= n == s.length <= 10^5
     * s[i] is either '0' or '1'.
     * @param s
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public long minimumSteps(String s) {
        int n = s.length(), one = 0;
        long res = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') res += one;
            else one++;
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public long minimumSteps2(String s) {
        int n = s.length();
        long res = 0;
        for (int i = 0, j = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '0') {
                res += i - j;
                j++;
            }
        }
        return res;
    }
}