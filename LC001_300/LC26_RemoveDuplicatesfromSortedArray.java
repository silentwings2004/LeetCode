package LC001_300;

public class LC26_RemoveDuplicatesfromSortedArray {
    /**
     * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique
     * element appears only once. The relative order of the elements should be kept the same.
     *
     * Since it is impossible to change the length of the array in some languages, you must instead have the result be
     * placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates,
     * then the first k elements of nums should hold the final result. It does not matter what you leave beyond the
     * first k elements.
     *
     * Return k after placing the final result in the first k slots of nums.
     *
     * Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1)
     * extra memory.
     *
     * Input: nums = [1,1,2]
     * Output: 2, nums = [1,2,_]
     *
     * Input: nums = [0,0,1,1,1,2,2,3,3,4]
     * Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 3 * 10^4
     * -100 <= nums[i] <= 100
     * nums is sorted in non-decreasing order.
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int removeDuplicates(int[] nums) {
        int n = nums.length, k = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[i - 1]) nums[k++] = nums[i];
        }
        return k;
    }
}
/**
 * C++ 里 unique() 函数
 */