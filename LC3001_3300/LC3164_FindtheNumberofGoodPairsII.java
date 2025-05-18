package LC3001_3300;
import java.util.*;
public class LC3164_FindtheNumberofGoodPairsII {
    /**
     * You are given 2 integer arrays nums1 and nums2 of lengths n and m respectively. You are also given a positive
     * integer k.
     *
     * A pair (i, j) is called good if nums1[i] is divisible by nums2[j] * k (0 <= i <= n - 1, 0 <= j <= m - 1).
     *
     * Return the total number of good pairs.
     *
     * Input: nums1 = [1,3,4], nums2 = [1,3,4], k = 1
     * Output: 5
     *
     * Input: nums1 = [1,2,4,12], nums2 = [2,4], k = 3
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= n, m <= 10^5
     * 1 <= nums1[i], nums2[j] <= 10^6
     * 1 <= k <= 103
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    // S1
    // time = O(n * sqrt(U / k) + m), space = O(U / k)
    HashMap<Integer, Integer> map;
    public long numberOfPairs(int[] nums1, int[] nums2, int k) {
        long res = 0;
        map = new HashMap<>();
        int n = nums1.length, m = nums2.length, cnt = 0;
        for (int x : nums1) get_divisors(x);
        for (int x : nums2) {
            res += map.getOrDefault(x * k, 0);
        }
        return res;
    }

    private void get_divisors(int n) {
        for (int i = 1; i <= n / i; i++) {
            if (n % i == 0) {
                map.put(i, map.getOrDefault(i, 0) + 1);
                if (i != n / i) map.put(n / i, map.getOrDefault(n / i, 0) + 1);
            }
        }
    }

    // S2
    // time = O(n + (U / k) * logm), space = O(n + m)
    public long numberOfPairs2(int[] nums1, int[] nums2, int k) {
        long res = 0;
        int n = nums1.length, m = nums2.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        HashMap<Integer, Integer> cnt = new HashMap<>();
        int mx = 0;
        for (int x : nums1) {
            if (x % k != 0) continue;
            map.put(x / k, map.getOrDefault(x / k, 0) + 1);
            mx = Math.max(mx, x / k);
        }
        for (int x : nums2) cnt.put(x, cnt.getOrDefault(x, 0) + 1);
        for (int v : cnt.keySet()) {
            for (int j = 1; v * j <= mx; j++) {
                if (map.containsKey(v * j)) res += 1L * map.get(v * j) * cnt.get(v);
            }
        }
        return res;
    }
}