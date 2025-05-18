package LC001_300;
import java.util.*;
public class LC154_FindMinimuminRotatedSortedArrayII {
    /**
     * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
     *
     * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
     *
     * Find the minimum element.
     *
     * The array may contain duplicates.
     *
     * Input: [2,2,2,0,1]
     * Output: 0
     *
     * Note:
     *
     * This is a follow up problem to Find Minimum in Rotated Sorted Array.
     * Would allow duplicates affect the run-time complexity? How and why?
     *
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int findMin(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (r > 0 && nums[r] == nums[0]) r--;
        if (nums[0] <= nums[r]) return nums[0];
        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] >= nums[0]) l = mid + 1;
            else r = mid;
        }
        return nums[r];
    }
}
/**
 * == 的时候没有办法判断是在左区间还是右区间
 * 根据LC153，加上相等的时候right--即可，把重复的删掉即可，不影响
 * [2，2，2，2，2，0，1，2]
 * [2,2,3,4,2,2,2,2,2]
 */