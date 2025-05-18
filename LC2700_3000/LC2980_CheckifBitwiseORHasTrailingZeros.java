package LC2700_3000;

public class LC2980_CheckifBitwiseORHasTrailingZeros {
    /**
     * You are given an array of positive integers nums.
     *
     * You have to check if it is possible to select two or more elements in the array such that the bitwise OR of the
     * selected elements has at least one trailing zero in its binary representation.
     *
     * For example, the binary representation of 5, which is "101", does not have any trailing zeros, whereas the binary
     * representation of 4, which is "100", has two trailing zeros.
     *
     * Return true if it is possible to select two or more elements whose bitwise OR has trailing zeros, return false
     * otherwise.
     *
     * Input: nums = [1,2,3,4,5]
     * Output: true
     *
     * Input: nums = [2,4,8,16]
     * Output: true
     *
     * Input: nums = [1,3,5,7,9]
     * Output: false
     *
     * Constraints:
     *
     * 2 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public boolean hasTrailingZeros(int[] nums) {
        int cnt = 0;
        for (int x : nums) {
            if ((x & 1) == 0) cnt++;
        }
        return cnt >= 2;
    }
}