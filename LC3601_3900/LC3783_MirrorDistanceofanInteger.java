package LC3601_3900;

public class LC3783_MirrorDistanceofanInteger {
    /**
     * You are given an integer n.
     *
     * Define its mirror distance as: abs(n - reverse(n)) where reverse(n) is the integer formed by reversing the digits
     * of n.
     *
     * Return an integer denoting the mirror distance of n.
     *
     * abs(x) denotes the absolute value of x.
     *
     * Input: n = 25
     * Output: 27
     *
     * Input: n = 10
     * Output: 9
     *
     * Input: n = 7
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= n <= 10^9
     * @param n
     * @return
     */
    // time = O(logn), space = O(1)
    public int mirrorDistance(int n) {
        return Math.abs(n - rev(n));
    }

    private int rev(int n) {
        int v = 0;
        while (n > 0) {
            v = v * 10 + n % 10;
            n /= 10;
        }
        return v;
    }
}