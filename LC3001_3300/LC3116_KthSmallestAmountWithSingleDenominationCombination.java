package LC3001_3300;

public class LC3116_KthSmallestAmountWithSingleDenominationCombination {
    /**
     * You are given an integer array coins representing coins of different denominations and an integer k.
     *
     * You have an infinite number of coins of each denomination. However, you are not allowed to combine coins of
     * different denominations.
     *
     * Return the kth smallest amount that can be made using these coins.
     *
     * Input: coins = [3,6,9], k = 3
     *
     * Output: 9
     *
     * Input: coins = [5,2], k = 7
     *
     * Output: 12
     *
     * Constraints:
     *
     * 1 <= coins.length <= 15
     * 1 <= coins[i] <= 25
     * 1 <= k <= 2 * 10^9
     * coins contains pairwise distinct integers.
     * @param coins
     * @param k
     * @return
     */
    // time = O(n * 2^n * log(m * k) * logM), space = O(1)  m: min(coins), M: max(coins)
    public long findKthSmallest(int[] coins, int k) {
        long l = 1, r = (long)1e15;
        while (l < r) {
            long mid = l + r >> 1;
            if (check(coins, k, mid)) r = mid;
            else l = mid + 1;
        }
        return r;
    }

    private boolean check(int[] a, int k, long mid) {
        int n = a.length;
        long s = 0;
        for (int i = 1; i < 1 << n; i++) {
            long t = 1;
            int cnt = Integer.bitCount(i);
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) == 1) {
                    t = lcm(t, a[j]);
                }
            }
            s += mid / t * (cnt % 2 == 1 ? 1 : -1);
        }
        return s >= k;
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }
}
/**
 * 提问：为什么二分的答案一定是一个能从coins中制造出的金额？=> 答案一定是某个 coins[i] 的倍数
 * 证明：反证法
 * 假设二分结果 ans 不是一个可以从 coins 中制造出的金额
 * <= ans 的有 k 个
 * <= ans - 1 的也有 k 个
 * => check(ans - 1) == True
 * 二分：有多少个金额是 <= m
 * 个数 < k
 * 容斥原理
 */