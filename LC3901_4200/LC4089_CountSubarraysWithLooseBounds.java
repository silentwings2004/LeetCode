package LC3901_4200;

public class LC4089_CountSubarraysWithLooseBounds {
    /**
     * You are given an integer array nums and two integers minK and maxK.
     *
     * A loose-bound subarray is a subarray in which every element is greater than or equal to minK and less than or
     * equal to maxK.
     *
     * Return the number of loose-bound subarrays in nums.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [1,4,7,3], minK = 1, maxK = 5
     * Output: 4
     *
     * Input: nums = [1,1], minK = 1, maxK = 1
     * Output: 3
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
        int n = nums.length;
        long res = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] < minK || nums[i] > maxK) continue;
            int j = i;
            while (j < n && nums[j] >= minK && nums[j] <= maxK) {
                res += j - i + 1;
                j++;
            }
            i = j;
        }
        return res;
    }
}