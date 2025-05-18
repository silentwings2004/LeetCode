package LC2401_2700;
import java.util.*;
public class LC2404_MostFrequentEvenElement {
    /**
     * Given an integer array nums, return the most frequent even element.
     *
     * If there is a tie, return the smallest one. If there is no such element, return -1.
     *
     * Input: nums = [0,1,2,2,4,4,1]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums.length <= 2000
     * 0 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public int mostFrequentEven(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int x : nums) {
            if (x % 2 == 0) map.put(x, map.getOrDefault(x, 0) + 1);
        }

        int max = 0, res = -1;
        for (int x : map.keySet()) {
            if (map.get(x) > max) {
                max = map.get(x);
                res = x;
            } else if (map.get(x) == max && x < res) res = x;
        }
        return res;
    }
}
