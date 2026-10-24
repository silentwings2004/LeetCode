package LC3901_4200;
import java.util.*;
public class LC4087_MaximumSubarraySumofFixedLengthAfteratMostKSwaps {
    /**
     * You are given an integer array nums and two integers length and k.
     *
     * You may perform at most k swap operations on nums.
     *
     * In one swap operation, you may choose two distinct indices i and j and swap nums[i] and nums[j].
     *
     * Return the maximum possible sum of a subarray of length exactly length after performing the swaps.
     *
     * Input: nums = [1,-1,0,2], length = 2, k = 1
     * Output: 3
     *
     * Input: nums = [4,3,2,4], length = 3, k = 2
     * Output: 11
     *
     * Input: nums = [-1,-2], length = 2, k = 0
     * Output: -3
     *
     * Constraints:
     *
     * 1 <= nums.length <= 300
     * -10^9 <= nums[i] <= 10^9
     * 1 <= length <= nums.length
     * 0 <= k <= nums.length
     * @param nums
     * @param length
     * @param k
     * @return
     */
    // S1: Greedy
    // time = O(n^2 * logn), space = O(n)
    public long maxSum(int[] nums, int length, int k) {
        int n = nums.length;
        long res = Long.MIN_VALUE;
        for (int i = 0; i + length - 1 < n; i++) {
            long s = 0;
            List<Integer> in = new ArrayList<>();
            List<Integer> out = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                int x = nums[j];
                if (j >= i && j < i + length) {
                    in.add(x);
                    s += x;
                } else out.add(x);
            }
            Collections.sort(in);
            Collections.sort(out, (o1, o2) -> o2 - o1);

            long[] preIn = new long[in.size() + 1];
            long[] preOut = new long[out.size() + 1];
            for (int j = 0; j < in.size(); j++) preIn[j + 1] = preIn[j] + in.get(j);
            for (int j = 0; j < out.size(); j++) preOut[j + 1] = preOut[j] + out.get(j);

            long best = s;
            int mt = Math.min(k, Math.min(in.size(), out.size()));
            for (int j = 1; j <= mt; j++) {
                long cur = s - preIn[j] + preOut[j];
                if (cur > best) best = cur;
            }
            res = Math.max(res, best);
        }
        return res;
    }

    // S2
    // time = O(nlogn), space = O(n)
    public long maxSum2(int[] nums, int length, int k) {
        int n = nums.length;
        if (length == n) {
            long s = 0;
            for (int x : nums) s += x;
            return s;
        }
        int b = Math.min(k, Math.min(length, n - length));

        long[] tmp = new long[n];
        for (int i = 0; i < n; i++) tmp[i] = nums[i];
        Arrays.sort(tmp);
        long sumTopL = 0;
        for (int i = n - 1; i >= n - length; i--) sumTopL += tmp[i];

        if (b >= 0) {
            Integer[] order = new Integer[n];
            for (int i = 0; i < n; i++) order[i] = i;
            Arrays.sort(order, (o1, o2) -> nums[o2] - nums[o1]);
            boolean[] marked = new boolean[n];
            for (int i = 0; i < length; i++) marked[order[i]] = true;
            int cnt = 0;
            for (int i = 0; i < length; i++) {
                if (marked[i]) cnt++;
            }
            if (cnt >= length - b) return sumTopL;
            for (int l = 1; l + length <= n; l++) {
                if (marked[l - 1]) cnt--;
                if (marked[l + length - 1]) cnt++;
                if (cnt >= length - b) return sumTopL;
            }
        }

        List<Integer> q = new ArrayList<>();
        for (int x : nums) q.add(x);
        q = new ArrayList<>(new HashSet<>(q));
        Collections.sort(q);
        long[] vals = new long[q.size()];
        for (int i = 0; i < q.size(); i++) vals[i] = q.get(i);
        int m = vals.length;
        long[] coord = new long[m + 1];
        for (int i = 0; i < m; i++) coord[i + 1] = vals[i];

        int[] idx = new int[n];
        for (int i = 0; i < n; i++) {
            idx[i] = find(vals, nums[i]) + 1; // 1-index
        }

        Fenwick inside = new Fenwick(m);
        Fenwick outside = new Fenwick(m);
        for (int i = 0; i < length; i++) inside.add(idx[i], 1, nums[i]);
        for (int i = length; i < n; i++)outside.add(idx[i], 1, nums[i]);

        long res = Long.MIN_VALUE;
        for (int l = 0; l + length <= n; l++) {
            long cur;
            if (b == 0) cur = inside.totalSum();
            else {
                long sumIn = inside.sumLargest(length - b, coord);
                long sumOut = outside.sumLargest(b, coord);
                cur = sumIn + sumOut;
            }
            res = Math.max(res, cur);
            if (l + length >= n) break;
            inside.add(idx[l], -1, -nums[l]);
            outside.add(idx[l], 1, nums[l]);
            inside.add(idx[l + length], 1, nums[l + length]);
            outside.add(idx[l + length], -1, -nums[l + length]);
        }
        return res;
    }

    private int find(long[] vals, long t) {
        int l = 0, r = vals.length - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (vals[mid] >= t) r = mid;
            else l = mid + 1;
        }
        return vals[r] >= t ? r : r + 1;
    }

    class Fenwick {
        int n;
        long[] bitCnt, bitSum;
        Fenwick(int n) {
            this.n = n;
            bitCnt = new long[n + 2];
            bitSum = new long[n + 2];
        }

        private void add(int idx, long dCnt, long dSum) {
            for (; idx <= n; idx += idx & -idx) {
                bitCnt[idx] += dCnt;
                bitSum[idx] += dSum;
            }
        }

        private long sumCnt(int idx) {
            long s = 0;
            for (; idx > 0; idx -= idx & -idx) s += bitCnt[idx];
            return s;
        }

        private long sumSum(int idx) {
            long s = 0;
            for (; idx > 0; idx -= idx & -idx) s += bitSum[idx];
            return s;
        }

        private long totalCnt() {
            return sumCnt(n);
        }

        private long totalSum() {
            return sumSum(n);
        }

        private int findKth(long k) {
            int idx = 0;
            int pw = Integer.highestOneBit(n);
            for (int d = pw; d != 0; d >>= 1) {
                int nxt = idx + d;
                if (nxt <= n && bitCnt[nxt] < k) {
                    k -= bitCnt[nxt];
                    idx = nxt;
                }
            }
            return idx + 1;
        }

        private long sumSmallest(long x, long[] coord) {
            if (x <= 0) return 0;
            long tot = totalCnt();
            if (x >= tot) return totalSum();
            int pos = findKth(x);
            long cntBefore = sumCnt(pos - 1);
            long sumBefore = sumSum(pos - 1);
            long need = x - cntBefore;
            return sumBefore + need * coord[pos];
        }

        private long sumLargest(long k, long[] coord) {
            if (k <= 0) return 0;
            long tot = totalCnt();
            if (k >= tot) return totalSum();
            return totalSum() - sumSmallest(tot - k, coord);
        }
    }
}