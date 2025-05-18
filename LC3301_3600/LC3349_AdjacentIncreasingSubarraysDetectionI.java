package LC3301_3600;
import java.util.*;
public class LC3349_AdjacentIncreasingSubarraysDetectionI {
    /**
     * Given an array nums of n integers and an integer k, determine whether there exist two adjacent subarrays of length k such that both subarrays are strictly increasing. Specifically, check if there are two subarrays starting at indices a and b (a < b), where:
     *
     * Both subarrays nums[a..a + k - 1] and nums[b..b + k - 1] are strictly increasing.
     * The subarrays must be adjacent, meaning b = a + k.
     * Return true if it is possible to find two such subarrays, and false otherwise.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [2,5,7,8,9,2,3,4,3,1], k = 3
     * Output: true
     *
     * Input: nums = [1,2,3,4,4,4,4,5,6,7], k = 5
     * Output: false
     *
     * Constraints:
     *
     * 2 <= nums.length <= 100
     * 1 <= 2 * k <= nums.length
     * -1000 <= nums[i] <= 1000
     * @param nums
     * @param k
     * @return
     */
    // S1
    // time = O(n * k), space = O(1)
    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        int n = nums.size();
        for (int i = 0; i + 2 * k - 1 < n; i++) {
            boolean flag = true;
            for (int j = 1; j < k; j++) {
                if (nums.get(i + j) <= nums.get(i + j - 1)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                for (int j = 1; j < k; j++) {
                    if (nums.get(i + k + j) <= nums.get(i + k + j - 1)) {
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) return true;
        }
        return false;
    }

    // S2
    // time = O(n), space = O(1)
    public boolean hasIncreasingSubarrays2(List<Integer> nums, int k) {
        int n = nums.size(), res = 0;
        int idx = -1, last = 0;
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && nums.get(j) > nums.get(j - 1)) j++;
            int len = j - i;
            res = Math.max(res, len / 2);
            if (idx == i - 1) res = Math.max(res, Math.min(last, len));
            if (res >= k) return true;
            idx = j - 1;
            last = len;
            i = j - 1;
        }
        return false;
    }
}