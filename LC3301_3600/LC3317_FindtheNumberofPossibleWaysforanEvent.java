package LC3301_3600;

public class LC3317_FindtheNumberofPossibleWaysforanEvent {
    /**
     * You are given three integers n, x, and y.
     *
     * An event is being held for n performers. When a performer arrives, they are assigned to one of the x stages.
     * All performers assigned to the same stage will perform together as a band, though some stages might remain empty.
     *
     * After all performances are completed, the jury will award each band a score in the range [1, y].
     *
     * Return the total number of possible ways the event can take place.
     *
     * Since the answer may be very large, return it modulo 109 + 7.
     *
     * Note that two events are considered to have been held differently if either of the following conditions is
     * satisfied:
     *
     * Any performer is assigned a different stage.
     * Any band is awarded a different score.
     *
     * Input: n = 1, x = 2, y = 3
     * Output: 6
     *
     * Input: n = 5, x = 2, y = 1
     * Output: 32
     *
     * Input: n = 3, x = 3, y = 4
     * Output: 684
     *
     * Constraints:
     *
     * 1 <= n, x, y <= 1000
     * @param n
     * @param x
     * @param y
     * @return
     */
    // S1: 第二类斯特林数
    // time = O(x * (x + logn)), space = O(x)
    public int numberOfWays(int n, int x, int y) {
        long mod = (long)(1e9 + 7);
        long[][] c = new long[n + 1][n + 1];
        c[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                c[i][j] = (c[i - 1][j - 1] + j * c[i - 1][j]) % mod;
            }
        }

        long res = 0, perm = 1, py = 1;
        for (int i = 1; i <= Math.min(n, x); i++) {
            perm = perm * (x + 1 - i) % mod;
            py = py * y % mod;
            res = (res + perm * c[n][i] % mod * py % mod) % mod;
        }
        return (int)res;
    }

    // S2: dp
    // time = O(x * (x + logn)), space = O(x)
    public int numberOfWays2(int n, int x, int y) {
        long mod = (long)(1e9 + 7);
        long[][] f = new long[n + 1][x + 1]; // 前 i 个人表演 j 个节目的方案数
        f[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= x; j++) {
                f[i][j] = (f[i - 1][j] * j + f[i - 1][j - 1] * (x - (j - 1))) % mod;
            }
        }
        long res = 0, p = 1;
        for (int i = 1; i <= x; i++) {
            p = p * y % mod;
            res = (res + f[n][i] * p) % mod;
        }
        return (int)res;
    }
}
/**
 * 第二类斯特林数
 * 枚举划分成多少个集合
 * A(x,i) * S(n,i) * y^i
 * s(n,k)
 * 最后一个人
 * 独立成团: s(n-1,k-1)
 * 加入任何之前的一个团: s(n-1,k) * k
 * 互斥 => 相加
 * s(n,k) = s(n-1,k-1) + k * s(n-1,k)
 */