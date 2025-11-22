package LC3601_3900;

public class LC3745_MaximizeExpressionofThreeElements {
    /**
     * You are given an integer array nums.
     *
     * Choose three elements a, b, and c from nums at distinct indices such that the value of the expression a + b - c
     * is maximized.
     *
     * Return an integer denoting the maximum possible value of this expression.
     *
     * Input: nums = [1,4,2,5]
     * Output: 8
     *
     * Input: nums = [-2,0,5,-2,4]
     * Output: 11
     *
     * Constraints:
     *
     * 3 <= nums.length <= 100
     * -100 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int maximizeExpressionOfThree(int[] nums) {
        int mx1 = -101, mx2 = -101, mn = 101;
        for (int x : nums) {
            if (x > mx1) {
                mx2 = mx1;
                mx1 = x;
            } else if (x > mx2) mx2 = x;
            if (x < mn) mn = x;
        }
        return mx1 + mx2 - mn;
    }
}