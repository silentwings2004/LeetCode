package LC3001_3300;

public class LC3165_MaximumSumofSubsequenceWithNonadjacentElements {
    /**
     * You are given an array nums consisting of integers. You are also given a 2D array queries, where
     * queries[i] = [posi, xi].
     *
     * For query i, we first set nums[posi] equal to xi, then we calculate the answer to query i which is the maximum
     * sum of a subsequence of nums where no two adjacent elements are selected.
     *
     * Return the sum of the answers to all queries.
     *
     * Since the final answer may be very large, return it modulo 10^9 + 7.
     *
     * A subsequence is an array that can be derived from another array by deleting some or no elements without
     * changing the order of the remaining elements.
     *
     * Input: nums = [3,5,9], queries = [[1,-2],[0,-3]]
     *
     * Output: 21
     *
     * Input: nums = [0,-1], queries = [[0,-5]]
     *
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 5 * 10^4
     * -105 <= nums[i] <= 10^5
     * 1 <= queries.length <= 5 * 10^4
     * queries[i] == [posi, xi]
     * 0 <= posi <= nums.length - 1
     * -10^5 <= xi <= 10^5
     * @param nums
     * @param queries
     * @return
     */
    // S1
    // time = O((n + q) * logn), space = O(n)
    final long mod = (long)(1e9 + 7);
    long[][] tr;
    int[] nums;
    public int maximumSumSubsequence(int[] nums, int[][] queries) {
        this.nums = nums;
        int n = nums.length;
        tr = new long[2 << (32 - Integer.numberOfLeadingZeros(n))][4]; // 比 n * 4 节省空间
        build(1, 0, n - 1);
        long res = 0;
        for (int[] q : queries) {
            modify(1, 0, n - 1, q[0], q[1]);
            res += tr[1][3];
        }
        return (int)(res % mod);
    }

    private void pushup(int u) {
        long[] l = tr[u << 1], r = tr[u << 1 | 1];
        tr[u][0] = Math.max(l[0] + r[2], l[1] + r[0]);
        tr[u][1] = Math.max(l[0] + r[3], l[1] + r[1]);
        tr[u][2] = Math.max(l[2] + r[2], l[3] + r[0]);
        tr[u][3] = Math.max(l[2] + r[3], l[3] + r[1]);
    }

    private void build(int u, int l, int r) {
        if (l == r) tr[u][3] = Math.max(nums[r], 0);
        else {
            int mid = l + r >> 1;
            build(u << 1, l, mid);
            build(u << 1 | 1, mid + 1, r);
            pushup(u);
        }
    }

    private void modify(int u, int l, int r, int x, int v) {
        if (l == r) tr[u][3] = Math.max(v, 0);
        else {
            int mid = l + r >> 1;
            if (x <= mid) modify(u << 1, l, mid, x, v);
            else modify(u << 1 | 1, mid + 1, r, x, v);
            pushup(u);
        }
    }

    // S1.2
    // time = O((n + q) * logn), space = O(n)
    class Solution {
        final long inf = (long)1e18, mod = (long)1e9 + 7;
        public int maximumSumSubsequence(int[] nums, int[][] queries) {
            SegTreeNode root = new SegTreeNode(0, nums.length - 1, nums);
            long res = 0;
            for (int[] q : queries) {
                root.update(q[0], q[1]);
                res += Math.max(root.info00, Math.max(root.info01, Math.max(root.info10, root.info11)));
                res %= mod;
            }
            return (int)res;
        }

        class SegTreeNode {
            SegTreeNode left, right;
            int start, end;
            long info00, info01, info10, info11;

            public SegTreeNode(int l, int r, int[] val) {
                start = l;
                end = r;
                if (l == r) {
                    info00 = 0;
                    info11 = val[r];
                    info01 = -inf;
                    info10 = -inf;
                    return;
                }

                int mid = l + r >> 1;
                if (left == null) {
                    left = new SegTreeNode(l, mid, val);
                    right = new SegTreeNode(mid + 1, r, val);
                    info00 = Math.max(left.info00 + right.info00, Math.max(left.info01 + right.info00, left.info00 + right.info10));
                    info01 = Math.max(left.info00 + right.info01, Math.max(left.info01 + right.info01, left.info00 + right.info11));
                    info10 = Math.max(left.info10 + right.info00, Math.max(left.info11 + right.info00, left.info10 + right.info10));
                    info11 = Math.max(left.info10 + right.info01, Math.max(left.info11 + right.info01, left.info10 + right.info11));
                }
            }

            private void update(int x, int val) {
                if (x < start || x > end) return;
                if (x <= start && end <= x) {
                    info00 = 0;
                    info11 = val;
                    return;
                }
                if (left != null) {
                    left.update(x, val);
                    right.update(x, val);
                    info00 = Math.max(left.info00 + right.info00, Math.max(left.info01 + right.info00, left.info00 + right.info10));
                    info01 = Math.max(left.info00 + right.info01, Math.max(left.info01 + right.info01, left.info00 + right.info11));
                    info10 = Math.max(left.info10 + right.info00, Math.max(left.info11 + right.info00, left.info10 + right.info10));
                    info11 = Math.max(left.info10 + right.info01, Math.max(left.info11 + right.info01, left.info10 + right.info11));
                }
            }
        }
    }
}
/**
 * 能否分支解决不带修改的版本？
 * 能 -> 用线段树动态维护带修改的版本
 *
 * f00(a) 在不选 a 的第一个数的情况下，计算打家劫舍的答案
 * f01(a) 在不选 a 的第一个数的情况下，计算打家劫舍的答案(最后一个数可选可不选)
 * f10(a) 在不选 a 的最后一个数的情况下，计算打家劫舍的答案(最后一个数可选可不选)
 * f11(a) 计算打家劫舍的答案(第一个数可选可不选，最后一个数可选可不选)
 *
 * p = a[:len(a) // 2]
 * q = a[:len(a) // 2:]
 * f11(a) = max(f10(p) + f11(q), f11(p) + f01(q))
 * f01(a) = max(f00(p) + f11(q), f01(p) + f01(q))
 *
 * 递归边界：f11(a) = max(a[0], 0)
 *
 * [i x x k] [k+1 x x x j] => max
 * dp00[i][j] = dp00[i][k] + dp00[k+1][j]
 * dp00[i][j] = dp00[i][k] + dp10[k+1][j]
 * dp00[i][j] = dp01[i][k] + dp00[k+1][j]
 *
 * dp01[i][j] = dp00[i][k] + dp01[k+1][j]
 * dp01[i][j] = dp00[i][k] + dp11[k+1][j]
 * dp01[i][j] = dp01[i][k] + dp01[k+1][j]
 *
 * dp10[i][j] = dp10[i][k] + dp00[k+1][j]
 * dp10[i][j] = dp10[i][k] + dp10[k+1][j]
 * dp10[i][j] = dp11[i][k] + dp00[k+1][j]
 *
 * dp11[i][j] = dp10[i][k] + dp01[k+1][j]
 * dp11[i][j] = dp10[i][k] + dp11[k+1][j]
 * dp11[i][j] = dp11[i][k] + dp01[k+1][j]
 *
 * x
 * dp00[i][i] = 0
 * dp11[i][i] = nums[i]
 * dp01[i][i] = -inf
 * dp10[i][i] = -inf
 */