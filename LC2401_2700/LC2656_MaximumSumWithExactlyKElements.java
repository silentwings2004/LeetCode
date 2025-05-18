package LC2401_2700;

public class LC2656_MaximumSumWithExactlyKElements {
    /**
     * You are given a 0-indexed integer array nums and an integer k. Your task is to perform the following operation
     * exactly k times in order to maximize your score:
     *
     * Select an element m from nums.
     * Remove the selected element m from the array.
     * Add a new element with a value of m + 1 to the array.
     * Increase your score by m.
     * Return the maximum score you can achieve after performing the operation exactly k times.
     *
     * Input: nums = [1,2,3,4,5], k = 3
     * Output: 18
     *
     * Input: nums = [5,5,5], k = 2
     * Output: 11
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     * 1 <= k <= 100
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public int maximizeSum(int[] nums, int k) {
        int maxv = nums[0];
        for (int x : nums) maxv = Math.max(maxv, x);
        int res = 0;
        for (int i = 0; i < k; i++) res += maxv++;
        return res;
    }
}