package LC2401_2700;
import java.util.*;
public class LC2673_MakeCostsofPathsEqualinaBinaryTree {
    /**
     * You are given an integer n representing the number of nodes in a perfect binary tree consisting of nodes numbered
     * from 1 to n. The root of the tree is node 1 and each node i in the tree has two children where the left child is
     * the node 2 * i and the right child is 2 * i + 1.
     *
     * Each node in the tree also has a cost represented by a given 0-indexed integer array cost of size n where cost[i]
     * is the cost of node i + 1. You are allowed to increment the cost of any node by 1 any number of times.
     *
     * Return the minimum number of increments you need to make the cost of paths from the root to each leaf node equal.
     *
     * Note:
     *
     * A perfect binary tree is a tree where each node, except the leaf nodes, has exactly 2 children.
     * The cost of a path is the sum of costs of nodes in the path.
     *
     * Input: n = 7, cost = [1,5,2,2,3,3,1]
     * Output: 6
     *
     * Input: n = 3, cost = [5,3,3]
     * Output: 0
     *
     * Constraints:
     *
     * 3 <= n <= 10^5
     * n + 1 is a power of 2
     * cost.length == n
     * 1 <= cost[i] <= 10^4
     * @param n
     * @param cost
     * @return
     */
    // time = O(nlogn), space = O(n)
    int level, lst;
    int[] w;
    HashMap<Integer, Long> map;
    long[] cnt;
    public int minIncrements(int n, int[] cost) {
        map = new HashMap<>();
        w = cost;
        cnt = new long[n + 1];
        level = 1 + (int)(Math.log(n) / Math.log(2));
        lst = (int)Math.pow(2, level - 1);
        dfs(1, 0);

        long maxv = 0;
        for (long v : map.values()) maxv = Math.max(maxv, v);
        for (int k : map.keySet()) cnt[k] = maxv - map.get(k);

        long res = 0;
        for (int i = level - 1; i > 0; i--) {
            int l = (int)Math.pow(2, i), r = (int)Math.pow(2, i + 1) - 1;
            for (int j = l; j <= r; j += 2) {
                if (cnt[j] > 0 && cnt[j + 1] > 0) {
                    long t = Math.min(cnt[j], cnt[j + 1]);
                    cnt[j / 2] = t;
                    res += Math.max(cnt[j], cnt[j + 1]) - t;
                } else res += Math.max(cnt[j], cnt[j + 1]);
            }
        }
        return (int)(res + cnt[1]);
    }

    private void dfs(int u, long sum) {
        if (u >= lst) {
            map.put(u, sum + w[u - 1]);
            return;
        }
        dfs(u * 2, sum + w[u - 1]);
        dfs(u * 2 + 1, sum + w[u - 1]);
    }

    // S2：Greedy
    // time = O(n), space = O（1）
    public int minIncrements2(int n, int[] cost) {
        int res = 0;
        for (int i = n / 2; i > 0; i--) {
            res += Math.abs(cost[2 * i - 1] - cost[i * 2]);
            cost[i - 1] += Math.max(cost[2 * i - 1], cost[i * 2]);
        }
        return res;
    }
}
/**
 * 应该先修改上面的节点还是下面的节点
 * 操作只有增加，没有减少
 * 把小的叶子节点改成和大的叶子一样，修改上面是没有意义的
 * 贪心：把叶子节点里的最小值变成最大值即可
 * 先修改叶子，把路径和传给父节点，可以直接比较2个父节点的值
 * 由于是满2叉树，所以不需要用递归，只要循环即可。
 */