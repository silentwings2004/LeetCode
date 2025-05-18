package LC2700_3000;
import java.util.*;
public class LC2876_CountVisitedNodesinaDirectedGraph {
    /**
     * There is a directed graph consisting of n nodes numbered from 0 to n - 1 and n directed edges.
     *
     * You are given a 0-indexed array edges where edges[i] indicates that there is an edge from node i to node edges[i].
     *
     * Consider the following process on the graph:
     *
     * You start from a node x and keep visiting other nodes through edges until you reach a node that you have already
     * visited before on this same process.
     * Return an array answer where answer[i] is the number of different nodes that you will visit if you perform the
     * process starting from node i.
     *
     * Input: edges = [1,2,0,0]
     * Output: [3,3,3,4]
     *
     * Input: edges = [1,2,3,4,0]
     * Output: [5,5,5,5,5]
     *
     * Constraints:
     *
     * n == edges.length
     * 2 <= n <= 10^5
     * 0 <= edges[i] <= n - 1
     * edges[i] != i
     * @param edges
     * @return
     */
    // time = O(n), space = O(n)
    final int N = 100010;
    int[] h, e, ne;
    int idx;
    boolean[] st;
    int[] in_stk, res, q, d;
    HashSet<Integer> set;
    public int[] countVisitedNodes(List<Integer> edges) {
        h = new int[N];
        e = new int[N];
        ne = new int[N];
        in_stk = new int[N];
        q = new int[N];
        d = new int[N];
        st = new boolean[N];
        set = new HashSet<>();
        Arrays.fill(h, -1);
        idx = 0;

        int n = edges.size();
        for (int i = 0; i < n; i++) {
            set.add(i);
            add(i, edges.get(i));
            d[edges.get(i)]++;
        }

        int hh = 0, tt = -1;
        for (int i = 0; i < n; i++) {
            if (d[i] == 0) q[++tt] = i;
        }
        int[] bd = d.clone();
        while (hh <= tt) {
            int t = q[hh++];
            set.remove(t);
            for (int i = h[t]; i != -1; i = ne[i]) {
                int j = e[i];
                if (--bd[j] == 0) q[++tt] = j;
            }
        }
        res = new int[n];
        for (int i = 0; i < n; i++) {
            if (d[i] == 0) dfs(i, 1);
        }
        for (int i = 0; i < n; i++) {
            if (!st[i]) dfs(i, 1);
        }
        return res;
    }

    private int dfs(int u, int depth) {
        st[u] = true;
        in_stk[u] = depth;

        int t = 1;
        for (int i = h[u]; i != -1; i = ne[i]) {
            int j = e[i];
            if (!st[j]) {
                t += dfs(j, depth + 1);
                if (set.contains(u)) t--;
            }
            else if (in_stk[j] > 0) {
                if (res[j] > 0) t += res[j];
                else {
                    int len = depth + 1 - in_stk[j];
                    t += len - 1;
                }
            }
        }
        res[u] = t;
        return t;
    }

    private void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    // S2: reverse graph
    // time = O(n), space = O(n)
    public int[] countVisitedNodes2(List<Integer> edges) {
        int n = edges.size();
        List<Integer>[] rdj = new List[n];
        for (int i = 0; i < n; i++) rdj[i] = new ArrayList<>();
        int[] d = new int[n];
        for (int i = 0; i < n; i++) {
            int a = i, b = edges.get(i);
            rdj[b].add(a); // reverse graph
            d[b]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (d[i] == 0) q.offer(i);
        }

        while (!q.isEmpty()) {
            int t = q.poll();
            int j = edges.get(t);
            if (--d[j] == 0) q.offer(j);
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            if (d[i] <= 0) continue;
            List<Integer> ring = new ArrayList<>();
            for (int x = i;; x = edges.get(x)) {
                ring.add(x);
                d[x] = -1;
                if (edges.get(x) == i) break;
            }
            int m = ring.size();
            for (int r : ring) rdfs(r, m, rdj, d, res);
        }
        return res;
    }

    private void rdfs(int u, int depth, List<Integer>[] rdj, int[] d, int[] res) {
        res[u] = depth;
        for (int j : rdj[u]) {
            if (d[j] == 0) rdfs(j, depth + 1, rdj, d, res);
        }
    }
}
/**
 * 基环树 (内向基环树)
 * 每个点只有一条出去的边，但有可能多条入边
 * 找到环，然后从环上的点出发，倒着走
 * 有可能有多个环
 * 如果是一般有向图？ n 个点 m 条边
 * SCC 强连通分量 Tarjan 缩点 O(n + m)
 * DAG 的反图
 */