package LC2401_2700;
import java.util.*;
public class LC2567_MinimumScorebyChangingTwoElements {
    /**
     * You are given a 0-indexed integer array nums.
     *
     * The low score of nums is the minimum value of |nums[i] - nums[j]| over all 0 <= i < j < nums.length.
     * The high score of nums is the maximum value of |nums[i] - nums[j]| over all 0 <= i < j < nums.length.
     * The score of nums is the sum of the high and low scores of nums.
     * To minimize the score of nums, we can change the value of at most two elements of nums.
     *
     * Return the minimum possible score after changing the value of at most two elements of nums.
     *
     * Note that |x| denotes the absolute value of x.
     *
     * Input: nums = [1,4,3]
     * Output: 0
     *
     * Input: nums = [1,4,7,8,5]
     * Output: 3
     *
     *
     Constraints:

     3 <= nums.length <= 10^5
     1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public int minimizeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        if (n == 3) return 0;
        int a = nums[n - 1] - nums[2], b = nums[n - 3] - nums[0], c = nums[n - 2] - nums[1];
        return Math.min(Math.min(a, b), c);
    }
}
/**
 * 贪心
 * 如果贪心地将两个修改的名额都用来降低 high score, 我们可以有三种方法：
 * (1) 将最大值改为次大值，最小值改为次小值，这样high score就是nums[n-2]-nums[1].
 * (2) 将最小的两个值都改为第三小的值，这样high score就是nums[n-1]-nums[2].
 * (3) 将最大的两个值都改为第三大的值，这样high score就是nums[n-3]-nums[0].
 * 我们选取上述的哪个方案，因为出现了重复元素，所以low score都是零
 */