package LC301_600;
import java.util.*;
public class LC436_FindRightInterval {
    /**
     * You are given an array of intervals, where intervals[i] = [starti, endi] and each starti is unique.
     *
     * The right interval for an interval i is an interval j such that startj >= endi and startj is minimized. Note
     * that i may equal j.
     *
     * Return an array of right interval indices for each interval i. If no right interval exists for interval i,
     * then put -1 at index i.
     *
     * Input: intervals = [[1,2]]
     * Output: [-1]
     *
     * Input: intervals = [[3,4],[2,3],[1,2]]
     * Output: [-1,0,1]
     *
     * Constraints:
     *
     * 1 <= intervals.length <= 2 * 10^4
     * intervals[i].length == 2
     * -106 <= starti <= endi <= 10^6
     * The start point of each interval is unique.
     * @param intervals
     * @return
     */
    // S1: TreeMap
    // time = O(nlogn), space = O(n)
    public int[] findRightInterval(int[][] intervals) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int n = intervals.length;
        for (int i = 0; i < n; i++) map.put(intervals[i][0], i);

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            Integer ck = map.ceilingKey(intervals[i][1]);
            res[i] = ck == null ? -1 : map.get(ck);
        }
        return res;
    }

    // S2
    // time = O(nlogn), space = O(n)
    public int[] findRightInterval2(int[][] intervals) {
        int n = intervals.length;
        int[][] arr = new int[n][3];
        for (int i = 0; i < n; i++) arr[i] = new int[]{intervals[i][0], intervals[i][1], i};
        Arrays.sort(arr, (o1, o2) -> o1[0] - o2[0]);

        int[] res = new int[n];
        Arrays.fill(res, -1);
        for (int[] x : arr) {
            int l = 0, r = n - 1;
            while (l < r) {
                int mid = l + r >> 1;
                if (arr[mid][0] >= x[1]) r = mid;
                else l = mid + 1;
            }
            if (arr[r][0] >= x[1]) res[x[2]] = arr[r][2];
        }
        return res;
    }
}