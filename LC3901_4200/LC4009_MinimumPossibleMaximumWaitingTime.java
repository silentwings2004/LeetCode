package LC3901_4200;
import java.util.*;
public class LC4009_MinimumPossibleMaximumWaitingTime {
    /**
     * You are given an integer array demand, where demand[i] is the amount of fuel required by the ith car.
     *
     * You are also given an integer array fuel of length 2. There are exactly two fuel dispensers, numbered 0 and 1,
     * where fuel[j] is the initial amount of fuel available in dispenser j.
     *
     * Cars are allowed to start refueling in increasing index order. Car 0 becomes allowed at time 0, and for each
     * i > 0, car i becomes allowed exactly when car i - 1 starts refueling.
     *
     * The refueling process follows these rules:
     *
     * Each dispenser can serve at most one car at a time.
     * A car may start refueling at any time at or after it becomes allowed.
     * A car can start on a dispenser only if the dispenser is free and has at least demand[i] fuel remaining.
     * If multiple free dispensers can serve the current car, you may choose any of them.
     * Refueling a car takes demand[i] seconds and reduces the remaining fuel in that dispenser by demand[i].
     * Once started, refueling cannot be interrupted.
     * When both dispensers are free, if neither has at least demand[i] fuel remaining, the process terminates and no
     * further cars can be served.
     * The waiting time of a car is the time between when it becomes allowed to start refueling and when it actually
     * starts.
     *
     * Return the minimum possible value of the maximum waiting time among all served cars over all assignments that
     * maximize the number of served cars. If no car can be served, return -1.
     *
     * Input: demand = [6,8,4,6,5], fuel = [16,13]
     * Output: 6
     *
     * Input: demand = [10,15], fuel = [12,17]
     * Output: 0
     *
     * Input: demand = [10,5], fuel = [8,8]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= demand.length <= 50
     * 1 <= demand[i] <= 20
     * fuel.length == 2
     * 1 <= fuel[i] <= 50
     * @param demand
     * @param fuel
     * @return
     */
    // time = O(n * f1 * f2 * d^2), space = O(n)
    int[] demand;
    HashMap<String, int[]> memo;
    public int minMaxWaitingTime(int[] demand, int[] fuel) {
        this.demand = demand;
        memo = new HashMap<>();
        int[] f = dfs(0, fuel[0], fuel[1], 0, 0);
        return f[0] > 0 ? f[1] : -1;
    }

    private int[] dfs(int u, int f0, int f1, int w0, int w1) {
        int res0 = 0, res1 = 0, c0 = 0, c1 = 0;
        int n = demand.length;
        if (u == n) return new int[]{0, 0};

        String h = u + "#" + f0 + "#" + f1 + "#" + w0 + "#" + w1;
        if (memo.containsKey(h)) return memo.get(h);

        int d = demand[u];
        if (f0 >= d) {
            int[] f = dfs(u + 1, f0 - d, f1, d, Math.max(0, w1 - w0));
            c0 = f[0] + 1;
            res0 = Math.max(f[1], w0);
        }
        if (f1 >= d) {
            int[] f = dfs(u + 1, f0, f1 - d, Math.max(0, w0 - w1), d);
            c1 = f[0] + 1;
            res1 = Math.max(f[1], w1);
        }

        int[] res;
        if (c0 < c1) res = new int[]{c1, res1};
        else if (c0 > c1) res = new int[]{c0, res0};
        else res = new int[]{c0, Math.min(res0, res1)};

        memo.put(h, res);
        return res;
    }
}