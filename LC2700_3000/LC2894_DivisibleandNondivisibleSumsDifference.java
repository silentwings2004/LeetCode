package LC2700_3000;

public class LC2894_DivisibleandNondivisibleSumsDifference {
    /**
     * You are given positive integers n and m.
     *
     * Define two integers, num1 and num2, as follows:
     *
     * num1: The sum of all integers in the range [1, n] that are not divisible by m.
     * num2: The sum of all integers in the range [1, n] that are divisible by m.
     * Return the integer num1 - num2.
     *
     * Input: n = 10, m = 3
     * Output: 19
     *
     * Input: n = 5, m = 6
     * Output: 15
     *
     * Input: n = 5, m = 1
     * Output: -15
     *
     * Constraints:
     *
     * 1 <= n, m <= 1000
     * @param n
     * @param m
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public int differenceOfSums(int n, int m) {
        int s1 = 0, s2 = 0;
        for (int i = 1; i <= n; i++) {
            if (i % m != 0) s1 += i;
            else s2 += i;
        }
        return s1 - s2;
    }

    // S2
    // time = O(1), space = O(1)
    public int differenceOfSums2(int n, int m) {
        int k = n / m;
        return n * (n + 1) / 2 - k * (k + 1) * m;
    }
}