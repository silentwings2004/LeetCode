package LC3901_4200;
import java.util.*;
public class LC4066_MaximumEqualAdjacentPairsAfteratMostOneReplacement {
    /**
     * You are given a 1-indexed integer array nums.
     *
     * You can choose two distinct values x and y and perform the following operation at most once:
     *
     * Replace every occurrence of x in nums with y.
     * Return the maximum possible number of pairs of adjacent elements that are equal after performing the operation.
     *
     * Input: nums = [1,2,3,2]
     * Output: 2
     *
     * Input: nums = [1,2,1,2,1]
     * Output: 4
     *
     * Input: nums = [1,1,1]
     * Output: 2
     *
     * Constraints:
     *
     * 2 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public int maxEqualAdjacentPairs(int[] nums) {
        int n = nums.length;
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 1; i < n; i++) {
            int a = nums[i - 1], b = nums[i];
            if (a > b) {
                int t = a;
                a = b;
                b = t;
            }
            String h = a + "#" + b;
            map.put(h, map.getOrDefault(h, 0) + 1);
        }

        int res = 0, cnt = 0;
        for (String h : map.keySet()) {
            int v = map.get(h);
            String[] strs = h.split("#");
            if (strs[0].equals(strs[1])) cnt += v;
            else res = Math.max(res, v);
        }
        return res + cnt;
    }
}