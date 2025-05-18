package LC2401_2700;
import java.util.*;
public class LC2406_DivideIntervalsIntoMinimumNumberofGroups {
    /**
     * You are given a 2D integer array intervals where intervals[i] = [lefti, righti] represents the inclusive interval
     * [lefti, righti].
     *
     * You have to divide the intervals into one or more groups such that each interval is in exactly one group, and
     * no two intervals that are in the same group intersect each other.
     *
     * Return the minimum number of groups you need to make.
     *
     * Two intervals intersect if there is at least one common number between them. For example, the intervals [1, 5]
     * and [5, 8] intersect.
     *
     * Input: intervals = [[5,10],[6,8],[1,5],[2,3],[1,10]]
     * Output: 3
     *
     * Input: intervals = [[1,3],[5,6],[8,10],[11,13]]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= intervals.length <= 10^5
     * intervals[i].length == 2
     * 1 <= lefti <= righti <= 10^6
     * @param intervals
     * @return
     */
    // S1: diff array
    // time = O(nlogn), space = O(n)
    public int minGroups(int[][] intervals) {
        List<int[]> diff = new ArrayList<>();
        for (int[] x : intervals) {
            diff.add(new int[]{x[0], 1});
            diff.add(new int[]{x[1] + 1, -1});
        }

        Collections.sort(diff, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);

        int res = 0, sum = 0;
        for (int[] x : diff) {
            sum += x[1];
            res = Math.max(res, sum);
        }
        return res;
    }

    // S2: PQ
    // time = O(nlogn), space = O(n)
    public int minGroups2(int[][] intervals) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        int res = 0;
        for (int[] x : intervals) {
            if (!pq.isEmpty() && pq.peek() < x[0]) pq.poll();
            pq.offer(x[1]);
            res = Math.max(res, pq.size());
        }
        return res;
    }
}
/**
 * ref: LC253
 * 这里需要注意的是此题允许的那个区间的左右端点是重合的。
 * 如果我们在某一个时刻累加所有的新增会议和结束会议，那么可能会得到互相抵消的结果。
 * 解决方案很巧妙，我们将所有的双闭区间处理为"左闭右开"的区间。
 * 对于[left,right]，我们在left时刻加入会议，在right+1时刻退出会议即可。
 */