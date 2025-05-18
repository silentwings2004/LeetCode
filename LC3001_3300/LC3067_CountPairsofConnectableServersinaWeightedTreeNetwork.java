package LC3001_3300;
import java.util.*;
public class LC3067_CountPairsofConnectableServersinaWeightedTreeNetwork {
    /**
     * You are given an unrooted weighted tree with n vertices representing servers numbered from 0 to n - 1, an array
     * edges where edges[i] = [ai, bi, weighti] represents a bidirectional edge between vertices ai and bi of weight
     * weighti. You are also given an integer signalSpeed.
     *
     * Two servers a and b are connectable through a server c if:
     *
     * a < b, a != c and b != c.
     * The distance from c to a is divisible by signalSpeed.
     * The distance from c to b is divisible by signalSpeed.
     * The path from c to b and the path from c to a do not share any edges.
     * Return an integer array count of length n where count[i] is the number of server pairs that are connectable
     * through the server i.
     *
     * Input: edges = [[0,1,1],[1,2,5],[2,3,13],[3,4,9],[4,5,2]], signalSpeed = 1
     * Output: [0,4,6,6,4,0]
     *
     * Input: edges = [[0,6,3],[6,5,3],[0,3,1],[3,2,7],[3,1,6],[3,4,2]], signalSpeed = 3
     * Output: [2,0,0,0,0,0,2]
     *
     * Constraints:
     *
     * 2 <= n <= 1000
     * edges.length == n - 1
     * edges[i].length == 3
     * 0 <= ai, bi < n
     * edges[i] = [ai, bi, weighti]
     * 1 <= weighti <= 10^6
     * 1 <= signalSpeed <= 10^6
     * The input is generated such that edges represents a valid tree.
     * @param edges
     * @param signalSpeed
     * @return
     */
    // time = O(n^2*logn), space = O(n)
    List<int[]>[] adj;
    int n, signalSpeed;
    int[] dist, p;
    public int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {
        this.signalSpeed = signalSpeed;
        n = edges.length + 1;
        dist = new int[n];
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0], b = e[1], c = e[2];
            adj[a].add(new int[]{b, c});
            adj[b].add(new int[]{a, c});
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist, 0);
            p = new int[n];
            for (int j = 0; j < n; j++) p[j] = j;
            dfs(i, -1);
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < n; j++) {
                if (j == i) continue;
                int fa = find(j);
                if (dist[j] % signalSpeed == 0) {
                    map.put(fa, map.getOrDefault(fa, 0) + 1);
                }
            }
            List<Integer> q = new ArrayList<>();
            int sum = 0;
            for (int v : map.values()) {
                sum += v;
                q.add(v);
            }
            if (q.size() < 2) {
                res[i] = 0;
                continue;
            }
            for (int j = 0; j < q.size(); j++) {
                res[i] += q.get(j) * (sum - q.get(j));
            }
            res[i] /= 2;
        }
        return res;
    }

    private void dfs(int u, int fa) {
        for (int[] t : adj[u]) {
            int v = t[0], c = t[1];
            if (v == fa) continue;
            dist[v] = dist[u] + c;
            if (fa != -1) p[find(v)] = find(u);
            dfs(v, u);
        }
    }

    private int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }

    // S2: dfs
    class Solution {
        // time = O(n^2), space = O(n)
        List<int[]>[] adj;
        int w;
        public int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {
            int n = edges.length + 1;
            w = signalSpeed;
            adj = new List[n];
            for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
            for (int[] e : edges) {
                int a = e[0], b = e[1], c = e[2];
                adj[a].add(new int[]{b, c});
                adj[b].add(new int[]{a, c});
            }

            int[] res = new int[n];
            for (int i = 0; i < n; i++) {
                int s = 0;
                for (int[] x : adj[i]) {
                    int cnt = dfs(x[0], x[1], i);
                    res[i] += cnt * s;
                    s += cnt;
                }
            }
            return res;
        }

        private int dfs(int u, int sum, int fa) {
            int cnt = sum % w == 0 ? 1 : 0;
            for (int[] x : adj[u]) {
                int v = x[0], t = x[1];
                if (v == fa) continue;
                cnt += dfs(v, sum + t, u);
            }
            return cnt;
        }
    }
}