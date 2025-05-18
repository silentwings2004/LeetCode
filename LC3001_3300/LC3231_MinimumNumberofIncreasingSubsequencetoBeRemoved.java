package LC3001_3300;
import java.util.*;
public class LC3231_MinimumNumberofIncreasingSubsequencetoBeRemoved {
    /**
     * Given an array of integers nums, you are allowed to perform the following operation any number of times:
     * Remove a strictly increasing subsequence from the array.
     * Your task is to find the minimum number of operations required to make the array empty.
     *
     * Input: nums = [5,3,1,4,2]
     * Output: 3
     *
     * Input: nums = [1,2,3,4,5]
     * Output: 1
     *
     * Input: nums = [5,4,3,2,1]
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int minOperations(int[] nums) {
        int n = nums.length;
        int[] f = new int[n];
        f[0] = 1;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(nums[0], 1);
        for (int i = 1; i < n; i++) {
            Integer lk = map.lowerKey(nums[i]);
            if (lk != null) {
                f[i] = f[i - 1];
                map.put(lk, map.get(lk) - 1);
                if (map.get(lk) == 0) map.remove(lk);
            } else f[i] = f[i - 1] + 1;
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        return f[n - 1];
    }
}