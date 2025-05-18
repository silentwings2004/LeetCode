package LC601_900;

public class LC713_SubarrayProductLessThanK {
    /**
     * Given an array of integers nums and an integer k, return the number of contiguous subarrays where the product
     * of all the elements in the subarray is strictly less than k.
     *
     * Input: nums = [10,5,2,6], k = 100
     * Output: 8
     *
     * Constraints:
     *
     * 1 <= nums.length <= 3 * 10^4
     * 1 <= nums[i] <= 1000
     * 0 <= k <= 10^6
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int n = nums.length, res = 0, p = 1;
        for (int i = 0, j = 0; i < n; i++) {
            p *= nums[i];
            while (j <= i && p >= k) p /= nums[j++];
            res += i - j + 1;
        }
        return res;
    }
}
/**
 * 当nums[j]>k时，右指针动不了，而左指针依然会顺移，所以可能会出现j<i的情况，此时只需要重置这两个指针即可
 */
