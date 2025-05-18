package LC2401_2700;

public class LC3284_SumofConsecutiveSubarrays {
    /**
     * We call an array arr of length n consecutive if one of the following holds:
     *
     * arr[i] - arr[i - 1] == 1 for all 1 <= i < n.
     * arr[i] - arr[i - 1] == -1 for all 1 <= i < n.
     * The value of an array is the sum of its elements.
     *
     * For example, [3, 4, 5] is a consecutive array of value 12 and [9, 8] is another of value 17. While [3, 4, 3]
     * and [8, 6] are not consecutive.
     *
     * Given an array of integers nums, return the sum of the values of all consecutive subarrays.
     *
     * Since the answer may be very large, return it modulo 10^9 + 7.
     *
     * Note that an array of length 1 is also considered consecutive.
     *
     * Input: nums = [1,2,3]
     * Output: 20
     *
     * Input: nums = [1,3,5,7]
     * Output: 16
     *
     * Input: nums = [7,6,1,2]
     * Output: 32
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int getSum(int[] nums) {
        long mod = (long)(1e9 + 7), res = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (i + 1 < n && Math.abs(nums[i + 1] - nums[i]) == 1) {
                int j = i + 1;
                int v = nums[i + 1] - nums[i];
                while (j < n && nums[j] - nums[j - 1] == v) j++;
                for (int k = i; k < j; k++) {
                    res = (res + 1L * nums[k] * (k - i + 1) * (j - k)) % mod;
                }
                if (j < n && nums[j] - nums[j - 1] == -v) {
                    res = (res - nums[j - 1] + mod) % mod;
                    i = j - 2;
                } else i = j - 1;
            } else res = (res + nums[i]) % mod;
        }
        return (int)res;
    }
}