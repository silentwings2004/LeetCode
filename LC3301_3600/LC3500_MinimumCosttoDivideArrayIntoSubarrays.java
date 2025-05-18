package LC3301_3600;
import java.util.*;
public class LC3500_MinimumCosttoDivideArrayIntoSubarrays {
    /**
     * You are given two integer arrays, nums and cost, of the same size, and an integer k.
     *
     * You can divide nums into subarrays. The cost of the ith subarray consisting of elements nums[l..r] is:
     *
     * (nums[0] + nums[1] + ... + nums[r] + k * i) * (cost[l] + cost[l + 1] + ... + cost[r]).
     * Note that i represents the order of the subarray: 1 for the first subarray, 2 for the second, and so on.
     *
     * Return the minimum total cost possible from any valid division.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [3,1,4], cost = [4,6,6], k = 1
     * Output: 110
     *
     * Input: nums = [4,8,5,1,14,2,2,12,1], cost = [7,2,8,4,2,2,1,1,2], k = 7
     * Output: 985
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * cost.length == nums.length
     * 1 <= nums[i], cost[i] <= 1000
     * 1 <= k <= 1000
     * @param nums
     * @param cost
     * @param k
     * @return
     */
    // time = O(n^2), space = O(n)
    public long minimumCost(int[] nums, int[] cost, int k) {
        int n = nums.length;
        long[] sn = new long[n + 1], sc = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            sn[i] = sn[i - 1] + nums[i - 1];
            sc[i] = sc[i - 1] + cost[i - 1];
        }

        final long inf = (long)1E18;
        long[] f = new long[n + 1];
        Arrays.fill(f, inf);
        f[0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                long t = sn[i] * (sc[i] - sc[j]) + k * (sc[n] - sc[j]);
                f[i] = Math.min(f[i], f[j] + t);
            }
        }
        return f[n];
    }
}
/**
 * (Sn(r+1) + k * i) * (S(r+1) - S(L)) =>
 * Sn(r+1) * (S(r+1) - S(L)) + k * i * (S(r+1) - S(L))
 * check: i * (S(r+1) - S(L)) = S(n) - S(L)
 * A + 2 * B + 3 * C = (A + B + C) + (B + C) + C
 * 总和不变，改变计算顺序 => 替换成 cost 数组的后缀和
 * 调整计算的顺序，这样就和 i 没有关系了
 * 带权子数组和
 * 1 * 第一个子数组和 + 2 * 第二个子数组和 + ... + k * 第 k 个子数组和 = k 个后缀和
 */