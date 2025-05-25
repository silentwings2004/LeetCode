package LC3301_3600;
import java.util.*;
public class LC3562_MaximumProfitfromTradingStockswithDiscounts {
    /**
     * You are given an integer n, representing the number of employees in a company. Each employee is assigned a unique
     * ID from 1 to n, and employee 1 is the CEO. You are given two 1-based integer arrays, present and future, each of
     * length n, where:
     *
     * present[i] represents the current price at which the ith employee can buy a stock today.
     * future[i] represents the expected price at which the ith employee can sell the stock tomorrow.
     * The company's hierarchy is represented by a 2D integer array hierarchy, where hierarchy[i] = [ui, vi] means that
     * employee ui is the direct boss of employee vi.
     *
     * Additionally, you have an integer budget representing the total funds available for investment.
     *
     * However, the company has a discount policy: if an employee's direct boss purchases their own stock, then the
     * employee can buy their stock at half the original price (floor(present[v] / 2)).
     *
     * Return the maximum profit that can be achieved without exceeding the given budget.
     *
     * Note:
     *
     * You may buy each stock at most once.
     * You cannot use any profit earned from future stock prices to fund additional investments and must buy only from
     * budget.
     *
     * Input: n = 2, present = [1,2], future = [4,3], hierarchy = [[1,2]], budget = 3
     * Output: 5
     *
     * Input: n = 2, present = [3,4], future = [5,8], hierarchy = [[1,2]], budget = 4
     * Output: 4
     *
     * Input: n = 3, present = [4,6,8], future = [7,9,11], hierarchy = [[1,2],[1,3]], budget = 10
     * Output: 10
     *
     * Input: n = 3, present = [5,2,3], future = [8,5,6], hierarchy = [[1,2],[2,3]], budget = 7
     * Output: 12
     *
     * Constraints:
     *
     * 1 <= n <= 160
     * present.length, future.length == n
     * 1 <= present[i], future[i] <= 50
     * hierarchy.length == n - 1
     * hierarchy[i] == [ui, vi]
     * 1 <= ui, vi <= n
     * ui != vi
     * 1 <= budget <= 160
     * There are no duplicate edges.
     * Employee 1 is the direct or indirect boss of every employee.
     * The input graph hierarchy is guaranteed to have no cycles.
     * @param n
     * @param present
     * @param future
     * @param hierarchy
     * @param budget
     * @return
     */
    // time = O(n * k^2), space = O(n * k)
    final int inf = 0x3f3f3f3f;
    int n, budget;
    int[] present, future;
    List<Integer>[] adj;
    int[][][] f;
    public int maxProfit(int n, int[] present, int[] future, int[][] hierarchy, int budget) {
        this.n = n;
        this.budget = budget;
        this.present = present;
        this.future = future;
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : hierarchy) {
            int a = e[0] - 1, b = e[1] - 1;
            adj[a].add(b);
        }

        f = new int[n][2][budget + 1];
        dfs(0);

