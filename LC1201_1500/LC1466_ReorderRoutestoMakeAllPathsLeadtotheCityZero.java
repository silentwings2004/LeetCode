package LC1201_1500;
import java.util.*;
public class LC1466_ReorderRoutestoMakeAllPathsLeadtotheCityZero {
    /**
     * There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one way to travel between two
     * different cities (this network form a tree). Last year, The ministry of transport decided to orient the roads in
     * one direction because they are too narrow.
     *
     * Roads are represented by connections where connections[i] = [ai, bi] represents a road from city ai to city bi.
     *
     * This year, there will be a big event in the capital (city 0), and many people want to travel to this city.
     *
     * Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number
     * of edges changed.
     *
     * It's guaranteed that each city can reach city 0 after reorder.
     *
     * Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
     * Output: 3
     *
     * Input: n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
     * Output: 2
     *
     * Input: n = 3, connections = [[1,0],[2,0]]
     * Output: 0
     *
     * Constraints:
     *
     * 2 <= n <= 5 * 10^4
     * connections.length == n - 1
     * connections[i].length == 2
     * 0 <= ai, bi <= n - 1
     * ai != bi
     * @param n
     * @param connections
     * @return
     */
    // time = O(n), space = O(n)
    final int N = 100010;
    int[] h, e, ne, w;
    int idx, res;
    public int minReorder(int n, int[][] connections) {
        h = new int[N];
        e = new int[N];
        ne = new int[N];
        w = new int[N];
        Arrays.fill(h, -1);
        idx = 0;
        res = 0;

        for (int[] x : connections) {
            int a = x[0], b = x[1];
            add(a, b, 1);
            add(b, a, -1);
        }

        dfs(0, -1);
        return res;
    }

    private void dfs(int u, int fa) {
        for (int i = h[u]; i != -1; i = ne[i]) {
            int j = e[i];
            if (j == fa) continue;
            if (w[i] > 0) res++;
            dfs(j, u);
        }
    }

    private void add(int a, int b, int c) {
        e[idx] = b;
        w[idx] = c;
        ne[idx] = h[a];
        h[a] = idx++;
    }
}