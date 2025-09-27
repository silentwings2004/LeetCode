package LC3601_3900;

public class LC3688_BitwiseORofEvenNumbersinanArray {
    /**
     * You are given an integer array nums.
     *
     * Return the bitwise OR of all even numbers in the array.
     *
     * If there are no even numbers in nums, return 0.
     *
     * Input: nums = [1,2,3,4,5,6]
     *
     * Output: 6
     *
     * Input: nums = [7,9,11]
     *
     * Output: 0
     *
     * Input: nums = [1,8,16]
     *
     * Output: 24
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int evenNumberBitwiseORs(int[] nums) {
        int res = 0;
        for (int x : nums) {
            if (x % 2 == 0) res |= x;
        }
        return res;
    }
}