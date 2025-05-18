package LC2700_3000;
import java.util.*;
public class LC2811_CheckifitisPossibletoSplitArray {
    /**
     * You are given an array nums of length n and an integer m. You need to determine if it is possible to split the
     * array into n non-empty arrays by performing a series of steps.
     *
     * In each step, you can select an existing array (which may be the result of previous steps) with a length of at
     * least two and split it into two subarrays, if, for each resulting subarray, at least one of the following holds:
     *
     * The length of the subarray is one, or
     * The sum of elements of the subarray is greater than or equal to m.
     * Return true if you can split the given array into n arrays, otherwise return false.
     *
     * Note: A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [2, 2, 1], m = 4
     * Output: true
     *
     * Input: nums = [2, 1, 3], m = 5
     * Output: false
     *
     * Input: nums = [2, 3, 3, 2, 3], m = 6
     * Output: true
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 100
     * 1 <= nums[i] <= 100
     * 1 <= m <= 200
     * @param nums
     * @param m
     * @return
     */
    // S1：贪心
    // time = O(n), space = O(1)
    public boolean canSplitArray(List<Integer> nums, int m) {
        int n = nums.size();
        if (n <= 2) return true;
        for (int i = 1; i < n - 1; i++) {
            if (nums.get(i) + nums.get(i - 1) >= m || nums.get(i) + nums.get(i + 1) >= m) return true;
        }
        return false;
    }

    // S2: 记忆化搜索
    // time = O(n^2), space = O(n^2)
    boolean[][] f;
    public boolean canSplitArray2(List<Integer> nums, int m) {
        int n = nums.size();
        if (n <= 2) return true;
        int s = 0;
        for (int x : nums) s += x;
        f = new boolean[n][n];
        return dfs(nums, 0, n - 1, s, m);

    }

    private boolean dfs(List<Integer> nums, int l, int r, int s, int m) {
        if (l + 1 >= r) return true;
        if (f[l][r]) return false;
        if (s - nums.get(l) >= m) {
            if (dfs(nums, l + 1, r, s - nums.get(l), m)) return true;
        }
        if (s - nums.get(r) >= m) {
            if (dfs(nums, l, r - 1, s - nums.get(r), m)) return true;
        }
        f[l][r] = true;
        return false;
    }

    // S3: dp
    // time = O(n^2), space = O(n^2)
    public boolean canSplitArray3(List<Integer> nums, int m) {
        int n = nums.size();
        boolean[][] f = new boolean[n + 1][n + 1];
        for (int i = 0; i <= n; i++) Arrays.fill(f[i], true);
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + nums.get(i - 1);
        for (int len = 3; len <= n; len++) {
            for (int i = 1; i + len - 1 <= n; i++) {
                int j = i + len - 1;
                f[i][j] = f[i][j - 1] && (s[j - 1] - s[i - 1] >= m) || f[i + 1][j] && (s[j] - s[i] >= m);
            }
        }
        return f[1][n];
    }
}
/**
 * dp[i][j] = dp[i+1][j] && sum[i+1:j] >= m || dp[i][j-1] && sum[i:j-1] >= m
 * for (int len = 3; len <= n; len++) {
 *     for (int i = 0; i + len - 1 < n; i++) {
 *         int j = i + len - 1;
 *         dp[i][j] = dp[i+1][j] && sum[i+1:j] >= m || dp[i][j-1] && sum[i:j-1] >= m；
 *     }
 *     return dp[0][n-1]
 * }
 */
