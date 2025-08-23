package LC3601_3900;

public class LC3653_XORAfterRangeMultiplicationQueriesI {
    /**
     * You are given an integer array nums of length n and a 2D integer array queries of size q, where queries[i] =
     * [li, ri, ki, vi].
     *
     * For each query, you must apply the following operations in order:
     *
     * Set idx = li.
     * While idx <= ri:
     * Update: nums[idx] = (nums[idx] * vi) % (10^9 + 7)
     * Set idx += ki.
     * Return the bitwise XOR of all elements in nums after processing all queries.
     *
     * Input: nums = [1,1,1], queries = [[0,2,1,4]]
     * Output: 4
     *
     * Input: nums = [2,3,1,5,4], queries = [[1,4,2,3],[0,2,1,2]]
     * Output: 31
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 10^3
     * 1 <= nums[i] <= 10^9
     * 1 <= q == queries.length <= 10^3
     * queries[i] = [li, ri, ki, vi]
     * 0 <= li <= ri < n
     * 1 <= ki <= n
     * 1 <= vi <= 10^5
     * @param nums
     * @param queries
     * @return
     */
    // time = O(n * n / k), space = O(1)
    public int xorAfterQueries(int[] nums, int[][] queries) {
        long mod = (long)(1e9 + 7);
        for (int[] q : queries) {
            int l = q[0], r = q[1], k = q[2], v = q[3];
            for (int i = l; i <= r; i += k) {
                nums[i] = (int)(1L * nums[i] * v % mod);
            }
        }
        long res = 0;
        for (int x : nums) res ^= x;
        return (int)res;
    }
}