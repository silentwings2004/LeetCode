package LC2401_2700;

public class LC2652_SumMultiples {
    /**
     * Given a positive integer n, find the sum of all integers in the range [1, n] inclusive that are divisible by 3,
     * 5, or 7.
     *
     * Return an integer denoting the sum of all numbers in the given range satisfying the constraint.
     *
     * Input: n = 7
     * Output: 21
     *
     * Input: n = 10
     * Output: 40
     *
     * Input: n = 9
     * Output: 30
     *
     * Constraints:
     *
     * 1 <= n <= 10^3
     * @param n
     * @return
     */
    // time = O(n), space = O(1)
    public int sumOfMultiples(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 || i % 5 == 0 || i % 7 == 0) sum += i;
        }
        return sum;
    }
}