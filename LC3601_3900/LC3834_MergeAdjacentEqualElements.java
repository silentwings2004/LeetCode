package LC3601_3900;
import java.util.*;
public class LC3834_MergeAdjacentEqualElements {
    /**
     * You are given an integer array nums.
     *
     * You must repeatedly apply the following merge operation until no more changes can be made:
     *
     * If any two adjacent elements are equal, choose the leftmost such adjacent pair in the current array and replace
     * them with a single element equal to their sum.
     * After each merge operation, the array size decreases by 1. Repeat the process on the updated array until no more
     * changes can be made.
     *
     * Return the final array after all possible merge operations.
     *
     * Input: nums = [3,1,1,2]
     * Output: [3,4]
     *
     * Input: nums = [2,2,4]
     * Output: [8]
     *
     * Input: nums = [3,7,5]
     * Output: [3,7,5]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public List<Long> mergeAdjacent(int[] nums) {
        int n = nums.length;
        long[] stk = new long[n + 1];
        int tt = 0;
        for (int x : nums) {
            stk[++tt] = x;
            while (tt > 1 && stk[tt] == stk[tt - 1]) {
                tt--;
                stk[tt] *= 2;
            }
        }
        List<Long> res = new ArrayList<>();
        for (int i = 1; i <= tt; i++) res.add(stk[i]);
        return res;
    }
}