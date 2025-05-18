package LC2700_3000;

public class LC2932_MaximumStrongPairXORI {
    /**
     * You are given a 0-indexed integer array nums. A pair of integers x and y is called a strong pair if it satisfies
     * the condition:
     *
     * |x - y| <= min(x, y)
     * You need to select two integers from nums such that they form a strong pair and their bitwise XOR is the maximum
     * among all strong pairs in the array.
     *
     * Return the maximum XOR value out of all possible strong pairs in the array nums.
     *
     * Note that you can pick the same integer twice to form a pair.
     *
     * Input: nums = [1,2,3,4,5]
     * Output: 7
     *
     * Input: nums = [10,100]
     * Output: 0
     *
     * Input: nums = [5,6,25,30]
     * Output: 7
     *
     * Constraints:
     *
     * 1 <= nums.length <= 50
     * 1 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n^2), space = O(1)
    public int maximumStrongPairXor(int[] nums) {
        int n = nums.length, res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(nums[i] - nums[j]) <= Math.min(nums[i], nums[j])) {
                    res = Math.max(res, nums[i] ^ nums[j]);
                }
            }
        }
        return res;
    }
}