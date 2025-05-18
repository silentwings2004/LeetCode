package LC001_300;
import java.util.*;
public class LC81_SearchinRotatedSortedArrayII {
    /**
     * You are given an integer array nums sorted in ascending order (not necessarily distinct values),
     * and an integer target.
     *
     * Suppose that nums is rotated at some pivot unknown to you beforehand (i.e., [0,1,2,4,4,4,5,6,6,7] might
     * become [4,5,6,6,7,0,1,2,4,4]).
     *
     * If target is found in the array return its index, otherwise, return -1.
     *
     * Input: nums = [2,5,6,0,0,1,2], target = 0
     * Output: true
     *
     * Constraints:
     *
     * 1 <= nums.length <= 5000
     * -10^4 <= nums[i] <= 10^4
     * nums is guaranteed to be rotated at some pivot.
     * -10^4 <= target <= 10^4
     *
     * Follow up: This problem is the same as Search in Rotated Sorted Array, where nums may contain duplicates.
     * Would this affect the run-time complexity? How and why?
     *
     * @param nums
     * @param target
     * @return
     */
    // S1: BS
    // time = O(logn) (worst : O(n)), space = O(1)
    public boolean search(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (r > 0 && nums[r] == nums[0]) r--;
        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] == target) return true;
            if (nums[mid] >= nums[0] && target >= nums[0] || nums[mid] < nums[0] && target < nums[0]) {
                if (nums[mid] < target) l = mid + 1;
                else r = mid;
            } else if (nums[mid] < nums[0]) r = mid - 1;
            else l = mid + 1;
        }
        return nums[r] == target;
    }

    // S2: BS
    // time = O(logn) (worst : O(n)), space = O(1)
    public boolean search2(int[] nums, int target) {
        int R = nums.length - 1;
        while (R >= 0 && nums[R] == nums[0]) R--;
        if (R < 0) return nums[0] == target;

        int l = 0, r = R;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (nums[mid] >= nums[0]) l = mid;
            else r = mid - 1;
        }

        if (target < nums[0]) {
            l++;
            r = R;
        } else l = 0;

        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] >= target) r = mid;
            else l = mid + 1;
        }
        return nums[r] == target ? true : false;
    }
}
/**
 * 相较于I,这里的问题出在判断nums[mid]与target是否在同一个区间的时候，你会有
 * 1 1 2 2 3 4 5 6 7 0 0 1 1 1     target = 1
 *   M                   M T
 * 怎么做？只要多加一行！最大问题就是它的头等于它的尾，这里只要找是否存在即可。
 * 如果把右边所有干扰的1一删除，就发觉原来的做法又成立了！
 *
 * 前半部分 x >= nums[0]
 * 后半部分 x < nums[0]
 *
 * 一旦出现重复，就可能没有二段性了
 * 最坏情况下 => O(n)
 * 只要把开头和结尾相同的数删掉即能二分了。
 */