package LC601_900;

public class LC674_LongestContinuousIncreasingSubsequence {
    /**
     * Given an unsorted array of integers nums, return the length of the longest continuous increasing subsequence
     * (i.e. subarray). The subsequence must be strictly increasing.
     *
     * A continuous increasing subsequence is defined by two indices l and r (l < r) such that it is [nums[l],
     * nums[l + 1], ..., nums[r - 1], nums[r]] and for each l <= i < r, nums[i] < nums[i + 1].
     *
     * Input: nums = [1,3,5,4,7]
     * Output: 3
     *
     * Input: nums = [2,2,2,2,2]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^4
     * -10^9 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int findLengthOfLCIS(int[] nums) {
        int n = nums.length, res = 0;
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && nums[j] > nums[j - 1]) j++;
            res = Math.max(res, j - i);
            i = j - 1;
        }
        return res;
    }
}