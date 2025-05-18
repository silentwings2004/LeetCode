package LC2401_2700;

public class LC2443_SumofNumberandItsReverse {
    /**
     * Given a non-negative integer num, return true if num can be expressed as the sum of any non-negative integer
     * and its reverse, or false otherwise.
     *
     * Input: num = 443
     * Output: true
     *
     * Input: num = 63
     * Output: false
     *
     * Input: num = 181
     * Output: true
     *
     * Constraints:
     *
     * 0 <= num <= 10^5
     * @param num
     * @return
     */
    // time = O(1), space = O(1)
    public boolean sumOfNumberAndReverse(int num) {
        int x = num / 2;
        for (int i = x; i <= num; i++) {
            if (i + reverse(i) == num) return true;
        }
        return false;
    }

    private int reverse(int x) {
        int res = 0;
        while (x > 0) {
            int t = x % 10;
            res = res * 10 + t;
            x /= 10;
        }
        return res;
    }
}
