package LC1801_2100;
import java.util.*;
public class LC1911_MaximumAlternatingSubsequenceSum {
    /**
     * The alternating sum of a 0-indexed array is defined as the sum of the elements at even indices minus the sum of
     * the elements at odd indices.
     *
     * For example, the alternating sum of [4,2,5,3] is (4 + 5) - (2 + 3) = 4.
     * Given an array nums, return the maximum alternating sum of any subsequence of nums (after reindexing the elements
     * of the subsequence).
     *
     * A subsequence of an array is a new array generated from the original array by deleting some elements
     * (possibly none) without changing the remaining elements' relative order. For example, [2,7,4] is a subsequence of
     * [4,2,3,7,2,1,4] (the underlined elements), while [2,4,2] is not.
     *
     * Input: nums = [4,2,5,3]
     * Output: 7
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // S1: Greedy
    // time = O(n), space = O(1)
    public long maxAlternatingSum(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return 0;

        long res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res += Math.max(nums[i] - nums[i - 1], 0);
        }
        return res;
    }

    // S2: DP
    // time = O(n), space = O(n)
    public long maxAlternatingSum2(int[] nums) {
        int n = nums.length;
        long[][] f = new long[n + 1][2];
        for (int i = 0; i <= n; i++) Arrays.fill(f[i], (long)1e15);
        for (int i = 1; i <= n; i++) {
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][1] - nums[i - 1]);
            f[i][1] = Math.max(f[i - 1][1], Math.max(0L, f[i - 1][0] + nums[i - 1]));
        }
        return Math.max(f[n][0], f[n][1]);
    }
}
/**
 * same as LC122
 * a-b  b-c  => a-c
 * 股市买卖 => 攫取每一天的利润
 * 有0对，直接加
 * 不违反正负交替的原则
 * 虚拟的给邻接的2位加上正负号，都互相前后抵消
 * 非常典型的greedy算法
 *
 * f(i,0):从前i个中选了偶数个数的最大和
 * f(i,1):从前i个中选了奇数个数的最大和
 * f(i,0) = max(f(i-1,0) + f(i-1,1) - w[i]}
 * f(i,1) = max(f(i-1,1) + max{0, f(i-1,0) + w[i]}
 */