package LC3301_3600;
import java.util.*;
public class LC3410_MaximizeSubarraySumAfterRemovingAllOccurrencesofOneElement {
    /**
     * You are given an integer array nums.
     *
     * You can do the following operation on the array at most once:
     *
     * Choose any integer x such that nums remains non-empty on removing all occurrences of x.
     * Remove all occurrences of x from the array.
     * Return the maximum subarray sum across all possible resulting arrays.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [-3,2,-2,-1,3,-2,3]
     * Output: 7
     *
     * Input: nums = [1,2,3,4]
     * Output: 10
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * -10^6 <= nums[i] <= 10^6
     * @param nums
     * @return
     */
    // S1: 线段树
    // time = O(nlogn), space = O(n)
    public long maxSubarraySum(int[] nums) {
        int n = nums.length;
        SegmentTree seg = new SegmentTree(nums);
        long res = seg.tr[1].ans;
        if (res <= 0) return res;

        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] < 0) {
                map.putIfAbsent(nums[i], new ArrayList<>());
                map.get(nums[i]).add(i);
            }
        }
        for (List<Integer> v : map.values()) {
            for (int x : v) seg.modify(1, 0, n - 1, x, 0);
            res = Math.max(res, seg.tr[1].ans);
            for (int x : v) seg.modify(1, 0, n - 1, x, nums[x]);
        }
        return res;
    }

    class SegmentTree {
        private Info[] tr;

        public SegmentTree(int[] nums) {
            int n = nums.length;
            int m = 32 - Integer.numberOfLeadingZeros(n);
            tr = new Info[2 << m];
            build(nums, 1, 0, n - 1);
        }

        private Info merge(Info a, Info b) {
            Info res = new Info(0, 0, 0, 0);
            res.ans = Math.max(Math.max(a.ans, b.ans), a.suf + b.pre);
            res.sum = a.sum + b.sum;
            res.pre = Math.max(a.pre, a.sum + b.pre);
            res.suf = Math.max(b.suf, a.suf + b.sum);
            return res;
        }

        private void pushup(int u) {
            tr[u] = merge(tr[u << 1], tr[u << 1 | 1]);
        }

        private void modify(int u, int l, int r, int x, int v) {
            if (l == r) {
                tr[u] = new Info(v, v, v, v);
                return;
            }
            int mid = l + r >> 1;
            if (x <= mid) modify(u << 1, l, mid, x, v);
            else modify(u << 1 | 1, mid + 1, r, x, v);
            pushup(u);
        }

        private Info query(int u, int l, int r, int L, int R) {
            if (L <= l && r <= R) return tr[u];
            int mid = l + r >> 1;
            Info res = new Info(0, 0, 0, 0);
            if (R <= mid) res = query(u << 1, l, mid, L, R);
            if (L > mid) res = merge(res, query(u << 1 | 1, mid + 1, r, L, R));
            return res;
        }

        private void build(int[] nums, int u, int l, int r) {
            if (l == r) {
                tr[u] = new Info(nums[l], nums[l], nums[l], nums[l]);
                return;
            }
            int mid = l + r >> 1;
            build(nums, u << 1, l, mid);
            build(nums, u << 1 | 1, mid + 1, r);
            pushup(u);
        }

        private class Info {
            long ans, sum, pre, suf;
            public Info(long ans, long sum, long pre, long suf) {
                this.ans = ans;
                this.sum = sum;
                this.pre = pre;
                this.suf = suf;
            }
        }
    }
}
/**
 * 把 x 改成 0 之后，再去算最大子数组和 => 线段树
 */
