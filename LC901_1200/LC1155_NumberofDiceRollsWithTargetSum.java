package LC901_1200;

public class  LC1155_NumberofDiceRollsWithTargetSum {
    /**
     * You have n dice and each die has k faces numbered from 1 to k.
     *
     * Given three integers n, k, and target, return the number of possible ways (out of the kn total ways) to roll the
     * dice so the sum of the face-up numbers equals target. Since the answer may be too large, return it
     * modulo 10^9 + 7.
     *
     * Input: n = 1, k = 6, target = 3
     * Output: 1
     *
     * Input: n = 2, k = 6, target = 7
     * Output: 6
     *
     * Input: n = 30, k = 30, target = 500
     * Output: 222616187
     *
     * Constraints:
     *
     * 1 <= n, k <= 30
     * 1 <= target <= 1000
     * @param n
     * @param k
     * @param target
     * @return
     */
    // time = O(n * t * k), space = O(t);
    public int numRollsToTarget(int n, int k, int target) {
        int mod = (int)(1e9 + 7);
        int[] f = new int[target + 1];
        f[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = target; j >= 0; j--) {
                f[j] = 0;
                for (int u = 1; u <= k && u <= j; u++) {
                    f[j] = (f[j] + f[j - u]) % mod;
                }
            }
        }
        return f[target];
    }
}
/**
 * 有限制的选择问题 => 分组背包问题
 * 1.物品
 * 2.体积
 * 3.决策
 * f(i,j) = f(i-1,j-u)
 */