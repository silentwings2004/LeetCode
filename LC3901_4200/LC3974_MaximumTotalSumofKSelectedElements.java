package LC3901_4200;
import java.util.*;
public class LC3974_MaximumTotalSumofKSelectedElements {
    /**
     * You are given an integer array nums and two integers k and mul.
     *
     * Select exactly k elements from nums. Process these elements one by one in any order you choose.
     *
     * For each selected element, independently choose one of the following:
     *
     * Add the element's value to the total sum, or
     * Multiply the element by the current value of mul and add the result to the total sum.
     * After processing each selected element, mul decreases by 1, regardless of which option was chosen. The current
     * value of mul may become 0 or negative.
     *
     * Return an integer denoting the maximum possible total sum.
     *
     * Input: nums = [6,1,2,9], k = 3, mul = 2
     * Output: 26
     *
     * Input: nums = [3,7,5,2], k = 2, mul = 4
     * Output: 43
     *
     * Input: nums = [4,4], k = 1, mul = 1
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * 1 <= k <= nums.length
     * 1 <= mul <= 10^5
     * @param nums
     * @param k
     * @param mul
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public long maxSum(int[] nums, int k, int mul) {
        Arrays.sort(nums);
        int n = nums.length;
        long res = 0;
        for (int i = n - 1; i >= 0 && k > 0; i--) {
            if (mul > 1) {
                res += 1L * mul * nums[i];
                mul--;
            } else res += nums[i];
            k--;
        }
        return res;
    }
}
/**
 * 排序不等式
 */