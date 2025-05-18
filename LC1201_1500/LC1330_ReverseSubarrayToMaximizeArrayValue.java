package LC1201_1500;

public class LC1330_ReverseSubarrayToMaximizeArrayValue {
    /**
     * You are given an integer array nums. The value of this array is defined as the sum of |nums[i] - nums[i + 1]|
     * for all 0 <= i < nums.length - 1.
     *
     * You are allowed to select any subarray of the given array and reverse it. You can perform this operation only once.
     *
     * Find maximum possible value of the final array.
     *
     * Input: nums = [2,3,1,5,4]
     * Output: 10
     *
     * Input: nums = [2,4,9,24,2,1,10]
     * Output: 68
     *
     * Constraints:
     *
     * 1 <= nums.length <= 3 * 10^4
     * -10^5 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int maxValueAfterReverse(int[] nums) {
        int n = nums.length, sum = 0;
        for (int i = 1; i < n; i++) sum += Math.abs(nums[i] - nums[i - 1]);

        int res = sum;
        for (int i = 1; i < n - 1; i++) {
            res = Math.max(res, sum - Math.abs(nums[i] - nums[i + 1]) + Math.abs(nums[i + 1] - nums[0]));
            res = Math.max(res, sum - Math.abs(nums[i] - nums[i - 1]) + Math.abs(nums[i - 1] - nums[n - 1]));
        }

        int a = Math.max(nums[0], nums[1]), b = Math.min(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            int c = Math.max(nums[i - 1], nums[i]);
            int d = Math.min(nums[i - 1], nums[i]);
            if (c < b) res = Math.max(res, sum + 2 * Math.abs(b - c));
            if (a < d) res = Math.max(res, sum + 2 * Math.abs(a - d));
            a = Math.min(a, c);
            b = Math.max(b, d);
        }
        return res;
    }
}
/**
 * 前缀和后缀分别有n种
 * 中间：O(n^2) -> 需要优化
 * 1. 初始：ab包含cd，只会相等或变小 => 不考虑
 * 2. ab不包含cd => ad+bc = ac+bd => 变大
 * 可以枚举下要翻转的数组的右端点
 * 如果ab比cd要高的话 => b越大越好
 * 如果ab比cd要低的话 => a越小越好
 */