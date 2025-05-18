package LC2401_2700;

public class LC2460_ApplyOperationstoanArray {
    /**
     * You are given a 0-indexed array nums of size n consisting of non-negative integers.
     *
     * You need to apply n - 1 operations to this array where, in the ith operation (0-indexed), you will apply the
     * following on the ith element of nums:
     *
     * If nums[i] == nums[i + 1], then multiply nums[i] by 2 and set nums[i + 1] to 0. Otherwise, you skip this operation.
     * After performing all the operations, shift all the 0's to the end of the array.
     *
     * For example, the array [1,0,2,0,0,1] after shifting all its 0's to the end, is [1,2,1,0,0,0].
     * Return the resulting array.
     *
     * Note that the operations are applied sequentially, not all at once.
     *
     * Input: nums = [1,2,2,1,1,0]
     * Output: [1,4,2,0,0,0]
     *
     * Input: nums = [0,1]
     * Output: [1,0]
     *
     * Constraints:
     *
     * 2 <= nums.length <= 2000
     * 0 <= nums[i] <= 1000
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int[] applyOperations(int[] nums) {
        int n = nums.length;
        for (int i = 0; i + 1 < n; i++) {
            if (nums[i] == nums[i + 1]) {
                nums[i] *= 2;
                nums[i + 1] = 0;
            }
        }

        int j = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) nums[j++] = nums[i];
        }
        while (j < n) nums[j++] = 0;
        return nums;
    }
}
