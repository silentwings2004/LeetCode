package LC3001_3300;
import java.util.*;
public class LC3245_AlternatingGroupsIII {
    /**
     * There are some red and blue tiles arranged circularly. You are given an array of integers colors and a 2D
     * integers array queries.
     *
     * The color of tile i is represented by colors[i]:
     *
     * colors[i] == 0 means that tile i is red.
     * colors[i] == 1 means that tile i is blue.
     * An alternating group is a contiguous subset of tiles in the circle with alternating colors (each tile in the
     * group except the first and last one has a different color from its adjacent tiles in the group).
     *
     * You have to process queries of two types:
     *
     * queries[i] = [1, sizei], determine the count of alternating groups with size sizei.
     * queries[i] = [2, indexi, colori], change colors[indexi] to colori.
     * Return an array answer containing the results of the queries of the first type in order.
     *
     * Note that since colors represents a circle, the first and the last tiles are considered to be next to each other.
     *
     * Input: colors = [0,1,1,0,1], queries = [[2,1,0],[1,4]]
     *
     * Output: [2]
     *
     * Input: colors = [0,0,1,0,1,1], queries = [[1,3],[2,3,0],[1,5]]
     *
     * Output: [2,0]
     *
     * Constraints:
     *
     * 4 <= colors.length <= 5 * 10^4
     * 0 <= colors[i] <= 1
     * 1 <= queries.length <= 5 * 10^4
     * queries[i][0] == 1 or queries[i][0] == 2
     * For all i that:
     * queries[i][0] == 1: queries[i].length == 2, 3 <= queries[i][1] <= colors.length - 1
     * queries[i][0] == 2: queries[i].length == 3, 0 <= queries[i][1] <= colors.length - 1, 0 <= queries[i][2] <= 1
     * @param colors
     * @param queries
     * @return
     */
    // time = O((n + q) * logn), space = O(n)
    Fenwick fen;
    TreeSet<Integer> set;
    int n;
    public List<Integer> numberOfAlternatingGroups(int[] colors, int[][] queries) {
        n = colors.length;
        fen = new Fenwick(n);
        set = new TreeSet<>(); // maintain end points

        for (int i = 0; i < n; i++) {
            if (colors[i] == colors[(i + 1) % n]) {
                add(i); // add an endpoint at i
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int[] q : queries) {
            if (q[0] == 1) { // sz = q[1]
                if (set.isEmpty()) res.add(n);
                else {
                    int[] t = fen.sum(q[1]);
                    res.add(t[1] - (q[1] - 1) * t[0]); // sum - cnt * (size - 1)
                }
            } else {
                int i = q[1]; // idx
                if (colors[i] == q[2]) continue; // keep the same
                int pre = (i - 1 + n) % n;
                int nxt = (i + 1) % n;
                if (colors[pre] == colors[i]) { // pre is endpoint
                    del(pre);
                }
                if (colors[i] == colors[nxt]) {
                    del(i);
                }
                colors[i] ^= 1; // modify
                if (colors[pre] == colors[i]) {
                    add(pre);
                }
                if (colors[i] == colors[nxt]) {
                    add(i);
                }
            }
        }
        return res;
    }

    private void add(int x) {
        if (set.isEmpty()) fen.add(n, 1); // size = n
        else update(x, 1);
        set.add(x);
    }

    private void del(int x) {
        set.remove(x);
        if (set.isEmpty()) fen.add(n, -1);
        else update(x, -1);
    }

    private void update(int x, int op) {
        Integer pre = set.floor(x);
        Integer nxt = set.ceiling(x);
        if (pre == null) pre = set.last();
        if (nxt == null) nxt = set.first();

        // remove old seg [pre, nxt]
        int v = (nxt - pre + n) % n;
        if (v == 0) v = n;
        fen.add(v, -op);
        fen.add((x - pre + n) % n, op);
        fen.add((nxt - x + n) % n, op);
    }

    class Fenwick {
        private int n;
        private int[][] a;
        public Fenwick(int n) {
            this.n = n;
            this.a = new int[n + 1][2];
        }

        private void add(int sz, int v) {
            int x = a.length - sz + 1; // x' + 1
            for (int i = x; i <= n; i += i & -i) {
                a[i - 1][0] += v; // cnt
                a[i - 1][1] += v * sz; // tot
            }
        }

        private int[] sum(int sz) {
            int cnt = 0, tot = 0;
            int x = a.length - sz + 1;
            for (int i = x; i > 0; i -= i & -i) {
                cnt += a[i - 1][0];
                tot += a[i - 1][1];
            }
            return new int[]{cnt, tot};
        }
    }
}