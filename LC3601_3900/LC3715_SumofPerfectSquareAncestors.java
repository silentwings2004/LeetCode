package LC3601_3900;
import java.util.*;
public class LC3715_SumofPerfectSquareAncestors {
    /**
     * You are given an integer n and an undirected tree rooted at node 0 with n nodes numbered from 0 to n - 1. This
     * is represented by a 2D array edges of length n - 1, where edges[i] = [ui, vi] indicates an undirected edge
     * between nodes ui and vi.
     *
     * You are also given an integer array nums, where nums[i] is the positive integer assigned to node i.
     *
     * Define a value ti as the number of ancestors of node i such that the product nums[i] * nums[ancestor] is a
     * perfect square.
     *
     * Return the sum of all ti values for all nodes i in range [1, n - 1].
     *
     * Note:
     *
     * In a rooted tree, the ancestors of node i are all nodes on the path from node i to the root node 0, excluding
     * i itself.
     * A perfect square is a number that can be expressed as the product of an integer by itself, like 1, 4, 9, 16.
     *
     * Input: n = 3, edges = [[0,1],[1,2]], nums = [2,8,2]
     * Output: 3
     *
     * Input: n = 3, edges = [[0,1],[0,2]], nums = [1,2,4]
     * Output: 1
     *
     * Input: n = 4, edges = [[0,1],[0,2],[1,3]], nums = [1,2,9,4]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * edges.length == n - 1
     * edges[i] = [ui, vi]
     * 0 <= ui, vi <= n - 1
     * nums.length == n
     * 1 <= nums[i] <= 10^5
     * The input is generated such that edges represents a valid tree.
     * @param n
     * @param edges
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    List<Integer>[] adj;
    HashMap<Long, Integer> map;
    int[] nums;
    long res;
    public long sumOfAncestors(int n, int[][] edges, int[] nums) {
        this.nums = nums;
        map = new HashMap<>();
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            adj[a].add(b);
            adj[b].add(a);
        }
        dfs(0, -1);
        return res;
    }

    private void dfs(int u, int fa) {
        long t = get(nums[u]);
        if (fa != -1) res += map.getOrDefault(t, 0);
        map.put(t, map.getOrDefault(t, 0) + 1);
        for (int v : adj[u]) {
            if (v == fa) continue;
            dfs(v, u);
        }
        map.put(t, map.get(t) - 1);
        if (map.get(t) == 0) map.remove(t);
    }

    private long get(int x) {
        long res = 1;
        int cnt = 0;
        while (x % 2 == 0) {
            cnt++;
            x /= 2;
        }
        if (cnt % 2 == 1) res *= 2;
        for (int i = 3; i <= x / i; i += 2) {
            cnt = 0;
            while (x % i == 0) {
                cnt++;
                x /= i;
            }
            if (cnt % 2 == 1) res *= i;
        }
        if (x > 1) res *= x;
        return res;
    }
}