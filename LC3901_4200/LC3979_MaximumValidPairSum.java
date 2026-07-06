package LC3901_4200;
import java.util.*;
public class LC3979_MaximumValidPairSum {
    /**
     * You are given an integer array nums of length n and an integer k.
     *
     * A pair of indices (i, j) is called valid if:
     *
     * 0 <= i < j < n
     * j - i >= k
     * Return the maximum value of nums[i] + nums[j] among all valid pairs.
     *
     * Input: nums = [1,3,5,2,8], k = 2
     * Output: 13
     *
     * Input: nums = [5,1,9], k = 1
     * Output: 14
     *
     * Constraints:
     *
     * 2 <= n == nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * 1 <= k <= n - 1
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public int maxValidPairSum(int[] nums, int k) {
        int n = nums.length, res = 0;
        for (int i = 0, j = k, mx = 0; j < n; j++) {
            mx = Math.max(mx, nums[i++]);
            res = Math.max(res, nums[j] + mx);
        }
        return res;
    }
}