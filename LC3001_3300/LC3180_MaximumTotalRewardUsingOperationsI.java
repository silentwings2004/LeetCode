package LC3001_3300;
import java.util.*;
public class LC3180_MaximumTotalRewardUsingOperationsI {
    /**
     * You are given an integer array rewardValues of length n, representing the values of rewards.
     *
     * Initially, your total reward x is 0, and all indices are unmarked. You are allowed to perform the following
     * operation any number of times:
     *
     * Choose an unmarked index i from the range [0, n - 1].
     * If rewardValues[i] is greater than your current total reward x, then add rewardValues[i] to x (i.e.,
     * x = x + rewardValues[i]), and mark the index i.
     * Return an integer denoting the maximum total reward you can collect by performing the operations optimally.
     *
     * Input: rewardValues = [1,1,3,3]
     *
     * Output: 4
     *
     * Input: rewardValues = [1,6,4,3,2]
     *
     * Output: 11
     *
     * Constraints:
     *
     * 1 <= rewardValues.length <= 2000
     * 1 <= rewardValues[i] <= 2000
     * @param rewardValues
     * @return
     */
    // S1
    // time = O(n * k), space = O(n * k)
    public int maxTotalReward(int[] rewardValues) {
        Arrays.sort(rewardValues);
        int n = rewardValues.length, sum = 0;
        for (int x : rewardValues) sum += x;
        boolean[] f = new boolean[sum * 2 + 1];
        f[0] = true;

        int res = 0;
        for (int i = 0; i < n; i++) {
            if (i > 0 && rewardValues[i] == rewardValues[i - 1]) continue;
            for (int j = 0; j < rewardValues[i]; j++) {
                f[j + rewardValues[i]] |= f[j];
                if (f[j]) res = Math.max(res, j + rewardValues[i]);
            }
        }
        return res;
    }

    // S2: 0-1 背包
    // time = O(n * k), space = O(k)
    public int maxTotalReward2(int[] rewardValues) {
        Arrays.sort(rewardValues);
        int n = rewardValues.length, mx = rewardValues[n - 1];
        boolean[] f = new boolean[mx * 2 + 1];
        f[0] = true;

        int res = 0;
        for (int i = 0; i < n; i++) {
            if (i > 0 && rewardValues[i] == rewardValues[i - 1]) continue; // 去重
            for (int j = mx * 2; j >= rewardValues[i]; j--) {
                if (j - rewardValues[i] < rewardValues[i]) {
                    f[j] |= f[j - rewardValues[i]];
                    if (f[j]) res = Math.max(res, j);
                }
            }
        }
        return res;
    }
}
/**
 * 先选大的，再选小的 => 不行
 * 只能先选小的，再选大的 => 排序
 * 类似0-1背包
 * 从数组中选一个子序列，子序列的和尽量大
 * f[i][j] 表示能否从前 i 个数中，选出和为 j 的子序列
 * 考虑 v = rewardValues[i] 选或不选
 * 不选: f[i][j] = f[i-1][j]
 * 选：f[i][j] = f[i-1][j-v] 0 <= j-v < v
 * => f[i][j] = f[i-1][j] or f[i-1][j-v]
 */