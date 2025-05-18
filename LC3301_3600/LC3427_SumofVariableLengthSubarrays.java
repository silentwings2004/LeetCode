package LC3301_3600;

public class LC3427_SumofVariableLengthSubarrays {
    /**
     * You are given an integer array nums of size n. For each index i where 0 <= i < n, define a subarray
     * nums[start ... i] where start = max(0, i - nums[i]).
     *
     * Return the total sum of all elements from the subarray defined for each index in the array.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [2,3,1]
     * Output: 11
     *
     * Input: nums = [3,1,1,2]
     * Output: 13
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 100
     * 1 <= nums[i] <= 1000
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public int subarraySum(int[] nums) {
        int n = nums.length, res = 0;
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + nums[i - 1];
        for (int i = 0; i < n; i++) {
            int start = Math.max(0, i - nums[i]);
            res += s[i + 1] - s[start];
        }
        return res;
    }
}