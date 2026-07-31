package LC3901_4200;
import java.util.*;
public class LC4005_MinimumOperationstoMakeArrayEqualIII {
    /**
     * You are given an integer array nums.
     *
     * In one operation, you may choose any element nums[i] and perform one of the following:
     *
     * Multiply nums[i] by an integer k, where k >= 2.
     * Divide nums[i] by an integer k, where 2 <= k < nums[i], provided that nums[i] is divisible by k.
     * Return the minimum number of operations required to make all elements of nums equal.
     *
     * Input: nums = [6,12,8]
     * Output: 3
     *
     * Input: nums = [5,15,20]
     * Output: 2
     *
     * Input: nums = [7,7,7]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n * sqrt(M)), space = O(m * sqrt(M))
    public long minOperations(int[] nums) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int x : nums) map.put(x, map.getOrDefault(x, 0) + 1);
        int ones = map.getOrDefault(1, 0);
        if (ones == n) return 0;

        HashMap<Integer, Integer> map2 = new HashMap<>();
        for (int k : map.keySet()) {
            if (k == 1) continue;
            int v = map.get(k);
            for (int i = 1; i * i <= k; i++) {
                if (k % i == 0) {
                    if (i > 1) map2.put(i, map2.getOrDefault(i, 0) + v);
                    int other = k / i;
                    if (other != i && other < k) {
                        map2.put(other, map2.getOrDefault(other, 0) + v);
                    }
                }
            }
        }

        long minOps = n;
        for (int k : map.keySet()) {
            if (k == 1) continue;
            long cx = map.get(k);
            long dx = 0;
            for (int i = 1; i * i <= k; i++) {
                if (k % i == 0) {
                    if (i > 1 && i != k && map.containsKey(i)) dx += map.get(i);
                    int other = k / i;
                    if (other != i && other != k && map.containsKey(other)) dx += map.get(other);
                }
            }
            long mx = map2.getOrDefault(k, 0);
            long curOps = 2L * n - ones - 2 * cx - dx - mx;
            minOps = Math.min(minOps, curOps);
        }
        return minOps;
    }
}