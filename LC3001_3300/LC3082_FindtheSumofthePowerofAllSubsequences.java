package LC3001_3300;
import java.util.*;
public class LC3082_FindtheSumofthePowerofAllSubsequences {
    /**
     * You are given an integer array nums of length n and a positive integer k.
     *
     * The power of an array of integers is defined as the number of
     * subsequences
     *  with their sum equal to k.
     *
     * Return the sum of power of all subsequences of nums.
     *
     * Since the answer may be very large, return it modulo 10^9 + 7.
     *
     * Input:  nums = [1,2,3], k = 3
     * Output:  6
     *
     * Input:  nums = [2,3,3], k = 5
     * Output:  4
     *
     * Input:  nums = [1,2,3], k = 7
     * Output:  0
     *
     * Constraints:
     *
     * 1 <= n <= 100
     * 1 <= nums[i] <= 10^4
     * 1 <= k <= 100
     * @param nums
     * @param k
     * @return
     */
    // S1
    // time = O(2^n), space = O(n^2 * k)
    long mod = (long)(1e9 + 7);
    int n;
    long[][][] f;
    public int sumOfPower(int[] nums, int k) {
        n = nums.length;
        f = new long[n][n][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(f[i][j], -1);
            }
        }
        return (int)dfs(nums, 0, 0, k);
    }

    private long dfs(int[] nums, int u, int cnt, int k) {
        if (k == 0) return qmi(2, n - cnt);
        if (u >= n) return 0;
        if (f[u][cnt][k] != -1) return f[u][cnt][k];

        long t = 0;
        for (int i = u; i < n; i++) {
            if (k - nums[i] >= 0) t = (t + dfs(nums, i + 1, cnt + 1, k - nums[i])) % mod;
        }
        f[u][cnt][k] = t;
        return t;
    }

    private long qmi(long a, long k) {
        long res = 1;
        while (k > 0) {
            if ((k & 1) == 1) res = res * a % mod;
            a = a * a % mod;
            k >>= 1;
        }
        return res;
    }

    // S2
    // time = O(n * k), space = O(k)
    public int sumOfPower2(int[] nums, int k) {
        long mod = (long)(1e9 + 7);
        long[] f = new long[k + 1];
        f[0] = 1;
        for (int x : nums) {
            for (int j = k; j >= 0; j--) {
                f[j] = (f[j] * 2 + (j >= x ? f[j - x] : 0)) % mod;
            }
        }
        return (int)f[k];
    }
}
/**
 * 贡献法
 * 巫师的力量和
 * 假设和为 k 的子序列 S 的长度是 c
 * 那么 S 会出现在 2^(n - c) 个包含 S 的子序列中
 * 所以 S 对答案的贡献就是 2^(n - c)
 * 二维 01 背包
 * 有 n 个 物品，每个物品的体积是 nums[i]
 * 恰好装满容量为 k 的背包，并且选的物品个数是 c 的方案数
 * 基础算法精讲 18
 *
 * f[i][j] 表示考虑前 i 个数 (nums[0] ~ nums[i-1])
 * 从中选出的子序列和为 j 时的能量和
 * 1. 考虑前 i-1 个数 (nums[0] ~ nums[i-2]) 选出的子序列和为 j 时的能量和
 * f[i-1][j]
 * 2. 考虑前 i-1 个数 (nums[0] ~ nums[i-2]) 选出的子序列和为 j 时的能量和
 * f[i-1][j]
 * 3. 考虑前 i-1 个数 (nums[0] ~ nums[i-2]) 选出的子序列和为 j-nums[i-1] 时的能量和
 * f[i+1][j] = f[i-1][j] * 2 + f[i-1][j-nums[i-1]]
 * f[0][0] = 1
 */