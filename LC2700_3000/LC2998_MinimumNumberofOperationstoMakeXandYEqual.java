package LC2700_3000;
import java.util.*;
public class LC2998_MinimumNumberofOperationstoMakeXandYEqual {
    /**
     * You are given two positive integers x and y.
     *
     * In one operation, you can do one of the four following operations:
     *
     * Divide x by 11 if x is a multiple of 11.
     * Divide x by 5 if x is a multiple of 5.
     * Decrement x by 1.
     * Increment x by 1.
     * Return the minimum number of operations required to make x and y equal.
     *
     * Input: x = 26, y = 1
     * Output: 3
     *
     * Input: x = 54, y = 2
     * Output: 4
     *
     * Input: x = 25, y = 30
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= x, y <= 10^4
     * @param x
     * @param y
     * @return
     */
    // S1: bfs
    // time = O(x), space = O(x)
    public int minimumOperationsToMakeEqual(int x, int y) {
        if (x <= y) return y - x;
        Queue<Integer> q = new LinkedList<>();
        HashSet<Integer> set = new HashSet<>();
        q.offer(x);
        set.add(x);

        int step = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            while (sz-- > 0) {
                int t = q.poll();
                if (t == y) return step;
                if (t % 11 == 0 && set.add(t / 11)) q.offer(t / 11);
                if (t % 5 == 0 && set.add(t / 5)) q.offer(t / 5);
                if (set.add(t - 1)) q.offer(t - 1);
                if (set.add(t + 1)) q.offer(t + 1);
            }
            step++;
        }
        return -1;
    }

    // S2: 记忆化搜索 + 贪心
    // time = O(log^2(x/y)), space = O(log^2(x/y))
    HashMap<Integer, Integer> memo;
    public int minimumOperationsToMakeEqual2(int x, int y) {
        memo = new HashMap<>();
        return dfs(x, y);
    }

    private int dfs(int x, int y) {
        if (x <= y) return y - x;
        if (memo.containsKey(x)) return memo.get(x);

        int res = x - y;
        res = Math.min(res, dfs(x / 11, y) + x % 11 + 1); // 向下-
        res = Math.min(res, dfs(x / 11 + 1, y) + 11 - x % 11 + 1); // 向上+
        res = Math.min(res, dfs(x / 5, y) + x % 5 + 1);
        res = Math.min(res, dfs(x / 5 + 1, y) + 5 - x % 5 + 1);
        memo.put(x, res);
        return res;
    }
}
/**
 * 方法1：bfs
 * 从 v 向 v / 11 连边
 * 从 v 向 v / 5 连边
 * 从 v 向 v - 1 连边
 * 从 v 向 v + 1 连边
 * 求从 x 到 y 的最短路长度
 *
 * 如果用 vis 数组记录访问过的点，那么 vis 数组应该开多大？
 * 最短路长度至多是 x - y
 * x + x - y + 1
 *
 * 方法2：记忆化搜索
 */