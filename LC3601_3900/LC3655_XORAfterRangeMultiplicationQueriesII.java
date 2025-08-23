package LC3601_3900;
import java.util.*;
public class LC3655_XORAfterRangeMultiplicationQueriesII {
    /**
     * You are given an integer array nums of length n and a 2D integer array queries of size q, where queries[i] =
     * [li, ri, ki, vi].
     *
     * For each query, you must apply the following operations in order:
     *
     * Set idx = li.
     * While idx <= ri:
     * Update: nums[idx] = (nums[idx] * vi) % (109 + 7).
     * Set idx += ki.
     * Return the bitwise XOR of all elements in nums after processing all queries.
     *
     * Input: nums = [1,1,1], queries = [[0,2,1,4]]
     * Output: 4
     *
     * Input: nums = [2,3,1,5,4], queries = [[1,4,2,3],[0,2,1,2]]
     * Output: 31
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * 1 <= q == queries.length <= 10^5
     * queries[i] = [li, ri, ki, vi]
     * 0 <= li <= ri < n
     * 1 <= ki <= n
     * 1 <= vi <= 10^5
     * @param nums
     * @param queries
     * @return
     */
    // time = O(n * sqrt(q) + qlogM), space = O(n * sqrt(q))
    public int xorAfterQueries(int[] nums, int[][] queries) {
        long mod = (long)(1e9 + 7);
        int n = nums.length, m = (int)Math.sqrt(n) + 1;
        int[][] diff = new int[m][];
        for (int[] q : queries) {
            int l = q[0], r = q[1], k = q[2];
            long v = q[3];
            if (k < m) {
                if (diff[k] == null) {
                    diff[k] = new int[n + k];
                    Arrays.fill(diff[k], 1);
                }
                diff[k][l] = (int)(diff[k][l] * v % mod);
                r = r - (r - l) % k + k; // 离 r 最近的 k 的倍数，因为商分数组右端点标记在 r + 1 的位置上，所以最后还要 + k
                diff[k][r] = (int)(diff[k][r] * qmi(v, mod - 2, mod) % mod);
            } else {
                for (int i = l; i <= r; i += k) {
                    nums[i] = (int)(nums[i] * v % mod);
                }
            }
        }

        for (int k = 0; k < m; k++) {
            int[] d = diff[k];
            if (d == null) continue;
            for (int start = 0; start < k; start++) {
                long mul = 1;
                for (int i = start; i < n; i += k) {
                    mul = mul * d[i] % mod;
                    nums[i] = (int)(nums[i] * mul % mod);
                }
            }
        }
        int res = 0;
        for (int x : nums) res ^= x;
        return res;
    }

    private long qmi(long a, long k, long mod) {
        long res = 1;
        while (k > 0) {
            if ((k & 1) == 1) res = res * a % mod;
            a = a * a % mod;
            k >>= 1 ;
        }
        return res;
    }
}
/**
 * 根号算法
 * B = sqrt(n)
 * k 较大时 => 直接暴力 (k >= B) => O(nq / B)
 * k 较小时 => 商分数组 (k < B) => O(B * n + qlogm)
 * n * (B + q / B) >= 2 * sqrt(q)  B = sqrt(q)
 * 对勾函数
 * time = O(n*sqrt(q) + qlogm)
 * r -> r - (r - l) % k + k
 * % k 的子集之间本什么没有交集 => 可以直接合并到一个数组中即可
 */