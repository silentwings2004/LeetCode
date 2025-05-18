package LC3001_3300;
import java.util.*;
public class LC3194_MinimumAverageofSmallestandLargestElements {
    /**
     * You have an array of floating point numbers averages which is initially empty. You are given an array nums of n
     * integers where n is even.
     *
     * You repeat the following procedure n / 2 times:
     *
     * Remove the smallest element, minElement, and the largest element maxElement, from nums.
     * Add (minElement + maxElement) / 2 to averages.
     * Return the minimum element in averages.
     *
     * Input: nums = [7,8,3,4,15,13,4,1]
     * Output: 5.5
     *
     * Input: nums = [1,9,8,3,10,5]
     * Output: 5.5
     *
     * Constraints:
     *
     * 2 <= n == nums.length <= 50
     * n is even.
     * 1 <= nums[i] <= 50
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public double minimumAverage(int[] nums) {
        double res = 1e9;
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            double v = (nums[i] + nums[j]) / 2.0;
            res = Math.min(res, v);
        }
        return res;
    }
}