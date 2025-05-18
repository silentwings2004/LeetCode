package LC1501_1800;
import java.util.*;
public class LC1766_TreeofCoprimes {
    /**
     * There is a tree (i.e., a connected, undirected graph that has no cycles) consisting of n nodes numbered from 0 to
     * n - 1 and exactly n - 1 edges. Each node has a value associated with it, and the root of the tree is node 0.
     *
     * To represent this tree, you are given an integer array nums and a 2D array edges. Each nums[i] represents the ith
     * node's value, and each edges[j] = [uj, vj] represents an edge between nodes uj and vj in the tree.
     *
     * Two values x and y are coprime if gcd(x, y) == 1 where gcd(x, y) is the greatest common divisor of x and y.
     *
     * An ancestor of a node i is any other node on the shortest path from node i to the root. A node is not considered
     * an ancestor of itself.
     *
     * Return an array ans of size n, where ans[i] is the closest ancestor to node i such that nums[i] and nums[ans[i]]
     * are coprime, or -1 if there is no such ancestor.
     *
     * Input: nums = [2,3,3,2], edges = [[0,1],[1,2],[1,3]]
     * Output: [-1,0,0,1]
     *
     * Constraints:
     *
     * nums.length == n
     * 1 <= nums[i] <= 50
     * 1 <= n <= 10^5
     * edges.length == n - 1
     * edges[j].length == 2
     * 0 <= uj, vj < n
     * uj != vj
     * @param nums
     * @param edges
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public int[] getCoprimes(int[] nums, int[][] edges) {
        List<Integer> path = new ArrayList<>(); // path[i] is the i-th node idx along the dfs path
        List<Integer>[] records = new List[51]; // records[i] contains the depth of all the nodes whose num = i;
        for (int i = 0; i < 51; i++) records[i] = new ArrayList<>();
        int n = nums.length;
        int[] res = new int[n];
        List<Integer>[] next = new List[100000];
        boolean[] visited = new boolean[100000];
        for (int i = 0; i < 100000; i++) next[i] = new ArrayList<>();
        for (int[] edge : edges) {
            next[edge[0]].add(edge[1]);
            next[edge[1]].add(edge[0]);
        }
        visited[0] = true;
        dfs(0, 0, nums, path, records, next, res, visited);
        return res;
    }

    private void dfs(int curIdx, int depth, int[] nums, List<Integer> path, List<Integer>[] records, List<Integer>[] next, int[] res, boolean[] visited) {
        int i = -1;
        for (int d = 1; d <= 50; d++) {
            if (records[d].size() > 0 && gcd(d, nums[curIdx]) == 1) {
                i = Math.max(i, records[d].get(records[d].size() - 1));
            }
        }
        res[curIdx] = (i == -1 ? -1 : path.get(i));
        path.add(curIdx);
        records[nums[curIdx]].add(depth);

        for (int nextId : next[curIdx]) {
            if (visited[nextId]) continue;
            visited[nextId] = true;
            dfs(nextId, depth + 1, nums, path, records, next, res, visited);
            visited[nextId] = false;
        }
        path.remove(path.size() - 1);
        records[nums[curIdx]].remove(records[nums[curIdx]].size() - 1);
    }

    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    // S2
    final int N = 100010, M = N * 2;
    int idx;
    int[] h, e, ne, w;
    int[] depth, pos;
    int[] ans;
    List<Integer>[] g;
    public int[] getCoprimes2(int[] nums, int[][] edges) {
        h = new int[N];
        e = new int[M];
        ne = new int[M];
        depth = new int[N];
        pos = new int[N];
        w = nums;
        int n = w.length;
        ans = new int[n];
        Arrays.fill(ans, -1);
        Arrays.fill(h, -1);
        idx = 0;
        g = new List[55];
        for (int i = 0; i < 55; i++) g[i] = new ArrayList<>();

        for (int[] e : edges) {
            int a = e[0], b = e[1];
            add(a, b);
            add(b, a);
        }

        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 50; j++) {
                if (gcd(i, j) == 1) g[i].add(j);
            }
        }

        Arrays.fill(pos, -1);
        dfs(0, -1);
        return ans;
    }

    private void dfs(int u, int fa) {
        int x = w[u];
        for (int y : g[x]) {
            if (pos[y] != -1) {
                if (ans[u] == -1 || depth[ans[u]] < depth[pos[y]]) ans[u] = pos[y];
            }
        }

        int t = pos[x];
        pos[x] = u;
        for (int i = h[u]; i != -1; i = ne[i]) {
            int j = e[i];
            if (j == fa) continue;
            depth[j] = depth[u] + 1;
            dfs(j, u);
        }
        pos[x] = t;
    }

    private void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    // S3
    // time = O(n * 50), space = O(n + 50)
    class Solution {
        final int inf = 0x3f3f3f3f;
        List<Integer>[] adj, p, q;
        int n;
        int[] nums, depth, d, res;
        public int[] getCoprimes(int[] nums, int[][] edges) {
            this.nums = nums;
            n = nums.length;
            depth = new int[n];
            d = new int[n];
            Arrays.fill(d, inf);
            res = new int[n];
            Arrays.fill(res, -1);
            adj = new List[n];
            for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
            p = new List[51];
            q = new List[51];
            for (int i = 0; i <= 50; i++) p[i] = new ArrayList<>();
            for (int i = 0; i <= 50; i++) q[i] = new ArrayList<>();
            for (int i = 1; i <= 50; i++) {
                for (int j = i; j <= 50; j++) {
                    if (gcd(i, j) == 1) {
                        p[i].add(j);
                        p[j].add(i);
                    }
                }
            }
            for (int[] e : edges) {
                int a = e[0], b = e[1];
                adj[a].add(b);
                adj[b].add(a);
            }
            dfs(0, -1);
            return res;
        }

        private void dfs(int u, int fa) {
            q[nums[u]].add(u);
            for (int v : adj[u]) {
                if (v == fa) continue;
                depth[v] = depth[u] + 1;
                for (int x : p[nums[v]]) {
                    for (int y : q[x]) {
                        if (d[v] > depth[v] - depth[y]) {
                            d[v] = depth[v] - depth[y];
                            res[v] = y;
                        }
                    }
                }
                dfs(v, u);
            }
            q[nums[u]].remove(q[nums[u]].size() - 1);
        }

        private int gcd(int a, int b) {
            return b == 0 ? a : gcd(b, a % b);
        }
    }
}
/**
 * 1 <= nums[i] <= 50 => 不需要回看所有支路上的祖先
 * 本题本质就是DFS。当我们考察某个节点curIdx时，在该DFS路径path上的所有节点都是它的祖先。
 * 我们需要从中找一个深度最大的、与nums[curIdx]互质的节点。理论上我们需要逆序遍历一遍path，总体复杂度是o(N^2)。
 * 本题特殊之处在于数据范围限制了所有节点的“数值”不超过50，于是我们可以不遍历节点、转而遍历“数值”来更高效的找到互质的节点。
 * 需要在维护path的同时，维护一个哈希表records，其中records[d]存储的就是path里所有数值是d的节点的深度。
 * 我们在1到50里面寻找那些与nums[curIdx]互质的d，其中最大的records[d].back()就是离curIdx最近的互质节点的深度。
 * 再根据这个深度，直接从path里面读取那个节点的idx。
 */