        int res = 0;
        for (int i = 0; i <= budget; i++) res = Math.max(res, f[0][0][i]);
        return res;
    }

    private void dfs(int u) {
        for (int v : adj[u]) dfs(v);
        f[u][0] = cal(u, 0);
        f[u][1] = cal(u, 1);
    }

    private int[] cal(int u, int o) {
        int[] f0 = new int[budget + 1], f1 = new int[budget + 1];
        Arrays.fill(f0, -inf);
        Arrays.fill(f1, -inf);
        f0[0] = 0;

        int cost = o == 1 ? present[u] / 2 : present[u];
        int profit = future[u] - cost;
        if (cost <= budget) f1[cost] = profit;

        for (int v : adj[u]) {
            f0 = merge(f0, f[v][0]);
            f1 = merge(f1, f[v][1]);
        }

        int[] dp = new int[budget + 1];
        for (int i = 0; i <= budget; i++) dp[i] = Math.max(f0[i], f1[i]);
        return dp;
    }

    private int[] merge(int[] a, int[] b) {
        int[] c = new int[budget + 1];
        Arrays.fill(c, -inf);
        for (int i = 0; i <= budget; i++) {
            if (a[i] <= -inf) continue;
            for (int j = 0; j + i <= budget; j++) {
                c[i + j] = Math.max(c[i + j], a[i] + b[j]);
            }
        }
        return c;
    }

    // S2
    // time = O(n * budget^2), space = O(n * budget)
    class Solution {
        int[] present, future;
        List<Integer>[] adj;
        int n, budget;
        public int maxProfit(int n, int[] present, int[] future, int[][] hierarchy, int budget) {
            this.n = n;
            this.budget = budget;
            this.present = present;
            this.future = future;
            adj = new List[n];
            for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
            for (int[] e : hierarchy) {
                int a = e[0] - 1, b = e[1] - 1;
                adj[a].add(b);
            }

            int[][] f0 = dfs(0);
            return f0[budget][0];
        }

        private int[][] dfs(int u) {
            int[][] g = new int[budget + 1][2];
            for (int v : adj[u]) {
                int[][] fv = dfs(v);
                for (int j = budget; j >= 0; j--) {
                    for (int t = 0; t <= j; t++) {
                        for (int k = 0; k < 2; k++) {
                            g[j][k] = Math.max(g[j][k], g[j - t][k] + fv[t][k]);
                        }
                    }
                }
            }

            int[][] f = new int[budget + 1][2];
            for (int j = 0; j <= budget; j++) {
                for (int k = 0; k < 2; k++) {
                    int cost = present[u] / (k + 1);
                    if (j >= cost) {
                        f[j][k] = Math.max(g[j][0], g[j - cost][1] + future[u] - cost);
                    } else {
                        f[j][k] = g[j][0];
                    }
                }
            }
            return f;
        }
    }
}
/**
 * 对于节点 x 来说：
 * 如果不买 x（即不买 present[x]），且预算至多为 j，那么问题变成：
 * 从 x 的所有子树 y 中能得到的最大利润之和。
 * 所有子树 y 的花费总和必须 ≤j。
 * y 不能半价购买。
 * 如果买 x，且预算至多为 j，那么问题变成：
 * 从 x 的所有子树 y 中能得到的最大利润之和，加上买 x 得到的利润。
 * 所有子树 y 的花费总和必须 ≤j−cost，其中 cost 等于 present[x] or present[x] / 2
 * y 可以半价购买。
 * dfs(x) 返回一个长为 (budget+1)×2 的二维数组 f，其中 f[j][k] 表示：
 * 从子树 x 中能得到的最大利润之和。
 * 预算为 j，即花费总和 ≤j。
 * k=0 表示 x 不能半价购买，k=1 表示 x 可以半价购买。
 * 首先计算 x 的所有儿子子树 y 的最大利润总和 subF[j][k]。枚举 x 的儿子 y：
 * 枚举分配给儿子 y 的预算 jy = 0,1,2,...j
 * 用前面遍历过的儿子的收益 subF[j−jy][k] 加上儿子 y 的收益dfs(y)[jy][k]，更新 subF[j][k] 的最大值。注意这里用到了 0-1 背包的空间优化。
 * 然后考虑 x 是否购买，计算 f[j][k]：
 * 不买 x，那么分配给儿子的预算不变，仍然为 j，即 f[j][k]=subF[j][0]，这里的 0 是因为对于子树 y 来说，父节点 x 一定不买。
 * 买 x，那么分配给儿子的预算要扣掉 cost，即 f[j][k]=subF[j−cost][1]，这里的 1 是因为对于子树 y 来说，父节点 x 一定买。
 * 两种情况取最大值，即为 f[j][k]。
 * 最终答案为根节点的 f[budget][0]，这里的 0 是因为根节点没有父节点。
 */