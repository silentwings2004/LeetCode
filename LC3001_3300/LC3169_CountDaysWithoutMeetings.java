package LC3001_3300;
import java.util.*;
public class LC3169_CountDaysWithoutMeetings {
    /**
     * You are given a positive integer days representing the total number of days an employee is available for work
     * (starting from day 1). You are also given a 2D array meetings of size n where, meetings[i] = [start_i, end_i]
     * represents the starting and ending days of meeting i (inclusive).
     *
     * Return the count of days when the employee is available for work but no meetings are scheduled.
     *
     * Note: The meetings may overlap.
     *
     * Input: days = 10, meetings = [[5,7],[1,3],[9,10]]
     * Output: 2
     *
     * Input: days = 5, meetings = [[2,4],[1,3]]
     * Output: 1
     *
     * Input: days = 6, meetings = [[1,6]]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= days <= 10^9
     * 1 <= meetings.length <= 10^5
     * meetings[i].length == 2
     * 1 <= meetings[i][0] <= meetings[i][1] <= days
     * @param days
     * @param meetings
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public int countDays(int days, int[][] meetings) {
        Arrays.sort(meetings, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        int st = -1, ed = -1;
        int res = 0;
        for (int[] x : meetings) {
            if (x[0] > ed) {
                if (st != -1) res += ed - st + 1;
                st = x[0];
                ed = x[1];
            } else ed = Math.max(ed, x[1]);
        }
        if (st != -1) res += ed - st + 1;
        return days - res;
    }
}