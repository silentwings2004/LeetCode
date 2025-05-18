package LC3301_3600;

public class LC3392_CountSubarraysofLengthThreeWithaCondition {
    /**
     * Given an integer array nums, return the number of subarrays of length 3 such that the sum of the first and third
     * numbers equals exactly half of the second number.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [1,2,1,4,1]
     * Output: 1
     *
     * Input: nums = [1,1,1]
     * Output: 0
     *
     * Constraints:
     *
     * 3 <= nums.length <= 100
     * -100 <= nums[i] <= 100
     * @param nums
     * @return
     */
    public int countSubarrays(int[] nums) {
        int n = nums.length, res = 0;
        for (int i = 0; i + 2 < n; i++) {
            int a = nums[i], b = nums[i + 1], c = nums[i + 2];
            if (2 * (a + c) == b) res++;
        }
        return res;
    }
}