package LC3601_3900;
import java.util.*;
public class LC3812_MinimumEdgeTogglesonaTree {
    /**
     * You are given an undirected tree with n nodes, numbered from 0 to n - 1. It is represented by a 2D integer array
     * edges of length n - 1, where edges[i] = [ai, bi] indicates that there is an edge
     * between nodes ai and bi in the tree.
     *
     * You are also given two binary strings start and target of length n. For each node x, start[x] is its initial
     * color and target[x] is its desired color.
     *
     * In one operation, you may pick an edge with index i and toggle both of its endpoints. That is, if the edge is
     * [u, v], then the colors of nodes u and v each flip from '0' to '1' or from '1' to '0'.
     *
     * Return an array of edge indices whose operations transform start into target. Among all valid sequences with
     * minimum possible length, return the edge indices in increasing order.
     *
     * If it is impossible to transform start into target, return an array containing a single element equal to -1.
     *
     * Input: n = 3, edges = [[0,1],[1,2]], start = "010", target = "100"
     * Output: [0]
     *
     * Input: n = 7, edges = [[0,1],[1,2],[2,3],[3,4],[3,5],[1,6]], start = "0011000", target = "0010001"
     * Output: [1,2,5]
     *
     * Input: n = 2, edges = [[0,1]], start = "00", target = "01"
     * Output: [-1]
     *
     * Constraints:
     *
     * 2 <= n == start.length == target.length <= 10^5
     * edges.length == n - 1
     * edges[i] = [ai, bi]
     * 0 <= ai, bi < n
     * start[i] is either '0' or '1'.
     * target[i] is either '0' or '1'.
     * The input is generated such that edges represents a valid tree.
     * @param n
     * @param edges
     * @param start
     * @param target
     * @return
     */
    // time = O(n), space = O(n)
    List<int[]>[] adj;
    int[] w;
    List<Integer> res;
    public List<Integer> minimumFlips(int n, int[][] edges, String start, String target) {
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        int m = edges.length;
        for (int i = 0; i < m; i++) {
            int a = edges[i][0], b = edges[i][1];
            adj[a].add(new int[]{b, i});
            adj[b].add(new int[]{a, i});
        }
        w = new int[n];
        int s = 0;
        for (int i = 0; i < n; i++) {
            int a = start.charAt(i) - '0';
            int b = target.charAt(i) - '0';
            w[i] = a ^ b;
            s += w[i];
        }
        if (s % 2 == 1) return Arrays.asList(-1);
        res = new ArrayList<>();
        dfs(0, -1);
        Collections.sort(res);
        return res;
    }

    private void dfs(int u, int fa) {
        for (int[] x : adj[u]) {
            int v = x[0], idx = x[1];
            if (v == fa) continue;
            dfs(v, u);
            if (w[v] == 1) {
                res.add(idx);
                w[u] ^= 1;
            }
        }
    }
}
/**
 * 操作方案是唯一确定的，并不存在多种解决方案
 * 从左到右，如果这个点已经等于target，不翻转，否则翻转
 * 当只剩下一个点需要变的话，就无解
 * 考虑树的话，先从叶子节点出发，如果叶子不用改，则不需要翻转，否则一定要翻转
 * 先把所有叶子都变成target，把叶子全部去掉，类似剥洋葱 -> 拓扑排序
 * 从叶子出发，先找叶子，就能决定叶子到父节点这条边需不需要翻转
 * 每条边是否要翻转，与我从哪个点开始递归无关
 * 从哪个点开始递归，不影响每条边是否要翻转这个结论
 */