package LC3301_3600;
import java.util.*;
public class LC3574_MaximizeSubarrayGCDScore {
    /**
     * You are given an array of positive integers nums and an integer k.
     *
     * You may perform at most k operations. In each operation, you can choose one element in the array and double its
     * value. Each element can be doubled at most once.
     *
     * The score of a contiguous subarray is defined as the product of its length and the greatest common divisor (GCD)
     * of all its elements.
     *
     * Your task is to return the maximum score that can be achieved by selecting a contiguous subarray from the
     * modified array.
     *
     * Note:
     *
     * The greatest common divisor (GCD) of an array is the largest integer that evenly divides all the array elements.
     *
     * Input: nums = [2,4], k = 1
     * Output: 8
     *
     * Input: nums = [3,5,7], k = 2
     * Output: 14
     *
     * Input: nums = [5,5,5], k = 1
     * Output: 15
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 1500
     * 1 <= nums[i] <= 10^9
     * 1 <= k <= n
     * @param nums
     * @param k
     * @return
     */
    // S1
    // time = O(n * (n + logU)), space = O(1)
    public long maxGCDScore(int[] nums, int k) {
        int n = nums.length;
        long res = 0;
        for (int i = 0; i < n; i++) {
            int minv = Integer.MAX_VALUE, cnt = 0, g = 0;
            for (int j = i; j >= 0; j--) {
                int x = nums[j];
                int lowbit = x & -x;
                if (lowbit < minv) {
                    minv = lowbit;
                    cnt = 1;
                } else if (lowbit == minv) cnt++;
                g = gcd(g, x);
                int ng = cnt <= k ? g * 2 : g;
                res = Math.max(res, 1L * ng * (i - j + 1));
            }
        }
        return res;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // S2: log trick
    // time = O(nlogU), space = O(n + logU)
    public long maxGCDScore2(int[] nums, int k) {
        int n = nums.length, mx = 0;
        for (int x: nums) mx = Math.max(mx, x);
        int m = 32 - Integer.numberOfLeadingZeros(mx);
        List<Integer>[] pos = new List[m];
        for (int i = 0; i < m; i++) pos[i] = new ArrayList<>();
        int[][] intervals = new int[m + 1][3];
        int sz = 0;

        long res = 0;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int tz = Integer.numberOfTrailingZeros(x);
            pos[tz].add(i); // 用 tz 代替 x 的 lowbit
            // 更新每个区间的 gcd
            for (int j = 0; j < sz; j++) intervals[j][0] = gcd(intervals[j][0], x);
            intervals[sz][0] = x;
            intervals[sz][1] = i - 1;
            intervals[sz][2] = i;
            sz++;

            // 合并 g 相通的区间(去重)
            int idx = 1;
            for (int j = 1; j < sz; j++) {
                if (intervals[j][0] != intervals[j - 1][0]) {
                    intervals[idx][0] = intervals[j][0];
                    intervals[idx][1] = intervals[j][1];
                    intervals[idx][2] = intervals[j][2];
                    idx++;
                } else {
                    intervals[idx - 1][2] = intervals[j][2];
                }
            }
            sz = idx;

            // 此时我们将区间 [0,i] 划分成了 size 个左开右闭区间
            // 对于 intervals 中的 (l,r]，对于任意 j∈(l,r]，gcd(区间[j,i]) 的计算结果均为 g
            for (int j = 0; j < sz; j++) {
                int g = intervals[j][0];
                int l = intervals[j][1];
                int r = intervals[j][2];

                // 不做任何操作
                res = Math.max(res, 1L * g * (i - l));

                // 看看能否乘 2
                List<Integer> p = pos[Integer.numberOfTrailingZeros(g)];
                int minL = p.size() > k ? Math.max(l, p.get(p.size() - k - 1)) : l;
                if (minL < r) res = Math.max(res, 1L * g * 2 * (i - minL)); // 可以乘 2
            }
        }
        return res;
    }
}