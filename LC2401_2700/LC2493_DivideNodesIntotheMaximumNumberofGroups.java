package LC2401_2700;
import java.util.*;
public class LC2493_DivideNodesIntotheMaximumNumberofGroups {
    /**
     * You are given a positive integer n representing the number of nodes in an undirected graph. The nodes are labeled
     * from 1 to n.
     *
     * You are also given a 2D integer array edges, where edges[i] = [ai, bi] indicates that there is a bidirectional
     * edge between nodes ai and bi. Notice that the given graph may be disconnected.
     *
     * Divide the nodes of the graph into m groups (1-indexed) such that:
     *
     * Each node in the graph belongs to exactly one group.
     * For every pair of nodes in the graph that are connected by an edge [ai, bi], if ai belongs to the group with
     * index x, and bi belongs to the group with index y, then |y - x| = 1.
     * Return the maximum number of groups (i.e., maximum m) into which you can divide the nodes. Return -1 if it is
     * impossible to group the nodes with the given conditions.
     *
     * Input: n = 6, edges = [[1,2],[1,4],[1,5],[2,6],[2,3],[4,6]]
     * Output: 4
     *
     * Input: n = 3, edges = [[1,2],[2,3],[3,1]]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= n <= 500
     * 1 <= edges.length <= 10^4
     * edges[i].length == 2
     * 1 <= ai, bi <= n
     * ai != bi
     * There is at most one edge between any pair of vertices.
     * @param n
     * @param edges
     * @return
     */
    // time = O(n^2), space = O(n)
    List<Integer>[] graph;
    int[] p;
    int n;
    public int magnificentSets(int n, int[][] edges) {
        this.n = n;
        graph = new List[n];
        p = new int[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            p[i] = i;
        }
        for (int[] x : edges) {
            int a = x[0] - 1, b = x[1] - 1;
            graph[a].add(b);
            graph[b].add(a);
            if (find(a) != find(b)) p[find(a)] = find(b);
        }

        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int fa = find(i);
            map.putIfAbsent(fa, new ArrayList<>());
            map.get(fa).add(i);
        }

        int res = 0;
        for (int x : map.keySet()) {
            int y = bfs(map.get(x));
            if (y == -1) return -1;
            else res += y;
        }
        return res;
    }

    private int bfs(List<Integer> nodes) {
        int res = 0;
        for (int start : nodes) {
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(start);
            int[] dist = new int[n];
            Arrays.fill(dist, -1);
            dist[start] = 1;

            while (!queue.isEmpty()) {
                int t = queue.poll();
                res = Math.max(res, dist[t]);
                for (int next : graph[t]) {
                    if (dist[next] == -1) {
                        queue.offer(next);
                        dist[next] = dist[t] + 1;
                    } else if (dist[next] != -1 && Math.abs(dist[next] - dist[t]) != 1) return -1;
                }
            }
        }
        return res;
    }

    private int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }

    // S2
    // time = O(V * E) = O(n * m), space = O(n)
    public int magnificentSets2(int n, int[][] edges) {
        List<Integer>[] g = new List[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0] - 1, b = e[1] - 1;
            g[a].add(b);
            g[b].add(a);
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int start = 0; start < n; start++) {
            int maxDepth = 0, smallestId = 510;
            Queue<int[]> queue = new LinkedList<>();
            int[] level = new int[n];
            queue.offer(new int[]{start, 1});
            level[start] = 1;

            while (!queue.isEmpty()) {
                int[] t = queue.poll();
                int cur = t[0], d = t[1];
                maxDepth = Math.max(maxDepth, d);
                smallestId = Math.min(smallestId, cur);

                for (int next : g[cur]) {
                    if (level[next] == 0) {
                        level[next] = d + 1;
                        queue.offer(new int[]{next, d + 1});
                    } else if (level[next] == d) return -1;
                }
            }
            map.put(smallestId, Math.max(map.getOrDefault(smallestId, 0), maxDepth));
        }

        int res = 0;
        for (int v : map.values()) res += v;
        return res;
    }
}
/**
 *
 */