package LC301_600;
import java.util.*;
public class LC494_TargetSum {
    /**
     * You are given an integer array nums and an integer target.
     *
     * You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums
     * and then concatenate all the integers.
     *
     * For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the
     * expression "+2-1".
     * Return the number of different expressions that you can build, which evaluates to target.
     *
     * Input: nums = [1,1,1,1,1], target = 3
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= nums.length <= 20
     * 0 <= nums[i] <= 1000
     * 0 <= sum(nums[i]) <= 1000
     * -1000 <= target <= 1000
     * @param nums
     * @param target
     * @return
     */
    // time = O(n * k), space = O(n * k)  k: sum of the array
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        int[][] f = new int[25][2010];
        int offset = 1000;
        f[0][offset] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = -1000; j <= 1000; j++) {
                int a = j - nums[i - 1], b = j + nums[i - 1];
                if (a >= -1000 && a <= 1000) f[i][j + offset] += f[i - 1][a + offset];
                if (b >= -1000 && b <= 1000) f[i][j + offset] += f[i - 1][b + offset];
            }
        }
        return f[n][target + offset];
    }

    // S2
    // time = O(n), space = O(n)
    public int findTargetSumWays2(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        for (int x : nums) {
            HashMap<Integer, Integer> hm = new HashMap<>(map);
            map.clear();
            for (int y : hm.keySet()) {
                map.put(y + x, map.getOrDefault(y + x, 0) + hm.get(y));
                map.put(y - x, map.getOrDefault(y - x, 0) + hm.get(y));
            }
        }
        return map.getOrDefault(target, 0);
    }
}
/**
 * dp[x]  x < 1000
 * 状态数组  dp[i][x] 考虑到第i个的时候
 * dp[i][x] = dp[i-1][...]
 * s s+1 / s-1
 * n  n     n
 * dp[i][s+1] += dp[i-1][s]
 * dp[i][s-1] += dp[i-1][s]
 * why use dp[sum]? 避免重复
 * 1 1 1 1  => 2^4 = 16
 *            4, -4 => 9
 *            N, -N = 2N
 * dp[i][5] : dp[i][s+nums[i]]
 *            dp[i][s'+nums[i']]
 *
 * dp状态表示：
 * 集合：前i个数总和为j的所有方案的集合
 * 属性：数量
 * 状态计算：
 * ai取正：f(i-1,j-ai)
 * ai取负：f(i-1,j+ai)
 * f(0,0) = 1
 */
