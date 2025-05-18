package LC3301_3600;
import java.util.*;
public class LC3526_RangeXORQuerieswithSubarrayReversals {
    /**
     * You are given an integer array nums of length n and a 2D integer array queries of length q, where each query is
     * one of the following three types:
     *
     * Update: queries[i] = [1, index, value]
     * Set nums[index] = value.
     *
     * Range XOR Query: queries[i] = [2, left, right]
     * Compute the bitwise XOR of all elements in the subarray nums[left...right], and record this result.
     *
     * Reverse Subarray: queries[i] = [3, left, right]
     * Reverse the subarray nums[left...right] in place.
     *
     * Return an array of the results of all range XOR queries in the order they were encountered.
     *
     * Input: nums = [1,2,3,4,5], queries = [[2,1,3],[1,2,10],[3,0,4],[2,0,4]]
     *
     * Output: [5,8]
     *
     * Input: nums = [7,8,9], queries = [[1,0,3],[2,0,2],[3,1,2]]
     *
     * Output: [2]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i] <= 10^9
     * 1 <= queries.length <= 10^5
     * queries[i].length == 3
     * queries[i][0] ∈ {1, 2, 3}
     * If queries[i][0] == 1:
     * 0 <= index < nums.length
     * 0 <= value <= 10^9
     * If queries[i][0] == 2 or queries[i][0] == 3:
     * 0 <= left <= right < nums.length
     * @param nums
     * @param queries
     * @return
     */
    // time = O(nlogn), space = O(n)
    Node[] tr;
    int[] nodes, w;
    int root, tt;
    public int[] getResults(int[] nums, int[][] queries) {
        int n = nums.length, m = queries.length;
        int N = n + 10;
        tr = new Node[N];
        for (int i = 0; i < N; i++) tr[i] = new Node();
        nodes = new int[N];
        for (int i = 1; i < N; i++) nodes[++tt] = i;
        w = new int[N];
        for (int i = 1; i <= n; i++) w[i] = nums[i - 1];
        root = build(0, n + 1, 0);

        List<Integer> res = new ArrayList<>();
        for (int[] q : queries) {
            int o = q[0], a = q[1], b = q[2];
            if (o == 1) {
                int idx = get_k(a + 2);
                splay(idx, 0);
                tr[idx].v = b;
                pushup(idx);
            } else if (o == 2) {
                int l = get_k(a + 1), r = get_k(b + 3);
                splay(l, 0);
                splay(r, l);
                int u = tr[r].s[0];
                res.add(tr[u].xor);
            } else {
                int l = get_k(a + 1), r = get_k(b + 3);
                splay(l, 0);
                splay(r, l);
                int u = tr[r].s[0];
                tr[u].rev ^= 1;
            }
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) ans[i] = res.get(i);
        return ans;
    }

    private int build(int l, int r, int p) {
        int mid = l + r >> 1;
        int u = nodes[tt--];
        tr[u].init(w[mid], p);
        if (l < mid) tr[u].s[0] = build(l, mid - 1, u);
        if (mid < r) tr[u].s[1] = build(mid + 1, r, u);
        pushup(u);
        return u;
    }

    private void pushup(int x) {
        Node u = tr[x], l = tr[u.s[0]], r = tr[u.s[1]];
        u.size = l.size + r.size + 1;
        u.xor = l.xor ^ r.xor ^ u.v;
    }

    private void pushdown(int x) {
        Node u = tr[x];
        if (u.rev != 0) {
            u.rev = 0;
            int a = u.s[0], b = u.s[1];
            if (a != 0) tr[a].rev ^= 1;
            if (b != 0) tr[b].rev ^= 1;
            u.s[0] = b;
            u.s[1] = a;
        }
    }

    private void rotate(int x) {
        int y = tr[x].p, z = tr[y].p;
        int k = tr[y].s[1] == x ? 1 : 0;
        tr[z].s[tr[z].s[1] == y ? 1 : 0] = x;
        tr[x].p = z;
        tr[y].s[k] = tr[x].s[k ^ 1];
        tr[tr[x].s[k ^ 1]].p = y;
        tr[x].s[k ^ 1] = y;
        tr[y].p = x;
        pushup(y);
        pushup(x);
    }

    private void splay(int x, int k) {
        while (tr[x].p != k) {
            int y = tr[x].p, z = tr[y].p;
            if (z != k) {
                if ((tr[z].s[1] == y) ^ (tr[y].s[1] == x)) rotate(x);
                else rotate(y);
            }
            rotate(x);
        }
        if (k == 0) root = x;
    }

    private int get_k(int k) {
        int u = root;
        while (u != 0) {
            pushdown(u);
            if (tr[tr[u].s[0]].size >= k) u = tr[u].s[0];
            else if (tr[tr[u].s[0]].size + 1 == k) return u;
            else {
                k -= tr[tr[u].s[0]].size + 1;
                u = tr[u].s[1];
            }
        }
        return -1;
    }

    class Node {
        private int[] s;
        private int p, v, rev, size, xor;
        public Node() {
            this.s = new int[2];
        }
        private void init(int v, int p) {
            this.v = v;
            this.p = p;
            s[0] = s[1] = 0;
            rev = 0;
            size = 1;
            xor = v;
        }
    }
}