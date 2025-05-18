package LC2700_3000;
import java.util.*;
public class LC2916_SubarraysDistinctElementSumofSquaresII {
    /**
     * You are given a 0-indexed integer array nums.
     *
     * The distinct count of a subarray of nums is defined as:
     *
     * Let nums[i..j] be a subarray of nums consisting of all the indices from i to j such that 0 <= i <= j < nums.length.
     * Then the number of distinct values in nums[i..j] is called the distinct count of nums[i..j].
     * Return the sum of the squares of distinct counts of all subarrays of nums.
     *
     * Since the answer may be very large, return it modulo 10^9 + 7.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [1,2,1]
     * Output: 15
     *
     * Input: nums = [2,2]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(n)
    final int N = 100010, mod = (int)1e9 +7;
    int n;
    Node[] tr;
    public int sumCounts(int[] nums) {
        tr = new Node[N << 2];
        n = nums.length;
        build(1, 1, n + 1);
        long[] f = new long[n + 1];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            int v = nums[i - 1];
            int lastIdx = 0;
            if (map.containsKey(v)) lastIdx = map.get(v);
            f[i] = f[i - 1] + (i - lastIdx) + 2 * query(1, lastIdx + 1, i);
            modify(1, lastIdx + 1, i, 1);
            map.put(v, i);
        }

        long res = 0;
        for (int i = 1; i<= n; i++) res = (res + f[i]) % mod;
        return (int)res;
    }

    private void build(int u, int l, int r) {
        tr[u] = new Node();
        tr[u].l = l;
        tr[u].r = r;
        if (l != r) {
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
            left.add += root.add;
            left.sum += (long)(left.r - left.l + 1) * root.add;
            right.add += root.add;
            right.sum += (long)(right.r - right.l + 1) * root.add;
            root.add = 0;
        }
    }

    private void modify(int u, int l, int r, int d) {
        if (tr[u].l >= l && tr[u].r <= r) {
            tr[u].sum += (long)(tr[u].r - tr[u].l + 1) * d;
            tr[u].add += d;
        } else { // 一定要分裂
            pushdown(u);
            int mid = tr[u].l + tr[u].r >> 1;
            if (l <= mid) modify(u << 1, l, r, d);
            if (r > mid) modify(u << 1 | 1, l, r, d);
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
        int l, r;
        long sum, add;
    }
}
/**
 * ref: LC2262
 * lazy 线段树
 */