package LC2401_2700;
import java.util.*;
public class LC2524_MaximumFrequencyScoreofaSubarray {
    /**
     * You are given an integer array nums and a positive integer k.
     *
     * The frequency score of an array is the sum of the distinct values in the array raised to the power of their
     * frequencies, taking the sum modulo 109 + 7.
     *
     * For example, the frequency score of the array [5,4,5,7,4,4] is (43 + 52 + 71) modulo (109 + 7) = 96.
     * Return the maximum frequency score of a subarray of size k in nums. You should maximize the value under the
     * modulo and not the actual value.
     *
     * A subarray is a contiguous part of an array.
     *
     * Input: nums = [1,1,1,2,1,2], k = 3
     * Output: 5
     *
     * Input: nums = [1,1,1,1,1,1], k = 4
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= k <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^6
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    public int maxFrequencyScore(int[] nums, int k) {
        long mod = (long)(1e9 + 7);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int x : nums) map.put(x, map.getOrDefault(x, 0) + 1);
        HashMap<Integer, HashMap<Integer, Long>> pw = new HashMap<>();
        for (int x : map.keySet()) {
            int y = map.get(x);
            long p = 1;
            pw.putIfAbsent(x, new HashMap<>());
            for (int i = 0; i < y; i++) {
                p = p * x % mod;
                pw.get(x).put(i + 1, p);
            }
        }

        int n = nums.length;
        map.clear();
        long sum = 0, res = 0;
        for (int i = 0, j = 0; i < n; i++) {
            int x = nums[i], cnt = map.getOrDefault(x, 0);
            map.put(x, map.getOrDefault(x, 0) + 1);
            sum = ((sum + pw.get(x).get(cnt + 1)) % mod - pw.get(x).getOrDefault(cnt, 0L) + mod) % mod;
            if (i - j + 1 > k) {
                int y = nums[j++];
                map.put(y, map.get(y) - 1);
                cnt = map.get(y);
                sum = ((sum + pw.get(y).getOrDefault(cnt, 0L)) % mod - pw.get(y).get(cnt + 1) + mod) % mod;
            }
            if (i - j + 1 == k) res = Math.max(res, sum);
        }
        return (int) res;
    }
}