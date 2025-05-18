package LC601_900;
import java.util.*;
public class LC847_ShortestPathVisitingAllNodes {
    /**
     * You have an undirected, connected graph of n nodes labeled from 0 to n - 1. You are given an array graph where
     * graph[i] is a list of all the nodes connected with node i by an edge.
     *
     * Return the length of the shortest path that visits every node. You may start and stop at any node, you may
     * revisit nodes multiple times, and you may reuse edges.
     *
     * Input: graph = [[1,2,3],[0],[0],[0]]
     * Output: 4
     *
     * Constraints:
     *
     * n == graph.length
     * 1 <= n <= 12
     * 0 <= graph[i].length < n
     * graph[i] does not contain i.
     * If graph[a] contains b, then graph[b] contains a.
     * The input graph is always connected.
     * @param graph
     * @return
     */
    // time = O(n^2*2^n), space = O(n*2^n)时间枚举进行状态转移
    // 状态的总数为O(n*2^n),对于每一个状态，我们需要O(n)
    final int INF = (int)1e8;
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        int[][] f = new int[1 << n][n];
        for (int i = 0; i < 1 << n; i++) Arrays.fill(f[i], INF);
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            f[1 << i][i] = 0;
            q.offer(new int[]{1 << i, i});
        }

        while (!q.isEmpty()) {
            int[] t = q.poll();
            int s = t[0], cur = t[1];
            for (int next : graph[cur]) {
                int ns = s | 1 << next;
                if (f[ns][next] > f[s][cur] + 1) {
                    f[ns][next] = f[s][cur] + 1;
                    q.offer(new int[]{ns, next});
                }
            }
        }

        int res = INF;
        for (int i = 0; i < n; i++) res = Math.min(res, f[(1 << n) - 1][i]);
        return res;
    }
}
/**
 * 图的问题
 * 任意选一个点作为起点
 * 把整个图遍历一遍，最短路径bfs
 * 0 -> 1 -> 2
 *        -> 3
 *        -> 4
 *   -> 2 -> 1 (重复，无限循环！）
 *   -> 3
 * 把所有结点走过一遍的话，有些点和边不得不多次走过
 * 但也不能简单的不去重，会造成无限循环
 * 两次2，状态是不一样的
 * C - A - B
 *     |
 *     D
 * 结点 + 状态
 * 状态怎么用一个整数来表示呢？状态压缩
 * 1 <= n <= 12，用一个32位bit即可表示，ref: LC864
 * 没访问过的状态是0，访问过的用1表示
 * 状态的个数可以提前知道，就那么多，所以可以用数组而不是set
 *
 * f(i,j):走过的点集为i，且最后处于点j的所有路径中长度的最小值
 * f(i | 1 << k, k) <- f(i,j) + 1
 * 每个点可以重复走
 * 如果j和k都属于i，状态的方式之间存在一个循环依赖
 * dp能做，必须是拓扑图 => 这里不能用
 * 看成是一种最短路
 * 每个状态看是一个点
 */