package LC3301_3600;
import java.util.*;
public class LC3366_MinimumArraySum {
    /**
     * You are given an integer array nums and three integers k, op1, and op2.
     *
     * You can perform the following operations on nums:
     *
     * Operation 1: Choose an index i and divide nums[i] by 2, rounding up to the nearest whole number. You can perform
     * this operation at most op1 times, and not more than once per index.
     * Operation 2: Choose an index i and subtract k from nums[i], but only if nums[i] is greater than or equal to k.
     * You can perform this operation at most op2 times, and not more than once per index.
     * Note: Both operations can be applied to the same index, but at most once each.
     *
     * Return the minimum possible sum of all elements in nums after performing any number of operations.
     *
     * Input: nums = [2,8,3,19,3], k = 3, op1 = 1, op2 = 1
     * Output: 23
     *
     * Input: nums = [2,4,3], k = 3, op1 = 2, op2 = 1
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 10^5
     * 0 <= k <= 105
     * 0 <= op1, op2 <= nums.length
     * @param nums
     * @param k
     * @param op1
     * @param op2
     * @return
     */
    // time = O(n^3), space = O(n^3)
    public int minArraySum(int[] nums, int k, int op1, int op2) {
        int n = nums.length, inf = 0x3f3f3f3f;
        int[][][] f = new int[n + 1][op1 + 1][op2 + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= op1; j++) {
                Arrays.fill(f[i][j], inf);
            }
        }
        f[0][0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= op1; j++) {
                for (int u = 0; u <= op2; u++) {
                    f[i][j][u] = f[i - 1][j][u] + nums[i - 1];
                    if (j > 0) f[i][j][u] = Math.min(f[i][j][u], f[i - 1][j - 1][u] + (nums[i - 1] + 1) / 2);
                    if (u > 0 && nums[i - 1] >= k) f[i][j][u] = Math.min(f[i][j][u], f[i - 1][j][u - 1] + nums[i - 1] - k);
                    if (j > 0 && u > 0) {
                        if (nums[i - 1] >= k) {
                            f[i][j][u] = Math.min(f[i][j][u], f[i - 1][j - 1][u - 1] + (nums[i - 1] - k + 1) / 2);
                        }
                        if ((nums[i - 1] + 1) / 2 >= k) {
                            f[i][j][u] = Math.min(f[i][j][u], f[i - 1][j - 1][u - 1] + (nums[i - 1] + 1) / 2 - k);
                        }
                    }
                }
            }
        }
        int res = inf;
        for (int i = 0; i <= op1; i++) {
            for (int j = 0; j <= op2; j++) {
                res = Math.min(res, f[n][i][j]);
            }
        }
        return res;
    }
}