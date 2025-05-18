package LC2700_3000;
import java.util.*;
public class LC2910_MinimumNumberofGroupstoCreateaValidAssignment {
    /**
     * You are given a 0-indexed integer array nums of length n.
     *
     * We want to group the indices so for each index i in the range [0, n - 1], it is assigned to exactly one group.
     *
     * A group assignment is valid if the following conditions hold:
     *
     * For every group g, all indices i assigned to group g have the same value in nums.
     * For any two groups g1 and g2, the difference between the number of indices assigned to g1 and g2 should not
     * exceed 1.
     * Return an integer denoting the minimum number of groups needed to create a valid group assignment.
     *
     * Input: nums = [3,2,3,2,3]
     * Output: 2
     *
     * Input: nums = [10,10,10,3,1,1]
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public int minGroupsForValidAssignment(int[] nums) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int x : nums) map.put(x, map.getOrDefault(x, 0) + 1);
        int m = n;
        for (int v : map.values()) m = Math.min(m, v);

        for (int i = m; i > 0; i--) {
            int t = 0;
            boolean flag = true;
            for (int v : map.values()) {
                int g = v / (i + 1), r = v % (i + 1);
                if (r == 0) t += g;
                else if (r == i) t += g + 1;
                else {
                    while (g > 0 && r % i != 0) {
                        g--;
                        r += i + 1;
                    }
                    if (r % i != 0) {
                        flag = false;
                        break;
                    }
                    t += g + r / i;
                }
            }
            if (flag) return t;
        }
        return n;
    }
}