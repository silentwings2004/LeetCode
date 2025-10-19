package LC3601_3900;

public class LC3717_MinimumOperationstoMaketheArrayBeautiful {
    /**
     * You are given an integer array nums.
     *
     * An array is called beautiful if for every index i > 0, the value at nums[i] is divisible by nums[i - 1].
     *
     * In one operation, you may increment any element of nums by 1.
     *
     * Return the minimum number of operations required to make the array beautiful.
     *
     * Input: nums = [3,7,9]
     * Output: 2
     *
     * Input: nums = [1,1,1]
     * Output: 0
     *
     * Input: nums = [4]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public long minOperations(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;
        long res = 0;
        for (int i = 1; i < n; i++) {
            int x = nums[i - 1], y = nums[i];
            if (y <= x) {
                nums[i] = x;
                res += x - y;
            } else {
                if (y % x == 0) continue;
                res += x - y % x;
                nums[i] += x - y % x;
            }
        }
        return res;
    }
}