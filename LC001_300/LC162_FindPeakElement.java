package LC001_300;
import java.util.*;
public class LC162_FindPeakElement {
    /**
     * A peak element is an element that is strictly greater than its neighbors.
     *
     * Given an integer array nums, find a peak element, and return its index.
     * If the array contains multiple peaks, return the index to any of the peaks.
     *
     * You may imagine that nums[-1] = nums[n] = -∞.
     *
     * Input: nums = [1,2,3,1]
     * Output: 2
     *
     * Input: nums = [1,2,1,3,5,6,4]
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * -231 <= nums[i] <= 231 - 1
     * nums[i] != nums[i + 1] for all valid i.
     *
     * @param nums
     * @return
     */
    // time = O(logn), space = O(1)
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] > nums[mid + 1]) r = mid;
            else l = mid + 1;
        }
        return r;
    }
}
/**
 * 每相邻元素不相等 => 斜率肯定不为0 -> 要么左边上，要么右边上
 */