package LC2401_2700;
import java.util.*;
public class LC2603_CollectCoinsinaTree {
    /**
     * There exists an undirected and unrooted tree with n nodes indexed from 0 to n - 1. You are given an integer n
     * and a 2D integer array edges of length n - 1, where edges[i] = [ai, bi] indicates that there is an edge between
     * nodes ai and bi in the tree. You are also given an array coins of size n where coins[i] can be either 0 or 1,
     * where 1 indicates the presence of a coin in the vertex i.
     *
     * Initially, you choose to start at any vertex in the tree. Then, you can perform the following operations any
     * number of times:
     *
     * Collect all the coins that are at a distance of at most 2 from the current vertex, or
     * Move to any adjacent vertex in the tree.
     * Find the minimum number of edges you need to go through to collect all the coins and go back to the initial
     * vertex.
     *
     * Note that if you pass an edge several times, you need to count it into the answer several times.
     *
     * Input: coins = [1,0,0,0,0,1], edges = [[0,1],[1,2],[2,3],[3,4],[4,5]]
     * Output: 2
     *
     * Input: coins = [0,0,0,1,1,0,0,1], edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[5,6],[5,7]]
     * Output: 2
     *
     * Constraints:
     *
     * n == coins.length
     * 1 <= n <= 3 * 10^4
     * 0 <= coins[i] <= 1
     * edges.length == n - 1
     * edges[i].length == 2
     * 0 <= ai, bi < n
     * ai != bi
     * edges represents a valid tree.
     * @param coins
     * @param edges
     * @return
     */
    // time = O(n), space = O(n)
    final int N = 30010, M = N * 2;
    int n, m;
    int[] h, e, ne;
    int idx;
    int[] d, q, dist;
    public int collectTheCoins(int[] coins, int[][] edges) {
        n = coins.length;
        m = edges.length;
        h = new int[N];
        e = new int[M];
        ne = new int[M];
        q = new int[N];
        dist = new int[N];
        Arrays.fill(h, -1);
        idx = 0;

        for (int[] e : edges) {
            int a = e[0], b = e[1];
            add(a, b);
            add(b, a);
            d[a]++;
            d[b]++;
        }

        int hh = 0, tt = -1;
        for (int i = 0; i < n; i++) {
            if (d[i] == 1 && coins[i] == 0) q[++tt] = i;
        }

        while (hh <= tt) {
            int t = q[hh++];
            for (int i = h[t]; i != -1; i = ne[i]) {
                int j = e[i];
                if (--d[j] == 1 && coins[j] == 0) q[++tt] = j;
            }
        }

        hh = 0;
        tt = -1;
        for (int i = 0; i < n; i++) {
            if (d[i] == 1 && coins[i] == 1) q[++tt] = i;
        }
        if (hh == tt) return 0;

        while (hh <= tt) {
            int t = q[hh++];
            for (int i = h[t]; i != -1; i = ne[i]) {
                int j = e[i];
                if (--d[j] == 1) {
                    dist[j] = dist[t] + 1;
                    q[++tt] = j;
                }
            }
        }

        int res = 0;
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            if (dist[a] >= 2 && dist[b] >= 2) res += 2;
        }
        return res;
    }

    private void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    // S1.2
    // time = O(n), space = O(n)
    public int collectTheCoins2(int[] coins, int[][] edges) {
        int n = coins.length;
        HashSet<Integer>[] next = new HashSet[n];
        for (int i = 0; i < n; i++) next[i] = new HashSet<>();
        int[] degree = new int[n];
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            next[a].add(b);
            next[b].add(a);
            degree[a]++;
            degree[b]++;
        }

        boolean[] deleted = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1 && coins[i] == 0) q.offer(i);
        }

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int t = q.poll();
                deleted[t] = true;
                for (int nxt : next[t]) {
                    degree[nxt]--;
                    next[nxt].remove(t);
                    if (degree[nxt] == 1 && coins[nxt] == 0) {
                        q.offer(nxt);
                    }
                }
            }
        }

        int[] depth = new int[n];
        Arrays.fill(depth, -1);
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1 && !deleted[i]) {
                q.offer(i);
                depth[i] = 1;
            }
        }

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int t = q.poll();
                for (int nxt : next[t]) {
                    degree[nxt]--;
                    next[nxt].remove(t);
                    depth[nxt] = Math.max(depth[nxt], depth[t] + 1);
                    if (degree[nxt] == 1) q.offer(nxt);
                }
            }
        }

        int m = 0;
        for (int i = 0; i < n; i++) {
            m += depth[i] >= 3 ? 1 : 0;
        }

        return m >= 1 ? (m - 1) * 2 : 0;
    }
}
/**
 * 边缘的金币是瓶颈
 * 把无关紧要的节点砍掉，整个支路都可以砍掉
 * 砍掉之后会发现所有的叶子节点都是金币节点
 * 两步之内可以收集到 => 可以继续砍
 * 定义最外层深度为1
 * 只需要访问深度 >= 3 的节点即可
 * 有多个分支不同深度的话，取最大深度的值
 * 取哪个点做起点无所谓，因为无论如何都要走回来的
 * (m-1)*2 where d >=3, m >= 2
 * 2次拓扑排序
 * 第一次砍掉非金币的子树
 * 第二次取金币
 */