package LC1201_1500;

public class LC1432_MaxDifferenceYouCanGetFromChanginganInteger {
    /**
     * You are given an integer num. You will apply the following steps exactly two times:
     *
     * Pick a digit x (0 <= x <= 9).
     * Pick another digit y (0 <= y <= 9). The digit y can be equal to x.
     * Replace all the occurrences of x in the decimal representation of num by y.
     * The new integer cannot have any leading zeros, also the new integer cannot be 0.
     * Let a and b be the results of applying the operations to num the first and second times, respectively.
     *
     * Return the max difference between a and b.
     *
     * Input: num = 555
     * Output: 888
     *
     * Input: num = 9
     * Output: 8
     *
     * Constraints:
     *
     * 1 <= num <= 10^8
     * @param num
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public int maxDiff(int num) {
        int a = Integer.MAX_VALUE, b = Integer.MIN_VALUE;
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                char[] s = get(num, i, j);
                if (s[0] != '0') {
                    int x = Integer.parseInt(String.valueOf(s));
                    a = Math.min(a, x);
                    b = Math.max(b, x);
                }
            }
        }
        return b - a;
    }

    private char[] get(int num, int x, int y) {
        char[] s = String.valueOf(num).toCharArray();
        int n = s.length;
        for (int i = 0; i < n; i++) {
            int u = s[i] - '0';
            if (u == x) s[i] = (char)('0' + y);
        }
        return s;
    }

    // S2
    // time = O(n), space = O(n)
    public int maxDiff2(int num) {
        int x = get(num, 9), y = get(num, 1), z = get(num, 0);
        return x - Math.min(y, z);
    }

    private int get(int x, int v) {
        char[] s = String.valueOf(x).toCharArray();
        int n = s.length, t = -1;
        int e = s[0] - '0';
        for (int i = 0; i < n; i++) {
            int u = s[i] - '0';
            if (u != v) {
                if (v == 0 && u == e) continue;
                if (t == -1) t = u;
                if (u == t) s[i] = (char)('0' + v);
            }
        }
        return Integer.parseInt(String.valueOf(s));
    }
}