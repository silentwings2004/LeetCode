package LC3301_3600;
import java.util.*;
public class LC3486_LongestSpecialPathII {
    /**
     * You are given an undirected tree rooted at node 0, with n nodes numbered from 0 to n - 1. This is represented by
     * a 2D array edges of length n - 1, where edges[i] = [ui, vi, lengthi] indicates an edge between nodes ui and vi
     * with length lengthi. You are also given an integer array nums, where nums[i] represents the value at node i.
     *
     * A special path is defined as a downward path from an ancestor node to a descendant node in which all node values
     * are distinct, except for at most one value that may appear twice.
     *
     * Return an array result of size 2, where result[0] is the length of the longest special path, and result[1] is the
     * minimum number of nodes in all possible longest special paths.
     *
     * Input: edges = [[0,1,1],[1,2,3],[1,3,1],[2,4,6],[4,7,2],[3,5,2],[3,6,5],[6,8,3]], nums = [1,1,0,3,1,2,1,1,0]
     * Output: [9,3]
     *
     * Input: edges = [[1,0,3],[0,2,4],[0,3,5]], nums = [1,1,0,2]
     * Output: [5,2]
     *
     * Constraints:
     *
     * 2 <= n <= 5 * 10^4
     * edges.length == n - 1
     * edges[i].length == 3
     * 0 <= ui, vi < n
     * 1 <= lengthi <= 10^3
     * nums.length == n
     * 0 <= nums[i] <= 5 * 10^4
     * The input is generated such that edges represents a valid tree.
     * @param edges
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    List<int[]>[] adj;
    HashMap<Integer, Stack<Integer>> map;
    List<Integer> path;
    int[] nums, res;
    public int[] longestSpecialPath(int[][] edges, int[] nums) {
        this.nums = nums;
        int n = nums.length;
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0], b = e[1], c = e[2];
            adj[a].add(new int[]{b, c});
            adj[b].add(new int[]{a, c});
        }
        map = new HashMap<>();
        path = new ArrayList<>();
        res = new int[]{0, n + 1};
        dfs(0, -1, 0, 0, 0);
        return res;
    }

    private void dfs(int u, int fa, int curLen, int md1, int md2) {
        int d = path.size();
        path.add(curLen);
        if (map.containsKey(nums[u]) && map.get(nums[u]).size() > 0) {
            int t = map.get(nums[u]).peek() + 1;
            if (t > md1) {
                md2 = md1;
                md1 = t;
            } else if (t > md2) md2 = t;
        }

        map.putIfAbsent(nums[u], new Stack<>());
        map.get(nums[u]).push(path.size() - 1);
        if (res[0] < curLen - path.get(md2)) {
            res[0] = curLen - path.get(md2);
            res[1] = d - md2 + 1;
        } else if (res[0] == curLen - path.get(md2) && res[1] > d - md2 + 1) {
            res[1] = d - md2 + 1;
        }

        for (int[] x : adj[u]) {
            int v = x[0], c = x[1];
            if (v == fa) continue;
            dfs(v, u, curLen + c, md1, md2);
        }
        map.get(nums[u]).pop();
        path.remove(path.size() - 1);
    }
}
/**
 * 树上滑窗
 * 维护这条链最上面的位置
 * 枚举右，维护左
 * 还得维护出现两次数字左边的位置 -> min(L1,L2) + 1
 */