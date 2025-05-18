package LC1201_1500;
import java.util.*;
public class LC1450_NumberofStudentsDoingHomeworkataGivenTime {
    /**
     * Given two integer arrays startTime and endTime and given an integer queryTime.
     *
     * The ith student started doing their homework at the time startTime[i] and finished it at time endTime[i].
     *
     * Return the number of students doing their homework at time queryTime. More formally, return the number of
     * students where queryTime lays in the interval [startTime[i], endTime[i]] inclusive.
     *
     * Input: startTime = [1,2,3], endTime = [3,2,7], queryTime = 4
     * Output: 1
     *
     * Input: startTime = [4], endTime = [4], queryTime = 4
     * Output: 1
     *
     * Constraints:
     *
     * startTime.length == endTime.length
     * 1 <= startTime.length <= 100
     * 1 <= startTime[i] <= endTime[i] <= 1000
     * 1 <= queryTime <= 1000
     * @param startTime
     * @param endTime
     * @param queryTime
     * @return
     */
    // time = O(n), space = O(1)
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int n = startTime.length, res = 0;
        for (int i = 0; i < n; i++) {
            if (startTime[i] <= queryTime && endTime[i] >= queryTime) res++;
        }
        return res;
    }
}