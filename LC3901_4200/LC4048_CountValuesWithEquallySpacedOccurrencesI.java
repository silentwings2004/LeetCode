package LC3901_4200;
import java.util.*;
public class LC4048_CountValuesWithEquallySpacedOccurrencesI {
    /**
     * You are given an integer array nums.
     *
     * An integer x is called special if:
     *
     * x appears exactly three times in nums.
     * All three occurrences of x are equally spaced in nums. In other words, if all occurrences of x are at indices
     * i1 < i2 < i3, then i2 - i1 = i3 - i2.
     * Return the number of distinct special integers in nums.
     *
     * Input: nums = [1,8,1,5,1,5,8,5]
     * Output: 2
     *
     * Input: nums = [8,8,8,8]
     * Output: 0
     *
     * Input: nums = [8,6,6,8,8]
     * Output: 0
     *
     * Constraints:
     *
     * 3 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public int countSpecialIntegers(int[] nums) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            map.putIfAbsent(x, new ArrayList<>());
            map.get(x).add(i);
        }

        int res = 0;
        for (List<Integer> q : map.values()) {
            int m = q.size();
            if (m != 3) continue;
            if (q.get(1) - q.get(0) == q.get(2) - q.get(1)) res++;
        }
        return res;
    }
}