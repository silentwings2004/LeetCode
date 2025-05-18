package LC001_300;
import java.util.*;
public class LC34_FindFirstandLastPositionofElementinSortedArray {
    /**
     * Given an array of integers nums sorted in ascending order, find the starting and ending position of
     * a given target value.
     *
     * If target is not found in the array, return [-1, -1].
     *
     * Follow up: Could you write an algorithm with O(log n) runtime complexity?
     *
     * Input: nums = [5,7,7,8,8,10], target = 8
     * Output: [3,4]
     *
     * Constraints:
     *
     * 0 <= nums.length <= 105
     * -109 <= nums[i] <= 109
     * nums is a non-decreasing array.
     * -109 <= target <= 109
     *
     * @param nums
     * @param target
     * @return
     */
    // S1: BS
    // time = O(logn), space = O(1)
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        if (nums == null || nums.length == 0) return res;

        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] >= target) r = mid;
            else l = mid + 1;
        }
        res[0] = nums[r] == target ? r : -1;

        l = 0;
        r = n - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (nums[mid] <= target) l = mid;
            else r = mid - 1;
        }
        res[1] = nums[r] == target ? r : -1;
        return res;
    }
}