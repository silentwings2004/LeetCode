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
    // time = O(n), space = O(n)
    public int maxDiff(int num) {
        char[] s = String.valueOf(num).toCharArray();
        int minv = Integer.MAX_VALUE, maxv = Integer.MIN_VALUE;
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                char[] t = get(s.clone(), i, j);
                if (t[0] != '0') {
                    int n = Integer.parseInt(String.valueOf(t));
                    minv = Math.min(minv, n);
                    maxv = Math.max(maxv, n);
                }
            }
        }
        return maxv - minv;
    }

    private char[] get(char[] s, int x, int y) {
        for (int i = 0; i < s.length; i++) {
            if (s[i] - '0' == x) s[i] = (char)(y + '0');
        }
        return s;
    }
}