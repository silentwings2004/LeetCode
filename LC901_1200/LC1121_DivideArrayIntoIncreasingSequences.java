package LC901_1200;
import java.util.*;
public class LC1121_DivideArrayIntoIncreasingSequences {
    /**
     * Given an integer array nums sorted in non-decreasing order and an integer k, return true if this array can be
     * divided into one or more disjoint increasing subsequences of length at least k, or false otherwise.
     *
     * Input: nums = [1,2,2,3,3,4,4], k = 3
     * Output: true
     *
     * Input: nums = [5,6,6,7,8], k = 3
     * Output: false
     *
     * Constraints:
     *
     * 1 <= k <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * nums is sorted in non-decreasing order.
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public boolean canDivideIntoSubsequences(int[] nums, int k) {
        int n = nums.length, cur = 1, group = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i - 1] < nums[i]) cur = 1;
            else cur++;
            group = Math.max(group, cur);
        }
        return n >= group * k;
    }
}