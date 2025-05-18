package LC3301_3600;
import java.util.*;
public class LC3321_FindXSumofAllKLongSubarraysII {
    /**
     * You are given an array nums of n integers and two integers k and x.
     *
     * The x-sum of an array is calculated by the following procedure:
     *
     * Count the occurrences of all elements in the array.
     * Keep only the occurrences of the top x most frequent elements. If two elements have the same number of
     * occurrences, the element with the bigger value is considered more frequent.
     * Calculate the sum of the resulting array.
     * Note that if an array has less than x distinct elements, its x-sum is the sum of the array.
     *
     * Return an integer array answer of length n - k + 1 where answer[i] is the x-sum of the subarray
     * nums[i..i + k - 1].
     *
     * Input: nums = [1,1,2,2,3,4,2,3], k = 6, x = 2
     *
     * Output: [6,10,12]
     *
     * Input: nums = [3,8,7,8,7,5], k = 2, x = 2
     *
     * Output: [11,15,15,15,12]
     *
     * Constraints:
     *
     * nums.length == n
     * 1 <= n <= 10^5
     * 1 <= nums[i] <= 10^9
     * 1 <= x <= k <= nums.length
     * @param nums
     * @param k
     * @param x
     * @return
     */
    // time = O(nlogn), space = O(n)
    TreeSet<int[]> minh, maxh;
    HashMap<Integer, Integer> map;
    long v;
    int x;
    public long[] findXSum(int[] nums, int k, int x) {
        minh = new TreeSet<>((o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        maxh = new TreeSet<>((o1, o2) -> o1[0] != o2[0] ? o2[0] - o1[0] : o2[1] - o1[1]);
        map = new HashMap<>();
        v = 0;
        this.x = x;
        int n = nums.length;
        long[] res = new long[n - k + 1];
        long sum = 0;

        for (int i = 0, j = 0; i < n; i++) {
            sum += nums[i];
            add(nums[i]);
            if (i - j + 1 == k) {
                res[j] = minh.size() < x ? sum : v;
                remove(nums[j]);
                sum -= nums[j];
                j++;
            }
        }
        return res;
    }

    private void add(int y) {
        int[] t = remove2(y);
        t[0]++;
        map.put(y, map.getOrDefault(y, 0) + 1);
        add2(t);
        rebalance();
    }

    private void remove(int y) {
        int[] t = remove2(y);
        t[0]--;
        map.put(y, map.get(y) - 1);
        if (map.get(y) == 0) map.remove(y);
        if (t[0] > 0) {
            add2(t);
            rebalance();
        }
    }

    private void add2(int[] t) {
        maxh.add(t);
        t = maxh.first();
        maxh.remove(t);
        minh.add(t);
        v += 1L * t[0] * t[1];
    }

    private int[] remove2(int y) {
        int cnt = map.getOrDefault(y, 0);
        int[] t = new int[]{cnt, y};
        if (minh.contains(t)) {
            minh.remove(t);
            v -= 1L * t[0] * t[1];
        } else maxh.remove(t);
        return t;
    }

    private void rebalance() {
        if (minh.size() > x) {
            int[] t = minh.first();
            minh.remove(t);
            v -= 1L * t[0] * t[1];
            maxh.add(t);
        }
        while (minh.size() < x && maxh.size() > 0) {
            int[] t = maxh.first();
            maxh.remove(t);
            minh.add(t);
            v += 1L * t[0] * t[1];
        }
    }
}