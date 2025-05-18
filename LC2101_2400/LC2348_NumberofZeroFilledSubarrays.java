package LC2101_2400;

public class LC2348_NumberofZeroFilledSubarrays {
    /**
     * Given an integer array nums, return the number of subarrays filled with 0.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [1,3,0,0,2,0,0,4]
     * Output: 6
     *
     *
     Constraints:

     1 <= nums.length <= 10^5
     -10^9 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public long zeroFilledSubarray(int[] nums) {
        int n = nums.length;
        long res = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                int j = i;
                while (j < n && nums[j] == 0) j++;
                long k = j - i;
                res += k * (k + 1) / 2;
                i = j - 1;
            }
        }
        return res;
    }
}
