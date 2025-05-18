package LC3301_3600;
import java.util.*;
public class LC3350_AdjacentIncreasingSubarraysDetectionII {
    /**
     * Given an array nums of n integers, your task is to find the maximum value of k for which there exist two adjacent
     * subarrays of length k each, such that both subarrays are strictly increasing. Specifically, check if there are
     * two subarrays of length k starting at indices a and b (a < b), where:
     *
     * Both subarrays nums[a..a + k - 1] and nums[b..b + k - 1] are strictly increasing.
     * The subarrays must be adjacent, meaning b = a + k.
     * Return the maximum possible value of k.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [2,5,7,8,9,2,3,4,3,1]
     * Output: 3
     *
     * Input: nums = [1,2,3,4,4,4,4,5,6,7]
     * Output: 2
     *
     * Constraints:
     *
     * 2 <= nums.length <= 2 * 10^5
     * -10^9 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public int maxIncreasingSubarrays(List<Integer> nums) {
        int n = nums.size(), res = 0;
        int idx = -1, last = 0;
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && nums.get(j) > nums.get(j - 1)) j++;
            int len = j - i;
            res = Math.max(res, len / 2);
            if (idx == i - 1) res = Math.max(res, Math.min(last, len));
            idx = j - 1;
            last = len;
            i = j - 1;
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public int maxIncreasingSubarrays2(List<Integer> nums) {
        int res = 0, pre_cnt = 0, cnt = 0;
        int n = nums.size();
        for (int i = 0; i < n; i++) {
            cnt++;
            if (i == n - 1 || nums.get(i) >= nums.get(i + 1)) {
                res = Math.max(res, Math.max(cnt / 2, Math.min(pre_cnt, cnt)));
                pre_cnt = cnt;
                cnt = 0;
            }
        }
        return res;
    }
}