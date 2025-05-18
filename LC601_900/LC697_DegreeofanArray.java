package LC601_900;
import java.util.*;
public class LC697_DegreeofanArray {
    /**
     * Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum
     * frequency of any one of its elements.
     *
     * Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree
     * as nums.
     *
     * Input: nums = [1,2,2,3,1]
     * Output: 2
     *
     * Input: nums = [1,2,2,3,1,4,2]
     * Output: 6
     *
     * Constraints:
     *
     * nums.length will be between 1 and 50,000.
     * nums[i] will be an integer between 0 and 49,999.
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public int findShortestSubArray(int[] nums) {
        HashMap<Integer, Integer> fst = new HashMap<>();
        HashMap<Integer, Integer> lst = new HashMap<>();
        HashMap<Integer, Integer> cnt = new HashMap<>();
        int n = nums.length, maxv = 0;
        for (int i = 0; i < n; i++) {
            if (!fst.containsKey(nums[i])) fst.put(nums[i], i);
            lst.put(nums[i], i);
            cnt.put(nums[i], cnt.getOrDefault(nums[i], 0) + 1);
            maxv = Math.max(maxv, cnt.get(nums[i]));
        }

        int res = n;
        for (int key : cnt.keySet()) {
            if (cnt.get(key) == maxv) {
                res = Math.min(res, lst.get(key) - fst.get(key) + 1);
            }
        }
        return res;
    }
}
/**
 * 将所有子数组分离
 * 以这个数来分类
 * 每一类里取个最小值
 */