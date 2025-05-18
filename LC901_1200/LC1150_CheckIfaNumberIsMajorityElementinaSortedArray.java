package LC901_1200;

public class LC1150_CheckIfaNumberIsMajorityElementinaSortedArray {
    /**
     * Given an integer array nums sorted in non-decreasing order and an integer target, return true if target is a
     * majority element, or false otherwise.
     *
     * A majority element in an array nums is an element that appears more than nums.length / 2 times in the array.
     *
     * Input: nums = [2,4,5,5,5,5,5,6,6], target = 5
     * Output: true
     *
     * Input: nums = [10,100,101,101], target = 101
     * Output: false
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * 1 <= nums[i], target <= 10^9
     * nums is sorted in non-decreasing order.
     * @param nums
     * @param target
     * @return
     */
    // time = O(logn), space = O(1)
    public boolean isMajorityElement(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] >= target) r = mid;
            else l = mid + 1;
        }
        return r + n / 2 < n && nums[r + n / 2] == target;
    }
}