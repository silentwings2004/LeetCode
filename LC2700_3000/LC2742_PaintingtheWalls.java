package LC2700_3000;
import java.util.*;
public class LC2742_PaintingtheWalls {
    /**
     * You are given two 0-indexed integer arrays, cost and time, of size n representing the costs and the time taken to
     * paint n different walls respectively. There are two painters available:
     *
     * A paid painter that paints the ith wall in time[i] units of time and takes cost[i] units of money.
     * A free painter that paints any wall in 1 unit of time at a cost of 0. But the free painter can only be used if
     * the paid painter is already occupied.
     * Return the minimum amount of money required to paint the n walls.
     *
     * Input: cost = [1,2,3,2], time = [1,2,3,2]
     * Output: 3
     *
     * Input: cost = [2,3,4,2], time = [1,1,1,1]
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= cost.length <= 500
     * cost.length == time.length
     * 1 <= cost[i] <= 10^6
     * 1 <= time[i] <= 500
     * @param cost
     * @param time
     * @return
     */
    // S1: 0-1背包
    // time = O(n^2), space = O(n)
    final int INF = 0x3f3f3f3f;
    public int paintWalls(int[] cost, int[] time) {
        int n = cost.length;
        int[] f = new int[n + 1];
        Arrays.fill(f, INF);
        f[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = n; j >= 0; j --) {
                f[j] = Math.min(f[j], f[Math.max(j - time[i] - 1, 0)] + cost[i]);
            }
        }
        return f[n];
    }

    // S2: 记忆化搜索
    // time = O(n^2), space = O(n^2)
    int[] cost, time;
    HashMap<String, Integer> memo;
    final int inf = 0x3f3f3f3f;
    public int paintWalls2(int[] cost, int[] time) {
        this.cost = cost;
        this.time = time;
        int n = cost.length;
        memo = new HashMap<>();
        return dfs(n - 1, 0);
    }

    private int dfs(int i, int j) {
        if (j > i) return 0;
        if (i < 0) return inf;
        if (memo.containsKey(i + " " + j)) return memo.get(i + " " + j);
        int res = Math.min(dfs(i - 1, j + time[i]) + cost[i], dfs(i - 1, j - 1));
        memo.put(i + " " + j, res);
        return res;
    }

    // S3: 记忆化搜索2：至少型0-1背包
    // time = O(n^2), space = O(n^2)
    class Solution {
        int[] cost, time;
        HashMap<String, Integer> memo;
        final int inf = 0x3f3f3f3f;
        public int paintWalls(int[] cost, int[] time) {
            this.cost = cost;
            this.time = time;
            int n = cost.length;
            memo = new HashMap<>();
            return dfs(n - 1, n);
        }

        private int dfs(int i, int j) { // j 表示剩余需要的体积
            if (j <= 0) return 0; // 没有约束：表示后面什么也不用选了
            if (i < 0) return inf;
            if (memo.containsKey(i + " " + j)) return memo.get(i + " " + j);
            int res = Math.min(dfs(i - 1, j - time[i] - 1) + cost[i], dfs(i - 1, j));
            memo.put(i + " " + j, res);
            return res;
        }
    }
}
/**
 * 选或不选
 * 枚举选哪个
 * 只有一个付费油漆匠，time[i]需要加起来
 * 如果第i面墙分配给免费的油漆匠，那么消耗1单位的时间
 * dfs(i,j): 刷完0~i的墙，且当前累计付费时间为j，最少开销为多少
 * 付费：dfs(i,j) = dfs(i-1,j+time[i]) + cost[i]
 * 免费：dfs(i,j) = dfs(i-1,j-1)
 * dfs(i,j) = min(dfs(i-1,j+time[i])+cost[i], dfs(i-1,j-1))
 * 递归边界：dfs(-1, < 0) = inf
 *         dfs(i, j) = 0 if j > i
 * 递归入口：dfs(n-1, 0)
 *
 * 01背包：
 * 付费墙个数+免费墙个数 = n
 * 付费时间和 >= 免费墙个数 = n - 付费墙个数
 * 付费时间和 >= n - 付费墙个数
 * 付费时间和 + 付费墙个数 >= n
 * time[i0] + time[i1] + time[i2] + 1 + 1 + 1 >= n
 * (time[i0]+1) + (time[i1]+1) + (time[i2]+1) >= n
 * [至少装满型]的0-1背包：物品体积是time[i]+1,物品价值是cost[i]
 * 选若干物品，体积和至少是n，最小化价值和
 * j: 表示剩余需要的体积
 *
 * 至少型0-1背包的递推：
 * f[i][j]: 前i个物品，选的体积和至少是j的时候，最小的价值和
 * return f[n][n]
 * j-time[i]-1会得到一个负数 => 与0取一个最大值(至少为-1与至少为0其实是一样的)
 *
 * dp[i][j]: the minimum cost if you complete the first i tasks, with the time difference between paid workers and free
 * workers as j
 * min {dp[n][j]} where j >= 0
 * x x x x i x x x x
 *
 */