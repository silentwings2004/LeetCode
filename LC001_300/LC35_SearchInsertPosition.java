package LC001_300;
import java.util.*;
public class LC35_SearchInsertPosition {
    /**
     * Given a sorted array of distinct integers and a target value, return the index if the target is found.
     * If not, return the index where it would be if it were inserted in order.
     *
     * Input: nums = [1,3,5,6], target = 5
     * Output: 2
     *
     * Input: nums = [1,3,5,6], target = 2
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^4
     * -104 <= nums[i] <= 10^4
     * nums contains distinct values sorted in ascending order.
     * -104 <= target <= 10^4
     * @param nums
     * @param target
     * @return
     */
    // time = O(logn), space = O(1)
    public int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n;
        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] >= target) r = mid;
            else l = mid + 1;
        }
        return r;
    }
}