package practice;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: SingleElementinaSortedArray
 * Creater: Silentwings
 * Date: May, 2020
 * Description: 540. Single Element in a Sorted Array
 */
public class LC540_SingleElementinaSortedArray {
    /**
     * You are given a sorted array consisting of only integers where every element appears exactly twice, except for
     * one element which appears exactly once. Find this single element that appears only once.
     *
     *
     *
     * Example 1:
     *
     * Input: [1,1,2,3,3,4,4,8,8]
     * Output: 2
     * Example 2:
     *
     * Input: [3,3,7,7,10,11,11]
     * Output: 10
     *
     *
     * Note: Your solution should run in O(log n) time and O(1) space.
     * @param nums
     * @return
     */
    public int singleNonDuplicate(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException();
        }

        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid - 1 >= 0 || mid + 1 <= nums.length - 1) {
                if (nums[mid - 1] != nums[mid] && nums[mid + 1] != mid) return nums[mid];
                if (nums[mid - 1] == nums[mid]) {
                    if ((mid - 1) % 2 == 1) right = mid - 1;
                    else left = mid + 1;
                } else {
                    if ((mid + 1) % 2 == 1) left = mid + 1;
                    else right = mid - 1;
                }
            } else {
                if (mid == 0 && nums[mid] == nums[mid + 1]) break;
                else return nums[mid];
                if (mid == nums.length - 1 && nums[mid] == nums[mid - 1]) break;
                else return nums[mid];
            }
        }
        return -1;
    }
}
