package LC3301_3600;
import java.util.*;
public class LC3530_MaximumProfitfromValidTopologicalOrderinDAG {
    /**
     * You are given a Directed Acyclic Graph (DAG) with n nodes labeled from 0 to n - 1, represented by a 2D array
     * edges, where edges[i] = [ui, vi] indicates a directed edge from node ui to vi. Each node has an associated score
     * given in an array score, where score[i] represents the score of node i.
     *
     * You must process the nodes in a valid topological order. Each node is assigned a 1-based position in the
     * processing order.
     *
     * The profit is calculated by summing up the product of each node's score and its position in the ordering.
     *
     * Return the maximum possible profit achievable with an optimal topological order.
     *
     * A topological order of a DAG is a linear ordering of its nodes such that for every directed edge u → v, node u
     * comes before v in the ordering.
     *
     * Input: n = 2, edges = [[0,1]], score = [2,3]
     * Output: 8
     *
     * Input: n = 3, edges = [[0,1],[0,2]], score = [1,6,3]
     * Output: 25
     *
     * Constraints:
     *
     * 1 <= n == score.length <= 22
     * 1 <= score[i] <= 10^5
     * 0 <= edges.length <= n * (n - 1) / 2
     * edges[i] == [ui, vi] denotes a directed edge from ui to vi.
     * 0 <= ui, vi < n
     * ui != vi
     * The input graph is guaranteed to be a DAG.
     * There are no duplicate edges.
     * @param n
     * @param edges
     * @param score
     * @return
     */
    // S1: 状压 DP
    // time = O(2^n * n), space = O(2^n)
    public int maxProfit(int n, int[][] edges, int[] score) {
        int[] g = new int[n];
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            g[v] |= 1 << u;
        }

        long[] f = new long[1 << n];
        Arrays.fill(f, -1);
        f[0] = 0;

        for (int i = 0; i < 1 << n; i++) {
            if (f[i] == -1) continue;
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) == 0) {
                    if ((i & g[j]) == g[j]) {
                        int ns = i | 1 << j;
                        long v = f[i] + 1L * score[j] * (Integer.bitCount(i) + 1);
                        f[ns] = Math.max(f[ns], v);
                    }
                }
            }
        }
        return (int)f[(1 << n) - 1];
    }

    // S2: dfs
    // time = O(m + n * 2^n), space = O(2^n)
    int[] pre, f, score;
    public int maxProfit2(int n, int[][] edges, int[] score) {
        if (edges.length == 0) {
            Arrays.sort(score);
            int res = 0;
            for (int i = 0; i < n; i++) res += score[i] * (i + 1);
            return res;
        }

        this.score = score;
        pre = new int[n];
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            pre[b] |= 1 << a;
        }

        f = new int[1 << n];
        return dfs(0);
    }

    private int dfs(int state) {
        if (f[state] > 0) return f[state];
        int res = 0, i = Integer.bitCount(state);
        for (int j = 0; j < pre.length; j++) {
            if ((state >> j & 1) == 0 && (state | pre[j]) == state) {
                res = Math.max(res, dfs(state | 1 << j) + score[j] * (i + 1));
            }
        }
        return f[state] = res;
    }
}
/**
 * 关键思路：把拓扑序理解为先修课关系，在学习课程 j 之前，j 的所有先修课（直接前驱）必须全部学完
 * 定义 dfs(S) 表示在已学课程集合为 S 的情况下，学完剩余未学课程可以获得的最大利润
 * 考虑下一门课程学哪个：
 * 枚举下一门要学习的课程为 j=0,1,2,…,n−1，要求满足 j ∈ S 且 pre[j]⊆S。其中 pre[j] 表示 j 的先修课集合
 * 要解决的问题变成：在已学课程集合为 S ∪ {j} 的情况下，学完剩余未学课程可以获得的最大利润，即 dfs(S ∪ {j})
 * 取最大值
 * 优化：如果 edges 是空的（没有边），那么根据 排序不等式，按照 score 从小到大选课是最优的。
 * 所以把 score 从小到大排序后，累加 score[i]⋅(i+1)，即为答案。
 *
 */