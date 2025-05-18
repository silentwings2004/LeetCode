package LC1501_1800;

public class LC1770_MaximumScorefromPerformingMultiplicationOperations {
    /**
     * You are given two integer arrays nums and multipliers of size n and m respectively, where n >= m. The arrays are
     * 1-indexed.
     *
     * You begin with a score of 0. You want to perform exactly m operations. On the ith operation (1-indexed), you will:
     *
     * Choose one integer x from either the start or the end of the array nums.
     * Add multipliers[i] * x to your score.
     * Remove x from the array nums.
     * Return the maximum score after performing m operations.
     *
     * Input: nums = [1,2,3], multipliers = [3,2,1]
     * Output: 14
     *
     * Input: nums = [-5,-3,-3,-2,7,1], multipliers = [-10,-5,3,4,6]
     * Output: 102
     *
     * Constraints:
     *
     * n == nums.length
     * m == multipliers.length
     * 1 <= m <= 10^3
     * m <= n <= 10^5
     * -1000 <= nums[i], multipliers[i] <= 1000
     * @param nums
     * @param multipliers
     * @return
     */
    // time = O(m^2), space = O(m^2)
    public int maximumScore(int[] nums, int[] multipliers) {
        int n = nums.length, m = multipliers.length;
        if (n >= m * 2) {
            int x = m, y = n - m;
            while (y < n) nums[x++] = nums[y++];
            n = x;
        }

        int[][] f = new int[n + 2][n + 2];
        for (int len = n - m + 1; len <= n; len++) {
            for (int i = 1; i + len - 1 <= n; i++) {
                int j = i + len - 1;
                f[i][j] = Math.max(f[i + 1][j] + nums[i - 1] * multipliers[n - len], f[i][j - 1] + nums[j - 1] * multipliers[n - len]);
            }
        }
        return f[1][n];
    }
}
/**
 * ref: AC492 简化版
 * 只会用到前后m的2部分，中间部分没用，直接删掉
 * 区间dp问题
 * f(i,j)
 * 状态表示：
 * 1. 集合：剩余区间为[i,j]时，所有操作方案的集合
 * 2. 属性：max
 * 状态计算：
 * 1. 删wi: f(i+1,j) + wi * c     下标：(n-len)
 * 2. 删wj：f(i,j-1) + wj * c
 *
 * dp[i][j]: the maximum solution for [i:j]
 * dp[i][j]: the maximum solution when we pick i elements from the head and pick j elements from the tail.
 * xxxooooooxxx
 *   i      j
 * 头尾是天然的，只要定义中间的2个位置即可。
 * dp[i][j]: max{dp[i-1][j] + nums[i] * multipliers[i + j - 1]
 *           dp[i][j-1] + nums[n-j+1] * multipliers[i + j - 1]};
 */