package LC3001_3300;
import java.util.*;
public class LC3020_FindtheMaximumNumberofElementsinSubset {
    /**
     * You are given an array of positive integers nums.
     *
     * You need to select a
     * subset
     *  of nums which satisfies the following condition:
     *
     * You can place the selected elements in a 0-indexed array such that it follows the pattern: [x, x2, x4, ..., xk/2,
     * xk, xk/2, ..., x4, x2, x] (Note that k can be be any non-negative power of 2). For example, [2, 4, 16, 4, 2] and
     * [3, 9, 3] follow the pattern while [2, 4, 8, 4, 2] does not.
     * Return the maximum number of elements in a subset that satisfies these conditions.
     *
     * Input: nums = [5,4,1,2,2]
     * Output: 3
     *
     * Input: nums = [1,3,2,4]
     * Output: 1
     *
     * Constraints:
     *
     * 2 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // S1
    // time = O(nloglog(U)), space = O(n)   U = max(nums)
    public int maximumLength(int[] nums) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int x : nums) map.put(x, map.getOrDefault(x, 0) + 1);

        int res = 1;
        for (int k : map.keySet()) {
            if (map.get(k) > 1) {
                if (k == 1) {
                    int v = map.get(k);
                    res = Math.max(res, v % 2 == 1 ? v : v - 1);
                } else {
                    int cnt = 0;
                    while (map.getOrDefault(k, 0) > 1) {
                        cnt++;
                        k *= k;
                    }
                    if (map.getOrDefault(k, 0) == 1) {
                        res = Math.max(res, cnt * 2 + 1);
                    } else res = Math.max(res, cnt * 2 - 1);
                }
            }
        }
        return res;
    }

    // S2
    // time = O(nloglog(U)), space = O(n)   U = max(nums)
    public int maximumLength2(int[] nums) {
        HashMap<Long, Integer> map = new HashMap<>();
        for (int x : nums) map.put(1L * x, map.getOrDefault(1L * x, 0) + 1);
        long res = 0;
        if (map.containsKey(1L)) {
            int x = map.get(1L);
            res += x - (x % 2 ^ 1);
            map.remove(1L);
        }
        for (long k : map.keySet()) {
            long x = k, t = 0;
            while (true) {
                if (!map.containsKey(x)) {
                    t--;
                    break;
                }
                if (map.getOrDefault(x, 0) == 1) {
                    t++;
                    break;
                }
                t += 2;
                x *= x;
            }
            res = Math.max(res, t);
        }
        return (int)res;
    }
}
/**
 * 数据的增长是指数级别的，或者超指数级别
 * -> 暴力枚举
 */