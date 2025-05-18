package LC601_900;
import java.util.*;
public class LC629_KInversePairsArray {
    /**
     * For an integer array nums, an inverse pair is a pair of integers [i, j] where 0 <= i < j < nums.length and
     * nums[i] > nums[j].
     *
     * Given two integers n and k, return the number of different arrays consist of numbers from 1 to n such that there
     * are exactly k inverse pairs. Since the answer can be huge, return it modulo 10^9 + 7.
     *
     * Input: n = 3, k = 0
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= n <= 1000
     * 0 <= k <= 1000
     * @param n
     * @param k
     * @return
     */
    // S1: dp
    // time = O(n * k), space = O(n * k)
    public int kInversePairs(int n, int k) {
        long[][] dp = new long[n + 1][k + 1];
        for (int i = 0; i <= n; i++) dp[i][0] = 1;
        int M = (int)1e9+7;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                if (j >= i) dp[i][j] = (dp[i][j - 1] + dp[i - 1][j] - dp[i - 1][j - i] + M) % M;
                else dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % M;
            }
        }
        return (int)dp[n][k];
    }

    // S2: dp (TLE!!!)
    // time = O(n^2 * k), space = O(n * k)
    public int kInversePairs2(int n, int k) {
        long[][] f = new long[n + 1][k + 1]; // f[i][j]表示1~i这些数的全排列中，逆序对数量为k的方案数量
        f[1][0] = 1; // 表示1~1,即数1的全排列逆序对数量为0
        long M = (long)(1e9 + 7);

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                for (int u = 0; u < i; u++) {
                    if (j - (i - 1 - u) >= 0) {
                        f[i][j] = (f[i][j] + f[i - 1][j - (i - 1 - u)]) % M;
                    }
                }
            }
        }
        return (int) f[n][k];
    }

    // S2.2 dp (optimized)
    // time = O(n * k), space = O(n * k)
    public int kInversePairs3(int n, int k) {
        long[][] f = new long[n + 1][k + 1];
        f[1][0] = 1;
        long M = (long)(1e9 + 7);

        for (int i = 2; i <= n; i++) {
            long s = 0;
            for (int j = 0; j <= k; j++) {
                s += f[i - 1][j];
                if (j - i >= 0) s -= f[i - 1][j - i];
                f[i][j] = s % M;
            }
        }
        return (int) f[n][k];
    }
}
/**
 * [6 x x x x x]
 * dp[i][j]:
 * dp[6][j] += dp[5][j-5] + dp[5][j-4] + ... + dp[5][j] j=0,1,2,3,4,......k
 * dp[5][j] j=0,1,2,3,4,......k
 *
 * dp[i][j] = sum {dp[i-1][j-k]} for k = 0,1,2,...i-1
 * 1000 * 1000 * 1000 = 10^9 -> TLE
 * dp[i][j] = dp[i-1][j-0] + dp[i-1][j-1] + dp[i-1][j-2] + ... + dp[i-1][j-(i-2)] + dp[i-1][j-(i-1)]
 * dp[i][j-1] = dp[i-1][j-1] + dp[i-1][j-2] + dp[i-1][j-3] + ... + dp[i-1][j-(i-1)] + dp[i-1][j-i]
 * dp[i][j] = dp[i][j-1] + dp[i-1][j] - dp[i-1][j-i]
 * => sum{dp[i-1][j-m]} for m = 0,1,2,...i-1
 *
 * if j < i => dp[i-1][j-i] = 0 前面都消完了
 *
 * dp的状态设计紧紧围绕着它的问题来
 * 状态转移方程：数组，考虑1个的时候整个dp是什么样的情况，然后又加入一个元素i的时候是什么情况，再加入一个i的时候又是什么情况
 * 每加入一个i我们就要更新这个dp[i][j]，要千方百计的要跟前面i-1, i-2, 甚至i-k扯上关系
 * 你要根据dp[i][j]考虑dp[i-1][j], dp[i][j-1]，更复杂dp[i-a][j-b]
 *
 * y x x x x x
 *
 * i = 1, j = 1 => dp[1][1] = dp[1][0] + dp[0][1] - dp[0][0] = 1 + 0 - 1 = 0
 *
 * dp
 * 1. 状态表示 f[i][j]
 * 集合：所有由1~i构成的包含j个逆序对的所有排列
 * 属性：个数
 * 2. 状态计算 —— 集合划分
 * 第i个数的位置来划分
 * 0 ~ i-1 => i - 1 - k 个逆序对， j - (i - 1 - k) => f[i-1][j-(i-1-k)]  k = 0,1,2,...i-1
 * f[i][j] = f[i-1][j] + f[i-1][j-1] + ... + f[i-1][j-(i-1)]  从j开始向前循环i个数
 * f[i][j+1] = f[i-1][j+1] + f[i-1][j] + ... + f[i-1][j+1-(i-1)]
 * => f[i][j+1] - f[i][j] = f[i-1][j+1] - f[i-1][j-(i-1)]
 * => f[i][j] = s + f[i-1][j] - f[i-1][j-i]
 *
 * dp:
 * 状态表示 f(i,j)
 * 1. 集合：所有由1~i构成的包含j个逆序对的排列数
 * 2. 属性：个数
 * 状态计算：找到最后一个不同点 -> 以i的位置来划分
 * 如果i放到第k个位置，看i后面的数和i构成逆序对 i-1-k
 * f(i-1, j-(i-1-k))  k = 0~i-1
 * => f(i,j) = f(i-1,j) + f(i-1,j-1) + ... + f(i-1, j-(i-1))
 *    f(i,j+1) = f(i-1,j+1) + ... + f(i-1,j+1-(i-1))
 * 对比下，发现多了一项f(i-1,j+1)，少了一项f(i-1,j-(i-1))
 */