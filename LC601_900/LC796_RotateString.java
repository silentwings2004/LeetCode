package LC601_900;

public class LC796_RotateString {
    /**
     * Given two strings s and goal, return true if and only if s can become goal after some number of shifts on s.
     *
     * A shift on s consists of moving the leftmost character of s to the rightmost position.
     *
     * For example, if s = "abcde", then it will be "bcdea" after one shift.
     *
     * Input: s = "abcde", goal = "cdeab"
     * Output: true
     *
     * Constraints:
     *
     * 1 <= s.length, goal.length <= 100
     * s and goal consist of lowercase English letters.
     * @param s
     * @param goal
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public boolean rotateString(String s, String goal) {
        int m = s.length(), n = goal.length();
        if (m != n) return false;
        if (s.equals(goal)) return true;

        s += s;
        return s.indexOf(goal) != -1;
    }

    // S2
    // time = O(n), space = O(n)
    final int N = 210, P = 131;
    long[] h, p;
    public boolean rotateString2(String s, String goal) {
        if (s.equals(goal)) return true;
        if (s.length() != goal.length()) return false;
        h = new long[N];
        p = new long[N];
        p[0] = 1;
        String t = " " + s + goal;
        int n = t.length() - 1;
        for (int i = 1; i <= n; i++) {
            p[i] = p[i - 1] * P;
            h[i] = h[i - 1] * P + t.charAt(i);
        }

        int m = s.length();
        for (int k = 1; k <= m; k++) {
            if (get(1, k) == get(n - k + 1, n) && get(k + 1, m) == get(m + 1, n - k)) return true;
        }
        return false;
    }

    private long get(int l, int r) {
        return h[r] - h[l - 1] * p[r - l + 1];
    }
}