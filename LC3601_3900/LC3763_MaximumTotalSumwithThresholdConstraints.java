package LC3601_3900;
import java.util.*;
public class LC3763_MaximumTotalSumwithThresholdConstraints {
    /**
     * You are given two integer arrays nums and threshold, both of length n.
     *
     * Starting at step = 1, you perform the following repeatedly:
     *
     * Choose an unused index i such that threshold[i] <= step.
     * If no such index exists, the process ends.
     * Add nums[i] to your running total.
     * Mark index i as used and increment step by 1.
     * Return the maximum total sum you can obtain by choosing indices optimally.
     *
     * Input: nums = [1,10,4,2,1,6], threshold = [5,1,5,5,2,2]
     *
     * Output: 17
     *
     * Input: nums = [4,1,5,2,3], threshold = [3,3,2,3,3]
     * Output: 0
     *
     * Input: nums = [2,6,10,13], threshold = [2,1,1,1]
     * Output: 31
     *
     * Constraints:
     *
     * n == nums.length == threshold.length
     * 1 <= n <= 10^5
     * 1 <= nums[i] <= 10^9
     * 1 <= threshold[i] <= n
     * @param nums
     * @param threshold
     * @return
     */
    // time = O(nlogn), space = O(n)
    public long maxSum(int[] nums, int[] threshold) {
        int n = nums.length;
        Integer[] p = new Integer[n];
        for (int i = 0; i < n; i++) p[i] = i;
        Arrays.sort(p, (o1, o2) -> threshold[o1] - threshold[o2]);
        long res = 0;
        for (int i = 0, step = 1; i < n; i++, step++) {
            if (threshold[p[i]] > step) break;
            res += nums[p[i]];
        }
        return res;
    }
}