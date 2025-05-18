package LC3301_3600;
import java.util.*;
public class LC3520_MinimumThresholdforInversionPairsCount {
    /**
     * You are given an array of integers nums and an integer k.
     *
     * An inversion pair with a threshold x is defined as a pair of indices (i, j) such that:
     *
     * i < j
     * nums[i] > nums[j]
     * The difference between the two numbers is at most x (i.e. nums[i] - nums[j] <= x).
     * Your task is to determine the minimum integer min_threshold such that there are at least k inversion pairs with
     * threshold min_threshold.
     *
     * If no such integer exists, return -1.
     *
     * Input: nums = [1,2,3,4,3,2,1], k = 7
     * Output: 2
     *
     * Input: nums = [10,9,9,9,1], k = 4
     * Output: 8
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^4
     * 1 <= nums[i] <= 10^9
     * 1 <= k <= 10^9
     * @param nums
     * @param k
     * @return
     */
    // time = O(logk * nlogn), space = O(n)
    List<Integer> q;
    public int minThreshold(int[] nums, int k) {
        q = new ArrayList<>();
        int l = 0, r = nums[0];
        for (int x : nums) {
            q.add(x);
            r = Math.max(r, x);
        }
        q = new ArrayList<>(new HashSet<>(q));
        Collections.sort(q);
        while (l < r) {
            int mid = l + r >> 1;
            if (check(nums, k, mid)) r = mid;
            else l = mid + 1;
        }
        return check(nums, k, r) ? r : -1;
    }

    private boolean check(int[] nums, int k, int mid) {
        int m = q.size(), n = nums.length, cnt = 0;
        Fenwick fen = new Fenwick(m);
        for (int i = 0; i < n; i++) {
            cnt += fen.rangeSum(find(nums[i]) + 1, find(nums[i] + mid) + 1);
            fen.add(find(nums[i]), 1);
        }
        return cnt >= k;
    }

    private int find(int x) {
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
            this.n = n;
            this.a = new int[n + 1];
        }

        private void add(int x, int v) {
            for (int i = x + 1; i <= n; i += i & -i) {
                a[i - 1] = a[i - 1] + v;
            }
        }

        private int sum(int x) {
            int ans = 0;
            for (int i = x; i > 0; i -= i & -i) {
                ans = ans + a[i - 1];
            }
            return ans;
        }

        private int rangeSum(int l, int r) {
            return sum(r) - sum(l);
        }
    }
}