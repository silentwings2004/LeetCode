package LC2101_2400;
import java.util.*;
public class LC2359_FindClosestNodetoGivenTwoNodes {
    /**
     * You are given a directed graph of n nodes numbered from 0 to n - 1, where each node has at most one outgoing edge.
     *
     * The graph is represented with a given 0-indexed array edges of size n, indicating that there is a directed edge
     * from node i to node edges[i]. If there is no outgoing edge from i, then edges[i] == -1.
     *
     * You are also given two integers node1 and node2.
     *
     * Return the index of the node that can be reached from both node1 and node2, such that the maximum between the
     * distance from node1 to that node, and from node2 to that node is minimized. If there are multiple answers,
     * return the node with the smallest index, and if no possible answer exists, return -1.
     *
     * Note that edges may contain cycles.
     *
     * Input: edges = [2,2,3,-1], node1 = 0, node2 = 1
     * Output: 2
     *
     * Input: edges = [1,2,-1], node1 = 0, node2 = 2
     * Output: 2
     *
     * Constraints:
     *
     * n == edges.length
     * 2 <= n <= 10^5
     * -1 <= edges[i] < n
     * edges[i] != i
     * 0 <= node1, node2 < n
     */
    // S1: bfs
    // time = O(n), space = O(n)
    List<Integer>[] graph;
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int n = edges.length;
        graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (edges[i] != -1) graph[i].add(edges[i]);
        }

        HashMap<Integer, Integer> map1 = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();

        bfs(node1, map1);
        bfs(node2, map2);

        int res = Integer.MAX_VALUE, node = -1;
        for (int k : map1.keySet()) {
            if (map2.containsKey(k)) {
                int maxv = Math.max(map1.get(k), map2.get(k));
                if (res > maxv) {
                    res = maxv;
                    node = k;
                }
            }
        }
        return node;
    }

    private void bfs(int u, HashMap<Integer, Integer> map) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(u);

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();
                map.put(cur, step);

                for (int next : graph[cur]) {
                    if (map.containsKey(next)) continue;
                    queue.offer(next);
                }
            }
            step++;
        }
    }

    // S2
    // time = O(n), space = O(n)
    public int closestMeetingNode2(int[] edges, int node1, int node2) {
        int n = edges.length, x = node1, y = node2;
        int[] d1 = new int[n], d2 = new int[n];
        Arrays.fill(d1, -1);
        Arrays.fill(d2, -1);
        d1[x] = d2[y] = 0;

        while (edges[x] != -1) {
            if (d1[edges[x]] != -1) break;
            d1[edges[x]] = d1[x] + 1;
            x = edges[x];
        }

        while (edges[y] != -1) {
            if (d2[edges[y]] != -1) break;
            d2[edges[y]] = d2[y] + 1;
            y = edges[y];
        }

        int res = -1, id = -1;
        for (int i = 0; i < n; i++) {
            int a = d1[i], b = d2[i];
            if (a != -1 && b != -1) {
                if (res == -1 || res > Math.max(a, b)) {
                    res = Math.max(a, b);
                    id = i;
                }
            }
        }
        return id;
    }
}
/**
 * 基环树模型
 * 有向图：每个点都有一条唯一的出边
 * 基环树：一个环上挂了一堆树
 * 无向图：n个点，n条边的连通图 -> 基环树
 * 直接写个bfs即可
 * 所有边的权重都是1
 * 不需要写bfs，每个点走到另外一个点的路径都是唯一的
 * 有向的基环树路径唯一，无向图则不唯一
 * 直接遍历即可，走到不能走为止
 * 找到x和y能走到的所有点和距离
 * 再枚举所有点，再判断下当前这个点能否被同时走到
 * 基环树的终极形式：仙人掌
 */