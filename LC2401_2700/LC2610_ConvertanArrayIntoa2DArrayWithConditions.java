package LC2401_2700;
import java.util.*;
public class LC2610_ConvertanArrayIntoa2DArrayWithConditions {
    /**
     * You are given an integer array nums. You need to create a 2D array from nums satisfying the following conditions:
     *
     * The 2D array should contain only the elements of the array nums.
     * Each row in the 2D array contains distinct integers.
     * The number of rows in the 2D array should be minimal.
     * Return the resulting array. If there are multiple answers, return any of them.
     *
     * Note that the 2D array can have a different number of elements on each row.
     *
     * Input: nums = [1,3,4,1,2,3,1]
     * Output: [[1,3,4,2],[1,3],[1]]
     *
     * Input: nums = [1,2,3,4]
     * Output: [[4,3,2,1]]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 200
     * 1 <= nums[i] <= nums.length
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public List<List<Integer>> findMatrix(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int x : nums) map.put(x, map.getOrDefault(x, 0) + 1);
        for (int key : map.keySet()) {
            int val = map.get(key);
            for (int i = 0; i < val; i++) {
                if (i >= res.size()) res.add(new ArrayList<>());
                res.get(i).add(key);
            }
        }
        return res;
    }
}