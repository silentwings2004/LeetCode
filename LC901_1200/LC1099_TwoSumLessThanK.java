package LC901_1200;

public class LC1099_TwoSumLessThanK {
    /**
     * Given an array nums of integers and integer k, return the maximum sum such that there exists i < j with
     * nums[i] + nums[j] = sum and sum < k. If no i, j exist satisfying this equation, return -1.
     *
     * Input: nums = [34,23,1,24,75,33,54,8], k = 60
     * Output: 58
     *
     * Input: nums = [10,20,30], k = 15
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 1000
     * 1 <= k <= 2000
     * @param nums
     * @param k
     * @return
     */
    // S1
    // time = O(n^2), space = O(1)
    public int twoSumLessThanK(int[] nums, int k) {
        int n = nums.length, res = -1;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = nums[i] + nums[j];
                if (sum >= k) continue;
                res = Math.max(res, sum);
            }
        }
        return res;
    }

    // S2
    // time = O(n), space = O(n)
    public int twoSumLessThanK2(int[] nums, int k) {
        int[] cnt = new int[1010];
        for (int x : nums) cnt[x]++;
        int l = 1, r = 1000, res = -1;
        while (l <= r) {
            while (cnt[l] == 0) l++;
            while (cnt[r] == 0) r--;
            if (l <= r) {
                if (l < r) {
                    if (l + r < k) {
                        res = Math.max(res, l + r);
                        l++;
                    } else r--;
                } else {
                    if (l + r < k && cnt[l] > 1) res = l + r;
                    break;
                }
            }
        }
        return res;
    }
}