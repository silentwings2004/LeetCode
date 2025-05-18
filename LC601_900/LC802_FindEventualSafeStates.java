package LC601_900;
import java.util.*;
public class LC802_FindEventualSafeStates {
    /**
     * We start at some node in a directed graph, and every turn, we walk along a directed edge of the graph. If we
     * reach a terminal node (that is, it has no outgoing directed edges), we stop.
     *
     * We define a starting node to be safe if we must eventually walk to a terminal node. More specifically, there is
     * a natural number k, so that we must have stopped at a terminal node in less than k steps for any choice of where
     * to walk.
     *
     * Return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.
     *
     * The directed graph has n nodes with labels from 0 to n - 1, where n is the length of graph. The graph is given
     * in the following form: graph[i] is a list of labels j such that (i, j) is a directed edge of the graph, going
     * from node i to node j.
     *
     * Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
     * Output: [2,4,5,6]
     *
     * Constraints:
     *
     * n == graph.length
     * 1 <= n <= 10^4
     * 0 <= graph[i].legnth <= n
     * graph[i] is sorted in a strictly increasing order.
     * The graph may contain self-loops.
     * The number of edges in the graph will be in the range [1, 4 * 104].
     * @param graph
     * @return
     */
    // S1: BFS
    // time = O(n + m), space = O(n + m)
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        int[] d = new int[n];
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (graph[i].length == 0) q.offer(i);
            else {
                for (int x : graph[i]) {
                    adj[x].add(i);
                    d[i]++;
                }
            }
        }

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : adj[u]) {
                if (--d[v] == 0) q.offer(v);
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (d[i] == 0) res.add(i);
        }
        return res;
    }

    // S2: DFS
    // time = O(V + E), space = O(V)
    public List<Integer> eventualSafeNodes2(int[][] graph) {
        List<Integer> res = new ArrayList<>();
        // corner case
        if (graph == null || graph.length == 0) return res;

        int n = graph.length;
        int[] status = new int[n];

        for (int i = 0; i < n; i++) {
            if (!dfs(graph, status, i)) res.add(i);
        }
        // graph[i] is sorted in a strictly increasing order => 这里不需要再sort了
        return res;
    }

    private boolean dfs(int[][] graph, int[] status, int cur) {
        if (status[cur] == 2) return false;
        if (status[cur] == 1) return true;

        status[cur] = 1;
        for (int next : graph[cur]) {
            if (dfs(graph, status, next)) return true;
        }
        status[cur] = 2;
        return false;
    }
}
/**
 * dfs: visited[i]
 * 0: never visited
 * 1: first-time visit
 * 2: permanently visit
 * 对无向图的话，最好的做法是用union find来判断是否有环！
 * S2: bfs
 * 拓扑排序的应用。最容易判定safe的节点，是那些出度为0的节点。将这些点剪除之后，接下来出度为0的节点，肯定还是safe的节点。
 * 以此BFS不断推进，如果还有剩下的节点，那么他们肯定出度都不为0，即是互相成环的，可以终止程序。
 *
 * 先去找到没有出边的点，这些都是安全的点
 * 再继续找，看当前哪些点没有出边 => 都是安全点，再删掉
 * 直到不能删为止，剩下的点一定是在环上 => 拓扑排序
 * 先得将所有边反向，这样出边变成入边，才能转化为一般的拓扑排序问题
 */