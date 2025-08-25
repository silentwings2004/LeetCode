package LC3601_3900;

public class LC3658_GCDofOddandEvenSums {
    /**
     * You are given an integer n. Your task is to compute the GCD (greatest common divisor) of two values:
     *
     * sumOdd: the sum of the first n odd numbers.
     *
     * sumEven: the sum of the first n even numbers.
     *
     * Return the GCD of sumOdd and sumEven.
     *
     * Input: n = 4
     * Output: 4
     *
     * Input: n = 5
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= n <= 1000
     * @param n
     * @return
     */
    // S1
    // time = O(logn), space = O(1)
    public int gcdOfOddEvenSums(int n) {
        return gcd(n * n, (n + 1) * n);
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // S2
    // time = O(1), space = O(1)
    public int gcdOfOddEvenSums2(int n) {
        return n;
    }
}