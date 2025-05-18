package LC2401_2700;
import java.util.*;
public class LC2479_MaximumXORofTwoNonOverlappingSubtrees {
    /**
     * There is an undirected tree with n nodes labeled from 0 to n - 1. You are given the integer n and a 2D integer
     * array edges of length n - 1, where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi
     * in the tree.
     *
     * Each node has an associated value. You are given an array values of length n, where values[i] is the value of
     * the ith node.
     *
     * Select any two non-overlapping subtrees. Your score is the bitwise XOR of the sum of the values within those
     * subtrees.
     *
     * Return the maximum possible score you can achieve. If it is impossible to find two nonoverlapping subtrees,
     * return 0.
     *
     * Note that:
     *
     * The subtree of a node is the tree consisting of that node and all of its descendants.
     * Two subtrees are non-overlapping if they do not share any common node.
     *
     * Input: n = 6, edges = [[0,1],[0,2],[1,3],[1,4],[2,5]], values = [2,8,3,6,2,5]
     * Output: 24
     *
     * Input: n = 3, edges = [[0,1],[1,2]], values = [4,6,1]
     * Output: 0
     *
     * Constraints:
     *
     * 2 <= n <= 5 * 10^4
     * edges.length == n - 1
     * 0 <= ai, bi < n
     * values.length == n
     * 1 <= values[i] <= 10^9
     * It is guaranteed that edges represents a valid tree.
     * @param n
     * @param edges
     * @param values
     * @return
     */
    // S1: DFS + XOR Trie
    // time = O(nlogn), space = O(n)
    List<Integer>[] graph;
    long[] w;
    int[] values;
    long res = 0;
    TrieNode root;
    public long maxXor(int n, int[][] edges, int[] values) {
        graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] x : edges) {
            int a = x[0], b = x[1];
            graph[a].add(b);
            graph[b].add(a);
        }

        w = new long[n];
        this.values = values;
        dfs(0, -1);

        root = new TrieNode();
        for (int x : graph[0]) {
            dfs2(x, 0);
        }
        return res;
    }

    private void dfs2(int u, int fa) {
        long x = w[u], sum = 0;
        TrieNode node = root;
        for (int i = 46; i >= 0; i--) {
            int t = (int)(x >> i & 1);
            if (node.next[1 - t] != null) {
                node = node.next[1 - t];
                sum = sum * 2 + 1;
            } else if (node.next[t] != null) {
                node = node.next[t];
                sum = sum * 2;
            } else break;
        }
        res = Math.max(res, sum);

        for (int next : graph[u]) {
            if (next == fa) continue;
            dfs2(next, u);
        }
        insert(x);
    }

    private void insert(long x) {
        TrieNode node = root;
        for (int i = 46; i >= 0; i--) {
            int t = (int)(x >> i & 1);
            if (node.next[t] == null) node.next[t] = new TrieNode();
            node = node.next[t];
        }
    }

    private long dfs(int u, int fa) {
        w[u] += values[u];
        for (int next : graph[u]) {
            if (next == fa) continue;
            w[u] += dfs(next, u);
        }
        return w[u];
    }

    private class TrieNode {
        private TrieNode[] next;
        public TrieNode() {
            this.next = new TrieNode[2];
        }
    }

    // S1.2 Trie + DFS
    class Solution {
        // time = O(nlogn), space = O(n)
        int[] h, e, ne;
        long[] w;
        int[][] son;
        long res, max;
        int[] values;
        int idx, cnt;
        public long maxXor(int n, int[][] edges, int[] values) {
            int N = n + 10, M = N * 2;
            h = new int[N];
            e = new int[M];
            ne = new int[M];
            Arrays.fill(h, -1);

            for (int[] edge : edges) {
                int a = edge[0], b = edge[1];
                add(a, b);
                add(b, a);
            }

            w = new long[n];
            this.values = values;
            dfs(0, -1);

            int S = Long.toBinaryString(max).length() * N;
            son = new int[S][2];
            for (int i = h[0]; i != -1; i = ne[i]) {
                int j = e[i];
                dfs2(j, 0);
            }
            return res;
        }

        private void dfs2(int u, int fa) {
            long x = w[u], sum = 0;
            int p = 0;
            for (int i = 46; i >= 0; i--) {
                int t = (int)(x >> i & 1);
                if (son[p][1 - t] != 0) {
                    p = son[p][1 - t];
                    sum = sum * 2 + 1;
                } else {
                    p = son[p][t];
                    sum = sum * 2;
                }
            }
            res = Math.max(res, sum);

            for (int i = h[u]; i != -1; i = ne[i]) {
                int j = e[i];
                if (j == fa) continue;
                dfs2(j, u);
            }
            insert(x);
        }

        private void insert(long x) {
            int p = 0;
            for (int i = 46; i >= 0; i--) {
                int u = (int)(x >> i & 1);
                if (son[p][u] == 0) son[p][u] = ++cnt;
                p = son[p][u];
            }
        }

        private long dfs(int u, int fa) {
            w[u] += values[u];
            for (int i = h[u]; i != -1; i = ne[i]) {
                int j = e[i];
                if (j == fa) continue;
                w[u] += dfs(j, u);
            }
            max = Math.max(max, w[u]);
            return w[u];
        }

        private void add(int a, int b) {
            e[idx] = b;
            ne[idx] = h[a];
            h[a] = idx++;
        }
    }
}