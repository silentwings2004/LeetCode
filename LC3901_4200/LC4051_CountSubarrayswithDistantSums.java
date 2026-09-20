package LC3901_4200;
import java.util.*;
public class LC4051_CountSubarrayswithDistantSums {
    /**
     * You are given an integer array nums and two integers goal and k.
     *
     * A subarray nums[i..j] is considered distant if the absolute difference between its sum and goal is at least k.
     *
     * Return the number of distant subarrays.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [1,2,1], goal = 4, k = 1
     * Output: 5
     *
     * Input: nums = [2,-1,3], goal = 2, k = 2
     * Output: 2
     *
     * Input: nums = [-3,1,2], goal = 0, k = 3
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * -10^9 <= nums[i] <= 10^9
     * -10^9 <= goal <= 10^9
     * 0 <= k <= 10^9
     * @param nums
     * @param goal
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(n)
    public long distantSubarrays(int[] nums, int goal, int k) {
        int n = nums.length;
        long tot = (n + 1L) * n / 2;
        if (k == 0) return tot;

        HashSet<Long> set = new HashSet<>();
        long s = 0;
        set.add(s);
        for (int x : nums) {
            s += x;
            set.add(s);
        }

        List<Long> q = new ArrayList<>(set);
        Collections.sort(q);

        int m = q.size();
        Fenwick fen = new Fenwick(m);
        int L = goal - k + 1;
        int R = goal + k - 1;
        long inside = 0;
        s = 0;
        for (int i = 0; i <= n; i++) {
            if (i > 0) {
                s += nums[i - 1];
                long low = s - R;
                long high = s - L;
                int l = lowerBound(q, low);
                int r = upperBound(q, high);
                inside += fen.rangeSum(l, r + 1);
            }
            int idx = lowerBound(q, s);
            fen.add(idx, 1);
        }
        return tot - inside;
    }

    private int lowerBound(List<Long> q, long x) {
        int l = 0, r = q.size() - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (q.get(mid) < x) l = mid + 1;
            else r = mid;
        }
        return q.get(r) >= x ? r : r + 1;
    }

    private int upperBound(List<Long> q , long x) {
        int l = 0, r = q.size() - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (q.get(mid) <= x) l = mid;
            else r = mid - 1;
        }
        return q.get(r) <= x ? r : r - 1;
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
}