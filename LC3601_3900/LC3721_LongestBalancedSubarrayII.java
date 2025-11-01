package LC3601_3900;
import java.util.*;
public class LC3721_LongestBalancedSubarrayII {
    /**
     * You are given an integer array nums.
     *
     * A subarray is called balanced if the number of distinct even numbers in the subarray is equal to the number of
     * distinct odd numbers.
     *
     * Return the length of the longest balanced subarray.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [2,5,4,3]
     * Output: 4
     *
     * Input: nums = [3,2,2,5,4]
     * Output: 5
     *
     * Input: nums = [1,2,3,2]
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
    Node[] tr;
    int n;
    public int longestBalanced(int[] nums) {
        n = nums.length;
        int mx = nums[0];
        for (int x : nums) mx = Math.max(mx, x);
        int[] f = new int[mx + 1];
        Arrays.fill(f, -1);

        tr = new Node[n << 2];
        build(1, 0, n - 1);
        int res = 0;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int v = x % 2 == 0 ? 1 : -1;
            int l = f[x] + 1, r = i;
            if (l <= r) modify(1, l, r, v);
            f[x] = i;
            int j = get(1, 0, i);
            if (j != n + 1 && j <= i) res = Math.max(res, i - j + 1);
        }
        return res;
    }

    private void build(int u, int l, int r) {
        tr[u] = new Node(l, r);
        if (l == r) tr[u].mn = tr[u].mx = tr[u].add = 0;
        else {
            int mid = l + r >> 1;
            build(u << 1, l, mid);
            build(u << 1 | 1, mid + 1, r);
            pushup(u);
        }
    }

    private void pushup(int u) {
        tr[u].mn = Math.min(tr[u << 1].mn, tr[u << 1 | 1].mn);
        tr[u].mx = Math.max(tr[u << 1].mx, tr[u << 1 | 1].mx);
    }

    private void add(Node t, int v) {
        t.mn += v;
        t.mx += v;
        t.add += v;
    }

    private void pushdown(int u) {
        if (tr[u].add != 0 && tr[u].l != tr[u].r) {
            add(tr[u << 1], tr[u].add);
            add(tr[u << 1 | 1], tr[u].add);
            tr[u].add = 0;
        }
    }

    private void modify(int u, int l, int r, int x) {
        if (tr[u].l >= l && tr[u].r <= r) add(tr[u], x);
        else {
            pushdown(u);
            int mid = tr[u].l + tr[u].r >> 1;
            if (l <= mid) modify(u << 1, l, r, x);
            if (r > mid) modify(u << 1 | 1, l, r, x);
            pushup(u);
        }
    }

    private int get(int u, int l, int r) {
        if (tr[u].l > r || tr[u].r < l) return n + 1;
        if (tr[u].l >= l && tr[u].r <= r) {
            if (tr[u].mn > 0 || tr[u].mx < 0) return n + 1;
            if (tr[u].l == tr[u].r) return tr[u].mn == 0 ? tr[u].l : n + 1;
            pushdown(u);
            int p = get(u << 1, l, r);
            if (p != n + 1) return p;
            return get(u << 1 | 1, l, r);
        }
        pushdown(u);
        int p = get(u << 1, l, r);
        if (p != n + 1) return p;
        return get(u << 1 | 1, l, r);
    }

    class Node {
        int l, r;
        int mn, mx, add;
        public Node(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }
}
/**
 * 包含2个1
 * 包含1个1
 * 这两件事是相等的
 * 重复元素只能统计一次
 * 怎么样把重复元素，只会统计一次，那么就会变成LC525
 * 保留最近一次出现的元素
 * 最近一次出现的偶数视作 -1
 * 最近一次出现的奇数视作 1
 * 其余重复出现的全部视作 0
 * 子数组和为 0 => 合法子数组
 *
 * 由于重复出现的视作 0，所以前缀和会变！
 * 所以要引入一个数据结构，支持我们动态修改前缀和！
 *
 * 更新操作：
 * 1. 首次访问一个元素的时候，我会把前缀和的某一段子数组 +1 或者 -1
 * 2. 当我们再次访问一个元素的时候，设上一次的下标是 j，当前下标是 i，那么把[j,i-1]之前的增加撤销掉
 * 如果之前是+1，那么现在变成区间-1
 * 如果之前是-1, 那么现在变成区间+1
 * => 线段树
 *
 * 查询：
 * 在前缀和数组的一个区间(子数组)内，找到一个数首次出现的位置
 */