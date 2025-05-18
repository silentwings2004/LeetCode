package LC2700_3000;

import java.math.BigInteger;

public class LC2939_MaximumXorProduct {
    /**
     * Given three integers a, b, and n, return the maximum value of (a XOR x) * (b XOR x) where 0 <= x < 2n.
     *
     * Since the answer may be too large, return it modulo 10^9 + 7.
     *
     * Note that XOR is the bitwise XOR operation.
     *
     * Input: a = 12, b = 5, n = 4
     * Output: 98
     *
     * Input: a = 6, b = 7 , n = 5
     * Output: 930
     *
     * Input: a = 1, b = 6, n = 3
     * Output: 12
     *
     * Constraints:
     *
     * 0 <= a, b < 2^50
     * 0 <= n <= 50
     * @param a
     * @param b
     * @param n
     * @return
     */
    // S1: 位运算
    // time = O(1), space = O(1)
    public int maximumXorProduct(long a, long b, int n) {
        if (a < b) {
            long t = a;
            a = b;
            b = t;
        }
        long mask = (1L << n) - 1; // n 个 1
        long ax = a & ~mask; // 无法通过 XOR x 修改的部分
        long bx = b & ~mask;
        a &= mask; // 把高位都去掉，保留低于 n 的比特位
        b &= mask;

        long left = a ^ b; // 其中一个是 1，另一个是 0 的那些比特位
        long one = mask ^ left; // a 和 b 在这个比特位全为 0 或者 全为 1
        ax |= one; // 异或结果一定是 1， 先加到异或结果中
        bx |= one;

        // 接下来就是分配
        if (left > 0 && ax == bx) {
            // 分配方案是 left 的最高位给 ax，其余给 bx
            long high_bit = 1L << (63 - Long.numberOfLeadingZeros(left));
            ax |= high_bit;
            left ^= high_bit; // 从 left 中去掉最高比特位
            // 剩下的都给 bx
        }
        bx |= left;
        long mod = (long)(1e9 + 7);
        return (int)(ax % mod * (bx % mod) % mod);
    }

    // S2
    // time = O(n), space = O(1)
    public int maximumXorProduct2(long a, long b, int n) {
        if (a < b) {
            long t = a;
            a = b;
            b = t;
        }
        long mod = (long)(1e9 + 7);
        long x = 0, y = 0;
        boolean flag = false;
        for (int i = 50; i >= 0; i--) {
            long u = a >> i & 1, v = b >> i & 1;
            if (i >= n) {
                if (u == 1) x |= 1L << i;
                if (v == 1) y |= 1L << i;
            } else {
                if (u == v) {
                    x |= 1L << i;
                    y |= 1L << i;
                } else {
                    if (x == y && !flag) {
                        x |= 1L << i;
                        flag = true;
                    } else y |= 1L << i;
                }
            }
        }
        x %= mod;
        y %= mod;
        return (int)(x * y % mod);
    }

    // S3
    // time = O(n), space = O(1)
    public int maximumXorProduct3(long a, long b, int n) {
        long mod = (long)(1e9 + 7);
        long x = 0, y = 0;
        for (int i = 50; i >= 0; i--) {
            long u = a >> i & 1, v = b >> i & 1;
            if (i >= n) {
                if (u == 1) x |= 1L << i;
                if (v == 1) y |= 1L << i;
            } else {
                if (u == v) {
                    x |= 1L << i;
                    y |= 1L << i;
                } else {
                    long nx = x | 1L << i, ny = y | 1L << i;
                    BigInteger nbx = BigInteger.valueOf(nx);
                    BigInteger nby = BigInteger.valueOf(ny);
                    BigInteger bx = BigInteger.valueOf(x);
                    BigInteger by = BigInteger.valueOf(y);
                    if (nbx.multiply(by).compareTo(nby.multiply(bx)) > 0) x = nx;
                    else y = ny;
                }
            }
        }
        x %= mod;
        y %= mod;
        return (int)(x * y % mod);
    }
}
/**
 * 1. 对于同一个比特位，如果一个是0，另一个是1，那么无论x填0还是1，那么这个地方异或的结果总是一个是0，另一个是1
 * 2.推论：在把同为0的比特位改成1之后，1的总个数是不变的
 * 3.推论2：ax = a ^ x, bx = b ^ x => ax + bx 是一个定值
 * 在ax + bx 是一个定值的情况下，要求：ax * bx 的最大值
 * 均值定理 基本不等式
 * x + y = c => x(c - x) 开口向下的抛物线
 * 4. 结论：让 ax 和 bx 尽量接近 (差的绝对值尽量小)
 * ax = 10000
 * bx = 01111
 * 5. 分配方案：把最高位的1给 ax, 其余的1给 bx
 * 6. 考虑 n 的限制，会对分配方案产生
 * 6.1 如果大于等于 n 的比特位， a = b, 那么分配方式同上
 * 6.2 如果大于等于 n 的比特位， a > b, 那么把低于 n 的 1 全部给 b
 * 6.3 如果大于等于 n 的比特位， a < b, 那么把低于 n 的 1 全部给 a
 */