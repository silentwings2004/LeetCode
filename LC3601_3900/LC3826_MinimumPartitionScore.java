package LC3601_3900;
import java.math.BigInteger;
import java.util.*;
public class LC3826_MinimumPartitionScore {
    /**
     * You are given an integer array nums and an integer k.
     *
     * Your task is to partition nums into exactly k subarrays and return an integer denoting the minimum possible score
     * among all valid partitions.
     *
     * The score of a partition is the sum of the values of all its subarrays.
     *
     * The value of a subarray is defined as sumArr * (sumArr + 1) / 2, where sumArr is the sum of its elements.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [5,1,2,1], k = 2
     * Output: 25
     *
     * Input: nums = [1,2,3,4], k = 1
     * Output: 55
     *
     * Input: nums = [1,1,1], k = 3
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * 1 <= nums[i] <= 10^4
     * 1 <= k <= nums.length
     * @param nums
     * @param k
     * @return
     */
    // S1: divide and conquer
    // time = O(k * nlogn), space = O(k * nlogn)
    final long inf = (long) 1E18;
    public long minPartitionScore(int[] nums, int k) {
        int n = nums.length;
        long[] s = new long[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + nums[i - 1];
        long[][] f = new long[n + 1][k + 1];
        for (int i = 0; i <= n; i++) Arrays.fill(f[i], inf);
        for (int i = 1; i <= n; i++) f[i][1] = s[i] * (s[i] + 1) / 2;
        for (int j = 2; j <= k; j++) helper(f, j, 1, n, j - 1, n, s);
        return f[n][k];
    }

    private void helper(long[][] f, int j, int l, int r, int opl, int opr, long[] s) {
        if (l > r) return;
        int mid = l + r >> 1;
        int bo = -1;
        long bv = inf;
        int st = Math.max(opl, j - 1);
        int ed = Math.min(opr, mid - 1);
        for (int i = st; i <= ed; i++) {
            long sum = s[mid] - s[i];
            long v = sum * (sum + 1) / 2;
            long cand = f[i][j - 1] + v;
            if (cand < bv) {
                bv = cand;
                bo = i;
            }
        }
        f[mid][j] = bv;
        helper(f, j, l, mid - 1, opl, bo, s);
        helper(f, j, mid + 1, r, bo, opr, s);
    }

    // S2: 斜率优化
    // time = O((n - k) * k), space = O(n)
    public long minPartitionScore2(int[] nums, int k) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) sum[i + 1] = sum[i] + nums[i];
        long[] f = new long[n + 1];
        Arrays.fill(f, inf);
        f[0] = 0;

        Vec[] dq = new Vec[n + 1];
        // min{(-2 * S[i]) * S[j] + 1 * (f[k-1][j] + S[j]^2 - S[j])}
        for (int u = 1; u <= k; u++) {
            int hh = 0, tt = -1;
            long s = sum[u - 1];
            dq[++tt] = new Vec(s, f[u - 1] + s * s - s);
            for (int i = u; i <= n - (k - u); i++) {
                s = sum[i];
                Vec p = new Vec(-2 * s, 1);
                while (hh < tt && dot(p, dq[hh]) >= dot(p, dq[hh + 1])) hh++;
                Vec v = new Vec(s, f[i] + s * s - s);
                f[i] = dot(p, dq[hh]) + s * s + s;
                while (hh < tt && detCmp(sub(dq[tt], dq[tt - 1]), sub(v, dq[tt])) <= 0) tt--;
                dq[++tt] = v;
            }
        }
        return f[n] / 2;
    }

    private Vec sub(Vec a, Vec b) {
        return new Vec(a.x - b.x, a.y - b.y);
    }

    private long dot(Vec a, Vec b) {
        return a.x * b.x + a.y * b.y;
    }

    private long det(Vec a, Vec b) {
        return a.x * b.y - a.y * b.x;
    }

    private int detCmp(Vec a, Vec b) {
        return BigInteger.valueOf(a.x).multiply(BigInteger.valueOf(b.y)).
                compareTo(BigInteger.valueOf(a.y).multiply(BigInteger.valueOf(b.x)));
    }

    class Vec {
        long x, y;
        Vec(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
}
/**
 * 斜率优化DP
 * 凸包
 * f[i] = min{f[j] + S[i] * S[j]} => min{S[i] * S[j] + 1 * f[j]}
 * 转化成点积 (S[i], 1), (S[j], f[j])
 * 每个点都表示一个向量
 * 点积的几何意义是什么？=> 最小化投影的长度 A * B = |A| * |B| * cosø
 * 我们求的 j 这个向量在 i 上的投影，但由于 (S[i], 1) 模长是固定的 sqrt(S[i]^2 + 1)
 * 问题就变成让 j 在 i 上投影变得尽量小即可
 * 这些向量投影长度从左往右看，先递减再递增 => 单峰函数，可以通过二分把峰谷给找出来
 * 在凸包里面和上侧的点投影长度一定比在凸包边缘上的点更长
 * 只需要关注下凸包上的点
 * 如果 S[i] 具有单调性 => 前缀和递增 => 双指针 O(n)
 * summary:
 * product -> 点积 -> 投影 -> 下凸包 -> 向量的叉积
 * 有了下凸包后，就可以做双指针 / 单调队列 去维护下凸包
 *
 * 状态转移，划分型 dp
 * f[k][i] = min{f[k-1][j] + (S[i] - S[j]) * (S[i] - S[j] + 1)}
 * => min{f[k-1][j] + S[i]^2 - 2 * S[i] * S[j] + S[j]^2 + S[i] - S[j]}
 * = S[i]^2 + S[i] + min{f[k-1][j] - 2 * S[i] * S[j] + S[j]^2 - S[j]}
 * min{(-2 * S[i]) * S[j] + 1 * (f[k-1][j] + S[j]^2 - S[j])}
 *          a         b     c          d
 * (a,c) * (b,d)
 * 只需要关心新添加到凸包里的向量
 */