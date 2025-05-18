package LC3001_3300;

public class LC3133_MinimumArrayEnd {
    /**
     * You are given two integers n and x. You have to construct an array of positive integers nums of size n where for
     * every 0 <= i < n - 1, nums[i + 1] is greater than nums[i], and the result of the bitwise AND operation between
     * all elements of nums is x.
     *
     * Return the minimum possible value of nums[n - 1].
     *
     * Input: n = 3, x = 4
     * Output: 6
     *
     * Input: n = 2, x = 7
     * Output: 15
     *
     * Constraints:
     *
     * 1 <= n, x <= 10^8
     * @param n
     * @param x
     * @return
     */
    // S1
    // time = O(logx + logn), space = O(1)
    public long minEnd(int n, int x) {
        n--;
        long t = x;
        for (int i = 0; n > 0; i++) {
            if ((t >> i & 1) == 0) {
                int v = n & 1;
                t |= 1L * v << i;
                n >>= 1;
            }
        }
        return t;
    }

    // S2
    // time = O(logn), space = O(1)
    public long minEnd2(int n, int x) {
        n--;
        int i = 0;
        long t = ~x, res = x;
        while ((n >> i) > 0) {
            long lb = t & -t;
            int bit = n >> i & 1;
            res |= bit * lb;
            t ^= lb;
            i++;
        }
        return res;
    }
}
/**
 * 1. 怎么保证 nums[i] 的 AND 是 x ?
 * x 的第 i 个比特是 1， 那么 nums[i] 的第 i 个比特也必须是 1
 * 核心：把x的二进制中的0看做是“空位”，往空位上依次填入n-1的二进制位即可得到最小的nums[n-1]
 * 2. lowbit => 快速枚举0， 直接跳过1， ~x 取反之后算lowbit即可
 */