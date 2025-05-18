package LC3001_3300;

public class LC3151_SpecialArrayI {
    /**
     * An array is considered special if every pair of its adjacent elements contains two numbers with different parity.
     * You are given an array of integers nums. Return true if nums is a special array, otherwise, return false.
     *
     * Input: nums = [1]
     * Output: true
     *
     * Input: nums = [2,1,4]
     * Output: true
     *
     * Input: nums = [4,3,1,6]
     * Output: false
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public boolean isArraySpecial(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) nums[i] %= 2;
        for (int i = 0; i + 1 < n; i++) {
            if ((nums[i] ^ nums[i + 1]) == 0) return false;
        }
        return true;
    }
}