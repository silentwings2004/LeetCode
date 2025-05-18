package LC3001_3300;
import java.util.*;
public class LC3282_ReachEndofArrayWithMaxScore {
    /**
     * You are given an integer array nums of length n.
     *
     * Your goal is to start at index 0 and reach index n - 1. You can only jump to indices greater than your current
     * index.
     *
     * The score for a jump from index i to index j is calculated as (j - i) * nums[i].
     *
     * Return the maximum possible total score by the time you reach the last index.
     *
     * Input: nums = [1,3,1,5]
     * Output: 7
     *
     * Input: nums = [4,3,1,3,2]
     * Output: 16
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public long findMaximumScore(List<Integer> nums) {
        int n = nums.size(), mx = nums.get(0);
        long res = 0;
        for (int i = 1; i < n; i++) {
            res += mx;
            mx = Math.max(mx, nums.get(i));
        }
        return res;
    }
}