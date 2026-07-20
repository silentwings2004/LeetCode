package LC3901_4200;

public class LC3987_MinimumTotalCosttoProcessAllElements {
    /**
     * You are given an integer array nums and an integer k.
     *
     * Initially, you have k units of resources.
     *
     * You must process the elements of nums from left to right. To process the ith element, you need nums[i] resources.
     *
     * If your available resources are less than nums[i], you may perform an operation that increases your available
     * resources by k. The value of k is fixed and does not change throughout the process. The first such operation
     * incurs a cost of 1, the second incurs a cost of 2, and so on.
     *
     * After processing the ith element, your available resources decrease by nums[i].
     *
     * Return an integer denoting the minimum total cost required to process all elements. Since the answer may be very
     * large, return it modulo 10^9 + 7.
     *
     * Input: nums = [1,2,3,4], k = 4
     * Output: 3
     *
     * Input: nums = [1,1,7,14], k = 4
     * Output: 15
     *
     * Input: nums = [1,2,3,4], k = 10
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * 1 <= k <= 10^9
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public int minimumCost(int[] nums, int k) {
        long mod = (long)(1e9 + 7), s = 0;
        for (int x : nums) s += x;
        long x = (s + k - 1) / k % mod;
        long res = (x - 1) * x / 2 % mod;
        return (int)res;
    }
}