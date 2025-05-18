package LC2700_3000;

public class LC2927_DistributeCandiesAmongChildrenIII {
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
     * 1 <= n <= 10^8
     * 1 <= limit <= 10^8
     * @param n
     * @param limit
     * @return
     */
    // time = O(1), space = O(1)
    public long distributeCandies(int n, int limit) {
        int overLimit = limit + 1;
        return C(n) - C(n - overLimit) * 3 + C(n - 2 * overLimit) * 3 - C(n - 3 * overLimit);
    }

    private long C(int a) {
        if (a < 0) return 0;
        a += 2;
        return 1L * a * (a - 1) / 2;
    }
}
/**
 * C(n + 2, 2) ->  Total ways without considering the limit
 * C(3,1) * C(n - over_limit, 2) ->  one or more children exceed the limit
 * C(3,2) * C(n - 2 * over_limit, 2) -> two or more children exceed the limit
 * C(3,3) * C(n - 3 * over_limit, 2) -> all three children exceed the limit
 */