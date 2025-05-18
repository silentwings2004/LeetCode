package LC3001_3300;

public class LC3139_MinimumCosttoEqualizeArray {
    /**
     * You are given an integer array nums and two integers cost1 and cost2. You are allowed to perform either of the
     * following operations any number of times:
     *
     * Choose an index i from nums and increase nums[i] by 1 for a cost of cost1.
     * Choose two different indices i, j, from nums and increase nums[i] and nums[j] by 1 for a cost of cost2.
     * Return the minimum cost required to make all elements in the array equal.
     *
     * Since the answer may be very large, return it modulo 10^9 + 7.
     *
     * Input: nums = [4,1], cost1 = 5, cost2 = 2
     *
     * Output: 15
     *
     * Input: nums = [2,3,3,3,5], cost1 = 2, cost2 = 1
     *
     * Output: 6
     *
     * Input: nums = [3,5,3], cost1 = 1, cost2 = 3
     *
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^6
     * 1 <= cost1 <= 10^6
     * 1 <= cost2 <= 10^6
     * @param nums
     * @param cost1
     * @param cost2
     * @return
     */
    // time = O(n + 2 * k), space = O(1)
    public int minCostToEqualizeArray(int[] nums, int cost1, int cost2) {
        long mod = (long)(1e9 + 7);
        int n = nums.length, minv = nums[0], maxv = nums[0];
        long sum = 0;
        for (int x : nums) {
            minv = Math.min(minv, x);
            maxv = Math.max(maxv, x);
            sum += x;
        }

        long s = 1L * maxv * n - sum;
        if (cost1 * 2 <= cost2) return (int)(s * cost1 % mod);

        long res = Long.MAX_VALUE;
        for (int x = maxv; x <= maxv * 2; x++) {
            int d = x - minv;
            long t = 0;
            if (d < s - d) t = s / 2 * cost2 + s % 2 * cost1;
            else t = (s - d) * cost2 + (d - (s - d)) * cost1;
            res = Math.min(res, t);
            s += n;
        }
        return (int)(res % mod);
    }
}
/**
 * 1. 枚举答案: 枚举最终所有数都变成什么(枚举值域)
 * 总共增加 s 次
 * 1 3 4 4
 * case 1: 如果 c1 * 2 <= c2 => 全部用操作1完成, 代价 = s * c1
 * case 2: 如果 c1 * 2 > c2 => 尽量多的采用操作2
 *  d = x - min(nums)
 *  case 2-1: d <= s - d  那么可以尽量多的使用操作2
 *            代价 = s // 2 * c2 + s % 2 * c1
 *  case 2-2: d > s - d
 *            代价 = (s - d) * cost2 + (d - (s - d)) * cost1
 * 2. 二分 / 三分
 * 3. 直线交点
 */