package LC3301_3600;

public class LC3467_TransformArraybyParity {
    /**
     * You are given an integer array nums. Transform nums by performing the following operations in the exact order
     * specified:
     *
     * Replace each even number with 0.
     * Replace each odd numbers with 1.
     * Sort the modified array in non-decreasing order.
     * Return the resulting array after performing these operations.
     *
     * Input: nums = [4,3,2,1]
     * Output: [0,0,1,1]
     *
     * Input: nums = [1,5,1,4,2]
     * Output: [0,0,1,1,1]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 1000
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int[] transformArray(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) nums[i] %= 2;
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) nums[j++] = nums[i];
        }
        while (j < n) nums[j++] = 1;
        return nums;
    }
}