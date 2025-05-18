package LC2401_2700;
import java.util.*;
public class LC2473_MinimumCosttoBuyApples {
    /**
     * You are given a positive integer n representing n cities numbered from 1 to n. You are also given a 2D array
     * roads, where roads[i] = [ai, bi, costi] indicates that there is a bidirectional road between cities ai and bi
     * with a cost of traveling equal to costi.
     *
     * You can buy apples in any city you want, but some cities have different costs to buy apples. You are given the
     * array appleCost where appleCost[i] is the cost of buying one apple from city i.
     *
     * You start at some city, traverse through various roads, and eventually buy exactly one apple from any city. After
     * you buy that apple, you have to return back to the city you started at, but now the cost of all the roads will
     * be multiplied by a given factor k.
     *
     * Given the integer k, return an array answer of size n where answer[i] is the minimum total cost to buy an apple
     * if you start at city i.
     *
     * Input: n = 4, roads = [[1,2,4],[2,3,2],[2,4,5],[3,4,1],[1,3,4]], appleCost = [56,42,102,301], k = 2
     * Output: [54,42,48,51]
     *
     * Input: n = 3, roads = [[1,2,5],[2,3,1],[3,1,2]], appleCost = [2,3,1], k = 3
     * Output: [2,3,1]
     *
     * Constraints:
     *
     * 2 <= n <= 1000
     * 1 <= roads.length <= 1000
     * 1 <= ai, bi <= n
     * ai != bi
     * 1 <= costi <= 10^5
     * appleCost.length == n
     * 1 <= appleCost[i] <= 10^5
     * 1 <= k <= 100
     * There are no repeated edges.
     * @param n
     * @param roads
     * @param appleCost
     * @param k
     * @return
     */
    // time = O(n^2*longn), space = O(n)
    List<int[]>[] graph;
    int n;
    final long INF = Long.MAX_VALUE;
    public long[] minCost(int n, int[][] roads, int[] appleCost, int k) {
        this.n = n;
        graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] x : roads) {
            int a = x[0] - 1, b = x[1] - 1, c = x[2];
            graph[a].add(new int[]{b, c});
            graph[b].add(new int[]{a, c});
        }

        long[] res = new long[n];
        for (int i = 0; i < n; i++) {
            res[i] = dijkstra(i, appleCost, k);
        }
        return res;
    }

    private long dijkstra(int u, int[] w, int k) {
        PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1[0], o2[0]));
        pq.offer(new long[]{w[u], 0, u});
        long[] st = new long[n];
        Arrays.fill(st, INF);

        long res = INF;
        while (!pq.isEmpty()) {
            long[] t = pq.poll();
            long cost = t[0], path = t[1];
            int cur = (int) t[2];
            if (cost >= st[cur]) continue;
            st[cur] = cost;
            res = Math.min(res, cost);

            for (int[] next : graph[cur]) {
                int id = next[0], weight = next[1];
                if ((path + weight) * (k + 1) >= res) continue;
                pq.offer(new long[]{(long) w[id] + (path + weight) * (k + 1), path + weight, id});
            }
        }
        return res;
    }
}