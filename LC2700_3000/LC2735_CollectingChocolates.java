package LC2700_3000;

public class LC2735_CollectingChocolates {
    /**
     * You are given a 0-indexed integer array nums of size n representing the cost of collecting different chocolates.
     * Each chocolate is of a different type, and originally, the chocolate at ith index is of ith type.
     *
     * In one operation, you can do the following with an incurred cost of x:
     *
     * Simultaneously change the chocolate of ith type to (i + 1)th type for all indexes i where 0 <= i < n - 1. When
     * i == n - 1, that chocolate will be changed to type of the chocolate at index 0.
     * Return the minimum cost to collect chocolates of all types, given that you can perform as many operations as you
     * would like.
     *
     * Input: nums = [20,1,15], x = 5
     * Output: 13
     *
     * Input: nums = [1,2,3], x = 4
     * Output: 6
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * 1 <= nums[i] <= 10^9
     * 1 <= x <= 10^9
     * @param nums
     * @param x
     * @return
     */
    // time = O(n^2), space = O(n^2)
    public long minCost(int[] nums, int x) {
        int n = nums.length;
        int[][] f = new int[n][n];
        for (int i = 0; i < n; i++) {
            f[i][0] = nums[i];
            for (int j = 1; j < n; j++) {
                f[i][j] = Math.min(f[i][j - 1], nums[(i + j) % n]);
            }
        }

        long res = Long.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            long sum = 0;
            for (int i = 0; i < n; i++) {
                sum += f[i][j];
            }
            res = Math.min(res, sum + (long)j * x);
        }
        return res;
    }
}