package LC3901_4200;

public class LC4078_ThreeFibonacciSum {
    /**
     * You are given an integer n.
     *
     * The Fibonacci sequence starts with 0 and 1, and each subsequent number is the sum of the previous two numbers.
     * Its first few terms are 0, 1, 1, 2, 3, 5, 8, 13, ....
     *
     * Return true if n can be expressed as the sum of three consecutive terms in the Fibonacci sequence, and false
     * otherwise.
     *
     * Input: n = 16
     * Output: true
     *
     * Input: n = 8
     * Output: false
     *
     * Input: n = 2
     * Output: true
     *
     * Constraints:
     *
     * 1 <= n <= 10^9
     * @param n
     * @return
     */
    // time = O(logn), space = O(1)
    public boolean threeFibonacciSum(int n) {
        if (n % 2 != 0) return false;
        int a = 0, b = 1;
        while (b <= n) {
            int c = a + b;
            if (c * 2 == n) return true;
            a = b;
            b = c;
        }
        return false;
    }
}
/**
 * F[n] = F[n-1] + f[n-2] => 2 * F[n]
 */