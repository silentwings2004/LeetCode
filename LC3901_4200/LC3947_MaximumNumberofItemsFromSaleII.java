package LC3901_4200;
import java.util.*;
public class LC3947_MaximumNumberofItemsFromSaleII {
    /**
     * You are given a 2D integer array items, where items[i] = [factori, pricei] represents the ith item. You are also
     * given an integer budget.
     *
     * There are unlimited copies of each item available for purchase. You may buy any number of copies of any items
     * such that the total cost of the purchased copies is at most budget.
     *
     * After buying items, you may receive free copies according to the following rules:
     *
     * Each purchased copy of item i can give you at most one free copy of another item j.
     * The free item must satisfy i != j and factori divides factorj.
     * For each ordered pair (i, j), you can receive a free copy of item j from purchases of item i at most once,
     * regardless of how many copies of item i you buy.
     * The same item j can be received multiple times for free if it is received from purchases of different item types.
     *
     * Return the maximum total number of item copies you can obtain, including both purchased copies and free copies,
     * while spending at most budget on purchased items.
     *
     * Input: items = [[1,6],[2,4],[3,5]], budget = 19
     * Output: 5
     *
     * Input: items = [[2,8],[1,10],[6,6],[4,12],[5,20],[5,17]], budget = 35
     * Output: 7
     *
     * Constraints:
     *
     * 1 <= items.length <= 10^5
     * items[i] = [factori, pricei]
     * 1 <= factori <= items.length
     * 1 <= pricei <= 10^9
     * 1 <= budget <= 10^9
     * @param items
     * @param budget
     * @return
     */
    // S1
    // time = O(nlogn), space = O(n)
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] cnt = new int[n + 1];
        int minv = items[0][1];
        for (int[] x : items) {
            cnt[x[0]]++;
            minv = Math.min(minv, x[1]);
        }

        int[] w = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j += i) {
                w[i] += cnt[j];
            }
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int[] x : items) {
            int factor = x[0], price = x[1];
            int d = w[factor] - 1;
            if (d > 0 && price < 2 * minv) map.put(price, map.getOrDefault(price, 0) + d);
        }
        List<Integer> q = new ArrayList<>(map.keySet());
        Collections.sort(q);
        int r = budget, p = 0;
        for (int x : q) {
            int y = map.get(x);
            int t = Math.min(y, r / x);
            p += t;
            r -= x * t;
        }
        return 2 * p + r / minv;
    }

    // S2
    // time = O(nlogn), space = O(n)
    public int maximumSaleItems2(int[][] items, int budget) {
        int n = items.length;
        int[] cnt_factor = new int[n + 1];
        int min_price = items[0][1];
        for (int i = 0; i < n; i++) {
            int factor = items[i][0], price = items[i][1];
            cnt_factor[factor]++;
            min_price = Math.min(min_price, price);
        }
        int[] cnt_multi = new int[n + 1];
        List<int[]> q = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int factor = items[i][0], price = items[i][1];
            if (price >= min_price * 2) continue;
            if (cnt_multi[factor] == 0) {
                for (int j = factor; j <= n; j += factor) {
                    cnt_multi[factor] += cnt_factor[j];
                }
            }
            int cnt = cnt_multi[factor] - 1;
            if (cnt > 0) q.add(new int[]{price, cnt});
        }
        Collections.sort(q, (o1, o2) -> o1[0] - o2[0]);
        int res = 0;
        for (int[] x : q) {
            int price = x[0], cnt = x[1];
            if (budget < price) break;
            int c = Math.min(cnt, budget / price);
            budget -= c * price;
            res += c * 2;
        }
        return res + budget / min_price;
    }
}
/**
 * 每次购买，要么没有免费物品，最多获得一个免费物品一共 2 个物品 => 贪心策略
 * 价格 >= 2 * minP 的物品完全不用考虑
 * 先考虑买一送一，价格从低到高 => 贪心 最大化物品总数，当然从价格最低的开始购买
 */