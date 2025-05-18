package LC2401_2700;

public class LC2444_CountSubarraysWithFixedBounds {
    /**
     * You are given an integer array nums and two integers minK and maxK.
     *
     * A fixed-bound subarray of nums is a subarray that satisfies the following conditions:
     *
     * The minimum value in the subarray is equal to minK.
     * The maximum value in the subarray is equal to maxK.
     * Return the number of fixed-bound subarrays.
     *
     * A subarray is a contiguous part of an array.
     *
     * Input: nums = [1,3,5,2,7,5], minK = 1, maxK = 5
     * Output: 2
     *
     * Input: nums = [1,1,1,1], minK = 1, maxK = 1
     * Output: 10
     *
     * Constraints:
     *
     * 2 <= nums.length <= 10^5
     * 1 <= nums[i], minK, maxK <= 10^6
     * @param nums
     * @param minK
     * @param maxK
     * @return
     */
    // time = O(n), space = O(1)
    public long countSubarrays(int[] nums, int minK, int maxK) {
        int n = nums.length, preMin = -1, preMax = -1, boundary = -1;
        long res = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == minK) preMin = i;
            if (nums[i] == maxK) preMax = i;
            if (nums[i] < minK || nums[i] > maxK) boundary = i;
            res += Math.max(0, Math.min(preMin, preMax) - boundary);
        }
        return res;
    }
}
/**
 * 存在至少一个minK和maxK
 * 先找出所有不在范围内的数，分割原数组
 * 只需要分别看每一段
 * j最靠近i的位置，使得[j:i]中至少存在一个minK和maxK
 */