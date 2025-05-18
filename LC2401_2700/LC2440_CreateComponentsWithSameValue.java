package LC2401_2700;
import java.util.*;
public class LC2440_CreateComponentsWithSameValue {
    /**
     * There is an undirected tree with n nodes labeled from 0 to n - 1.
     *
     * You are given a 0-indexed integer array nums of length n where nums[i] represents the value of the ith node. You
     * are also given a 2D integer array edges of length n - 1 where edges[i] = [ai, bi] indicates that there is an edge
     * between nodes ai and bi in the tree.
     *
     * You are allowed to delete some edges, splitting the tree into multiple connected components. Let the value of a
     * component be the sum of all nums[i] for which node i is in the component.
     *
     * Return the maximum number of edges you can delete, such that every connected component in the tree has the same
     * value.
     *
     * Input: nums = [6,2,2,2,6], edges = [[0,1],[1,2],[1,3],[3,4]]
     * Output: 2
     *
     * Input: nums = [2], edges = []
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= n <= 2 * 10^4
     * nums.length == n
     * 1 <= nums[i] <= 50
     * edges.length == n - 1
     * edges[i].length == 2
     * 0 <= edges[i][0], edges[i][1] <= n - 1
     * edges represents a valid tree.
     * @param nums
     * @param edges
     * @return
     */
    // time = O(n * sqrt(n * A), space = O(n)  A: 每个点权值的最大值
    List<Integer>[] graph;
    public int componentValue(int[] nums, int[][] edges) {
        int n = nums.length;
        graph = new List[n];
        int[] d = new int[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] x : edges) {
            int a = x[0], b = x[1];
            graph[a].add(b);
            graph[b].add(a);
            d[a]++;
            d[b]++;
        }

        int sum = 0;
        for (int x : nums) sum += x;

        int root = 0;
        for (int i = 0; i < n; i++) {
            if (d[i] == 1) {
                root = i;
                break;
            }
        }

        for (int i = 1; i <= sum; i++) {
            if (sum % i != 0) continue;
            if (dfs(root, -1, i, nums) == 0) return sum / i - 1;
        }
        return 0;
    }

    private int dfs(int u, int p, int sum, int[] nums) {
        int t = 0;
        for (int j : graph[u]) {
            if (j == p) continue;
            t += dfs(j, u, sum, nums);
        }
        t += nums[u];
        if (t == sum) t = 0;
        return t;
    }
}
