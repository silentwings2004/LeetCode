package LC2101_2400;
import java.util.*;
public class LC2364_CountNumberofBadPairs {
    /**
     * You are given a 0-indexed integer array nums. A pair of indices (i, j) is a bad pair if i < j and
     * j - i != nums[j] - nums[i].
     *
     * Return the total number of bad pairs in nums.
     *
     * Input: nums = [4,1,3,3]
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public long countBadPairs(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        long res = C(n);
        for (int i = 0; i < n; i++) {
            map.put(nums[i] - i, map.getOrDefault(nums[i] - i, 0) + 1);
        }

        for (int v : map.values()) res -= C(v);
        return res;
    }

    private long C(int x) {
        return x * (x - 1L) / 2;
    }
}
/**
 * j - i = a[j] - a[i]
 * a[j] - j = a[i] - i
 * bi = ai - i
 */