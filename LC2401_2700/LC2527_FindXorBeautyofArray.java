package LC2401_2700;

public class LC2527_FindXorBeautyofArray {
    /**
     * You are given a 0-indexed integer array nums.
     *
     * The effective value of three indices i, j, and k is defined as ((nums[i] | nums[j]) & nums[k]).
     *
     * The xor-beauty of the array is the XORing of the effective values of all the possible triplets of indices
     * (i, j, k) where 0 <= i, j, k < n.
     *
     * Return the xor-beauty of nums.
     *
     * Note that:
     *
     * val1 | val2 is bitwise OR of val1 and val2.
     * val1 & val2 is bitwise AND of val1 and val2.
     *
     * Input: nums = [1,4]
     * Output: 5
     *
     * Input: nums = [15,45,20,2,34,35,5,44,32,30]
     * Output: 34
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public int xorBeauty(int[] nums) {
        int res = 0;
        for (int x : nums) res ^= x;
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public int xorBeauty2(int[] nums) {
        int state = 0;
        for (int x : nums) {
            for (int i = 0; i < 32; i++) {
                if ((x >> i & 1) == 1) state ^= 1 << i;
            }
        }
        return state;
    }
}
/**
 * xor of a series of bits
 * => 1  if there is an odd number of bit 1
 * => 0 if there is an even number of bit 1
 *
 */