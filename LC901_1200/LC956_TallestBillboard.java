package LC901_1200;
import java.util.*;
public class LC956_TallestBillboard {
    /**
     * You are installing a billboard and want it to have the largest height. The billboard will have two steel supports,
     * one on each side. Each steel support must be an equal height.
     *
     * You are given a collection of rods that can be welded together. For example, if you have rods of lengths 1, 2,
     * and 3, you can weld them together to make a support of length 6.
     *
     * Return the largest possible height of your billboard installation. If you cannot support the billboard, return 0.
     *
     * Input: rods = [1,2,3,6]
     * Output: 6
     *
     * Input: rods = [1,2,3,4,5,6]
     * Output: 10
     *
     * Input: rods = [1,2]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= rods.length <= 20
     * 1 <= rods[i] <= 1000
     * sum(rods[i]) <= 5000
     * @param rods
     * @return
     */
    // time = O(n * m), space = O(n * m)
    public int tallestBillboard(int[] rods) {
        int n = rods.length, m = 0;
        final int INF = (int)1e8;
        for (int x : rods) m += x;
        int[][] f = new int[n + 1][m * 2 + 1];
        for (int i = 0; i <= n; i++) Arrays.fill(f[i], -INF);
        f[0][m] = 0;
        for (int i = 1; i <= n; i++) {
            int x = rods[i - 1];
            for (int j = 0; j <= m * 2; j++) {
                f[i][j] = f[i - 1][j];
                if (j - x >= 0) f[i][j] = Math.max(f[i][j], f[i - 1][j - x] + x);
                if (j + x <= m * 2) f[i][j] = Math.max(f[i][j], f[i - 1][j + x]);
            }
        }
        return f[n][m];
    }
}
/**
 * 有限制的组合最优化问题
 * 限制：左右两边差值 = 0
 * f(i,j):从前i个棍子中选，左-右=j,左边的最大值
 * f(0,0) = 0
 * => f(n,0)
 * 1. 不选：f(i-1,j)
 * 2. 选左边：f(i-1,j-x) + x
 * 3. 选右边：f(i-1,j+x)
 */