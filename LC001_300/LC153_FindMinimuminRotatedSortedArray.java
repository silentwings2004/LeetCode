package LC001_300;
import java.util.*;
public class LC153_FindMinimuminRotatedSortedArray {
    /**
     * Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
     * For example, the array nums = [0,1,2,4,5,6,7] might become:
     *
     * [4,5,6,7,0,1,2] if it was rotated 4 times.
     * [0,1,2,4,5,6,7] if it was rotated 7 times.
     * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array
     * [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
     *
     * Given the sorted rotated array nums, return the minimum element of this array.
     *
     * Input: nums = [3,4,5,1,2]
     * Output: 1
     *
     * Constraints:
     *
     * n == nums.length
     * 1 <= n <= 5000
     * -5000 <= nums[i] <= 5000
     * All the integers of nums are unique.
     * nums is sorted and rotated between 1 and n times.
     *
     * @param nums
     * @return
     */
    // time = O(logn), space = O(1)
    public int findMin(int[] nums) {
        int n = nums.length;
        if (nums[0] <= nums[n - 1]) return nums[0];
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] >= nums[0]) l = mid + 1;
            else r = mid;
        }
        return nums[r];
    }
}
/**
 * 通过mid与right的比较就可以判断在左还是右区间
 */