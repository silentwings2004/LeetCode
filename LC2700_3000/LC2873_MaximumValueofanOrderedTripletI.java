package LC2700_3000;

public class LC2873_MaximumValueofanOrderedTripletI {
    /**
     * You are given a 0-indexed integer array nums.
     *
     * Return the maximum value over all triplets of indices (i, j, k) such that i < j < k. If all such triplets have a
     * negative value, return 0.
     *
     * The value of a triplet of indices (i, j, k) is equal to (nums[i] - nums[j]) * nums[k].
     *
     * Input: nums = [12,6,1,2,7]
     * Output: 77
     *
     * Input: nums = [1,10,3,4,19]
     * Output: 133
     *
     * Input: nums = [1,2,3]
     * Output: 0
     *
     * Constraints:
     *
     * 3 <= nums.length <= 100
     * 1 <= nums[i] <= 10^6
     * @param nums
     * @return
     */
    // time = O(n^3), space = O(1)
    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        long res = 0;
        for (int i = 0; i + 2 < n; i++) {
            for (int j = i + 1; j + 1 < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    res = Math.max(res, (1L * nums[i] - nums[j]) * nums[k]);
                }
            }
        }
        return res;
    }
}