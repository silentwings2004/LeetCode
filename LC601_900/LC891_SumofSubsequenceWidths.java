package LC601_900;
import java.util.*;
public class LC891_SumofSubsequenceWidths {
    /**
     * The width of a sequence is the difference between the maximum and minimum elements in the sequence.
     *
     * Given an array of integers nums, return the sum of the widths of all the non-empty subsequences of nums. Since
     * the answer may be very large, return it modulo 109 + 7.
     *
     * A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing
     * the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
     *
     * Input: nums = [2,1,3]
     * Output: 6
     *
     * Input: nums = [2]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int sumSubseqWidths(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        long mod = (long)(1e9 + 7);
        long[] p = new long[n + 1];
        p[0] = 1;
        for (int i = 1; i <= n; i++) p[i] = p[i - 1] * 2 % mod;

        long res = 0;
        for (int i = 0; i < n; i++) {
            res = (res + nums[i] * p[i] - nums[i] * p[n - i - 1]) % mod;
        }
        return (int) res;
    }
}
/**
 * 双关键字排序
 * 相等元素排在前面的比较小 => 最大最小值就是唯一的了，避免重复计算
 * 看下每个数对答案的贡献是多少
 * ai要么出现在最大值，要么最小值位置才有贡献
 * 分情况来看，集合里出现的元素必须要在ai的左边 => 2^i个集合 => ai * 2^i
 * 作为最小值 => n - i - 1 => -ai * 2^(n - i - 1)
 */