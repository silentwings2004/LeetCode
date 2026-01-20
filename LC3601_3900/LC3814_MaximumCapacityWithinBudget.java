package LC3601_3900;
import java.util.*;
public class LC3814_MaximumCapacityWithinBudget {
    /**
     * You are given two integer arrays costs and capacity, both of length n, where costs[i] represents the purchase
     * cost of the ith machine and capacity[i] represents its performance capacity.
     *
     * You are also given an integer budget.
     *
     * You may select at most two distinct machines such that the total cost of the selected machines is strictly less
     * than budget.
     *
     * Return the maximum achievable total capacity of the selected machines.
     *
     * Input: costs = [4,8,5,3], capacity = [1,5,2,7], budget = 8
     * Output: 8
     *
     * Input: costs = [3,5,7,4], capacity = [2,4,3,6], budget = 7
     * Output: 6
     *
     * Input: costs = [2,2,2], capacity = [3,5,4], budget = 5
     * Output: 9
     *
     * Constraints:
     *
     * 1 <= n == costs.length == capacity.length <= 10^5
     * 1 <= costs[i], capacity[i] <= 10^5
     * 1 <= budget <= 2 * 10^5
     * @param costs
     * @param capacity
     * @param budget
     * @return
     */
    // S1
    // time = O(nlogn), space = O(n)
    public int maxCapacity(int[] costs, int[] capacity, int budget) {
        int n = costs.length;
        Integer[] p = new Integer[n];
        for (int i = 0; i < n; i++) p[i] = i;
        Arrays.sort(p, (o1, o2) -> costs[o1] - costs[o2]);
        int res = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0, mx = 0; i < n; i++) {
            int idx = p[i];
            if (costs[idx] >= budget) break;
            int d = budget - 1 - costs[idx];
            Integer fk = map.floorKey(d);
            res = Math.max(res, capacity[idx] + (fk == null ? 0 : map.get(fk)));
            mx = Math.max(mx, capacity[idx]);
            map.put(costs[idx], mx);
        }
        return res;
    }

    // S2
    // time = O(n + k), space = O(k) k: budget
    public int maxCapacity2(int[] costs, int[] capacity, int budget) {
        int n = costs.length;
        int[] mx1 = new int[budget];
        int[] mx2 = new int[budget];
        for (int i = 0; i < n; i++) {
            int c = costs[i], cap = capacity[i];
            if (c >= budget) continue;
            if (cap > mx1[c]) {
                mx2[c] = mx1[c];
                mx1[c] = cap;
            } else if (cap > mx2[c]) mx2[c] = cap;
        }

        int res = 0;
        int[] preMax = new int[budget];
        for (int c = 1; c < budget; c++) {
            if (mx1[c] == 0) preMax[c] = preMax[c - 1]; // no machine at this price
            else {
                // case 1: only pick one machine
                res = Math.max(res, mx1[c]);
                // case 2: pick 2 machines of the same price
                if (mx2[c] > 0 && c * 2 < budget) res = Math.max(res, mx1[c] + mx2[c]);
                // case 3: pick one at c, the other one + c < budget and < c but has the largest capacity
                int limit = Math.min(c - 1, budget - 1 - c);
                if (limit >= 1 && preMax[limit] > 0) res = Math.max(res, mx1[c] + preMax[limit]);
                preMax[c] = Math.max(preMax[c - 1], mx1[c]);
            }
        }
        return res;
    }
}
/**
 * 处理连续的元素，处理起来更方便
 * -> 把数据按照价格从小到大排序
 * 枚举其中一台机器，另外一台机器在前缀中，
 * 问题变成这个前缀的容量的最大值
 * -> 前缀最大值
 */