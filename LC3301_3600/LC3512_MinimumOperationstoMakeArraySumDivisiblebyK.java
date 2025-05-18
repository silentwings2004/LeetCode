package LC3301_3600;

public class LC3512_MinimumOperationstoMakeArraySumDivisiblebyK {
    /**
     * You are given an integer array nums and an integer k. You can perform the following operation any number of times:
     *
     * Select an index i and replace nums[i] with nums[i] - 1.
     * Return the minimum number of operations required to make the sum of the array divisible by k.
     *
     * Input: nums = [3,9,7], k = 5
     * Output: 4
     *
     * Input: nums = [4,1,3], k = 4
     * Output: 0
     *
     * Input: nums = [3,2], k = 6
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * 1 <= nums[i] <= 1000
     * 1 <= k <= 100
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public int minOperations(int[] nums, int k) {
        int s = 0;
        for (int x : nums) s += x;
        return s % k;
    }
}