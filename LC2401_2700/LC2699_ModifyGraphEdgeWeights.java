package LC2401_2700;
import java.util.*;
public class LC2699_ModifyGraphEdgeWeights {
    /**
     * You are given an undirected weighted connected graph containing n nodes labeled from 0 to n - 1, and an integer
     * array edges where edges[i] = [ai, bi, wi] indicates that there is an edge between nodes ai and bi with weight wi.
     *
     * Some edges have a weight of -1 (wi = -1), while others have a positive weight (wi > 0).
     *
     * Your task is to modify all edges with a weight of -1 by assigning them positive integer values in the range
     * [1, 2 * 109] so that the shortest distance between the nodes source and destination becomes equal to an integer
     * target. If there are multiple modifications that make the shortest distance between source and destination equal
     * to target, any of them will be considered correct.
     *
     * Return an array containing all edges (even unmodified ones) in any order if it is possible to make the shortest
     * distance from source to destination equal to target, or an empty array if it's impossible.
     *
     * Note: You are not allowed to modify the weights of edges with initial positive weights.
     *
     * Input: n = 5, edges = [[4,1,-1],[2,0,-1],[0,3,-1],[4,3,-1]], source = 0, destination = 1, target = 5
     * Output: [[4,1,1],[2,0,1],[0,3,3],[4,3,1]]
     *
     * Input: n = 3, edges = [[0,1,-1],[0,2,5]], source = 0, destination = 2, target = 6
     * Output: []
     *
     * Input: n = 4, edges = [[1,0,4],[1,2,3],[2,3,5],[0,3,-1]], source = 0, destination = 2, target = 6
     * Output: [[1,0,4],[1,2,3],[2,3,5],[0,3,1]]
     *
     * Constraints:
     *
     * 1 <= n <= 100
     * 1 <= edges.length <= n * (n - 1) / 2
     * edges[i].length == 3
     * 0 <= ai, bi < n
     * wi = -1 or 1 <= wi <= 10^7
     * ai != bi
     * 0 <= source, destination < n
     * source != destination
     * 1 <= target <= 10^9
     * The graph is connected, and there are no self-loops or repeated edges
     * @param n
     * @param edges
     * @param source
     * @param destination
     * @param target
     * @return
     */
    // S1: spfa
    // time = O(n^2*m), space = O(n + m)
    final int N = 110, M = 2 * N * N, INF = 0x3f3f3f3f;
    int[] h, e, ne, w;
    int idx;
    int[] dist;
    boolean[] st;
    List<Integer> ids;
    public int[][] modifiedGraphEdges(int n, int[][] edges, int source, int destination, int target) {
        h = new int[N];
        e = new int[M];
        ne = new int[M];
        w = new int[M];
        dist = new int[N];
        st = new boolean[N];
        ids = new ArrayList<>();
        Arrays.fill(h, -1);
        idx = 0;

        for (int i = 0; i < edges.length; i++) {
            int a = edges[i][0], b = edges[i][1], c = edges[i][2];
            if (c < 0) ids.add(i);
            else {
                add(a, b, c);
                add(b, a, c);
            }
        }

        int t = spfa(source, destination);
        if (t < target) return new int[0][0];
        else if (t == target) {
            for (int[] edge : edges) {
                if (edge[2] < 0) edge[2] = INF;
            }
            return edges;
        }

        for (int i = 0; i < ids.size(); i++) {
            int idx = ids.get(i);
            int a = edges[idx][0], b = edges[idx][1];
            add(a, b, 1);
            add(b, a, 1);
            t = spfa(source, destination);
            if (t > target) continue;
            if (t <= target) {
                for (int j = 0; j < edges.length; j++) {
                    if (edges[j][2] == -1) {
                        if (j < idx) edges[j][2] = 1;
                        else if (j == idx) edges[j][2] = target - t + 1;
                        else edges[j][2] = INF;
                    }
                }
                return edges;
            }
        }
        return new int[0][0];
    }

    private int spfa(int src, int dst) {
        Arrays.fill(dist, INF);
        Arrays.fill(st, false);
        dist[src] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(src);
        st[src] = true;

        while (!q.isEmpty()) {
            int t = q.poll();
            st[t] = false;

            for (int i = h[t]; i != -1; i = ne[i]) {
                int j = e[i];
                if (dist[j] > dist[t] + w[i]) {
                    dist[j] = dist[t] + w[i];
                    if (!st[j]) {
                        q.offer(j);
                        st[j] = true;
                    }
                }
            }
        }
        return dist[dst];
    }

