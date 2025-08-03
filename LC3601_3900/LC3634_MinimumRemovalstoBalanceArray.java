package LC3601_3900;
import java.util.*;
public class LC3634_MinimumRemovalstoBalanceArray {
    /**
     * You are given an integer array nums and an integer k.
     *
     * An array is considered balanced if the value of its maximum element is at most k times the minimum element.
     *
     * You may remove any number of elements from nums without making it empty.
     *
     * Return the minimum number of elements to remove so that the remaining array is balanced.
     *
     * Note: An array of size 1 is considered balanced as its maximum and minimum are equal, and the condition always
     * holds true.
     *
     * Input: nums = [2,1,5], k = 2
     * Output: 1
     *
     * Input: nums = [1,6,2,9], k = 3
     * Output: 2
     *
     * Input: nums = [4,6], k = 2
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * 1 <= k <= 10^5
     * @param nums
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public int minRemoval(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length, res = n;
        for (int i = 0; i < n; i++) res = Math.min(res, i + n - 1 - find(nums, i, 1L * nums[i] * k));
        return res;
    }

    private int find(int[] nums, int l, long t) {
        int r = nums.length - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (nums[mid] <= t) l = mid;
            else r = mid - 1;
        }
        return nums[r] <= t ? r : r + 1;
    }
}