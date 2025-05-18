package LC2700_3000;
import java.util.*;
public class LC2817_MinimumAbsoluteDifferenceBetweenElementsWithConstraint {
    /**
     * You are given a 0-indexed integer array nums and an integer x.
     *
     * Find the minimum absolute difference between two elements in the array that are at least x indices apart.
     *
     * In other words, find two indices i and j such that abs(i - j) >= x and abs(nums[i] - nums[j]) is minimized.
     *
     * Return an integer denoting the minimum absolute difference between two elements that are at least x indices apart.
     *
     * Input: nums = [4,3,2,4], x = 2
     * Output: 0
     *
     * Input: nums = [5,3,2,10,15], x = 1
     * Output: 1
     *
     * Input: nums = [1,2,3,4], x = 3
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * 0 <= x < nums.length
     * @param nums
     * @param x
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int minAbsoluteDifference(List<Integer> nums, int x) {
        int n = nums.size();
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = n - 1; i >= x; i--) map.put(nums.get(i), map.getOrDefault(nums.get(i), 0) + 1);

        int res = (int)1e9;
        for (int i = 0, j = x; i < n; i++) {
            if (i + x >= n) break;
            if (i > 0) {
                int y = nums.get(j++);
                map.put(y, map.get(y) - 1);
                if (map.get(y) == 0) map.remove(y);
            }
            int y = nums.get(i);
            Integer fk = map.floorKey(y);
            Integer ck = map.ceilingKey(y);
            if (fk != null) res = Math.min(res, y - fk);
            if (ck != null) res = Math.min(res, ck - y);
            if (res == 0) break;
        }
        return res;
    }
}