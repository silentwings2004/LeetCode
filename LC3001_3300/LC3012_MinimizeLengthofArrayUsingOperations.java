package LC3001_3300;
import java.util.*;
public class LC3012_MinimizeLengthofArrayUsingOperations {
    /**
     * You are given a 0-indexed integer array nums containing positive integers.
     *
     * Your task is to minimize the length of nums by performing the following operations any number of times
     * (including zero):
     *
     * Select two distinct indices i and j from nums, such that nums[i] > 0 and nums[j] > 0.
     * Insert the result of nums[i] % nums[j] at the end of nums.
     * Delete the elements at indices i and j from nums.
     * Return an integer denoting the minimum length of nums after performing the operation any number of times.
     *
     * Input: nums = [1,4,3,1]
     * Output: 1
     *
     * Input: nums = [5,5,5,10,5]
     * Output: 2
     *
     * Input: nums = [2,3,4]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public int minimumArrayLength(int[] nums) {
        int minv = nums[0], cnt = 0;
        for (int x : nums) minv = Math.min(minv, x);
        for (int x : nums) {
            if (x % minv != 0) return 1;
            if (x == minv) cnt++;
        }
        return (cnt + 1) / 2;
    }

    // S2
    // time = O(nlogn), space = O(n)
    public int minimumArrayLength2(int[] nums) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int n = nums.length;
        for (int x : nums) map.put(x, map.getOrDefault(x, 0) + 1);
        int res = 0;
        while (map.size() > 1) {
            int x = map.firstKey(), y = map.higherKey(x);
            map.put(x, map.get(x) - 1);
            map.put(y, map.get(y) - 1);
            if (map.get(x) == 0) map.remove(x);
            if (map.get(y) == 0) map.remove(y);
            int t1 = x % y, t2 = y % x;
            if (t2 == 0) map.put(t1, map.getOrDefault(t1, 0) + 1);
            else {
                int cnt1 = map.getOrDefault(t1, 0);
                int cnt2 = map.getOrDefault(t2, 0);
                if (cnt1 < cnt2) map.put(t1, map.getOrDefault(t1, 0) + 1);
                else map.put(t2, map.getOrDefault(t2, 0) + 1);
            }
        }
        int cnt = map.get(map.firstKey());
        return (cnt + 1) / 2;
    }
}
/**
 * 如果取模得到是一个非0数字，那么可以继续操作这个数字
 * 2 3 4 5 6
 * 用最小的数字操作
 * 相当于每次操作，删除一个大于2的数
 * 如果最小的数只有一个，那么最后就只剩下一个数了(最优解)
 * 能不能得到一个小于 min(nums) 的数
 *
 * 设 x 不是 m 的倍数
 * 那么 0 < x % m < m
 *
 * 如果 nums 的所有数字都是 m 的倍数呢？
 * 6m 9m [1,m-1] 3m
 * 最优做法：把大于 m 的数字都去掉，然后剩下的 cnt 个 m 两两一对
 * (cnt + 1) / 2
 * 求gcd
 */