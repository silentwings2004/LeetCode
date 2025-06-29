package LC3301_3600;
import java.util.*;
public class LC3590_KthSmallestPathXORSum {
    /**
     * You are given an undirected tree rooted at node 0 with n nodes numbered from 0 to n - 1. Each node i has an
     * integer value vals[i], and its parent is given by par[i].
     *
     * The path XOR sum from the root to a node u is defined as the bitwise XOR of all vals[i] for nodes i on the path
     * from the root node to node u, inclusive.
     *
     * You are given a 2D integer array queries, where queries[j] = [uj, kj]. For each query, find the kjth smallest
     * distinct path XOR sum among all nodes in the subtree rooted at uj. If there are fewer than kj distinct path XOR
     * sums in that subtree, the answer is -1.
     *
     * Return an integer array where the jth element is the answer to the jth query.
     *
     * In a rooted tree, the subtree of a node v includes v and all nodes whose path to the root passes through v, that
     * is, v and its descendants.
     *
     * Input: par = [-1,0,0], vals = [1,1,1], queries = [[0,1],[0,2],[0,3]]
     * Output: [0,1,-1]
     *
     * Input: par = [-1,0,1], vals = [5,2,7], queries = [[0,1],[1,2],[1,3],[2,1]]
     * Output: [0,7,-1,0]
     *
     * Constraints:
     *
     * 1 <= n == vals.length <= 5 * 10^4
     * 0 <= vals[i] <= 10^5
     * par.length == n
     * par[0] == -1
     * 0 <= par[i] < n for i in [1, n - 1]
     * 1 <= queries.length <= 5 * 10^4
     * queries[j] == [uj, kj]
     * 0 <= uj < n
     * 1 <= kj <= n
     * The input is generated such that the parent array par represents a valid tree.
     * @param par
     * @param vals
     * @param queries
     * @return
     */
    // time = O(nlogn), space = O(n)
    int n;
    int[] xor, sz, son, vals, res;
    List<Integer>[] adj;
    List<int[]>[] qs;
    List<Integer> dvs;
    HashMap<Integer, Integer> map;
    public int[] kthSmallest(int[] par, int[] vals, int[][] queries) {
        this.vals = vals;
        n = vals.length;
        int m = queries.length;
        xor = new int[n];
        sz = new int[n];
        son = new int[n];
        Arrays.fill(son, -1);
        res = new int[m];
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int i = 1; i < n; i++) adj[par[i]].add(i);
        cal(0, 0);

        List<Integer> alls = new ArrayList<>();
        for (int x : xor) alls.add(x);
        Collections.sort(alls);
        map = new HashMap<>();
        int idx = 0;
        dvs = new ArrayList<>();
        for (int i = 0; i < alls.size(); i++) {
            if (i == 0 || !alls.get(i).equals(alls.get(i - 1))) {
                int x = alls.get(i);
                map.put(x, idx++);
                dvs.add(x);
            }
        }
        int vz = dvs.size();
        dfs(0);
        qs = new List[n];
        for (int i = 0; i < n; i++) qs[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int a = queries[i][0], b = queries[i][1];
            qs[a].add(new int[]{i, b});
        }
        dfs2(0, vz);
        return res;
    }

    private void cal(int u, int pre) {
        xor[u] = pre ^ vals[u];
        for (int v : adj[u]) cal(v, xor[u]);
    }

    private void dfs(int u) {
        sz[u] = 1;
        for (int v : adj[u]) {
            dfs(v);
            sz[u] += sz[v];
            if (son[u] == -1 || sz[v] > sz[son[u]]) son[u] = v;
        }
    }

    private Node dfs2(int u, int vz) {
        Node root = null;
        if (son[u] != -1) root = dfs2(son[u], vz);
        for (int v : adj[u]) {
            if (v == son[u]) continue;
            Node child = dfs2(v, vz);
            root = merge(root, child, 0, vz - 1);
        }
        int x = map.get(xor[u]);
        root = insert(root, 0, vz - 1, x);
        for (int[] q : qs[u]) {
            int idx = q[0], k = q[1];
            if (root == null || root.sum < k) res[idx] = -1;
            else {
                int pos = query(root, 0, vz - 1, k);
                res[idx] = dvs.get(pos);
            }
        }
        return root;
    }

    private Node merge(Node a, Node b, int l, int r) {
        if (a == null) return b;
        if (b == null) return a;
        if (l == r) {
            a.sum = 1;
            return a;
        }
        int mid = l + r >> 1;
        a.l = merge(a.l, b.l, l, mid);
        a.r = merge(a.r, b.r, mid + 1, r);
        a.sum = (a.l != null ? a.l.sum : 0) + (a.r != null ? a.r.sum : 0);
        return a;
    }

    private Node insert(Node root, int l, int r, int x) {
        if (root == null) root = new Node();
        if (l == r) {
            root.sum = 1;
            return root;
        }
        int mid = l + r >> 1;
        if (x <= mid) root.l = insert(root.l, l, mid, x);
        else root.r = insert(root.r, mid + 1, r, x);
        root.sum = (root.l != null ? root.l.sum : 0) + (root.r != null ? root.r.sum : 0);
        return root;
    }

    private int query(Node root, int l, int r, int k) {
        if (l == r) return l;
        int mid = l + r >> 1;
        int ls = root.l != null ? root.l.sum : 0;
        if (ls >= k) return query(root.l, l, mid, k);
        return query(root.r, mid + 1, r, k - ls);
    }

    class Node {
        Node l, r;
        int sum;
    }
}
/**
 * 一堆数字，异或一个定值 c，结果的第 k 小
 */