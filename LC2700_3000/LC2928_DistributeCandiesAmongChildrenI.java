package LC2700_3000;

public class LC2928_DistributeCandiesAmongChildrenI {
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
     * 1 <= n <= 50
     * 1 <= limit <= 50
     * @param n
     * @param limit
     * @return
     */
    // time = O(n^2), space = O(1)
    public int distributeCandies(int n, int limit) {
        int res = 0;
        for (int i = 0; i <= Math.min(limit, n); i++) {
            for (int j = 0; j <= Math.min(limit, n - i); j++) {
                int k = n - i - j;
                if (k >= 0 && k <= limit) res++;
            }
        }
        return res;
    }
}