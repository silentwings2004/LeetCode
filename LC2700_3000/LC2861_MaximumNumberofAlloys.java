package LC2700_3000;
import java.util.*;
public class LC2861_MaximumNumberofAlloys {
    /**
     * You are the owner of a company that creates alloys using various types of metals. There are n different types
     * of metals available, and you have access to k machines that can be used to create alloys. Each machine requires
     * a specific amount of each metal type to create an alloy.
     *
     * For the ith machine to create an alloy, it needs composition[i][j] units of metal of type j. Initially, you have
     * stock[i] units of metal type i, and purchasing one unit of metal type i costs cost[i] coins.
     *
     * Given integers n, k, budget, a 1-indexed 2D array composition, and 1-indexed arrays stock and cost, your goal is
     * to maximize the number of alloys the company can create while staying within the budget of budget coins.
     *
     * All alloys must be created with the same machine.
     *
     * Return the maximum number of alloys that the company can create.
     *
     * Input: n = 3, k = 2, budget = 15, composition = [[1,1,1],[1,1,10]], stock = [0,0,0], cost = [1,2,3]
     * Output: 2
     *
     * Input: n = 3, k = 2, budget = 15, composition = [[1,1,1],[1,1,10]], stock = [0,0,100], cost = [1,2,3]
     * Output: 5
     *
     * Input: n = 2, k = 3, budget = 10, composition = [[2,1],[1,2],[1,1]], stock = [1,1], cost = [5,5]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= n, k <= 100
     * 0 <= budget <= 10^8
     * composition.length == k
     * composition[i].length == n
     * 1 <= composition[i][j] <= 100
     * stock.length == cost.length == n
     * 0 <= stock[i] <= 10^8
     * 1 <= cost[i] <= 100
     * @param n
     * @param k
     * @param budget
     * @param composition
     * @param stock
     * @param cost
     * @return
     */
    // time = O(k * n * logN), space = O(n)
    int n, k, budget;
    List<List<Integer>> comp;
    List<Integer> stock, cost;
    public int maxNumberOfAlloys(int n, int k, int budget, List<List<Integer>> composition, List<Integer> stock, List<Integer> cost) {
        this.n = n;
        this.k = k;
        this.budget = budget;
        this.comp = composition;
        this.stock = stock;
        this.cost = cost;

        int l = 0, r = (int)1e9;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (check(mid)) l = mid;
            else r = mid - 1;
        }
        return r;
    }

    private boolean check(int mid) {
        for (int i = 0; i < k; i++) {
            long tot = 0;
            for (int j = 0; j < n; j++) {
                long amount = Math.max(0, (long)comp.get(i).get(j) * mid- stock.get(j));
                tot += amount * cost.get(j);
            }
            if (tot <= budget) return true;
        }
        return false;
    }
}