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
/**
 * 只需要关注操作的子数组 => 增量的想法
 * 原始数组的交替和 + 增量
 * 目标：修改一次后，最大化增量
 * 3 1 [4 1 5 9] 2 6
 * + - +  - + - +  -
 * 想清楚每个元素正负号怎么变
 * 操作一个奇数长度子数组 => 本质上来说还是操作一个偶数长度的子数组
 * 一次操作等价于：
 * 选择 nums 的一个长为偶数的子数组，把子数组内的每个数前面的正负号取反
 * (-1)^i * ai  => delta = -2 * (-1)^i * ai = 2 * (-1)^(i+1) * ai
 * bi = 2 * (-1)^(i+1) * ai
 * 在 b 数组里选择一个子数组，它的和最大 => LC53 最大子数组和问题
 * 但这里是选择一个偶数长度的子数组，如何保证是个偶数子数组 => 类似脑筋急转弯
 * 把这些数两两一对
 * dfs(i): 以 i 结尾的最大子数组和
 * 1. 0
 * 2. max(dfs(i-2),0) + bi-1 + bi
 */