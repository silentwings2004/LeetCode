package LC3301_3600;
import java.util.*;
public class LC3422_MinimumOperationstoMakeSubarrayElementsEqual {
    /**
     * You are given an integer array nums and an integer k. You can perform the following operation any number of times:
     *
     * Increase or decrease any element of nums by 1.
     * Return the minimum number of operations required to ensure that at least one
     * subarray
     *  of size k in nums has all elements equal.
     *
     * Input: nums = [4,-3,2,1,-4,6], k = 3
     * Output: 5
     *
     * Input: nums = [-2,-2,3,1,4], k = 2
     * Output: 0
     *
     * Constraints:
     *
     * 2 <= nums.length <= 10^5
     * -10^6 <= nums[i] <= 10^6
     * 2 <= k <= nums.length
     * @param nums
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(n)
    TreeMap<Integer, Integer> minh, maxh;
    long s1, s2;
    int cnt1, cnt2;
    public long minOperations(int[] nums, int k) {
        minh = new TreeMap<>();
        maxh = new TreeMap<>((o1, o2) -> o2 - o1);
        int n = nums.length;
        long res = Long.MAX_VALUE;
        for (int i = 0, j = 0; i < n; i++) {
            add(nums[i]);
            if (i - j + 1 == k) {
                res = Math.min(res, cal());
                remove(nums[j++]);
            }
        }
        return res;
    }

    private long cal() {
        int tot = cnt1 + cnt2;
        int med = tot % 2 == 1 ? minh.firstKey() : (minh.firstKey() + maxh.firstKey()) / 2;
        return s1 - 1L * med * cnt1 + 1L * med * cnt2 - s2;
    }

    private void add(int x) {
        maxh.put(x, maxh.getOrDefault(x, 0) + 1);
        s2 += x;
        cnt2++;
        int y = maxh.firstKey();
        maxh.put(y, maxh.get(y) - 1);
        if (maxh.get(y) == 0) maxh.remove(y);
        s2 -= y;
        cnt2--;
        minh.put(y, minh.getOrDefault(y, 0) + 1);
        s1 += y;
        cnt1++;
        rebalance();
    }

    private void rebalance() {
        if (cnt1 - cnt2 > 1) {
            int x = minh.firstKey();
            minh.put(x, minh.get(x) - 1);
            if (minh.get(x) == 0) minh.remove(x);
            s1 -= x;
            cnt1--;
            maxh.put(x, maxh.getOrDefault(x, 0) + 1);
            s2 += x;
            cnt2++;
        }
    }

    private void remove(int x) {
        if (minh.containsKey(x)) {
            minh.put(x, minh.get(x) - 1);
            if (minh.get(x) == 0) minh.remove(x);
            s1 -= x;
            cnt1--;
        } else {
            maxh.put(x, maxh.get(x) - 1);
            if (maxh.get(x) == 0) maxh.remove(x);
            s2 -= x;
            cnt2--;
        }
        rebalance();
    }
}