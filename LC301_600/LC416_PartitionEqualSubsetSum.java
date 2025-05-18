package LC301_600;
import java.util.*;
public class LC416_PartitionEqualSubsetSum {
    /**
     * Given a non-empty array nums containing only positive integers, find if the array can be partitioned into two
     * subsets such that the sum of elements in both subsets is equal.
     *
     * Input: nums = [1,5,11,5]
     * Output: true
     *
     * Constraints:
     *
     * 1 <= nums.length <= 200
     * 1 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // S1: dp
    // time = O(n * target), space = O(target)
    public boolean canPartition(int[] nums) {
        int t = 0;
        for (int x : nums) t += x;
        if (t % 2 != 0) return false;
        t /= 2;
        boolean[] f = new boolean[t + 1];
        f[0] = true;

        int n = nums.length;
        for (int i = 1; i <= n; i++) {
            for (int j = t; j >= nums[i - 1]; j--) {
                f[j] |= f[j - nums[i - 1]];
            }
        }
        return f[t];
    }

    // S2: dp (从现在推将来)
    // time = O(n * k), space = O(n * k)
    final int N = 210, M = 20010;
    public boolean canPartition2(int[] nums) {
        int sum = 0;
        for (int x : nums) sum += x;
        if (sum % 2 == 1) return false;

        boolean[][] f = new boolean[N][M];
        f[0][0] = true;

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int s = 0; s <= sum / 2; s++) {
                if (f[i][s]) {
                    f[i + 1][s] = true;
                    f[i + 1][s + nums[i]] = true;
                }
            }
        }
        return f[n][sum / 2];
    }

    // S3
    public boolean canPartition3(int[] nums) {
        BitSet f = new BitSet(10001);
        f.set(0);
        int sum = 0;
        for (int x : nums) {
            f = f.get(x, f.length());
            sum += x;
        }
        if (sum % 2 != 0) return false;
        return f.get(sum / 2);
    }
}
/**
 * 本身是个NP问题，本质上还是个搜索。
 * dfs是种比较显然的想法，代价就是时间复杂度比较高
 * 你每个数都要试一下，本质就是O(2^n) => TLE
 * 扫一下数据范围，莫名有点小
 * 本质上是个动态规划，01背包问题，要么选要么不选
 * 01背包问题：切入点和思考角度不一样
 * dfs:在高维上搜索解答
 * (1,0,0,1,...,1,0,0)  subset sum = sum / 2  => 大海捞针 2^n
 * s = subset sum
 * dp[s]: whether we can find a subset whose sum equals to s
 * 0 ~ 2*10^4
 * dp[s_small] => dp[s_large]
 * dp[10] = true => dp[20] = true
 * 1 4 6, 10
 *
 * 01背包模板：
 * for (int x : nums) {  // 遍历物品
 *     for (int s = 0; s <= sum / 2; s++) { // 遍历容量
 *         if (dp'[s - x]) {
 *             dp[s] = true;
 *         }
 *     }
 * }
 *
 * dp[100000] => 稀疏，可以改用set来做
 *
 * dp[i][s]: from the first i elements, if there is a method to pick some numbers whose sum equals s
 * 1. dp[i-1][s] = true => dp[i][s] = true;
 * 2. dp[i-1][s-nums[i]] = true => dp[i][s] = true
 * dp[i][s] = dp[i-1][s] || (s >= nums[i] && dp[i-1][s-nums[i]])
 * return dp[n][sum / 2]
 *
 * 经典01背包问题
 * 用位运算来优化
 * 相当于右移x，可以用bitSet去表示，压位32位
 */