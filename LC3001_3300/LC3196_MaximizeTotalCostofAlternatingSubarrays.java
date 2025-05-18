package LC3001_3300;
import java.util.*;
public class LC3196_MaximizeTotalCostofAlternatingSubarrays {
    /**
     * You are given an integer array nums with length n.
     *
     * The cost of a subarray nums[l..r], where 0 <= l <= r < n, is defined as:
     *
     * cost(l, r) = nums[l] - nums[l + 1] + ... + nums[r] * (−1)r − l
     *
     * Your task is to split nums into subarrays such that the total cost of the subarrays is maximized, ensuring each
     * element belongs to exactly one subarray.
     *
     * Formally, if nums is split into k subarrays, where k > 1, at indices i1, i2, ..., ik − 1, where
     * 0 <= i1 < i2 < ... < ik - 1 < n - 1, then the total cost will be:
     *
     * cost(0, i1) + cost(i1 + 1, i2) + ... + cost(ik − 1 + 1, n − 1)
     *
     * Return an integer denoting the maximum total cost of the subarrays after splitting the array optimally.
     *
     * Note: If nums is not split into subarrays, i.e. k = 1, the total cost is simply cost(0, n - 1).
     *
     * Input: nums = [1,-2,3,4]
     * Output: 10
     *
     * Input: nums = [1,-1,1,-1]
     * Output: 4
     *
     * Input: nums = [0]
     * Output: 0
     *
     * Input: nums = [1,-1]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * -10^9 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public long maximumTotalCost(int[] nums) {
        long inf = (long)1e18;
        int n = nums.length;
        long[][] f = new long[n][2];
        for (int i = 0; i < n; i++) Arrays.fill(f[i], -inf);
        f[0][0] = nums[0];

        for (int i = 1; i < n; i++) {
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][1]) + nums[i];
            f[i][0] = Math.max(f[i][0], f[i - 1][1] + nums[i]);
            f[i][1] = Math.max(f[i][1], f[i - 1][0] - nums[i]);
        }
        return Math.max(f[n - 1][0], f[n - 1][1]);
    }
}
/**
 * 寻找子问题
 * 考虑 a[i] 是 取 + 还是取 -
 * dfs(i,j) 考虑 a[i] ~ a[n - 1], 且 a[i] 这个数，在不分割的情况下，是否要变号
 * j = 0 不变号 +a[i]
 * j - 1 变号 -a[i]
 * 讨论：
 * j = 0 的情况
 * 分割: 考虑 a[i+1] ~ a[n-1]， 且 a[i+1] 这个数，在不分割的情况下需要变号 dfs(i+1,1)
 * 不分割: 考虑 a[i+1] ~ a[n-1]， 且 a[i+1] 这个数，在不分割的情况下需要变号 dfs(i+1,1)
 * dfs(i,0) = dfs(i+1,1) + a[i]
 * j = 1 的情况
 * 分割: 考虑 a[i+1] ~ a[n-1]， 且 a[i+1] 这个数，在不分割的情况下需要变号 dfs(i+1,1) + a[i]
 * 不分割: 考虑 a[i+1] ~ a[n-1]， 且 a[i+1] 这个数，在不分割的情况下需要变号 dfs(i+1,1) - a[i]
 * dfs(i,0) = max(dfs(i+1,1) + a[i], dfs(i+1,0) - a[i])
 */