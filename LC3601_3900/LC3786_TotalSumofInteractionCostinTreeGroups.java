package LC3601_3900;
import java.util.*;
public class LC3786_TotalSumofInteractionCostinTreeGroups {
    /**
     * You are given an integer n and an undirected tree with n nodes numbered from 0 to n - 1. This is represented by
     * a 2D array edges of length n - 1, where edges[i] = [ui, vi] indicates an undirected edge between nodes ui and vi.
     *
     * You are also given an integer array group of length n, where group[i] denotes the group label assigned to node i.
     *
     * Two nodes u and v are considered part of the same group if group[u] == group[v].
     * The interaction cost between u and v is defined as the number of edges on the unique path connecting them in the
     * tree.
     * Return an integer denoting the sum of interaction costs over all unordered pairs (u, v) with u != v such that
     * group[u] == group[v].
     *
     * Input: n = 3, edges = [[0,1],[1,2]], group = [1,1,1]
     * Output: 4
     *
     * Input: n = 3, edges = [[0,1],[1,2]], group = [3,2,3]
     * Output: 2
     *
     * Input: n = 4, edges = [[0,1],[0,2],[0,3]], group = [1,1,4,4]
     * Output: 3
     *
     * Input: n = 2, edges = [[0,1]], group = [9,8]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * edges.length == n - 1
     * edges[i] = [ui, vi]
     * 0 <= ui, vi <= n - 1
     * group.length == n
     * 1 <= group[i] <= 20
     * The input is generated such that edges represents a valid tree.
     * @param n
     * @param edges
     * @param group
     * @return
     */
    // time = O(n * U), space = O(n) U: size of group (20)
    List<Integer>[] adj;
    int[] group, w;
    long res;
    public long interactionCosts(int n, int[][] edges, int[] group) {
        this.group = group;
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            adj[a].add(b);
            adj[b].add(a);
        }

