package LC2401_2700;
import java.util.*;
public class LC2553_SeparatetheDigitsinanArray {
    /**
     * Given an array of positive integers nums, return an array answer that consists of the digits of each integer
     * in nums after separating them in the same order they appear in nums.
     *
     * To separate the digits of an integer is to get all the digits it has in the same order.
     *
     * For example, for the integer 10921, the separation of its digits is [1,0,9,2,1].
     *
     * Input: nums = [13,25,83,77]
     * Output: [1,3,2,5,8,3,7,7]
     *
     * Input: nums = [7,1,3,9]
     * Output: [7,1,3,9]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * 1 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // time = O(nlogk), space = O(nlogk)
    public int[] separateDigits(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int x : nums) {
            List<Integer> q = new LinkedList<>();
            while (x > 0) {
                q.add(0, x % 10);
                x /= 10;
            }
            res.addAll(q);
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) ans[i] = res.get(i);
        return ans;
    }
}