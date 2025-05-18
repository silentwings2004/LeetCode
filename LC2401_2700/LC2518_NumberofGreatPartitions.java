package LC2401_2700;

public class LC2518_NumberofGreatPartitions {
    /**
     * You are given an array nums consisting of positive integers and an integer k.
     *
     * Partition the array into two ordered groups such that each element is in exactly one group. A partition is called
     * great if the sum of elements of each group is greater than or equal to k.
     *
     * Return the number of distinct great partitions. Since the answer may be too large, return it modulo 10^9 + 7.
     *
     * Two partitions are considered distinct if some element nums[i] is in different groups in the two partitions.
     *
     * Input: nums = [1,2,3,4], k = 4
     * Output: 6
     *
     * Input: nums = [3,3,3], k = 4
     * Output: 0
     *
     * Input: nums = [6,6], k = 2
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums.length, k <= 1000
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @param k
     * @return
     */
    // time = O(n * k), space = O(k)
    final int N = 1010;
    public int countPartitions(int[] nums, int k) {
        long sum = 0, mod = (long)(1e9 + 7);
        int n = nums.length;
        for (int x : nums) sum += x;
        if (sum < k * 2) return 0;

        long p = 1;
        for (int i = 0; i < n; i++) p = p * 2 % mod;

        long[] f = new long[k];
        f[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = k - 1; j >= nums[i - 1]; j--) {
                f[j] = (f[j] + f[j - nums[i - 1]]) % mod;
            }
        }

        long tot = 0;
        for (int i = 0; i < k; i++) tot = (tot + f[i]) % mod;
        return (int)((p - tot * 2 + mod) % mod);
    }
}
/**
 * dp[i][s]: from the first i elements, how many different partitions s.t. the sum of group A is s.
 * x x x x x i
 * dp[i][s] = dp[i-1][s] + dp[i-1][s-nums[i]]
 * 0,1,2,...k-1,(k,k+1,k+2,....) => 2^i 总集
 * for (int i = 0; i < n; i++)
 *      for (int s = 0; s < k; s++)
 *          dp[i][s] = dp[i-1][s] + dp[i-1][s-nums[i]];
 *
 * totalSum >= 2 * k
 * # of partitions s.t. such {group A} >= k
 * 2^n - sum{dp[n][0] + dp[n][1] + ... + dp[n][k-1]}
 *     - # of partitions s.t. such {group A} >= k && sum{group B} < k => 本质上是一个东西
 *     => - # of partitions s.t. such that sum{group B} < k
 *     => - # of partitions s.t. such that sum{group A} < k
 */