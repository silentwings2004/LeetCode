package LC3001_3300;
import java.util.*;
public class LC3145_FindProductsofElementsofBigArray {
    /**
     * A powerful array for an integer x is the shortest sorted array of powers of two that sum up to x. For example,
     * the powerful array for 11 is [1, 2, 8].
     *
     * The array big_nums is created by concatenating the powerful arrays for every positive integer i in ascending
     * order: 1, 2, 3, and so forth. Thus, big_nums starts as [1, 2, 1, 2, 4, 1, 4, 2, 4, 1, 2, 4, 8, ...].
     *
     * You are given a 2D integer matrix queries, where for queries[i] = [fromi, toi, modi] you should calculate (big_
     * nums[fromi] * big_nums[fromi + 1] * ... * big_nums[toi]) % modi.
     *
     * Return an integer array answer such that answer[i] is the answer to the ith query.
     *
     * Input: queries = [[1,3,7]]
     * Output: [4]
     *
     * Input: queries = [[2,5,3],[7,7,4]]
     * Output: [2,2]
     *
     * Constraints:
     *
     * 1 <= queries.length <= 500
     * queries[i].length == 3
     * 0 <= queries[i][0] <= queries[i][1] <= 10^15
     * 1 <= queries[i][2] <= 105
     * @param queries
     * @return
     */
    // time = O(nlogm), space = O(1)
    public int[] findProductsOfElements(long[][] queries) {
        int n = queries.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            long l = queries[i][0], r = queries[i][1], mod = queries[i][2];
            long cr = cal(r + 1);
            long cl = cal(l);
            res[i] = (int)qmi(2, cr - cl, mod);
        }
        return res;
    }

    private long cal(long x) {
        long t = 0, n = 0, cnt = 0, s = 0;
        for (int i = 63 - Long.numberOfLeadingZeros(x + 1); i > 0; i--) {
            long v = (1L * cnt << i) + (1L * i << (i - 1));
            if (v <= x) {
                x -= v;
                t += (s << i) + ((1L * i * (i - 1) / 2) << (i - 1));
                s += i;
                cnt++;
                n |= 1L << i;
            }
        }
        if (cnt <= x) {
            x -= cnt;
            t += s;
            n++;
        }
        while (x-- > 0) {
            t += Long.numberOfTrailingZeros(n);
            n &= n - 1;
        }
        return t;
    }

    private long qmi(long a, long k, long mod) {
        long res = 1 % mod;
        while (k > 0) {
            if ((k & 1) == 1) res = res * a % mod;
            a = a * a % mod;
            k >>= 1;
        }
        return res;
    }
}
/**
 * 1. 强数组的幂次
 * 0 = []
 * 1 = [0]
 * 2 = [1]
 * 3 = [0, 1]
 * 4 = [2] = [] + 2
 * 5 = [0, 2] = [0] + 2
 * 6 = [1, 2] = [1] + 2
 * 7 = [0, 1, 2] = [0, 1] + 2
 * 8 = [3]
 * 前 k 个数幂次的和
 * 0, 1, 0, 1, 2, 0, 2, 1, 2, 0, 1, 2, 3
 *
 * 前4个幂次，来自前3个强数组的幂次
 * 前7个幂次，来自前5个强数组的幂次
 * 前10个幂次，来自前6个强数组的幂次，加上 7 的强数组的第一个幂次
 *
 * 2. 前 n 个强数组总共有多少个幂次
 * 已知 k 如何反推 n?
 *
 * 前 1 个强数组总共有多少个幂次 0
 * 前 2 个强数组总共有多少个幂次 1
 * 前 4 个强数组总共有多少个幂次 4
 * 前 8 个强数组总共有多少个幂次 12
 *
 * a(i) = 前 2^i 个强数组总共有多少个幂次, [0, 2^i - 1] 
 * a(0) = 0
 * a(i + 1) = a(i) * 2 + 2 ^ i
 * a(n) = n * 2^(n - 1)
 * 试填法
 */