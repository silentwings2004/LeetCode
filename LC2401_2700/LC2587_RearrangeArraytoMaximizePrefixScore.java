package LC2401_2700;
import java.util.*;
public class LC2587_RearrangeArraytoMaximizePrefixScore {
    /**
     * You are given a 0-indexed integer array nums. You can rearrange the elements of nums to any order (including
     * the given order).
     *
     * Let prefix be the array containing the prefix sums of nums after rearranging it. In other words, prefix[i] is
     * the sum of the elements from 0 to i in nums after rearranging it. The score of nums is the number of positive
     * integers in the array prefix.
     *
     * Return the maximum score you can achieve.
     *
     * Input: nums = [2,-1,0,1,-3,3,-3]
     * Output: 6
     *
     * Input: nums = [-2,-3,0]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * -106 <= nums[i] <= 10^6
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public int maxScore(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length, cnt = 0;
        long s = 0;
        for (int i = n - 1; i >= 0; i--) {
            s += nums[i];
            if (s > 0) cnt++;
        }
        return cnt;
    }
}