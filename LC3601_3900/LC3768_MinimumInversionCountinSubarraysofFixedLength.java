package LC3601_3900;
import java.util.*;
public class LC3768_MinimumInversionCountinSubarraysofFixedLength {
    /**
     * You are given an integer array nums of length n and an integer k.
     *
     * An inversion is a pair of indices (i, j) from nums such that i < j and nums[i] > nums[j].
     *
     * The inversion count of a subarray is the number of inversions within it.
     *
     * Return the minimum inversion count among all subarrays of nums with length k.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [3,1,2,5,4], k = 3
     * Output: 0
     *
     * Input: nums = [5,3,2,1], k = 4
     * Output: 6
     *
     * Input: nums = [2,1], k = 1
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * 1 <= k <= n
     * @param nums
     * @param k
     * @return
     */
    // time = O(nlogn), sapce = O(n)
    public long minInversionCount(int[] nums, int k) {
        int n = nums.length;
        int[] b = nums.clone();
        Arrays.sort(b);
        int id = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int x : b) {
            if (!map.containsKey(x)) map.put(x, id++);
        }

        int m = map.size();
        Fenwick fen = new Fenwick(m);
        long s = 0;
        for (int i = 0; i < k; i++) {
            int r = map.get(nums[i]);
            s += fen.rangeSum(r + 1, m);
            fen.add(r, 1);
        }
        long res = s;
        for (int i = k; i < n; i++) {
            int rout = map.get(nums[i - k]);
            s -= fen.rangeSum(0, rout);
            fen.add(rout, -1);

            int rin = map.get(nums[i]);
            s += fen.rangeSum(rin + 1, m);
            fen.add(rin, 1);

            res = Math.min(res, s);
        }
        return res;
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