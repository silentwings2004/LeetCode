package LC601_900;
import java.util.*;
public class LC704_BinarySearch {
    /**
     * Given a sorted (in ascending order) integer array nums of n elements and a target value,
     * write a function to search target in nums. If target exists, then return its index, otherwise return -1.
     *
     * Note:
     *
     * You may assume that all elements in nums are unique.
     * n will be in the range [1, 10000].
     * The value of each element in nums will be in the range [-9999, 9999].
     *
     * @param nums
     * @param target
     * @return
     */
    // time = O(logg), space = O(1)
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] < target) l = mid + 1;
            else r = mid;
        }
        return nums[r] == target ? r : -1;
    }
}