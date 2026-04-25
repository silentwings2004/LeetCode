package LC3901_4200;

public class LC3908_ValidDigitNumber {
    /**
     * You are given an integer n and a digit x.
     *
     * A number is considered valid if:
     *
     * It contains at least one occurrence of digit x, and
     * It does not start with digit x.
     * Return true if n is valid, otherwise return false.
     *
     * Input: n = 101, x = 0
     * Output: true
     *
     * Input: n = 232, x = 2
     * Output: false
     *
     * Input: n = 5, x = 1
     * Output: false
     *
     * Constraints:
     *
     * 0 <= n <= 10^5
     * 0 <= x <= 9
     * @param n
     * @param x
     * @return
     */
    // time = O(logn), space = O(1)
    public boolean validDigit(int n, int x) {
        boolean f = false;
        while (n > 0) {
            int v = n % 10;
            n /= 10;
            if (v == x) {
                if (n == 0) return false;
                f = true;
            }
        }
        return f;
    }
}