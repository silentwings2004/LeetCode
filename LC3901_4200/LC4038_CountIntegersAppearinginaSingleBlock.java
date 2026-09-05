package LC3901_4200;
import java.util.*;
public class LC4038_CountIntegersAppearinginaSingleBlock {
    /**
     * You are given an integer array nums.
     *
     * An integer x is special if all occurrences of x in nums appear in a single contiguous block.
     *
     * Return the number of distinct special integers in nums.
     *
     * Input: nums = [1,2,2,1]
     * Output: 1
     *
     * Input: nums = [3,3,1,2,2,1]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
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
            if (q.get(m - 1) - q.get(0) + 1 == m) res++;
        }
        return res;
    }
}