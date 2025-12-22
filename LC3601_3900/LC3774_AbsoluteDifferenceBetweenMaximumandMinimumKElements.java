package LC3601_3900;
import java.util.*;
public class LC3774_AbsoluteDifferenceBetweenMaximumandMinimumKElements {
    /**
     * You are given an integer array nums and an integer k.
     *
     * Find the absolute difference between:
     *
     * the sum of the k largest elements in the array; and
     * the sum of the k smallest elements in the array.
     * Return an integer denoting this difference.
     *
     * Input: nums = [5,2,2,4], k = 2
     * Output: 5
     *
     * Input: nums = [100], k = 1
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 100
     * 1 <= nums[i] <= 100
     * 1 <= k <= n
     * @param nums
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public int absDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length, res = 0;
        for (int i = 0, j = n - 1; i < k; i++, j--) res += nums[j] - nums[i];
        return res;
    }
}