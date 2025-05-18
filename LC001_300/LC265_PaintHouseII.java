package LC001_300;
import java.util.*;
public class LC265_PaintHouseII {
    /**
     * There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house
     * with a certain color is different. You have to paint all the houses such that no two adjacent houses have the
     * same color.
     *
     * The cost of painting each house with a certain color is represented by an n x k cost matrix costs.
     *
     * For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house
     * 1 with color 2, and so on...
     * Return the minimum cost to paint all houses.
     *
     * Input: costs = [[1,5,3],[2,9,4]]
     * Output: 5
     *
     * Constraints:
     *
     * costs.length == n
     * costs[i].length == k
     * 1 <= n <= 100
     * 1 <= k <= 20
     * 1 <= costs[i][j] <= 20
     * @param costs
     * @return
     */
    // S1: DP (最优解！)
    // time = O(n * k), space = O(1)
    final int INF = (int) 1e8;
    public int minCostII(int[][] costs) {
        int n = costs.length, m = costs[0].length;
        int[][] f = new int[n + 1][m];
        for (int i = 0; i <= n; i++) Arrays.fill(f[i], INF);
        for (int i = 0; i < m; i++) f[0][i] = 0;

        int a = 0, b = 0, c1 = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                if (j != c1) f[i][j] = a + costs[i - 1][j];
                else f[i][j] = b + costs[i - 1][j];
            }

            a = INF;
            b = INF;
            for (int j = 0; j < m; j++) {
                if (f[i][j] < a) {
                    b = a;
                    a = f[i][j];
                    c1 = j;
                } else if (f[i][j] < b) {
                    b = f[i][j];
                }
            }
        }

        int res = INF;
        for (int i = 0; i < m; i++) res = Math.min(res, f[n][i]);
        return res;
    }
}
/**
 * i-1, 0
 *      1
 *      2
 *      ..
 * i, j
 * dp[i][j]: the minimum cost of painting houses [0:j] ending with cost[i][j]
 * ref: LC1289 与本题几乎完全一样，代码很相似
 * 状态转移层次清晰
 * 把房子拆开来一个个看，我在处理第i个房子的时候，只关心第i-1个房子的状态
 * 至于i-2,i-3等房子我都不用管
 * 状态的个数是有限的
 * 很容易想到是动态规划
 * 如果再复杂一点，可能还要再加一层循环
 * */
