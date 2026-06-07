package LC3901_4200;

public class LC3946_MaximumNumberofItemsFromSaleI {
    /**
     * You are given a 2D integer array items, where items[i] = [factori, pricei] represents the ith item. You are also
     * given an integer budget.
     *
     * There are unlimited copies of each item available for purchase.You may buy any number of copies of any items
     * such that the total cost of the purchased copies is at most budget.
     *
     * After buying items, you may receive free copies according to the following rules:
     *
     * For each item i that you bought at least one copy of, you receive one free copy of every item j such that j != i
     * and factori divides factorj.
     * Buying multiple copies of the same item i does not give additional free copies through item i.
     * The same item j can be received multiple times for free if it is received from purchases of different item types.
     *
     * Return the maximum total number of item copies you can obtain, including both purchased copies and free copies,
     * while spending at most budget on purchased items.
     *
     * Input: items = [[6,2],[2,6],[3,4]], budget = 9
     * Output: 4
     *
     * Input: items = [[2,4],[3,2],[4,1],[6,4],[12,4]], budget = 8
     * Output: 10
     *
     * Constraints:
     *
     * 1 <= items.length <= 1000
     * items[i] = [factori, pricei]
     * 1 <= factori, pricei <= 1500
     * 1 <= budget <= 1500
     * @param items
     * @param budget
     * @return
     */
    // S1
    // time = O(n * b + nlogn), space = O(b + n)
    final int N = 1500;
    public int maximumSaleItems(int[][] items, int budget) {
        int[] cnt = new int[N + 1];
        for (int[] x : items) cnt[x[0]]++;
        int[] w = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = i; j <= N; j += i) {
                w[i] += cnt[j];
            }
        }

        int[] f = new int[budget + 1];
        for (int[] x : items) {
            int factor = x[0], price = x[1];
            int[] g = f.clone();
            int free = w[factor] - 1;
            for (int i = 0; i < price; i++) {
                if (i > budget) break;
                int best = f[i];
                for (int j = 1, t = i + price; t <= budget; j++, t += price) {
                    g[t] = Math.max(g[t], best + j + free);
                    best = Math.max(best, f[t] - j);
                }
            }
            f = g;
        }
        int res = 0;
        for (int x : f) res = Math.max(res, x);
        return res;
    }

    // S2
    // time = O(n * (n + b)), space = O(b)
    public int maximumSaleItems2(int[][] items, int budget) {
        int[] f = new int[budget + 1];
        int minv = items[0][1];
        for (int[] x : items) {
            int factor = x[0], price = x[1];
            minv = Math.min(minv, price);
            int cnt = 0;
            for (int[] q : items) {
                if (q[0] % factor == 0) cnt++;
            }

            for (int j = budget; j >= price; j--) {
                f[j] = Math.max(f[j], f[j - price] + cnt);
            }
        }

        int res = 0;
        for (int i = 0; i <= budget; i++) {
            res = Math.max(res, f[i] + (budget - i) / minv);
        }
        return res;
    }
}
/**
 * 2 1
 * 6 2
 * 4 2
 * 变成 01 背包问题
 * 物品价值 -> 个数
 * 重复购买 -> 无法获取免费物品
 * 最大化物品总数 -> 重复购买便宜的
 * 分成 2 步：
 * 1. 首次购买
 * 2. 重复购买 -> 只需要买最便宜的
 * 直接枚举 100  x (首次购买：01 背包)   100 - x (重复购买: minP)
 * 优化：调和级数枚举
 */