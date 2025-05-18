package LC2700_3000;
import java.util.*;
public class LC2740_FindtheValueofthePartition {
    /**
     * You are given a positive integer array nums.
     *
     * Partition nums into two arrays, nums1 and nums2, such that:
     *
     * Each element of the array nums belongs to either the array nums1 or the array nums2.
     * Both arrays are non-empty.
     * The value of the partition is minimized.
     * The value of the partition is |max(nums1) - min(nums2)|.
     *
     * Here, max(nums1) denotes the maximum element of the array nums1, and min(nums2) denotes the minimum element of
     * the array nums2.
     *
     * Return the integer denoting the value of such partition.
     *
     * Input: nums = [1,3,2,4]
     * Output: 1
     *
     * Input: nums = [100,1,10]
     * Output: 9
     *
     * Constraints:
     *
     * 2 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public int findValueOfPartition(int[] nums) {
        Arrays.sort(nums);
        int res = (int)1e9;
        for (int i = 0; i + 1 < nums.length; i++) res = Math.min(res, nums[i + 1] - nums[i]);
        return res;
    }
}