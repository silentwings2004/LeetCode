package LC001_300;
import java.util.*;
public class LC253_MeetingRoomsII {
    /**
     * Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number
     * of conference rooms required.
     *
     * Input: intervals = [[0,30],[5,10],[15,20]]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= intervals.length <= 10^4
     * 0 <= starti < endi <= 10^6
     * @param intervals
     * @return
     */
    // S1
    // time = O(nlogn), space = O(n)
    public int minMeetingRooms(int[][] intervals) {
        List<int[]> diff = new ArrayList<>();
        for (int[] x : intervals) {
            int a = x[0], b = x[1];
            diff.add(new int[]{a, 1});
            diff.add(new int[]{b, -1});
        }

        Collections.sort(diff, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);

        int res = 0, sum = 0;
        for (int[] x : diff) {
            sum += x[1];
            res = Math.max(res, sum);
        }
        return res;
    }

    // S2: Sort + PQ
    // time = O(nlogn), space = O(n)
    public int minMeetingRooms2(int[][] intervals) {
        // corner case
        if (intervals == null || intervals.length == 0 || intervals[0] == null || intervals[0].length == 0) {
            return 0;
        }

        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        int count = 0, i = 0, n = intervals.length;
        while (i < n) {
            while (pq.isEmpty() || i < n && pq.peek()[1] > intervals[i][0]) {
                pq.offer(intervals[i]);
                i++;
            }
            count = Math.max(count, pq.size());
            pq.poll(); // 弹出最早结束的一个会议
        }
        return count;
    }

    // S3: diff array
    // time = O(n), space = O(n)
    final int N = 1000010;
    public int minMeetingRooms3(int[][] intervals) {
        int[] b = new int[N];
        for (int[] x : intervals) {
            b[x[0]]++;
            b[x[1]]--;
        }

        int sum = 0, res = 0;
        for (int i = 0; i <= (int)1e6; i++) {
            sum += b[i];
            res = Math.max(res, sum);
        }
        return res;
    }
}