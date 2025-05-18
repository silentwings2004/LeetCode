package LC301_600;
import java.util.*;
public class LC561_ArrayPartition {
    /**
     * Given an integer array nums of 2n integers, group these integers into n pairs (a1, b1), (a2, b2), ..., (an, bn)
     * such that the sum of min(ai, bi) for all i is maximized. Return the maximized sum.
     *
     * Input: nums = [1,4,3,2]
     * Output: 4
     *
     * Input: nums = [6,2,6,5,1,2]
     * Output: 9
     *
     * Constraints:
     *
     * 1 <= n <= 10^4
     * nums.length == 2 * n
     * -10^4 <= nums[i] <= 10^4
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(1)
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length, res = 0;
        for (int i = 0; i < n; i += 2) res += nums[i];
        return res;
    }
}