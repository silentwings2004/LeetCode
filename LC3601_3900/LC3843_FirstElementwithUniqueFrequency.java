package LC3601_3900;
import java.util.*;
public class LC3843_FirstElementwithUniqueFrequency {
    /**
     * You are given an integer array nums.
     *
     * Return an integer denoting the first element (scanning from left to right) in nums whose frequency is unique.
     * That is, no other integer appears the same number of times in nums. If there is no such element, return -1.
     *
     * Input: nums = [20,10,30,30]
     * Output: 30
     *
     * Input: nums = [20,20,10,30,30,30]
     * Output: 20
     *
     * Input: nums = [10,10,20,20]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public int firstUniqueFreq(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int x : nums) map.put(x, map.getOrDefault(x, 0) + 1);
        int n = nums.length;
        int[] cnt = new int[n + 1];
        for (int v : map.values()) cnt[v]++;
        for (int x : nums) {
            if (cnt[map.get(x)] == 1) return x;
        }
        return -1;
    }
}