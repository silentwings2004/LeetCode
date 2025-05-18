package LC3301_3600;
import java.util.*;
public class LC3505_MinimumOperationstoMakeElementsWithinKSubarraysEqual {
    /**
     * You are given an integer array nums and two integers, x and k. You can perform the following operation any number
     * of times (including zero):
     *
     * Increase or decrease any element of nums by 1.
     * Return the minimum number of operations needed to have at least k non-overlapping subarrays of size exactly x in
     * nums, where all elements within each subarray are equal.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [5,-2,1,3,7,3,6,4,-1], x = 3, k = 2
     * Output: 8
     *
     * Input: nums = [9,-2,-2,-2,1,5], x = 2, k = 2
     * Output: 3
     *
     * Constraints:
     *
     * 2 <= nums.length <= 10^5
     * -106 <= nums[i] <= 10^6
     * 2 <= x <= nums.length
     * 1 <= k <= 15
     * 2 <= k * x <= nums.length
     * @param nums
     * @param x
     * @param k
     * @return
     */
    // time = O(nlogn + n * k), space = O(n * k)
    final long inf = (long)1E18;
    TreeMap<Integer, Integer> minMap, maxMap;
    int a, b;
    long smin, smax;
    public long minOperations(int[] nums, int x, int k) {
        minMap = new TreeMap<>();
        maxMap = new TreeMap<>((o1, o2) -> o2 - o1);
        int n = nums.length;
        long[] w = new long[n - x + 1];
        for (int i = 0; i < n; i++) {
            add(nums[i]);
            if (i >= x - 1) {
                w[i - x + 1] = getCost();
                remove(nums[i - x + 1]);
            }
        }

        long[][] f = new long[n + 1][k + 1];
        for (int i = 0; i <= n; i++) Arrays.fill(f[i], inf);
        f[0][0] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                if (f[i][j] == inf) continue;
                if (i + 1 <= n) f[i + 1][j] = Math.min(f[i + 1][j], f[i][j]);
                if (j < k && i + x <= n) f[i + x][j + 1] = Math.min(f[i + x][j + 1], f[i][j] + w[i]);
            }
        }
        long res = inf;
        for (int i = 0; i <= n; i++) res = Math.min(res, f[i][k]);
        return res;
    }

    private void add(int x) {
        maxMap.put(x, maxMap.getOrDefault(x, 0) + 1);
        int y = maxMap.firstKey();
        minMap.put(y, minMap.getOrDefault(y, 0) + 1);
        maxMap.put(y, maxMap.get(y) - 1);
        if (maxMap.get(y) == 0) maxMap.remove(y);
        smax += x;
        smax -= y;
        smin += y;
        a++;
        rebalance();
    }

    private void remove(int x) {
        if (minMap.containsKey(x)) {
            minMap.put(x, minMap.get(x) - 1);
            if (minMap.get(x) == 0) minMap.remove(x);
            smin -= x;
            a--;
        } else {
            maxMap.put(x, maxMap.get(x) - 1);
            if (maxMap.get(x) == 0) maxMap.remove(x);
            smax -= x;
            b--;
        }
        rebalance();
    }

    private void rebalance() {
        while (a - b > 1) {
            int x = minMap.firstKey();
            minMap.put(x, minMap.get(x) - 1);
            if (minMap.get(x) == 0) minMap.remove(x);
            maxMap.put(x, maxMap.getOrDefault(x, 0) + 1);
            smin -= x;
            smax += x;
            a--;
            b++;
        }
        while (a < b) {
            int x = maxMap.firstKey();
            maxMap.put(x, maxMap.get(x) - 1);
            if (maxMap.get(x) == 0) maxMap.remove(x);
            minMap.put(x, minMap.getOrDefault(x, 0) + 1);
            smax -= x;
            smin += x;
            b--;
            a++;
        }
    }

    private int getMedian() {
        return minMap.firstKey();
    }

    private long getCost() {
        int median = getMedian();
        return 1L * median * b - smax + smin - 1L * median * a;
    }
}