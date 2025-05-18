package LC3001_3300;
import java.util.*;
public class LC3098_FindtheSumofSubsequencePowers {
    /**
     * You are given an integer array nums of length n, and a positive integer k.
     *
     * The power of a
     * subsequence
     *  is defined as the minimum absolute difference between any two elements in the subsequence.
     *
     * Return the sum of powers of all subsequences of nums which have length equal to k.
     *
     * Since the answer may be large, return it modulo 10^9 + 7.
     *
     * Input: nums = [1,2,3,4], k = 3
     * Output: 4
     *
     * Input: nums = [2,2], k = 2
     * Output: 0
     *
     * Input: nums = [4,3,-1], k = 2
     * Output: 10
     *
     * Constraints:
     *
     * 2 <= n == nums.length <= 50
     * -10^8 <= nums[i] <= 10^8
     * 2 <= k <= n
     * @param nums
     * @param k
     * @return
     */
    // S1: memo
    // time = O(n^4), space = O(n^3)
    int mod = (int)(1e9 + 7);
    int[] nums;
    int n;
    HashMap<String, Integer> map;
    public int sumOfPowers(int[] nums, int k) {
        Arrays.sort(nums);
        this.nums = nums;
        n = nums.length;
        map = new HashMap<>();
        return dfs(0, Integer.MAX_VALUE, k, -1);
    }

    private int dfs(int u, int minDiff, int k, int last) {
        if (k == 0) return minDiff;
        if (u == n) return 0;

        String h = k + "#" + minDiff + "#" + last;
        if (map.containsKey(h)) return map.get(h);
        int updateDiff = last == -1 ? minDiff : Math.min(minDiff, nums[u] - nums[last]);
        int x = dfs(u + 1, updateDiff, k - 1, u);
        int y = dfs(u + 1, minDiff, k, last);
        map.put(h, (x + y) % mod);
        return map.get(h);
    }

    // S2: dp
    public int sumOfPowers2(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        long res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int d = nums[j] - nums[i];
                res = (res + helper(nums, k, d, i, j)) % mod;
            }
        }
        return (int)res;
    }

    private long helper(int[] nums, int k, int d, int a, int b) {
        int n = nums.length;
        long[][] dp1 = new long[n + 1][n + 2];
        long[][] dp2 = new long[n + 1][n + 2];
        for (int i = 1; i <= n; i++) dp1[i][1] = dp2[i][1] = 1;
        a++;
        b++;
        for (int i = 1; i <= a; i++) {
            for (int j = 2; j <= k; j++) {
                for (int u = 1; nums[i - 1] - nums[u - 1] > d && u < i; u++) {
                    dp1[i][j] = (dp1[i][j] + dp1[u][j - 1]) % mod;
                }
            }
        }
        for (int i = n - 1; i >= b; i--) {
            for (int j = 2; j <= k; j++) {
                for (int u = n; nums[u - 1] - nums[i - 1] >= d && u > i; u--) {
                    dp2[i][j] = (dp2[i][j] + dp2[u][j - 1]) % mod;
                }
            }
        }
        long res = 0;
        for (int i = 1; i < k; i++) {
            res = (res + dp1[a][i] * dp2[b][k - i] % mod) % mod;
        }
        return res * d;
    }
}
/**
 * 子序列：
 * 相邻无关 0-1背包
 * 相邻相关 最长递增子序列
 * 选或不选
 * 递增子序列个数
 * dfs(i, pre)
 * 不选 dfs(i - 1, pre)
 * 选 dfs(i - 1, nums[i])
 * dfs(i,j,pre)
 * i 当前下标
 * j 还需要选多少个数
 * pre 上一个选的数
 * min_diff 目前选的数的能量
 * dfs(i,j,pre,min_diff)
 * 不选 dfs(i-1,j,pre,min_diff)
 * 选 dfs(i-1,j-1,nums[i],min(min_diff, pre-nums[i]))
 * 递归边界：
 * j = 0 的时候，返回 min_diff
 * j > i + 1, 即使剩下的数全部选，也不足 j 个，返回 0
 *
 * dp[i][j]: how many subsequences ending at i, length of j, adjacent diff > d
 * if nums[i] - nums[k] > d
 *      dp[i][j] = dp[k][j-1]
 * dp2[i][j]: how many subsequences starting at i, length of j, adjacent diff >= d
 * if nums[i] - nums[k] >= d
 *      dp[i][j] += dp[k][j-1]
 * for (int t = 1; t < K; t++)
 *      res += dp[a][t] * dp[b][K - t]
 */