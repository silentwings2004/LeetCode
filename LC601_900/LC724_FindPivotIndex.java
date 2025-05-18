package LC601_900;

public class LC724_FindPivotIndex {
    /**
     * Given an array of integers nums, calculate the pivot index of this array.
     *
     * The pivot index is the index where the sum of all the numbers strictly to the left of the index is equal to the
     * sum of all the numbers strictly to the index's right.
     *
     * If the index is on the left edge of the array, then the left sum is 0 because there are no elements to the left.
     * This also applies to the right edge of the array.
     *
     * Return the leftmost pivot index. If no such index exists, return -1.
     *
     * Input: nums = [1,7,3,6,5,6]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= nums.length <= 104
     * -1000 <= nums[i] <= 1000
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int pivotIndex(int[] nums) {
        int sum = 0;
        for (int x : nums) sum += x;
        for (int i = 0, s = 0; i < nums.length; i++) {
            if (s == sum - s - nums[i]) return i;
            s += nums[i];
        }
        return -1;
    }
}