package LC3901_4200;

public class LC3901_GoodSubsequenceQueries {
    /**
     * You are given an integer array nums of length n and an integer p.
     *
     * A non-empty subsequence of nums is called good if:
     *
     * Its length is strictly less than n.
     * The greatest common divisor (GCD) of its elements is exactly p.
     * You are also given a 2D integer array queries of length q, where each queries[i] = [indi, vali] indicates that
     * you should update nums[indi] to vali.
     *
     * After each query, determine whether there exists any good subsequence in the current array.
     *
     * Return the number of queries for which a good subsequence exists.
     *
     * The term gcd(a, b) denotes the greatest common divisor of a and b.
     *
     * Input: nums = [4,8,12,16], p = 2, queries = [[0,3],[2,6]]
     * Output: 1
     *
     * Input: nums = [4,5,7,8], p = 3, queries = [[0,6],[1,9],[2,3]]
     * Output: 2
     *
     * Input: nums = [5,7,9], p = 2, queries = [[1,4],[2,8]]
     * Output: 0
     *
     * Constraints:
     *
     * 2 <= n == nums.length <= 5 * 10^4
     * 1 <= nums[i] <= 5 * 10^4
     * 1 <= queries.length <= 5 * 10^4
     * queries[i] = [indi, vali]
     * 1 <= vali, p <= 5 * 10^4
     * 0 <= indi <= n - 1
     * @param nums
     * @param p
     * @param queries
     * @return
     */
    // time = O(nlogU + q(logn + logU)), space = O(n)  U: max(nums)
    public int countGoodSubseq(int[] nums, int p, int[][] queries) {
        int n = nums.length, cnt = 0;
        for (int x : nums) {
            if (x % p == 0) cnt++;
        }

        SegmentTree seg = new SegmentTree(nums, p);
        int res = 0;
        for (int[] q : queries) {
            int i = q[0], x = q[1];
            if (nums[i] % p == 0) cnt--;
            if (x % p == 0) cnt++;
            nums[i] = x;
            seg.modify(i, x);
            if (seg.queryAll() == p && (cnt < n || n > 6 || seg.check(n))) res++;
        }
        return res;
    }

    // 模板来源 https://leetcode.cn/circle/discuss/mOr1u6/
    // 线段树有两个下标，一个是线段树节点的下标，另一个是线段树维护的区间的下标
    //节点的下标：从 1 开始，如果你想改成从 0 开始，需要把左右儿子下标分别改成 node * 2 + 1 和 node * 2 + 2
    // 区间的下标：从 0 开始
    class SegmentTree {
        int g, n;
        int[] tr;
        public SegmentTree(int[] a, int g) {
            this.g = g;
            n = a.length;
            tr = new int[2 << (32 - Integer.numberOfLeadingZeros(n - 1))];
            build(a, 1, 0, n - 1);
        }

        private void build(int[] a, int u, int l, int r) {
            if (l == r) {
                tr[u] = a[r] % g == 0 ? a[r] : 0;
                return;
            }
            int mid = l + r >> 1;
            build(a, u << 1, l, mid);
            build(a, u << 1 | 1, mid + 1, r);
            pushup(u);
        }

        private void pushup(int u) {
            tr[u] = gcd(tr[u << 1], tr[u << 1 | 1]);
        }

        private void modify(int u, int l, int r, int i, int v) {
            if (l == r) {
                tr[u] = v % g == 0 ? v : 0;
                return;
            }
            int mid = l + r >> 1;
            if (i <= mid) modify(u << 1, l, mid, i, v);
            else modify(u << 1 | 1, mid + 1, r, i, v);
            pushup(u);
        }

        private int query(int u, int l, int r, int L, int R) {
            if (L > R) return 0;
            if (L <= l && r <= R) return tr[u];
            int mid = l + r >> 1;
            if (R <= mid) return query(u << 1, l, mid, L, R);
            if (L > mid) return query(u << 1 | 1, mid + 1, r, L , R);
            int lv = query(u << 1, l, mid, L, R);
            int rv = query(u << 1 | 1, mid + 1, r, L, R);
            return gcd(lv, rv);
        }

        private void modify(int i, int v) {
            modify(1, 0, n - 1, i, v);
        }

        private int query(int l, int r) {
            return query(1, 0, n - 1, l, r);
        }

        private int queryAll() {
            return tr[1];
        }

        private boolean check(int n) {
            for (int i = 0; i < n; i++) {
                if (gcd(query(0, i - 1), query(i + 1, n - 1)) == g) return true;
            }
            return false;
        }

        private int gcd(int a, int b) {
            return b == 0 ? a : gcd(b, a % b);
        }
    }
}
/**
 * 1. 只关注是 p 的倍数的 nums[i]
 * 2. 选的数越多，GCD 越小，月可能等于 p
 * 3. 应该把[所有] p 的倍数全选了
 * 4. 然而，本题不能全选，如果所有 nums[i] 都是p 的倍数，且 GCD(nums) = p，那么必须删除一个数(不选一个数)
 * 5. 能不能构造一个nums，删除任意一个数，剩余的 n-1 个数的 GCD 都大于 p ？
 * 6. 设 a[i] = nums[i] / p，那么 GCD(a) = 1，删除任意一个数，剩余的 n-1 个数的 GCD 都大于 1
 * 7. if n > 7, [4] 一定成立
 * 8. if n <= 7，直接暴力
 */