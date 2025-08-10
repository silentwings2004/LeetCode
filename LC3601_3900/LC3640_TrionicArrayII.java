package LC3601_3900;
import java.util.*;
public class LC3640_TrionicArrayII {
    /**
     * You are given an integer array nums of length n.
     *
     * A trionic subarray is a contiguous subarray nums[l...r] (with 0 <= l < r < n) for which there exist indices
     * l < p < q < r such that:
     *
     * nums[l...p] is strictly increasing,
     * nums[p...q] is strictly decreasing,
     * nums[q...r] is strictly increasing.
     * Return the maximum sum of any trionic subarray in nums.
     *
     * Input: nums = [0,-2,-1,-3,0,2,-1]
     * Output: -4
     *
     * Input: nums = [1,4,2,7]
     * Output: 14
     *
     * Constraints:
     *
     * 4 <= n = nums.length <= 10^5
     * -109 <= nums[i] <= 10^9
     * It is guaranteed that at least one trionic subarray exists.
     * @param nums
     * @return
     */
    // S1: DP
    // time = O(n), space = O(1)
    public long maxSumTrionic(int[] nums) {
        int n = nums.length;
        long inf = (long)1E18;
        long res = -inf;
        long f1 = -inf, f2 = -inf, f3 = -inf;
        for (int i = 1; i < n; i++) {
            int x = nums[i - 1], y = nums[i];
            f3 = x < y ? Math.max(f3, f2) + y : -inf;
            f2 = x > y ? Math.max(f2, f1) + y : -inf;
            f1 = x < y ? Math.max(f1, x) + y : -inf;
            res = Math.max(res, f3);
        }
        return res;
    }

    // S2: 分组循环
    // time = O(n), space = O(1)
    public long maxSumTrionic2(int[] nums) {
        int n = nums.length;
        long ans = Long.MIN_VALUE;
        for (int i = 0; i < n;) {
            // Section 1
            int start = i;
            for (i++; i < n && nums[i - 1] < nums[i]; i++);
            if (i == start + 1) continue;

            // Section 2
            int peak = i - 1;
            long res = nums[peak - 1] + nums[peak]; // 第一段的最后两个数必选
            for (; i < n && nums[i - 1] > nums[i]; i++) res += nums[i];
            if (i == peak + 1 || i == n || nums[i - 1] == nums[i]) continue;

            // Section 3
            int bottom = i - 1;
            res += nums[i]; // 第三段的前两个数必选（第一个数在上面的循环中加了）
            long ms = 0, s = 0;
            for (i++; i < n && nums[i - 1] < nums[i]; i++) {
                s += nums[i];
                ms = Math.max(ms, s);
            }
            res += ms;

            ms = 0;
            s = 0;
            for (int j = peak - 2; j >= start; j--) {
                s += nums[j];
                ms = Math.max(ms, s);
            }
            res += ms;
            ans = Math.max(ans, res);
            i = bottom; // 第三段的起点也是下一个极大三段式子数组的第一段的起点
        }
        return ans;
    }
}