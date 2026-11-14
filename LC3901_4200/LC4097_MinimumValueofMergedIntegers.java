package LC3901_4200;

public class LC4097_MinimumValueofMergedIntegers {
    /**
     * You are given an integer array nums of even length n.
     *
     * You have to merge the elements of nums in pairs by merging nums[0] with nums[1], nums[2] with nums[3], and so on,
     * until nums[n - 2] with nums[n - 1].
     *
     * Return the minimum value among all the resulting merged integers.
     *
     * To merge two integers, concatenate all the decimal digits of the first integer followed by all the decimal digits
     * of the second integer.
     *
     * Input: nums = [23,55,42,21]
     * Output: 2355
     *
     * Input: nums = [34,7,11,11]
     * Output: 347
     *
     * Input: nums = [9,20]
     * Output: 920
     *
     * Constraints:
     *
     * 2 <= n == nums.length <= 100
     * n is even
     * 1 <= nums[i] <= 1000
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int minMergedInteger(int[] nums) {
        int n = nums.length, res = Integer.MAX_VALUE;
        for (int i = 0; i + 1 < n; i += 2) {
            int a = nums[i], b = nums[i + 1];
            int d = 10;
            if (b == 1000) d = 10000;
            else if (b >= 100) d = 1000;
            else if (b >= 10) d = 100;
            res = Math.min(res, a * d + b);
        }
        return res;
    }
}