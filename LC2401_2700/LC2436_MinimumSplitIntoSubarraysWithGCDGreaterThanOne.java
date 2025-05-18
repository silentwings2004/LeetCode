package LC2401_2700;

public class LC2436_MinimumSplitIntoSubarraysWithGCDGreaterThanOne {
    /**
     * You are given an array nums consisting of positive integers.
     *
     * Split the array into one or more disjoint subarrays such that:
     *
     * Each element of the array belongs to exactly one subarray, and
     * The GCD of the elements of each subarray is strictly greater than 1.
     * Return the minimum number of subarrays that can be obtained after the split.
     *
     * Note that:
     *
     * The GCD of a subarray is the largest positive integer that evenly divides all the elements of the subarray.
     * A subarray is a contiguous part of the array.
     *
     * Input: nums = [12,6,3,14,8]
     * Output: 2
     *
     * Input: nums = [4,12,6,14]
     * Output: 1
     *
     *
     Constraints:

     1 <= nums.length <= 2000
     2 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int minimumSplits(int[] nums) {
        int n = nums.length, res = 0, x = nums[0];
        for (int i = 1; i < n; i++) {
            while (i < n && gcd(x, nums[i]) > 1) x = gcd(x, nums[i++]);
            res++;
            if (i < n) x = nums[i];
        }
        return x == nums[n - 1] ? res + 1 : res;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}