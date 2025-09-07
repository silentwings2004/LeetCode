package LC3601_3900;
import java.util.*;
public class LC3671_SumofBeautifulSubsequences {
    /**
     * You are given an integer array nums of length n.
     *
     * For every positive integer g, we define the beauty of g as the product of g and the number of strictly increasing
     * subsequences of nums whose greatest common divisor (GCD) is exactly g.
     *
     * Return the sum of beauty values for all positive integers g.
     *
     * Since the answer could be very large, return it modulo 10^9 + 7.
     *
     * A subsequence is a non-empty array that can be derived from another array by deleting some or no elements without
     * changing the order of the remaining elements.
     *
     * Input: nums = [1,2,3]
     * Output: 10
     *
     * Input: nums = [4,6]
     * Output: 12
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 10^4
     * 1 <= nums[i] <= 7 * 10^4
     * @param nums
     * @return
     */
    // time = O(klogk + n * (logk)^2), space = O(klogk)  k: maximum of nums
    long mod = (long)(1e9 + 7);
    public int totalBeauty(int[] nums) {
        int mx = 0;
        for (int x : nums) mx = Math.max(mx, x);
        int[] phi = new int[mx + 1];
        for (int i = 0; i <= mx; i++) phi[i] = i;
        for (int i = 2; i <= mx; i++) {
            if (phi[i] == i) {
                for (int j = i; j <= mx; j += i) {
                    phi[j] -= phi[j] / i;
                }
            }
        }

        List<Integer>[] q = new List[mx + 1];
        for (int i = 0; i <= mx; i++) q[i] = new ArrayList<>();
        for (int i = 1; i <= mx; i++) {
            for (int j = i; j <= mx; j += i) {
                q[j].add(i);
            }
        }

        long[] f = new long[mx + 1];
        Fenwick[] fen = new Fenwick[mx + 1];
        for (int i = 1; i <= mx; i++) fen[i] = new Fenwick(mx / i + 1);
        for (int x : nums) {
            for (int d : q[x]) {
                int p = x / d;
                long s = fen[d].sum(p - 1);
                long cnt = (s + 1) % mod;
                f[d] = (f[d] + cnt) % mod;
                fen[d].add(p - 1, cnt);
            }
        }
        long res = 0;
        for (int i = 1; i <= mx; i++) {
            long t = phi[i] * f[i] % mod;
            res = (res + t) % mod;
        }
        return (int)res;
    }

    class Fenwick {
        private int n;
        private long[] a;
        public Fenwick(int n) {
            this.n = n;
            this.a = new long[n + 1];
        }

        private void add(int x, long v) {
            for (int i = x + 1; i <= n; i += i & -i) {
                a[i - 1] = (a[i - 1] + v) % mod;
            }
        }

        private long sum(int x) {
            long ans = 0;
            for (int i = x; i > 0; i -= i & -i) {
                ans = (ans + a[i - 1]) % mod;
            }
            return ans;
        }

        private long rangeSum(int l, int r) {
            return sum(r) - sum(l);
        }
    }
}
/**
 * 倍数容斥 -> gcd = 2 * k - f4 - f6 - f8 - ...
 * [1,2,3,4,4,3,2] -> [2,4,4,2]
 * 给定一个数组 a，如何计算 a 的严格递增子序列
 * f(x) = 1 + sum(fy) (y = 1, x - 1)
 * 值域上的前缀和，有单点更新和前缀查询操作 => 树状数组
 * 2 个 DP
 * 懒重置
 */
