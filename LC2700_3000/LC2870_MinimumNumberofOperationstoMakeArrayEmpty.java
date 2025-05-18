package LC2700_3000;
import java.util.*;
public class LC2870_MinimumNumberofOperationstoMakeArrayEmpty {
    /**
     * You are given a 0-indexed array nums consisting of positive integers.
     *
     * There are two types of operations that you can apply on the array any number of times:
     *
     * Choose two elements with equal values and delete them from the array.
     * Choose three elements with equal values and delete them from the array.
     * Return the minimum number of operations required to make the array empty, or -1 if it is not possible.
     *
     * Input: nums = [2,3,3,2,2,4,2,3,4]
     * Output: 4
     *
     * Input: nums = [2,1,2,2,3,3]
     * Output: -1
     *
     * Constraints:
     *
     * 2 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^6
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public int minOperations(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int x : nums) map.put(x, map.getOrDefault(x, 0) + 1);
        int res = 0;
        for (int v : map.values()) {
            if (v == 1) return -1;
            if (v % 3 == 0) res += v / 3;
            else res += v / 3 + 1;
        }
        return res;
    }
}