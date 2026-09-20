package LC3901_4200;

public class LC4058_MaximumPulseValueAfterOneSubarrayRotation {
    /**
     * You are given an integer array nums of length n.
     *
     * Define the pulse value of an integer array arr as the alternating sum starting at index 0: pulse(arr) =
     * arr[0] - arr[1] + arr[2] - arr[3] + ...
     *
     * You may perform at most one operation on nums:
     *
     * Choose two indices l and r such that 0 <= l < r <= n - 1.
     * Left-rotate the subarray nums[l..r] by exactly one position. For example, [a, b, c, d] becomes [b, c, d, a].
     * Return the maximum pulse value that can be obtained after performing at most one such operation.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [1,5,2]
     * Output: 6
     *
     * Input: nums = [6,4,3]
     * Output: 7
     *
     * Input: nums = [9,7]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 10^5
     * -10^9 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public long maxValue(int[] nums) {
        int n = nums.length;
        long as = 0;
        for (int i = 0; i < n; i++) {
            as += i % 2 == 0 ? nums[i] : -nums[i];
        }

        long[] f = new long[n + 1];
        long mx = 0;
        for (int i = 1; i < n; i++) {
            int d = nums[i] - nums[i - 1];
            f[i + 1] = Math.max(f[i - 1], 0) + (i % 2 == 1 ? d : -d);
            mx = Math.max(mx, f[i + 1]);
        }
        return as + mx * 2;
    }
}