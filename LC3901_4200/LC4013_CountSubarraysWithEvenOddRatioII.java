package LC3901_4200;
import java.util.*;
public class LC4013_CountSubarraysWithEvenOddRatioII {
    /**
     * You are given an integer array nums and two integers a and b.
     *
     * For a subarray, let:
     *
     * x be the number of even elements.
     * y be the number of odd elements.
     * The ratio of even to odd numbers in a subarray is defined as x / y, where the ratio is compared by its exact
     * rational value.
     *
     * A subarray is considered valid if:
     *
     * y > 0, and
     * x / y <= a / b.
     * Return the number of valid subarrays in nums.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [1,2,1,2], a = 3, b = 2
     * Output: 7
     *
     * Input: nums = [2,2,1], a = 2, b = 1
     * Output: 3
     *
     * Input: nums = [2,2,2], a = 1, b = 1
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * 1 <= a, b <= 10^9
     * @param nums
     * @param a
     * @param b
     * @return
     */
    // S1: 值域树状数组
    // time = O(nlogm), space = O(n + m)
    public long countRatioSubarrays(int[] nums, int a, int b) {
        int n = nums.length;
        long[] s = new long[n + 1];
        List<Long> q = new ArrayList<>();
        q.add(s[0]);
        for (int i = 1; i <= n; i++) {
            s[i] = s[i - 1] + (nums[i - 1] % 2 == 0 ? b : -a);
            q.add(s[i]);
        }
        q = new ArrayList<>(new HashSet<>(q));
        Collections.sort(q);
        int m = q.size();

        Fenwick fen = new Fenwick(m);
        int t = 0;
        long res = 0;
        for (long x : s) {
            int r = find(q, x);
            int smaller = fen.sum(r);
            res += t - smaller;
            fen.add(r, 1);
            t++;
        }
        return res;
    }

    private int find(List<Long> q, long x) {
        int l = 0, r = q.size() - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (q.get(mid) >= x) r = mid;
            else l = mid + 1;
        }
        return q.get(r) >= x ? r : r + 1;
    }

    class Fenwick {
        private int n;
        private int[] a;

        public Fenwick(int n) {
            init(n);
        }

        public void init(int n) {
            this.n = n;
            this.a = new int[n];
        }

        public void add(int x, int v) {
            for (int i = x + 1; i <= n; i += i & -i)
                a[i - 1] += v;
        }

        public int sum(int x) {
            int ans = 0;
            for (int i = x; i > 0; i -= i & -i)
                ans += a[i - 1];
            return ans;
        }

        public int rangeSum(int l, int r) { // 左开右闭
            return sum(r) - sum(l);
        }

        public int select(int k) {
            int x = 0, cur = 0;
            for (int i = Integer.highestOneBit(n); i > 0; i /= 2) {
                if (x + i <= n && cur + a[x + i - 1] <= k) {
                    x += i;
                    cur += a[x - 1];
                }
            }
            return x;
        }
    }

    // S2: 归并排序
    // time = O(nlogn), space = O(n)
    public long countRatioSubarrays2(int[] nums, int a, int b) {
        int n = nums.length;
        long[] s = new long[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + (nums[i - 1] % 2 == 0 ? -b : a);
        return merge(s, 0, s.length - 1);
    }

    private long merge(long[] s, int l, int r) {
        if (l >= r) return 0;

        int mid = l + r >> 1;
        long res = merge(s, l, mid) + merge(s, mid + 1, r);

        int k = 0, i = l, j = mid + 1;
        long[] tmp = new long[r - l + 1];
        while (i <= mid && j <= r) {
            if (s[i] <= s[j]) tmp[k++] = s[i++];
            else {
                tmp[k++] = s[j++];
                res += i - l;
            }
        }
        while (i <= mid) tmp[k++] = s[i++];
        while (j <= r) {
            tmp[k++] = s[j++];
            res += i - l;
        }
        for (i = l, j = 0; i <= r; i++, j++) s[i] = tmp[j];
        return res;
    }
}
/**
 * x / y <= a / b => a * y - b * x >= 0
 * 把 nums 中的奇数视作 a，偶数视作 −b，得到数组 arr。
 * 问题等价为：计算 arr 中有多少个元素和 >= 0 的非空连续子数组。
 * arr 的子数组 [L,R−1] 的元素和等于 s[R]−s[L]。
 * 问题等价于：有多少个下标对 (L,R) 满足 0 <= L < R <= n 且 s[R]−s[L] ≥ 0？
 * 枚举 R，我们需要知道在 R 的左边有多少个 s[L] <= s[R]。
 * 类似逆序对，这可以用值域树状数组或归并排序计算。
 */