        w = new int[21];
        for (int x : group) w[x]++;
        dfs(0, -1);
        return res;
    }

    private int[] dfs(int u, int fa) {
        int[] cnt = new int[21];
        cnt[group[u]]++;
        for (int v : adj[u]) {
            if (v == fa) continue;
            int[] t = dfs(v, u);
            for (int i = 1; i <= 20; i++) {
                res += 1L * t[i] * (w[i] - t[i]);
                cnt[i] += t[i];
            }
        }
        return cnt;
    }

    // S2: 单调栈虚树
    // time = O(nlogn), space = O(nlogn)
    class Solution {
        int n;
        List<Integer>[] adj, vt;
        int[] isNode;
        int[] dfn, dep;
        int[][] fa;
        int ts, mx;
        long res;
        public long interactionCosts(int n, int[][] edges, int[] group) {
            this.n = n;
            adj = new List[n];
            for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
            for (int[] e : edges) {
                int a = e[0], b = e[1];
                adj[a].add(b);
                adj[b].add(a);
            }

            dfn = new int[n];
            dep = new int[n];
            fa = new int[n][17];
            for (int i = 0; i < n; i++) Arrays.fill(fa[i], -1);

            build(0, -1);

            mx = 32 - Integer.numberOfLeadingZeros(n);
            for (int i = 0; i + 1 < mx; i++) {
                for (int v = 0; v < n; v++) {
                    int p = fa[v][i];
                    fa[v][i + 1] = p == -1 ? -1 : fa[p][i];
                }
            }

            HashMap<Integer, List<Integer>> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                map.putIfAbsent(group[i], new ArrayList<>());
                map.get(group[i]).add(i);
            }

            vt = new List[n];
            for (int i = 0; i < n; i++) vt[i] = new ArrayList<>();
            isNode = new int[n];
            Arrays.fill(isNode, -1);

            int root = 0;
            int[] stk = new int[n + 1];
            int tt = 0;
            for (int key : map.keySet()) {
                List<Integer> nodes = map.get(key);
                Collections.sort(nodes, (o1, o2) -> dfn[o1] - dfn[o2]);
                vt[root].clear();
                tt = 0;
                stk[++tt] = root;
                for (int v : nodes) {
                    isNode[v] = key;
                    if (v == root) continue;
                    vt[v].clear();
                    int lca = getLCA(stk[tt], v);
                    while (tt > 1) {
                        int top = stk[tt--];
                        int second = stk[tt];
                        if (dfn[lca] <= dfn[second]) vt[second].add(top);
                        else {
                            stk[++tt] = top;
                            break;
                        }
                    }
                    if (lca != stk[tt]) {
                        vt[lca].clear();
                        vt[lca].add(stk[tt--]);
                        stk[++tt] = lca;
                    }
                    stk[++tt] = v;
                }
                while (tt > 1) {
                    int v = stk[tt--];
                    vt[stk[tt]].add(v);
                }

                int rt = root;
                if (isNode[rt] != key && vt[rt].size() == 1) rt = vt[rt].get(0);
                dfs(rt, nodes.size(), key);
            }
            return res;
        }

        private void build(int u, int p) {
            dfn[u] = ts++;
            fa[u][0] = p;
            for (int v : adj[u]) {
                if (v == p) continue;
                dep[v] = dep[u] + 1;
                build(v, u);
            }
        }

        private int uptoDep(int u, int d) {
            int diff = dep[u] - d;
            while (diff > 0) {
                int k = Integer.numberOfTrailingZeros(diff);
                u = fa[u][k];
                diff &= diff - 1;
            }
            return u;
        }

        private int getLCA(int a, int b) {
            if (dep[a] > dep[b]) {
                int t = a;
                a = b;
                b = t;
            }
            b = uptoDep(b, dep[a]);
            if (a == b) return a;
            for (int i = mx - 1; i >= 0; i--) {
                if (fa[a][i] != fa[b][i]) {
                    a = fa[a][i];
                    b = fa[b][i];
                }
            }
            return fa[a][0];
        }

        private int dfs(int u, int tot, int val) {
            int size = isNode[u] == val ? 1 : 0;
            for (int v : vt[u]) {
                int sz = dfs(v, tot, val);
                int wt = dep[v] - dep[u];
                res += 1L * wt * sz * (tot - sz);
                size += sz;
            }
            return size;
        }
    }
}
/**
 * 从暴力枚举到虚树
 * 贡献法
 * 简单版本：所有点权相同 => 树上所有点对的距离之和 => O(n^2) => O(n)
 * 路径的基本单元：边，有多少路径会经过这些边，把每条边上经过路径的数量全部累加起来 = 所有路径长度之和 (换种角度去看)
 * 和暴力区别？更快的方式去计算有多少条路径经过同一条边。e.g. 1<>2 = 3 * 2 = 6 条路径经过这条边 (2,4,5) * (1,3) 乘法
 *       1
 *    2     3
 * 4    5
 * 数一数连通块大小，即子树节点个数 => 子树大小 dfs => 路径个数 = 子树大小 x (n - 子树大小)
 * 另一侧的节点个数 = 总节点个数 - 该子树节点个数
 * 如果节点的边权互不相同 => 对每个点权统计
 * 用整个树的点权个数 - 该子树的该点权的个数，原理是一样的
 * group <= 20 => 可以暴力枚举
 *
 * 虚树
 * O(n * U) U <= 20 what if U at the same scale of n? => O(n^2)
 * 压缩这棵树 => 合并后边权发生变化
 * 设计一个算法，去构建这棵压缩后的树，节点个数更少
 * 1.找到这些节点，相同编号放到一个列表里 [5,7,8],这些节点必须在压缩后的树里，
 * 还有哪些节点必须在这棵树里呢？必须要有支点! => 找到这几个节点的最小公共祖先LCA!
 * 必须把路径上拐弯的点放在这棵树上，才能得到对应的路径
 * 本质上路径 = 起点+终点+必须经过的拐点(LCA)
 * 最关键的问题：我怎么知道LCA在哪？
 * 节点的列表是乱序的，并不知道这些节点是按照什么顺序排布的 => 枚举所有点对的LCA => O(n^2)
 * 减少计算LCA的次数 => 如何快速找到所有拐弯的位置LCA(最关键的一步!)
 * 结论：有了合适的顺序，就可以快速求出LCA
 * 如果2个节点足够近，就能求出这两点的LCA => 找“比较近”的点对
 * 如何定义“比较近” => 遍历这棵树的过程中，有个遍历dfs顺序
 * 按照dfs访问顺序的时间戳给这些关键节点排个序 5 7 8 A B
 * 从5走到7，一定会走到5,7之间的LCA，以此类推
 * 关键：按照dfs序排序，排完序之后只要求出列表里相邻点对的LCA即可!
 * 所以，5 -> 8 的LCA一定在5,7和7，8之间的LCA之间
 * 2. 求出LCA之后再排序，就可以开始构建虚树
 * 按照什么顺序去连边？
 * 在节点的列表里，再按照访问顺序排个序，就按照相邻节点和它们的LCA连边
 * 方法: 找到上一个访问的点，找到当前点和上个点的LCA，将该LCA与该点连边!
 * 3. 边权：问树上2个点的距离，求出每个点的深度，把两者的深度相减，就是边权
 * 最关键的2个思想：
 * 1. 需要包含额外拐弯的lca
 * 2. 连边规则：找到当前节点上一个访问的点，在找到它们的lca，在当前点和lca之间连边
 * 最后在压缩后的虚树上计算每条边的贡献，拐弯点是额外加入的，不算在贡献的点对之中!
 * 对相同的 group[i] 构建 虚树，虚树上的相邻节点之间的边权，等于原树上这两点的最短距离。
 * 此时这条边的贡献为：边权乘以左右两个连通块的大小（节点个数）的乘积。
 */