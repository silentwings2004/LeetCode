package LC2700_3000;

public class LC2874_MaximumValueofanOrderedTripletII {
    /**
     * You are given a 0-indexed integer array nums.
     *
     * Return the maximum value over all triplets of indices (i, j, k) such that i < j < k. If all such triplets have a
     * negative value, return 0.
     *
     * The value of a triplet of indices (i, j, k) is equal to (nums[i] - nums[j]) * nums[k].
     *
     * Input: nums = [12,6,1,2,7]
     * Output: 77
     *
     * Input: nums = [1,10,3,4,19]
     * Output: 133
     *
     * Input: nums = [1,2,3]
     * Output: 0
     *
     * Constraints:
     *
     * 3 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^6
     * @param nums
     * @return
     */
    // S1: 枚举j
    // time = O(n), space = O(n)
    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        int[] pre = new int[n], suf = new int[n];
        suf[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) suf[i] = Math.max(suf[i + 1], nums[i]);
        pre[0] = nums[0];
        for (int i = 1; i < n; i++) pre[i] = Math.max(pre[i - 1], nums[i]);

        long res = 0;
        for (int j = 1; j < n - 1; j++) {
            res = Math.max(res, 1L * (pre[j - 1] - nums[j]) * suf[j + 1]);
        }
        return res;
    }

    // S2: 枚举k
    // time = O(n), space = O(1)
    public long maximumTripletValue2(int[] nums) {
        long ans = 0, max_diff = 0, pre_max = 0;
        for (int x : nums) {
            // 先把 x 当做 nums[k]
            ans = Math.max(ans, max_diff * x);
            // 再把 x 当做 nums[j]
            max_diff = Math.max(max_diff, pre_max - x);
            // 再把 x 当做 nums[i]
            pre_max = Math.max(pre_max, x);
        }
        return ans;
    }
}
/**
 * 1. 枚举j
 * (nums[i] - nums[j]) * nums[k]
 * 需要知道 num[j + 1] ~ nums[n - 1]的最大值  => 数组的后缀最大值
 * 需要知道 num[0] ~ nums[j - 1]的最大值 => 数组的前缀最大值
 * 计算数组的后缀最大值 和 前缀最大值
 * suf_max[i] = max(suf_max[i+1], nums[i])
 * pre_max[i] = max(pre-_max[i-1], nums[i])
 *
 * 2. 枚举k
 * 需要维护nums[i] - nums[j]的最大值 max_diff
 * 用j左边的最大值 - nums[j] 就是最大值
 * 把当前枚举的数当做 nums[j]，我们还需要知道左侧的最大值 pre_max
 */