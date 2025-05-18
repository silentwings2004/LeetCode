package LC2401_2700;

public class LC2447_NumberofSubarraysWithGCDEqualtoK {
    /**
     * Given an integer array nums and an integer k, return the number of subarrays of nums where the greatest common
     * divisor of the subarray's elements is k.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * The greatest common divisor of an array is the largest integer that evenly divides all the array elements.
     *
     * Input: nums = [9,3,1,2,6,3], k = 3
     * Output: 4
     *
     * Input: nums = [4], k = 7
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * 1 <= nums[i], k <= 10^9
     * @param nums
     * @param k
     * @return
     */
    // time = O(n^2), space = O(1)
    public int subarrayGCD(int[] nums, int k) {
        int n = nums.length, res = 0;
        for (int i = 0; i < n; i++) {
            int last = nums[i];
            for (int j = i; j < n; j++) {
                last = gcd(last, nums[j]);
                if (last == k) res++;
                else if (last < k) break;
            }
        }
        return res;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
