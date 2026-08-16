package LC3901_4200;

public class LC4017_PeaksinArrayII {
    /**
     * You are given an integer array nums of length n and a 2D integer array queries.
     *
     * A subarray nums[i..j] is called a peak subarray if:
     *
     * Its length is at least 3.
     * There exists an index k such that i < k < j and:
     * nums[k] > nums[k - 1]
     * nums[k] > nums[k + 1]
     * You have to process queries of two types:
     *
     * [1, li, ri]: Calculate the number of peak subarrays fully contained within nums[li..ri].
     * [2, indexi, vali]: Update nums[indexi] to vali. This update applies to all subsequent queries.
     * Return an array answer, where answer[i] is the answer to the ith query of type 1 in the order they appear.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [1,3,2,4], queries = [[1,0,3],[2,1,1],[1,0,3]]
     * Output: [2,0]
     *
     * Input: nums = [9,8,9,8], queries = [[1,1,3],[2,2,1],[1,0,2]]
     * Output: [1,0]
     *
     * Input: nums = [3,6,2,7,1], queries = [[1,1,3],[2,3,0],[1,0,4]]
     * Output: [0,3]
     *
     * Constraints:
     *
     * 3 <= n == nums.length <= 10^5
     * 0 <= nums[i] <= 10^5
     * 1 <= queries.length <= 10^5
     * queries[i] = [1, li, ri] or queries[i] = [2, indexi, vali]
     * 0 <= li < ri <= n - 1
     * 0 <= indexi <= n - 1
     * 0 <= vali <= 10^5
     * @param nums
     * @param queries
     * @return
     */
    // time = O(n + qlogn), space = O(n)
    public long[] countOfPeaks(int[] nums, int[][] queries) {
        int m = 0;
        for (int[] q : queries) m += 2 - q[0];

        int n = nums.length;
        SegmentTree seg = new SegmentTree(nums);
        long[] res = new long[m];

        int idx = 0;
        for (int[] q : queries) {
            if (q[0] == 1) {
                res[idx++] = seg.query(q[1], q[2]);
                continue;
            }
            int i = q[1];
            nums[i] = q[2];
            for (int j = Math.max(i - 1, 1); j <= Math.min(i + 1, n - 2); j++) {
                boolean hasPeak = nums[j - 1] < nums[j] && nums[j] > nums[j + 1];
                seg.update(j, hasPeak);
            }
        }
        return res;
    }

    class SegmentTree {

        class Data {
            long cnt;
            int pre;
            int suf;
            int len;
            boolean hasPeak;

            Data(long cnt, int pre, int suf, int len, boolean hasPeak) {
                this.cnt = cnt;
                this.pre = pre;
                this.suf = suf;
                this.len = len;
                this.hasPeak = hasPeak;
            }
        }

        private final int n;
        private final Data[] tree;

        private Data mergeData(Data a, Data b) {
            long cnt = a.cnt + b.cnt + (long) a.len * b.len - (long) a.suf * b.pre;
            int pre = a.hasPeak ? a.pre : a.len + b.pre;
            int suf = b.hasPeak ? b.suf : b.len + a.suf;
            return new Data(cnt, pre, suf, a.len + b.len, a.hasPeak || b.hasPeak);
        }

        public SegmentTree(int[] a) {
            n = a.length;
            tree = new Data[4 * n];
            build(a, 1, 0, n - 1);
        }

        public void update(int i, boolean hasPeak) {
            update(1, 0, n - 1, i, hasPeak);
        }

        public long query(int ql, int qr) {
            return query(1, 0, n - 1, ql, qr).cnt;
        }

        private void maintain(int node) {
            tree[node] = mergeData(tree[node * 2], tree[node * 2 + 1]);
        }

        private void build(int[] a, int node, int l, int r) {
            if (l == r) {
                boolean hasPeak = 0 < l && l < n - 1 && a[l - 1] < a[l] && a[l] > a[l + 1];
                tree[node] = new Data(0, 1, 1, 1, hasPeak);
                return;
            }

            int m = (l + r) >>> 1;
            build(a, node * 2, l, m);
            build(a, node * 2 + 1, m + 1, r);
            maintain(node);
        }

        private void update(int node, int l, int r, int i, boolean hasPeak) {
            if (l == r) {
                Data d = tree[node];
                tree[node] = new Data(d.cnt, d.pre, d.suf, d.len, hasPeak);
                return;
            }

            int m = (l + r) >>> 1;

            if (i <= m) {
                update(node * 2, l, m, i, hasPeak);
            } else {
                update(node * 2 + 1, m + 1, r, i, hasPeak);
            }

            maintain(node);
        }

        private Data query(int node, int l, int r, int ql, int qr) {
            if (ql <= l && r <= qr) {
                return tree[node];
            }

            int m = (l + r) >>> 1;

            if (qr <= m) {
                return query(node * 2, l, m, ql, qr);
            }

            if (ql > m) {
                return query(node * 2 + 1, m + 1, r, ql, qr);
            }

            Data lRes = query(node * 2, l, m, ql, qr);
            Data rRes = query(node * 2 + 1, m + 1, r, ql, qr);
            return mergeData(lRes, rRes);
        }
    }
}