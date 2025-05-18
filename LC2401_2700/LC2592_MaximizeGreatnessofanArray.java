package LC2401_2700;
import java.util.*;
public class LC2592_MaximizeGreatnessofanArray {
    /**
     * You are given a 0-indexed integer array nums. You are allowed to permute nums into a new array perm of your
     * choosing.
     *
     * We define the greatness of nums be the number of indices 0 <= i < nums.length for which perm[i] > nums[i].
     *
     * Return the maximum possible greatness you can achieve after permuting nums.
     *
     * Input: nums = [1,3,5,2,1,3,1]
     * Output: 4
     *
     * Input: nums = [1,2,3,4]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int maximizeGreatness(int[] nums) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int x : nums) map.put(x, map.getOrDefault(x, 0) + 1);
        int res = 0;
        for (int x : nums) {
            Integer hk = map.higherKey(x);
            if (hk != null) {
                res++;
                map.put(hk, map.get(hk) - 1);
                if (map.get(hk) == 0) map.remove(hk);
            }
        }
        return res;
    }
}