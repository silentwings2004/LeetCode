package LC601_900;

public class LC887_SuperEggDrop {
    /**
     * You are given k identical eggs and you have access to a building with n floors labeled from 1 to n.
     *
     * You know that there exists a floor f where 0 <= f <= n such that any egg dropped at a floor higher than f will
     * break, and any egg dropped at or below floor f will not break.
     *
     * Each move, you may take an unbroken egg and drop it from any floor x (where 1 <= x <= n). If the egg breaks, you
     * can no longer use it. However, if the egg does not break, you may reuse it in future moves.
     *
     * Return the minimum number of moves that you need to determine with certainty what the value of f is.
     *
     * Input: k = 1, n = 2
     * Output: 2
     *
     * Input: k = 2, n = 6
     * Output: 3
     *
     * Input: k = 3, n = 14
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= k <= 100
     * 1 <= n <= 10^4
     * @param k
     * @param n
     * @return
     */
    // time = O(n * k), space = O(n * k)
    public int superEggDrop(int k, int n) {
        int[][] f = new int[10010][110];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                f[i][j] = f[i - 1][j - 1] + 1 + f[i - 1][j];
            }
            if (f[i][k] >= n) return i;
        }
        return -1;
    }
}
/**
 * k个鸡蛋， 0~n共n+1种情况
 * f(i,j):用j枚鸡蛋，扔i次，能得到答案的最大高度
 * f(i,k) >= n 最小的i
 * (1) 没碎：x ~ f(i-1,j) + x
 * (2) 碎了: 0 ~ f(i-1,j-1)
 * => x = f(i-1,j-1) + 1
 * => f(i,j) = f(i-1,j-1) + 1 + f(i-1,j)
 *
 * f(i,j): 用 j 枚忌惮扔 i 次，能得到答案的最大高度
 * i 最多就是从 1 枚举到 n
 * f(i,k) >= n 求一个最小的 i
 * f(i,k+1) >= f(i,k)
 * 假设在 x 层扔一枚鸡蛋
 * (1) 没碎: f(i-1,j)
 * (2) 碎了: 答案在下面 => f(i-1,j-1)
 * x = f(i-1,j-1) + 1
 */