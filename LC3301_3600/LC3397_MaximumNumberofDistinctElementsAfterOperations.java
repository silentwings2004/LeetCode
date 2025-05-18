package LC3301_3600;
import java.util.*;
public class LC3397_MaximumNumberofDistinctElementsAfterOperations {
    /**
     * You are given an integer array nums and an integer k.
     *
     * You are allowed to perform the following operation on each element of the array at most once:
     *
     * Add an integer in the range [-k, k] to the element.
     * Return the maximum possible number of distinct elements in nums after performing the operations.
     *
     * Input: nums = [1,2,2,3,3,4], k = 2
     * Output: 6
     *
     * Input: nums = [4,4,4,4], k = 1
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * 0 <= k <= 10^9
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public int maxDistinctElements(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length, res = n;
        nums[0] -= k;
        for (int i = 1; i < n; i++) {
            nums[i] = Math.min(nums[i] + k, Math.max(nums[i - 1] + 1, nums[i] - k));
            if (nums[i] <= nums[i - 1]) res--;
        }
        return res;
    }
}