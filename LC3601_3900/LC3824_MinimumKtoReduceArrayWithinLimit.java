package LC3601_3900;

public class LC3824_MinimumKtoReduceArrayWithinLimit {
    /**
     * You are given a positive integer array nums.
     *
     * For a positive integer k, define nonPositive(nums, k) as the minimum number of operations needed to make every
     * element of nums non-positive. In one operation, you can choose an index i and reduce nums[i] by k.
     *
     * Return an integer denoting the minimum value of k such that nonPositive(nums, k) <= k^2.
     *
     * Input: nums = [3,7,5]
     * Output: 3
     *
     * Input: nums = [1]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(1)
    public int minimumK(int[] nums) {
        int l = 1, r = 100010;
        while (l < r) {
            int mid = l + r >> 1;
            if (check(nums, mid)) r = mid;
            else l = mid + 1;
        }
        return r;
    }

    private boolean check(int[] nums, int mid) {
        int t = 0;
        for (int x : nums) t += (x + mid - 1) / mid;
        return t <= 1L * mid * mid;
    }
}