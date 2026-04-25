package LC3901_4200;
import java.util.*;
public class LC3910_CountConnectedSubgraphswithEvenNodeSum {
    /**
     * You are given an undirected graph with n nodes labeled from 0 to n - 1. Node i has a value of nums[i], which is
     * either 0 or 1. The edges of the graph are given by a 2D array edges where edges[i] = [ui, vi] represents an edge
     * between node ui and node vi.
     *
     * For a non-empty subset s of nodes in the graph, we consider the induced subgraph of s generated as follows:
     *
     * We keep only the nodes in s.
     * We keep only the edges whose two endpoints are both in s.
     * Return an integer representing the number of non-empty subsets s of nodes in the graph such that:
     *
     * The induced subgraph of s is connected.
     * The sum of node values in s is even.
     *
     * Input: nums = [1,0,1], edges = [[0,1],[1,2]]
     * Output: 2
     *
     * Input: nums = [1], edges = []
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 13
     * nums[i] is 0 or 1.
     * 0 <= edges.length <= n * (n - 1) / 2
     * edges[i] = [ui, vi]
     * 0 <= ui < vi < n
     * All edges are distinct.
     * @param nums
     * @param edges
     * @return
     */
    // time = O(2^n * n), space = O(n)
    public int evenSumSubgraphs(int[] nums, int[][] edges) {
        int n = nums.length;
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            adj[a].add(b);
            adj[b].add(a);
        }

        int res = 0;
        for (int i = 1; i < 1 << n; i++) {
            boolean[] st = new boolean[n];
            int s = 0;
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) == 1) {
                    s += nums[j];
                    st[j] = true;
                }
            }
            if (s % 2 == 1) continue;
            if (check(adj, st)) res++;
        }
        return res;
    }

    private boolean check(List<Integer>[] adj, boolean[] st) {
        int n = st.length, start = 0;
        for (int i = 0; i < n; i++) {
            if (st[i]) {
                start = i;
                break;
            }
        }
        dfs(start, adj, st);
        for (int i = 0; i < n; i++) {
            if (st[i]) return false;
        }
        return true;
    }

    private void dfs(int u, List<Integer>[] adj, boolean[] st) {
        st[u] = false;
        for (int v : adj[u]) {
            if (st[v]) dfs(v, adj, st);
        }
    }
}