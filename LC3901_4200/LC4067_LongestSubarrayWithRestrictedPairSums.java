package LC3901_4200;

public class LC4067_LongestSubarrayWithRestrictedPairSums {
    /**
     * You are given an integer array nums.
     *
     * A subarray nums[l..r] is valid if there are no three distinct indices i, j, and k such that l <= i, j, k <= r and:
     *
     * nums[i] + nums[j] == nums[k]
     * Return the maximum length of a valid subarray of nums.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [2,3,5,3,2,1]
     * Output: 3
     *
     * Input: nums = [3,4,5,6]
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * 1 <= nums[i] <= 500
     * @param nums
     * @return
     */
    // time = O(n^2 + U), space = O(U), U = max{nums}
    public int maxSubarray(int[] nums) {
        int mx = nums[0];
        for (int x : nums) mx = Math.max(mx, x);

        int[] cnts = new int[mx * 2 + 1];
        int[] cntd = new int[mx + 1];
        int n = nums.length, l = 0, res = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            while (cnts[x] > 0 || cntd[x] > 0) {
                int y = nums[l++];
                for (int j = l; j < i; j++) {
                    int z = nums[j];
                    cnts[y + z]--;
                    cntd[Math.abs(y - z)]--;
                }
            }

            for (int j = l; j < i; j++) {
                int y = nums[j];
                cnts[x + y]++;
                cntd[Math.abs(x - y)]++;
            }
            res = Math.max(res, i - l + 1);
        }
        return res;
    }
}
/**
 * 越长越不满足要求 => 滑动窗口
 * 枚举右端点，取寻找最小的左端点
 * 如何快速判断两数相加或者两数相减 = x
 */