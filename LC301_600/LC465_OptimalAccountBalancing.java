package LC301_600;
import java.util.*;
public class LC465_OptimalAccountBalancing {
    /**
     * You are given an array of transactions transactions where transactions[i] = [fromi, toi, amounti] indicates that
     * the person with ID = fromi gave amounti $ to the person with ID = toi.
     *
     * Return the minimum number of transactions required to settle the debt.
     *
     * Input: transactions = [[0,1,10],[2,0,5]]
     * Output: 2
     *
     * Input: transactions = [[0,1,10],[1,0,1],[1,2,5],[2,0,5]]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= transactions.length <= 8
     * transactions[i].length == 3
     * 0 <= fromi, toi < 12
     * fromi != toi
     * 1 <= amounti <= 100
     * @param transactions
     * @return
     */
    // S1: state compression
    // time = O(3^n), space = O(2^n)
    public int minTransfers(int[][] transactions) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int[] t : transactions) {
            int a = t[0], b = t[1], c = t[2];
            map.put(a, map.getOrDefault(a, 0) + c);
            map.put(b, map.getOrDefault(b, 0) - c);
        }

        List<Integer> nums = new ArrayList<>();
        for (int v : map.values()) nums.add(v);

        int n = nums.size();
        int[] sum = new int[1 << n];
        for (int state = 0; state < (1 << n); state++) {
            for (int i = 0; i < n; i++) {
                if (((state >> i) & 1) == 1) sum[state] += nums.get(i);
            }
        }

        int[] dp = new int[1 << n];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        for (int state = 0; state < (1 << n); state++) {
            if (sum[state] != 0) continue;
            dp[state] = Integer.bitCount(state) - 1;
            for (int subset = state - 1; subset > 0; subset = (subset - 1) & state) {
                if (sum[subset] == 0) {
                    dp[state] = Math.min(dp[state], dp[subset] + dp[state - subset]);
                }
            }
        }
        return dp[(1 << n) - 1];
    }

    // S2: dfs
    // time = O(n * 2^n), space = O(n)
    public int minTransfers2(int[][] transactions) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int[] t : transactions) {
            map.put(t[0], map.getOrDefault(t[0], 0) + t[2]);
            map.put(t[1], map.getOrDefault(t[1], 0) - t[2]);
        }
        return dfs(0, new ArrayList<>(map.values()));
    }

    private int dfs(int u, List<Integer> debt) {
        while (u < debt.size() && debt.get(u) == 0) u++;
        if (u == debt.size()) return 0;

        int res = Integer.MAX_VALUE;
        for (int i = u + 1; i < debt.size(); i++) { // O(n)
            if (debt.get(u) * debt.get(i) < 0) {
                debt.set(i, debt.get(i) + debt.get(u));
                res = Math.min(res, 1 + dfs(u + 1, debt));
                debt.set(i, debt.get(i) - debt.get(u)); // setback
            }
        }
        return res;
    }
}
