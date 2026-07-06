package LC3901_4200;
import java.util.*;
public class LC3975_FilterOccupiedIntervals {
    /**
     * You are given a 2D integer array occupiedIntervals, where occupiedIntervals[i] = [starti, endi] represents a time
     * interval during which you are occupied. Each interval starts at starti and ends at endi, inclusive. These
     * intervals may overlap.
     *
     * Additionally, you are given two integers, freeStart and freeEnd, which define a time interval during which you
     * are free. This free interval starts at freeStart and ends at freeEnd, inclusive.
     *
     * Your task is to merge all occupied intervals that overlap or touch, then remove all integer points in the free
     * interval from the merged occupied intervals.
     *
     * Two intervals touch if the second interval starts immediately after the first one ends. For example, [1, 1] and
     * [2, 2] touch and should be merged into [1, 2].
     *
     * Return the remaining occupied intervals in sorted order. The returned intervals must be non-overlapping and must
     * contain the minimum number of intervals possible. If there are no remaining occupied points, return an empty list.
     *
     * Input: occupiedIntervals = [[2,6],[4,8],[10,10],[10,12],[14,16]], freeStart = 7, freeEnd = 11
     * Output: [[2,6],[12,12],[14,16]]
     *
     * Input: occupiedIntervals = [[1,5],[2,3]], freeStart = 3, freeEnd = 8
     * Output: [[1,2]]
     *
     * Constraints:
     *
     * 1 <= occupiedIntervals.length <= 5 * 10^4
     * occupiedIntervals[i].length == 2
     * 1 <= starti <= endi <= 10^9
     * 1 <= freeStart <= freeEnd <= 10^9
     * @param occupiedIntervals
     * @param freeStart
     * @param freeEnd
     * @return
     */
    // time = O(nlogn), space = O(n)
    public List<List<Integer>> filterOccupiedIntervals(int[][] occupiedIntervals, int freeStart, int freeEnd) {
        Arrays.sort(occupiedIntervals, (o1, o2) -> o1[0] - o2[0]);
        int st = -1, ed = -1;
        List<int[]> q = new ArrayList<>();
        for (int[] x : occupiedIntervals) {
            if (ed + 1 < x[0]) {
                if (st != -1) q.add(new int[]{st, ed});
                st = x[0];
                ed = x[1];
            } else ed = Math.max(ed, x[1]);
        }
        if (st != -1) q.add(new int[]{st, ed});

        List<List<Integer>> res = new ArrayList<>();
        for (int[] x : q) {
            if (x[1] < freeStart - 1 || x[0] > freeEnd + 1) res.add(Arrays.asList(x[0], x[1]));
            else {
                if (x[0] <= freeStart - 1) res.add(Arrays.asList(x[0], freeStart - 1));
                if (x[1] >= freeEnd + 1) res.add(Arrays.asList(freeEnd + 1, x[1]));
            }
        }
        return res;
    }
}