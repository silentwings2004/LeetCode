package LC1201_1500;
import java.util.*;
public class LC1449_FormLargestIntegerWithDigitsThatAdduptoTarget {
    /**
     * Given an array of integers cost and an integer target, return the maximum integer you can paint under the
     * following rules:
     *
     * The cost of painting a digit (i + 1) is given by cost[i] (0-indexed).
     * The total cost used must be equal to target.
     * The integer does not have 0 digits.
     * Since the answer may be very large, return it as a string. If there is no way to paint any integer given the
     * condition, return "0".
     *
     * Input: cost = [4,3,2,5,6,7,2,5,5], target = 9
     * Output: "7772"
     *
     * Input: cost = [7,6,5,5,5,6,8,7,8], target = 12
     * Output: "85"
     *
     * Input: cost = [2,4,6,2,4,6,4,4,4], target = 5
     * Output: "0"
     *
     * Constraints:
     *
     * cost.length == 9
     * 1 <= cost[i], target <= 5000
     * @param cost
     * @param target
     * @return
     */
    // S1
    // time = O(t), space = O(t)
    final int INF = (int) 1e8;
    public String largestNumber(int[] cost, int target) {
        int[][] f = new int[10][target + 1];

        for (int i = 1; i <= target; i++) f[0][i] = -INF;
        for (int i = 1; i <= 9; i++) {
            for (int j = 0; j <= target; j++) {
                f[i][j] = f[i - 1][j];
                if (j >= cost[i - 1]) f[i][j] = Math.max(f[i][j], f[i][j - cost[i - 1]] + 1);
            }
        }
        if (f[9][target] < 1) return "0";
        StringBuilder sb = new StringBuilder();
        for (int i = 9, j = target; i > 0; i--) {
            while (j >= cost[i - 1] && f[i][j] == f[i][j - cost[i - 1]] + 1) {
                sb.append(i);
                j -= cost[i - 1];
            }
        }
        return sb.toString();
    }

    // S2: dp
    // time = O(t), space = O(t)
    public String largestNumber2(int[] cost, int target) {
        String[] f = new String[target + 1];
        Arrays.fill(f, "");

        for (int j = 1; j <= target; j++) {
            f[j] = "#";
            for (int i = 1; i <= 9; i++) {
                if (j < cost[i - 1] || f[j - cost[i - 1]].equals("#")) continue;
                String candidate = f[j - cost[i - 1]] + i;
                if (candidate.length() > f[j].length() || candidate.length() == f[j].length() && candidate.compareTo(f[j]) > 0) {
                    f[j] = candidate;
                }
            }
        }
        return f[target].equals("#") ? "0" : f[target];
    }
}
/**
 * 背包模型：有限值的组合问题求最优解
 * 背包容量是target
 * 9个物品
 * 体积：cost[i]
 * 价值：1
 * 1. 位数最多
 * 2. 字典序最大
 * => 完全背包
 * f(i,j) = max{f(i-1,j), f(i,j-cost[i]) + 1}
 * f(9,target)
 * f(8,target)
 * f(9,target-cost[9])+1 => 能取就取
 *
 * 01 knapsack
 * obj[i], cap
 * dp[c]: the maximum goal if using capacity c
 * for i in obj:
 *      for c in capacity:
 *          dp[i][c] = max(dp[i-1][c], dp[i-1][c-cost[i]] + val[i])
 * return max{dp[n][c] } for c = ...
 *
 * for c in capacity:
 *      // dp[c]
 *      for i in obj:
 *          dp[c] = max{ dp[c], val[i] + dp[c-cost[i]}
 * return max{dp[c]} for c = ....
 */