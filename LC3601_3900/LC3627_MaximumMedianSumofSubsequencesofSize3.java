package LC3601_3900;
import java.util.*;
public class LC3627_MaximumMedianSumofSubsequencesofSize3 {
    /**
     * You are given an integer array nums with a length divisible by 3.
     *
     * You want to make the array empty in steps. In each step, you can select any three elements from the array,
     * compute their median, and remove the selected elements from the array.
     *
     * The median of an odd-length sequence is defined as the middle element of the sequence when it is sorted in
     * non-decreasing order.
     *
     * Return the maximum possible sum of the medians computed from the selected elements.
     *
     * Note: Please do not copy the description during the contest to maintain the integrity of your submissions.
     *
     * Input: nums = [2,1,3,2,1,3]
     * Output: 5
     *
     * Input: nums = [1,1,10,10,10,10]
     * Output: 20
     *
     * Constraints:
     *
     * 1 <= nums.length <= 5 * 10^5
     * nums.length % 3 == 0
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(nlogn), sapce = O(logn)
    public long maximumMedianSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        long res = 0;
        for (int i = 0, j = n - 1; i < j; i++, j -= 2) res += nums[j - 1];
        return res;
    }
}