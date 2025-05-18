package LC2401_2700;
import java.util.*;
public class LC2569_HandlingSumQueriesAfterUpdate {
    /**
     * You are given two 0-indexed arrays nums1 and nums2 and a 2D array queries of queries. There are three types of
     * queries:
     *
     * For a query of type 1, queries[i] = [1, l, r]. Flip the values from 0 to 1 and from 1 to 0 in nums1 from index l
     * to index r. Both l and r are 0-indexed.
     * For a query of type 2, queries[i] = [2, p, 0]. For every index 0 <= i < n, set nums2[i] = nums2[i] + nums1[i] * p.
     * For a query of type 3, queries[i] = [3, 0, 0]. Find the sum of the elements in nums2.
     * Return an array containing all the answers to the third type queries.
     *
     * Input: nums1 = [1,0,1], nums2 = [0,0,0], queries = [[1,1,1],[2,1,0],[3,0,0]]
     * Output: [3]
     *
     * Input: nums1 = [1], nums2 = [5], queries = [[2,0,0],[3,0,0]]
     * Output: [5]
     *
     * Constraints:
     *
     * 1 <= nums1.length,nums2.length <= 10^5
     * nums1.length = nums2.length
     * 1 <= queries.length <= 10^5
     * queries[i].length = 3
     * 0 <= l <= r <= nums1.length - 1
     * 0 <= p <= 10^6
     * 0 <= nums1[i] <= 1
     * 0 <= nums2[i] <= 10^9
     * @param nums1
     * @param nums2
     * @param queries
     * @return
     */
    // time = O(nlogn), space = O(n)
    final int N = 100010;
    Node[] tr;
    int[] w;
    public long[] handleQuery(int[] nums1, int[] nums2, int[][] queries) {
        tr = new Node[N * 4];
        int n = nums1.length, m = queries.length;
        w = new int[n + 1];
        for (int i = 1; i <= n; i++) w[i] = nums1[i - 1];
        build(1, 1, n);
        long sum = 0;
        for (int x : nums2) sum += x;

        List<Long> res = new ArrayList<>();
        for (int[] q : queries) {
            if (q[0] == 1) modify(1, q[1] + 1, q[2] + 1);
            else if (q[0] == 2) sum += query(1, 1, n) * q[1];
            else res.add(sum);
        }

        long[] ans = new long[res.size()];
        for (int i = 0; i < res.size(); i++) ans[i] = res.get(i);
        return ans;
    }

    private void build(int u, int l, int r) {
        tr[u] = new Node(l, r);
        if (l == r) tr[u].sum = w[r];
        else {
            int mid = l + r >> 1;
            build(u << 1, l, mid);
            build(u << 1 | 1, mid + 1, r);
            pushup(u);
        }
    }

    private void pushup(int u) {
        tr[u].sum = tr[u << 1].sum + tr[u << 1 | 1].sum;
    }

    private void pushdown(int u) {
        Node root = tr[u], left = tr[u << 1], right = tr[u << 1 | 1];
        if (root.add != 0) {
            left.add ^= 1;
            left.sum = (long)(left.r - left.l + 1) - left.sum;
            right.add ^= 1;
            right.sum = (long)(right.r - right.l + 1) - right.sum;
            root.add = 0;
        }
    }

    private void modify(int u, int l, int r) {
        if (tr[u].l >= l && tr[u].r <= r) {
            tr[u].sum = (long)(tr[u].r - tr[u].l + 1) - tr[u].sum;
            tr[u].add ^= 1;
        } else {
            pushdown(u);
            int mid = tr[u].l + tr[u].r >> 1;
            if (l <= mid) modify(u << 1, l, r);
            if (r > mid) modify(u << 1 | 1, l, r);
            pushup(u);
        }
    }

    private long query(int u, int l, int r) {
        if (tr[u].l >= l && tr[u].r <= r) return tr[u].sum;
        pushdown(u);
        int mid = tr[u].l + tr[u].r >> 1;
        long sum = 0;
        if (l <= mid) sum = query(u << 1, l, r);
        if (r > mid) sum += query(u << 1 | 1, l, r);
        return sum;
    }

    private class Node {
        private int l, r;
        private long sum, add;
        public Node(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }
}
/**
 * 本题的本质就是需要高效的区间更新函数来维护nums1，来实现第一类query。
 * 对于第二类query，只需要操作sum += total(nums1)*p即可，其中total就是对nums1全部元素取和。
 * 对于第三类query，就是输出当前的sum。
 * 既要区间更新，又要区间求和 => 线段树
 * 支持logn区间更新，和logn区间求和
 *
 */