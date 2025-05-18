package LC1501_1800;

public class LC1780_CheckifNumberisaSumofPowersofThree {
    /**
     * Given an integer n, return true if it is possible to represent n as the sum of distinct powers of three.
     * Otherwise, return false.
     *
     * An integer y is a power of three if there exists an integer x such that y == 3^x.
     *
     * Input: n = 12
     * Output: true
     *
     * Input: n = 91
     * Output: true
     *
     * Input: n = 21
     * Output: false
     *
     * Constraints:
     *
     * 1 <= n <= 10^7
     * @param n
     * @return
     */
    // time = O(logn), space = O(1)
    public boolean checkPowersOfThree(int n) {
        while (n > 0) {
            if (n % 3 == 2) return false;
            n /= 3;
        }
        return true;
    }
}