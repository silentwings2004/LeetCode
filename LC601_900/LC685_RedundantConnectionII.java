package LC601_900;
import java.util.*;
public class LC685_RedundantConnectionII {
    /**
     * In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all
     * other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which
     * has no parents.
     *
     * The given input is a directed graph that started as a rooted tree with n nodes (with distinct values from 1 to n),
     * with one additional directed edge added. The added edge has two different vertices chosen from 1 to n, and was
     * not an edge that already existed.
     *
     * The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [ui, vi] that represents a
     * directed edge connecting nodes ui and vi, where ui is a parent of child vi.
     *
     * Return an edge that can be removed so that the resulting graph is a rooted tree of n nodes. If there are multiple
     * answers, return the answer that occurs last in the given 2D-array.
     *
     * Input: edges = [[1,2],[1,3],[2,3]]
     * Output: [2,3]
     *
     * Constraints:
     *
     * n == edges.length
     * 3 <= n <= 1000
     * edges[i].length == 2
     * 1 <= ui, vi <= n
     * ui != vi
     * @param edges
     * @return
     */
    // time = O(n), space = O(n)
    int[] p;
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        p = new int[n + 1];
        int[] cand1 = null, cand2 = null;
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            if (p[b] != 0) {
                cand1 = e;
                cand2 = new int[]{p[b], b};
                break;
            } else p[b] = a;
        }

        for (int i = 1; i <= n; i++) p[i] = i;

        // 先任意删一个，然后判断剩下的是否能成环 -> union find / dfs 搜索
        // tree -> n edges => n + 1 nodes now we have n edges with one fake edge -> we have n nodes
        for (int[] e : edges) {
            if (e == cand1) continue; // 相当于当做没有这条边存在，不参与union
            int a = e[0], b = e[1];
            if (find(a) != find(b)) p[find(b)] = find(a);
            else { // a, b之间本身已经连通，现在再加一条连通边就成环了
                // cand1, cand2可能都是空 -> 就是这条边造成了环，并且肯定连接到了root上，所有结点都有一个入度，连root都有1个root =>
                // 整张图就是一个指向root的环，环里任何一条边切断都可以
                return cand2 == null ? e : cand2;
            }
        }
        return cand1; // cand1切掉之后相安无事 => 罪魁祸首就是cand1
    }

    private int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }

    // S2: tarjan
    // time = O(n), space = O(n)
    int n;
    boolean[] st1, st2, st, in_c, in_k;
    List<Integer>[] g;
    Stack<Integer> stk;
    public int[] findRedundantDirectedConnection2(int[][] edges) {
        n = edges.length;
        st1 = new boolean[n + 1];
        st2 = new boolean[n + 1];
        st = new boolean[n + 1];
        in_c = new boolean[n + 1];
        in_k = new boolean[n + 1];
        g = new List[n + 1];
        stk = new Stack<>();
        for (int i = 0; i <= n; i++) g[i] = new ArrayList<>();

        work1(edges);
        work2(edges);

        for (int i = n - 1; i >= 0; i--) {
            if (st1[i] && st2[i]) return edges[i];
        }
        for (int i = n - 1; i >= 0; i--) {
            if (st1[i] || st2[i]) return edges[i];
        }
        return new int[0];
    }

    private void work1(int[][] edges) {
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
        }

        for (int i = 1; i <= n; i++) {
            if (!st[i] && dfs(i)) break;
        }

        for (int i = 0; i < n; i++) {
            int a = edges[i][0], b = edges[i][1];
            if (in_c[a] && in_c[b]) st1[i] = true;
        }
    }

    private void work2(int[][] edges) {
        int[] p = new int[n + 1];
        Arrays.fill(p, -1);
        for (int i = 0; i < n; i++) {
            int a = edges[i][0], b = edges[i][1];
            if (p[b] != -1) {
                st2[p[b]] = st2[i] = true;
                break;
            } else p[b] = i;
        }
    }

    private boolean dfs(int u) {
        st[u] = true;
        stk.push(u);
        in_k[u] = true;

        for (int x : g[u]) {
            if (!st[x]) {
                if (dfs(x)) return true;
            } else if (in_k[x]) {
                while (stk.peek() != x) in_c[stk.pop()] = true;
                in_c[x] = true;
                return true;
            }
        }
        stk.pop();
        in_k[u] = false;
        return false;
    }
}
/**
 * 与上一题的区别：无向 vs 有向
 *       1
 *   2 3  4 5
 *         6 7
 * => 找到一个结点有2个parent，找additional direct edge
 * 如果这条directional edge正好指向root，所有结点都为1，包括root
 * 0  1 -> 2
 *
 * 1. 只有环，基环树，去掉环上任何一条边都可以
 * 2. 某个点的入度 = 2，没有出现环 => 这个点的2条入边去掉任何一条边都可以
 * 3. 以上1，2都有，某个子节点链接到某个祖先结点 => 只能去掉1，2的公共边
 * 做的时候，判断哪些边在环里，再判断是否是某个点的2条入边之一
 * 如果1，2都有的话，就要找1，2的公共边
 */
