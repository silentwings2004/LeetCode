package LC2401_2700;
import java.util.*;
public class LC2467_MostProfitablePathinaTree {
    /**
     * There is an undirected tree with n nodes labeled from 0 to n - 1, rooted at node 0. You are given a 2D integer
     * array edges of length n - 1 where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in
     * the tree.
     *
     * At every node i, there is a gate. You are also given an array of even integers amount, where amount[i] represents:
     *
     * the price needed to open the gate at node i, if amount[i] is negative, or,
     * the cash reward obtained on opening the gate at node i, otherwise.
     * The game goes on as follows:
     *
     * Initially, Alice is at node 0 and Bob is at node bob.
     * At every second, Alice and Bob each move to an adjacent node. Alice moves towards some leaf node, while Bob moves
     * towards node 0.
     * For every node along their path, Alice and Bob either spend money to open the gate at that node, or accept the
     * reward. Note that:
     * If the gate is already open, no price will be required, nor will there be any cash reward.
     * If Alice and Bob reach the node simultaneously, they share the price/reward for opening the gate there. In other
     * words, if the price to open the gate is c, then both Alice and Bob pay c / 2 each. Similarly, if the reward at
     * the gate is c, both of them receive c / 2 each.
     * If Alice reaches a leaf node, she stops moving. Similarly, if Bob reaches node 0, he stops moving. Note that
     * these events are independent of each other.
     * Return the maximum net income Alice can have if she travels towards the optimal leaf node.
     *
     * Input: edges = [[0,1],[1,2],[1,3],[3,4]], bob = 3, amount = [-2,4,2,-4,6]
     * Output: 6
     *
     * Input: edges = [[0,1]], bob = 1, amount = [-7280,2350]
     * Output: -7280
     *
     * Constraints:
     *
     * 2 <= n <= 10^5
     * edges.length == n - 1
     * edges[i].length == 2
     * 0 <= ai, bi < n
     * ai != bi
     * edges represents a valid tree.
     * 1 <= bob < n
     * amount.length == n
     * amount[i] is an even integer in the range [-10^4, 10^4].
     * @param edges
     * @param bob
     * @param amount
     * @return
     */
    // time = O(n), space = O(n)
    final int N = 100010, M = N * 2;
    int n, idx;
    int[] h, e, ne, w;
    int[] bt, p;
    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        h = new int[N];
        e = new int[M];
        ne = new int[M];
        w = new int[M];
        bt = new int[N];
        p = new int[N];
        Arrays.fill(h, -1);
        idx = 0;

        n = amount.length;
        for (int i = 0; i < n; i++) w[i] = amount[i];
        for (int[] x : edges) {
            int a = x[0], b = x[1];
            add(a, b);
            add(b, a);
        }

        dfs1(0, -1);
        Arrays.fill(bt, -1);
        int t = 0;
        while (true) {
            bt[bob] = t;
            t++;
            if (bob == 0) break;
            bob = p[bob];
        }

        return dfs2(0, -1, 0);
    }

    private int dfs2(int u, int fa, int t) {
        int val = 0;
        if (bt[u] == -1 || t < bt[u]) val = w[u];
        else if (bt[u] == t) val = w[u] / 2;

        int max = Integer.MIN_VALUE;
        for (int i = h[u]; i != -1; i = ne[i]) {
            int j = e[i];
            if (j == fa) continue;
            max = Math.max(max, dfs2(j, u, t + 1));
        }
        if (max == Integer.MIN_VALUE) max = 0;
        return val + max;
    }

    private void dfs1(int u, int fa) {
        for (int i = h[u]; i != -1; i = ne[i]) {
            int j = e[i];
            if (j == fa) continue;
            p[j] = u;
            dfs1(j, u);
        }
    }

    private void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }
}
