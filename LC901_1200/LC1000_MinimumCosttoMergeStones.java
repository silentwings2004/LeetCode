package LC901_1200;
import java.util.*;
public class LC1000_MinimumCosttoMergeStones {
    /**
     * There are n piles of stones arranged in a row. The ith pile has stones[i] stones.
     *
     * A move consists of merging exactly k consecutive piles into one pile, and the cost of this move is equal to the
     * total number of stones in these k piles.
     *
     * Return the minimum cost to merge all piles of stones into one pile. If it is impossible, return -1.
     *
     * Input: stones = [3,2,4,1], k = 2
     * Output: 20
     *
     * Input: stones = [3,2,4,1], k = 3
     * Output: -1
     *
     * Input: stones = [3,5,1,2,6], k = 3
     * Output: 25
     *
     * Constraints:
     *
     * n == stones.length
     * 1 <= n <= 30
     * 1 <= stones[i] <= 100
     * 2 <= k <= 30
     * @param stones
     * @param k
     * @return
     */
    // S1: 区间DP
    // time = O(n^3 * k), space = O(n^2 * k)
    public int mergeStones(int[] stones, int k) {
        int n = stones.length, INF = 0x3f3f3f3f;
        if ((n - 1) % (k - 1) != 0) return -1;
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + stones[i - 1];
        int[][][] f = new int[n][n][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(f[i][j], INF);
            }
        }
        for (int i = 0; i < n; i++) f[i][i][1] = 0;

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                for (int l = 2; l <= k; l++) {
                    for (int u = j - 1; u >= i; u -= k - 1) {
                        f[i][j][l] = Math.min(f[i][j][l], f[i][u][l - 1] + f[u + 1][j][1]);
                    }
                }
                f[i][j][1] = f[i][j][k] + s[j + 1] - s[i];
            }
        }
        return f[0][n - 1][1];
    }

    // S2: 二维优化版DP
    // time = O(n^3), space = O(n^2)
    public int mergeStones2(int[] stones, int k) {
        int n = stones.length, INF = 0x3f3f3f3f;
        if ((n - 1) % (k - 1) != 0) return -1;
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + stones[i - 1];
        int[][] f = new int[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(f[i], INF);
        for (int i = 0; i < n; i++) f[i][i] = 0;

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                // 分成左边k - 1堆，右边1堆，所以要从右向左枚举 k-1 | 1 堆的分界线
                // 如果分成左边1堆，右边k-1堆的话，就要写成 for (int u = i; u < j; u += k - 1) 从左向右去枚举分界线！
                for (int u = j - 1; u >= i; u -= k - 1) {
                    f[i][j] = Math.min(f[i][j], f[i][u] + f[u + 1][j]); // 这里左右两边并没有合并，两边各自合并的代价在f中算过了！
                }
                if ((len - 1) % (k - 1) == 0) f[i][j] += s[j + 1] - s[i]; // 只有满足整除条件的，才能最终使左右两边合并为一堆!
            }
        }
        return f[0][n - 1];
    }
}
/**
 * 合并相邻的k堆 => 合并石子的扩展
 * 区间dp
 * 1. 状态表示：f(i,j,k)
 * 集合：所有将[i:j]这段合并成k堆的方案集合
 * 属性：最小值
 * 2. 状态计算：
 * 找出最后一堆，假设是[u+1:j] => [i+1,j], [i+2,j], ...[j,j]
 * 每次合并m个数 => 每次会减少m-1个数
 * 同时要让n个数变成一个数，一共减少n-1个数
 * => m-1一定要能整除n-1才可以，否则一定无解
 * [u+1:j]变成一堆，一共有j-u个数，每次减少m-1个数 => 每一次差值必须是m-1的倍数才可以
 * (j-i+1-k) % (m - 1) == 0 => k = (j - i + 1) % m => k是唯一确定的，可以去掉第三维
 */