package LC3601_3900;

public class LC3747_CountDistinctIntegersAfterRemovingZeros {
    /**
     * You are given a positive integer n.
     *
     * For every integer x from 1 to n, we write down the integer obtained by removing all zeros from the decimal
     * representation of x.
     *
     * Return an integer denoting the number of distinct integers written down.
     *
     * Input: n = 10
     * Output: 9
     *
     * Input: n = 3
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= n <= 10^15
     * @param n
     * @return
     */
    // time = O(logn), space = O(logn)
    public long countDistinct(long n) {
        String s = String.valueOf(n);
        int m = s.length();
        long pow9 = (long)Math.pow(9, m), res = (pow9 - 9) / 8;
        for (int i = 0; i < m; i++) {
            int u = s.charAt(i) - '0';
            if (u == 0) break;
            long v = u - 1;
            if (i == m - 1) v++;
            pow9 /= 9;
            res += v * pow9;
        }
        return res;
    }
}
/**
 * len = 1 => 1 ~ 9
 * len = 2 => 11 ~ 99 => 9^2 = 81
 * ...
 * len = m  <= n
 * n = 234
 */