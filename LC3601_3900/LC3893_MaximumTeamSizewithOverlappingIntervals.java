package LC3601_3900;
import java.util.*;
public class LC3893_MaximumTeamSizewithOverlappingIntervals {
    /**
     * You are given two integer arrays startTime and endTime of length n.
     *
     * startTime[i] represents the start time of the ith employee.
     * endTime[i] represents the end time of the ith employee.
     * Two employees i and j can interact if their time intervals overlap. Two intervals are considered overlapping if
     * they share at least one common time point.
     *
     * A team is valid if there exists at least one employee in the team who can interact with every other member of the
     * team.
     *
     * Return an integer denoting the maximum possible size of such a team.
     *
     * Input: startTime = [1,2,3], endTime = [4,5,6]
     * Output: 3
     *
     * Input: startTime = [2,5,8], endTime = [3,7,9]
     * Output: 1
     *
     * Input: startTime = [3,4,6], endTime = [8,5,7]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= n == startTime.length == endTime.length <= 10^5
     * 0 <= startTime[i] <= endTime[i] <= 10^9
     * @param startTime
     * @param endTime
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int maximumTeamSize(int[] startTime, int[] endTime) {
        int n = startTime.length;
        int[] a = startTime.clone();
        int[] b = endTime.clone();
        Arrays.sort(a);
        Arrays.sort(b);

        int res = 1;
        for (int i = 0; i < n; i++) {
            int l = startTime[i], r = endTime[i];
            int x = upper_bound(a, r);
            int y = lower_bound(b, l);
            res = Math.max(res, x - y);
        }
        return res;
    }

    private int upper_bound(int[] nums, int t) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (nums[mid] <= t) l = mid;
            else r = mid - 1;
        }
        return nums[r] <= t ? r : r - 1;
    }

    private int lower_bound(int[] nums, int t) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (nums[mid] < t) l = mid;
            else r = mid - 1;
        }
        return nums[r] < t ? r : r - 1;
    }
}