    private void add(int a, int b, int c) {
        e[idx] = b;
        w[idx] = c;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    // S2
    // time = O(n^2 + m), space = O(n^2)
    class Solution {
        final int N = 110, INF = Integer.MAX_VALUE;
        HashMap<Integer, Integer>[] map;
        boolean[][] todo;
        public int[][] modifiedGraphEdges(int n, int[][] edges, int source, int destination, int target) {
            map = new HashMap[N];
            todo = new boolean[N][N];
            for (int i = 0; i < N; i++) map[i] = new HashMap<>();
            for (int[] e : edges) {
                int a = e[0], b = e[1], c = e[2];
                if (c == -1) {
                    c = 1;
                    todo[a][b] = true;
                    todo[b][a] = true;
                }
                map[a].put(b, c);
                map[b].put(a, c);
            }

            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
            pq.offer(new int[]{0, destination});
            int[] dist1 = new int[N];
            Arrays.fill(dist1, INF / 3);

            while (!pq.isEmpty()) {
                int[] t = pq.poll();
                int d = t[0], cur = t[1];
                if (dist1[cur] != INF / 3) continue;
                dist1[cur] = d;
                for (int next: map[cur].keySet()) {
                    int weight = map[cur].get(next);
                    if (dist1[next] != INF / 3) continue;
                    pq.offer(new int[]{d + weight, next});
                }
            }

            int[] dist = new int[N];
            Arrays.fill(dist, INF / 3);
            pq.offer(new int[]{0, source});
            while (!pq.isEmpty()) {
                int[] t = pq.poll();
                int d = t[0], cur = t[1];
                if (dist[cur] != INF / 3) continue;
                dist[cur] = d;
                if (cur == destination && d != target) return new int[0][0];
                for (int next : map[cur].keySet()) {
                    int weight = map[cur].get(next);
                    if (dist[next] != INF / 3) continue;
                    if (todo[cur][next] && dist[cur] + weight + dist1[next] < target) {
                        weight = target - dist[cur] - dist1[next];
                        map[cur].put(next, weight);
                        map[next].put(cur, weight);
                    }
                    pq.offer(new int[]{d + weight, next});
                }
            }

            for (int[] e : edges) {
                int a = e[0], b = e[1], c = e[2];
                e[2] = map[a].get(b);
            }
            return edges;
        }
    }
}
/**
 * 因为最终修正边权之后的图里要求所有的边都是正数，所以我们第一步肯定先将所有能修改的边从-1改为为最小的正数值1放入图中。
 * 最暴力的思想就是不停地跑Dijkstra求起点到终点的最短距离。
 * 如果当前的最短距离已经大于target，那么无解。
 * 如果当前的最短距离就是target，那么我们就不需要改动。
 * 如果当前的最短距离小于target，且最短距离里不包括任何可修改的边，那么也是无解。
 * 剩下的情况就是最短距离小于target，且其中包含了至少一条可修改的边，那么我们可以贪心地将该边权调大，使得路径恰为target。
 * 这样我们就消灭了一条小于target的路径。然后重复以上的过程。
 * 这样的算法可能会跑O(E)遍的Dijkstra，会TLE。
 * 注意当我们每次从PQ里弹出一个已经确定最短距离的的点，会尝试通过其邻接的边将一个新点加入PQ，
 * 如果我们所用到的所有的边都是不可修改的，那么我们弹出的点及其最短路径也都是不可修改的。
 * 但是当我们需要用到一条可修改的边时，比如说已知从起点到a的最短路径，然后a与b有一条可修改的边，此时我们在将b加入PQ时就会有所顾虑。
 * 如果“起点到a的最短距离”+“ab之间的边权1”+“b到终点的最短距离”小于target的话，那么我们就违反了题意。
 * 所以我们可以贪心地更改这条可修改边，使得三段距离之和变成target。
 * 这就意味着我们需要提前计算“b到终点的最短距离”。
 * 这样，当b收录进入PQ的时候，我们就保证了这条到达b的路径，不会造成任何“起点到终点的最短路径小于target”，我们可以放心地加入PQ共后续使用。
 * 所以依据上面的算法，可以在一次的Dijkstra的过程中不断地贪心地设置可修改边的边权。
 * 知道我们发现终点从PQ里弹出时，意味着我们已经确定了起点到终点的最短距离。
 * 如果这个距离不为target，那么就是无解。
 */