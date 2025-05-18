package LC3301_3600;
import java.util.*;
public class LC3386_ButtonwithLongestPushTime {
    /**
     * You are given a 2D array events which represents a sequence of events where a child pushes a series of buttons
     * on a keyboard.
     *
     * Each events[i] = [indexi, timei] indicates that the button at index indexi was pressed at time timei.
     *
     * The array is sorted in increasing order of time.
     * The time taken to press a button is the difference in time between consecutive button presses. The time for the
     * first button is simply the time at which it was pressed.
     * Return the index of the button that took the longest time to push. If multiple buttons have the same longest
     * time, return the button with the smallest index.
     *
     * Input: events = [[1,2],[2,5],[3,9],[1,15]]
     * Output: 1
     *
     * Input: events = [[10,5],[1,7]]
     * Output: 10
     *
     * Constraints:
     *
     * 1 <= events.length <= 1000
     * events[i] == [indexi, timei]
     * 1 <= indexi, timei <= 10^5
     * The input is generated such that events is sorted in increasing order of timei.
     * @param events
     * @return
     */
    // time = O(n), space = O(1)
    public int buttonWithLongestTime(int[][] events) {
        int n = events.length, res = events[0][0], mx = events[0][1];
        for (int i = 1; i < n; i++) {
            int d = events[i][1] - events[i - 1][1];
            if (d > mx) {
                mx = d;
                res = events[i][0];
            } else if (d == mx && events[i][0] < res) res = events[i][0];
        }
        return res;
    }
}