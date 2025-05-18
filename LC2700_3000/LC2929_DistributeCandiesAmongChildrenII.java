package LC2700_3000;

public class LC2929_DistributeCandiesAmongChildrenII {
    /**
     * You are given two positive integers n and limit.
     *
     * Return the total number of ways to distribute n candies among 3 children such that no child gets more than limit
     * candies.
     *
     * Input: n = 5, limit = 2
     * Output: 3
     *
     * Input: n = 3, limit = 3
     * Output: 10
     *
     * Constraints:
     *
     * 1 <= n <= 10^6
     * 1 <= limit <= 10^6
     * @param n
     * @param limit
     * @return
     */
    // time = O(1), space = O(1)
    public long distributeCandies(int n, int limit) {
        int overLimit = limit + 1;
        return C(n) - C(n - overLimit) * 3 + C(n - 2 * overLimit) * 3 - C(n - 3 * overLimit);
    }

    private long C(int n) {
        if (n < 0) return 0;
        n += 2;
        return 1L * n * (n - 1) / 2;
    }
}