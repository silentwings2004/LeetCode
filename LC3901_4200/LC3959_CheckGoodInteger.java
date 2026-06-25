package LC3901_4200;

public class LC3959_CheckGoodInteger {
    /**
     * You are given a positive integer n.
     *
     * Let digitSum be the sum of the digits of n, and let squareSum be the sum of the squares of the digits of n.
     *
     * An integer is called good if squareSum - digitSum >= 50.
     *
     * Return true if n is good. Otherwise, return false.
     *
     * Input: n = 1000
     * Output: false
     *
     * Input: n = 19
     * Output: true
     *
     * Constraints:
     *
     * 1 <= n <= 10^9
     * @param n
     * @return
     */
    // time = O(logn), space = O(1)
    public boolean checkGoodInteger(int n) {
        int s1 = 0, s2 = 0;
        while (n > 0) {
            int x = n % 10;
            s1 += x;
            s2 += x * x;
            n /= 10;
        }
        return s2 - s1 >= 50;
    }
}