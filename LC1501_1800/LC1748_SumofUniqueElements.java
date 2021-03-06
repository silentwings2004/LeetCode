package LC1501_1800;
import java.util.*;
public class LC1748_SumofUniqueElements {
    /**
     * You are given an integer array nums. The unique elements of an array are the elements that appear exactly once
     * in the array.
     *
     * Return the sum of all the unique elements of nums.
     *
     * Input: nums = [1,2,3,2]
     * Output: 4
     *
     * Input: nums = [1,1,1,1,1]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     *
     * @param nums
     * @return
     */
    // S1: HashMap
    // time = O(n), space = O(n)
    public int sumOfUnique(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return 0;

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        int res = 0;
        for (int key : map.keySet()) {
            if (map.get(key) == 1) res += key;
        }
        return res;
    }

    // S2: int array
    // time = O(1), space = O(1) 最优解！！！
    public int sumOfUnique2(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return 0;

        int[] count = new int[101];

        for (int n : nums) count[n]++;

        int res = 0;
        for (int i = 1; i < count.length; i++) {
            if (count[i] == 1) res += i;
        }
        return res;
    }
}
