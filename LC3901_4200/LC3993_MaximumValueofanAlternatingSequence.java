package LC3901_4200;

public class LC3993_MaximumValueofanAlternatingSequence {
    /**
     * You are given three integers n, s, and m.
     *
     * A sequence seq of integers of length n is considered valid if:
     *
     * seq[0] = s.
     * The sequence is alternating, meaning that either:
     * seq[0] > seq[1] < seq[2] > ..., or
     * seq[0] < seq[1] > seq[2] < ....
     * For every adjacent pair, |seq[i] - seq[i - 1]| <= m.
     * A sequence of length 1 is considered alternating.
     *
     * Return the maximum possible element that can appear in any valid sequence.
     *
     * Input: n = 4, s = 3, m = 5
     * Output: 12
     *
     * Input: n = 2, s = 4, m = 3
     * Output: 7
     *
     * Constraints:
     *
     * 1 <= n, s <= 10^9
     * 1 <= m <= 10^5
     * @param n
     * @param s
     * @param m
     * @return
     */
    // S1
    // time = O(1), space = O(1)
    public long maximumValue(int n, int s, int m) {
        if (n == 1) return s;
        int k = n - 1 - n / 2;
        if ((n - 1) % 2 == 0) k--;
        return s + 1L * m * (n / 2) - k;
    }

    // S1
    // time = O(1), space = O(1)
    public long maximumValue2(int n, int s, int m) {
        if (n == 1) return s;
        return s + m + 1L * (m - 1) * ((n - 2) / 2);
    }
}
/**
 * 从 n=2 开始，n 每增加 2，最大值就增加 m−1
 */