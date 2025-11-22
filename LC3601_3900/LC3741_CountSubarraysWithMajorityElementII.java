package LC3601_3900;
import java.util.*;
public class LC3741_CountSubarraysWithMajorityElementII {
    /**
     * You are given an integer array nums.
     *
     * A tuple (i, j, k) of 3 distinct indices is good if nums[i] == nums[j] == nums[k].
     *
     * The distance of a good tuple is abs(i - j) + abs(j - k) + abs(k - i), where abs(x) denotes the absolute value of x.
     *
     * Return an integer denoting the minimum possible distance of a good tuple. If no good tuples exist, return -1.
     *
     * Input: nums = [1,2,1,1,3]
     * Output: 6
     *
     * Input: nums = [1,1,2,3,2,1,2]
     * Output: 8
     *
     * Input: nums = [1]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 10^5
     * 1 <= nums[i] <= n
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public int minimumDistance(int[] nums) {
        final int inf = 0x3f3f3f3f;
        int n = nums.length, res = inf;
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            map.putIfAbsent(x, new ArrayList<>());
            map.get(x).add(i);
        }

        for (List<Integer> v : map.values()) {
            if (v.size() < 3) continue;
            int m = v.size();
            for (int i = 2; i < m; i++) {
                int a = v.get(i - 2), b = v.get(i - 1), c = v.get(i);
                int d = (b - a) + (c - b) + (c - a);
                res = Math.min(res, d);
            }
        }
        return res == inf ? -1 : res;
    }
}