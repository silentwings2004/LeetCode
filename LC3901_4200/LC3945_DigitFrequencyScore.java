package LC3901_4200;

public class LC3945_DigitFrequencyScore {
    /**
     * You are given an integer n.
     *
     * The score of n is defined as the sum of d * freq(d) over all distinct digits d, where freq(d) denotes the number
     * of times the digit d appears in n.
     *
     * Return an integer denoting the score of n.
     *
     * Input: n = 122
     * Output: 5
     *
     * Input: n = 101
     * Output: 2
     *
     * Constraints:
     * 1 <= n <= 10^9
     * @param n
     * @return
     */
    // time = O(logn), space = O(1)
    public int digitFrequencyScore(int n) {
        int res = 0;
        while (n > 0) {
            res += n % 10;
            n /= 10;
        }
        return res;
    }
}