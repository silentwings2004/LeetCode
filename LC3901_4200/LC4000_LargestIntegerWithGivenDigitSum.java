package LC3901_4200;

public class LC4000_LargestIntegerWithGivenDigitSum {
    /**
     * You are given two non-negative integers n and s.
     *
     * Return the largest integer that has at most n digits and whose sum of digits is s. If no such integer exists,
     * return -1.
     *
     * Input: n = 2, s = 9
     * Output: 90
     *
     * Input: n = 2, s = 19
     * Output: -1
     *
     * Input: n = 5, s = 0
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= n <= 5
     * 0 <= s <= 100
     * @param n
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public int largestInteger(int n, int s) {
        if (s == 0) return 0;
        if (s > 9 * n) return -1;
        int res = 0;
        for (int i = 0; i < n; i++) {
            int x = Math.min(s, 9);
            res = res * 10 + x;
            s -= x;
        }
        return res;
    }
}