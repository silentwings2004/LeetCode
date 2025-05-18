package LC1501_1800;
import java.util.*;
public class LC1509_MinimumDifferenceBetweenLargestandSmallestValueinThreeMoves {
    /**
     * You are given an integer array nums.
     *
     * In one move, you can choose one element of nums and change it to any value.
     *
     * Return the minimum difference between the largest and smallest value of nums after performing at most three moves.
     *
     * Input: nums = [5,3,2,4]
     * Output: 0
     *
     * Input: nums = [1,5,0,10,14]
     * Output: 1
     *
     * Input: nums = [3,100,20]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * -10^9 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public int minDifference(int[] nums) {
        int n = nums.length;
        if (n <= 4) return 0;
        Arrays.sort(nums);
        int res = Integer.MAX_VALUE;
        for (int i = 0, j = 3; i <= 3; i++, j--) {
            res = Math.min(res, nums[n - j - 1] - nums[i]);
        }
        return res;
    }
}