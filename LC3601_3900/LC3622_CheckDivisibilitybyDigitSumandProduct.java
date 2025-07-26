package LC3601_3900;

public class LC3622_CheckDivisibilitybyDigitSumandProduct {
    /**
     * You are given a positive integer n. Determine whether n is divisible by the sum of the following two values:
     *
     * The digit sum of n (the sum of its digits).
     *
     * The digit product of n (the product of its digits).
     *
     * Return true if n is divisible by this sum; otherwise, return false.
     *
     * Input: n = 99
     * Output: true
     *
     * Input: n = 23
     * Output: false
     *
     * Constraints:
     *
     * 1 <= n <= 10^6
     * @param n
     * @return
     */
    // time = O(logn), space = O(1)
    public boolean checkDivisibility(int n) {
        int s1 = 0, s2 = 1, v = n;
        while (v > 0) {
            int x = v % 10;
            v /= 10;
            s1 += x;
            s2 *= x;
        }
        return n % (s1 + s2) == 0;
    }
}