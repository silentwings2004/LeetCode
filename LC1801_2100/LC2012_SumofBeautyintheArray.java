package LC1801_2100;

public class LC2012_SumofBeautyintheArray {
    /**
     * You are given a 0-indexed integer array nums. For each index i (1 <= i <= nums.length - 2) the beauty of nums[i]
     * equals:
     *
     * 2, if nums[j] < nums[i] < nums[k], for all 0 <= j < i and for all i < k <= nums.length - 1.
     * 1, if nums[i - 1] < nums[i] < nums[i + 1], and the previous condition is not satisfied.
     * 0, if none of the previous conditions holds.
     * Return the sum of beauty of all nums[i] where 1 <= i <= nums.length - 2.
     *
     * Input: nums = [1,2,3]
     * Output: 2
     *
     * Constraints:
     *
     * 3 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public int sumOfBeauties(int[] nums) {
        int n = nums.length;
        int[] w = new int[n];
        w[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) w[i] = Math.min(nums[i], w[i + 1]);
        int mx = nums[0], res = 0;
        for (int i = 1; i < n - 1; i++) {
            if (mx < nums[i] && nums[i] < w[i + 1]) res += 2;
            else if (nums[i - 1] < nums[i] && nums[i] < nums[i + 1]) res++;
            mx = Math.max(mx, nums[i]);
        }
        return res;
    }
}