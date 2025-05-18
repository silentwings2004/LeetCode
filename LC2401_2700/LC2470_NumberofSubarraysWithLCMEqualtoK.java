package LC2401_2700;

public class LC2470_NumberofSubarraysWithLCMEqualtoK {
    /**
     * Given an integer array nums and an integer k, return the number of subarrays of nums where the least common
     * multiple of the subarray's elements is k.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * The least common multiple of an array is the smallest positive integer that is divisible by all the array
     * elements.
     *
     * Input: nums = [3,6,2,7,1], k = 6
     * Output: 4
     *
     * Input: nums = [3], k = 2
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * 1 <= nums[i], k <= 1000
     * @param nums
     * @param k
     * @return
     */
    // time = O(n^2 * logn), space = O(1)
    public int subarrayLCM(int[] nums, int k) {
        int n = nums.length, res = 0;
        for (int i = 0; i < n; i++) {
            int t = nums[i];
            for (int j = i; j < n; j++) {
                t = t * nums[j] / gcd(t, nums[j]);
                if (t == k) res++;
                else if (t > k) break;
            }
        }
        return res;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
