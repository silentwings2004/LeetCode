package LC3301_3600;

public class LC3525_FindXValueofArrayII {
    /**
     * You are given an array of positive integers nums and a positive integer k. You are also given a 2D array queries,
     * where queries[i] = [indexi, valuei, starti, xi].
     *
     * You are allowed to perform an operation once on nums, where you can remove any suffix from nums such that nums
     * remains non-empty.
     *
     * The x-value of nums for a given x is defined as the number of ways to perform this operation so that the product
     * of the remaining elements leaves a remainder of x modulo k.
     *
     * For each query in queries you need to determine the x-value of nums for xi after performing the following actions:
     *
     * Update nums[indexi] to valuei. Only this step persists for the rest of the queries.
     * Remove the prefix nums[0..(starti - 1)] (where nums[0..(-1)] will be used to represent the empty prefix).
     * Return an array result of size queries.length where result[i] is the answer for the ith query.
     *
     * A prefix of an array is a subarray that starts from the beginning of the array and extends to any point within it.
     *
     * A suffix of an array is a subarray that starts at any point within the array and extends to the end of the array.
     *
     * A subarray is a contiguous sequence of elements within an array.
     *
     * Note that the prefix and suffix to be chosen for the operation can be empty.
     *
     * Note that x-value has a different definition in this version.
     *
     * Input: nums = [1,2,3,4,5], k = 3, queries = [[2,2,0,2],[3,3,3,0],[0,1,0,1]]
     * Output: [2,2,2]
     *
     * Input: nums = [1,2,4,8,16,32], k = 4, queries = [[0,2,0,2],[0,2,0,1]]
     * Output: [1,0]
     *
     * Input: nums = [1,1,2,1,1], k = 2, queries = [[2,1,0,1]]
     * Output: [5]
     *
     * Constraints:
     *
     * 1 <= nums[i] <= 10^9
     * 1 <= nums.length <= 10^5
     * 1 <= k <= 5
     * 1 <= queries.length <= 2 * 10^4
     * queries[i] == [indexi, valuei, starti, xi]
     * 0 <= indexi <= nums.length - 1
     * 1 <= valuei <= 10^9
     * 0 <= starti <= nums.length - 1
     * 0 <= xi <= k - 1
     * @param nums
     * @param k
     * @param queries
     * @return
     */
    // time = O(k * (n + q) * logn), space = O(n)
    Node[] tr;
    int[] a;
    int k;
    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.k = k;
        int n = nums.length, m = queries.length;
        a = new int[n + 1];
        for (int i = 1; i <= n; i++) a[i] = nums[i - 1] % k;

        tr = new Node[n << 4];
        build(1, 1, n);

        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int idx = queries[i][0] + 1;
            int val = queries[i][1] % k;
            int start = queries[i][2] + 1;
            int x = queries[i][3];
            modify(1, idx, val);
            res[i] = (int) query(1, start, n).cnt[x];
        }
        return res;
    }

    private Node pushup(Node nl, Node nr) {
        Node node = new Node(nl.l, nr.r);
        node.p = nl.p * nr.p % k;
        for (int i = 0; i < k; i++) node.cnt[i] = nl.cnt[i];
        for (int i = 0; i < k; i++) {
            int to = (int) (nl.p * i % k);
            node.cnt[to] += nr.cnt[i];
        }
        return node;
    }

    private void build(int u, int l, int r) {
        tr[u] = new Node(l, r);
        if (l == r) {
            int val = a[r];
            tr[u].p = val;
            tr[u].cnt[val] = 1;
        } else {
            int mid = (l + r) >> 1;
            build(u << 1, l, mid);
            build(u << 1 | 1, mid + 1, r);
            tr[u] = pushup(tr[u << 1], tr[u << 1 | 1]);
        }
    }

    private void modify(int u, int x, int v) {
        if (tr[u].l == x && tr[u].r == x) {
            tr[u].p = v;
            tr[u].cnt = new long[k];
            tr[u].cnt[v] = 1;
        }
        else {
            int mid = tr[u].l + tr[u].r >> 1;
            if (x <= mid) modify(u << 1, x, v);
            else modify(u << 1 | 1, x, v);
            tr[u] = pushup(tr[u << 1], tr[u << 1 | 1]);
        }
    }

    private Node query(int u, int l, int r) {
        if (tr[u].l >= l && tr[u].r <= r) return tr[u];
        int mid = tr[u].l + tr[u].r >> 1;
        if (r <= mid) return query(u << 1, l, r);
        else if (l > mid) return query(u << 1 | 1, l, r);
        return pushup(query(u << 1, l, r), query(u << 1 | 1, l, r));
    }

    class Node {
        private int l, r;
        private long p;
        private long[] cnt;
        public Node(int l, int r) {
            this.l = l;
            this.r = r;
            this.cnt = new long[k];
        }
    }
}
/**
 * 左端点固定，右端点可以枚举 n - st 个
 * 每个询问是
 * 计算左端点为 start (注意这是一个定值),右端点为 start, start+1,...,这一共 n-start 个子数组，
 * 元素积模 k 为 x 的子数组个数
 *
 * 如果一个问题我想清楚了怎么用分治解决，那么就能用线段树解决
 * 如何用分治计算 [l,r]，也就是左端点为 l，右端点为 l, l+1,...,r 的子数组的个数，满足元素积模 k 为 x (0~k-1)
 *
 */