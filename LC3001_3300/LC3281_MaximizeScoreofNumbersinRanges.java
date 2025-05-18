package LC3001_3300;
import java.util.*;
public class LC3281_MaximizeScoreofNumbersinRanges {
    /**
     * You are given an array of integers start and an integer d, representing n intervals [start[i], start[i] + d].
     *
     * You are asked to choose n integers where the ith integer must belong to the ith interval. The score of the chosen
     * integers is defined as the minimum absolute difference between any two integers that have been chosen.
     *
     * Return the maximum possible score of the chosen integers.
     *
     * Input: start = [6,0,3], d = 2
     *
     * Output: 4
     *
     * Input: start = [2,6,13,13], d = 5
     *
     * Output: 5
     *
     * Constraints:
     *
     * 2 <= start.length <= 10^5
     * 0 <= start[i] <= 10^9
     * 0 <= d <= 10^9
     * @param start
     * @param d
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public int maxPossibleScore(int[] start, int d) {
        Arrays.sort(start);
        long l = 0, r = (long)2e9;
        while (l < r) {
            long mid = l + r + 1 >> 1;
            if (check(start, d, mid)) l = mid;
            else r = mid - 1;
        }
        return (int)r;
    }

    private boolean check(int[] nums, int d, long mid) {
        int n = nums.length;
        long x = nums[0];
        for (int i = 1; i < n; i++) {
            int l = nums[i], r = nums[i] + d;
            if (x + mid > r) return false;
            x = Math.max(x + mid, l);
        }
        return true;
    }
}
