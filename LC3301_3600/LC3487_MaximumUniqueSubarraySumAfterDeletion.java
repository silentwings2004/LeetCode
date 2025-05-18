package LC3301_3600;
import java.util.*;
public class LC3487_MaximumUniqueSubarraySumAfterDeletion {
    /**
     * You are given an integer array nums.
     *
     * You are allowed to delete any number of elements from nums without making it empty. After performing the
     * deletions, select a subarray of nums such that:
     *
     * All elements in the subarray are unique.
     * The sum of the elements in the subarray is maximized.
     * Return the maximum sum of such a subarray.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [1,2,3,4,5]
     * Output: 15
     *
     * Input: nums = [1,1,0,1,1]
     * Output: 1
     *
     * Input: nums = [1,2,-1,-2,1,0,-1]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * -100 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int maxSum(int[] nums) {
        int n = nums.length, res = 0, mx = nums[0];
        boolean[] st = new boolean[201];
        for (int x : nums) {
            if (x >= 0 && !st[x + 100]) res += x;
            st[x + 100] = true;
            mx = Math.max(mx, x);
        }
        if (mx < 0) return mx;
        return res;
    }
}