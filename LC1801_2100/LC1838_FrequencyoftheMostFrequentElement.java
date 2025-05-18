package LC1801_2100;
import java.util.*;
public class LC1838_FrequencyoftheMostFrequentElement {
    /**
     * The frequency of an element is the number of times it occurs in an array.
     *
     * You are given an integer array nums and an integer k. In one operation, you can choose an index of nums and
     * increment the element at that index by 1.
     *
     * Return the maximum possible frequency of an element after performing at most k operations.
     *
     * Input: nums = [1,2,4], k = 5
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * 1 <= k <= 10^5
     * @param nums
     * @param k
     * @return
     */
    // S1: Sliding Window
    // time = O(nlogn), space = O(1)
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length, res = 1;
        long s = 0;
        for (int i = 1, j = 0; i < n; i++) {
            s += (i - j) * (nums[i] - nums[i - 1]);
            while (s > k) s -= nums[i] - nums[j++];
            res = Math.max(res, i - j + 1);
        }
        return res;
    }
}
/**
 * [1, 2, (3), 4]
 * 操作后最终得到的最高频率元素一定是数组中既有的元素
 * 元素前面紧邻的元素变成该元素 -> 滑窗
 *  x x [j x x i-1 i i+1] x x
 *  nums[i] => [j:i], count
 *  nums[i + 1] => [jj: i + 1], count += (i - j + 1) * (nums[i + 1] - nums[i])
 *                              count -= nums[i + 1] - nums[j]
 *  排序 + 双指针
 *  x x [j x x x i-1 i]
 *
 * 性质1：最高频数可以为原数组中的数
 * => 二分
 */


