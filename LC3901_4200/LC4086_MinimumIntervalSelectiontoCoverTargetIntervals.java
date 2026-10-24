package LC3901_4200;
import java.util.*;
public class LC4086_MinimumIntervalSelectiontoCoverTargetIntervals {
    /**
     * You are given two 2D integer arrays, availableIntervals and targetIntervals, each representing a list of closed
     * intervals.
     *
     * Select a subset of intervals from availableIntervals so that every real point contained in any interval in
     * targetIntervals belongs to at least one selected interval.
     *
     * Points outside the target intervals do not need to be covered. Selected intervals may overlap or extend beyond
     * the target intervals.
     *
     * Return the minimum number of intervals you need to select. If it is impossible to cover all the target intervals,
     * return -1.
     *
     * A subset of an array is a selection of elements (possibly none) of the array.
     *
     * Input: availableIntervals = [[1,4],[1,7],[3,9],[5,10],[10,15]], targetIntervals = [[1,3],[2,5],[7,8],[10,13],
     * [11,15],[12,13]]
     * Output: 3
     *
     * Input: availableIntervals = [[4,6],[1,4],[2,5]], targetIntervals = [[3,4],[2,5],[6,7]]
     * Output: -1
     *
     * Input: availableIntervals = [[1,2],[3,4]], targetIntervals = [[1,4]]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= availableIntervals.length, targetIntervals.length <= 5 * 10^4
     * availableIntervals[i].length == targetIntervals[j].length == 2
     * 1 <= availableIntervals[i][0] <= availableIntervals[i][1] <= 10^5
     * 1 <= targetIntervals[j][0] <= targetIntervals[j][1] <= 10^5
     * @param availableIntervals
     * @param targetIntervals
     * @return
     */
    // time = O(nlogn + mlogm), space = O(m)
    public int minSelection(int[][] availableIntervals, int[][] targetIntervals) {
        List<int[]> q = merge(targetIntervals);
        Arrays.sort(availableIntervals, (o1, o2) -> o1[0] - o2[0]); // 按照左端点从小到大排序
        int curEnd = 0; // 已建造的桥的右端点
        int nxtEnd = 0; // 下一座桥的右端点的最大值
        int i = 0, res = 0;
        for (int[] x : q) {
            int l = x[0], r = x[1];
            while (curEnd < r) {
                int uncover = Math.max(l, curEnd); // 当前最靠左的未覆盖位置
                // 寻找下一座桥的最远点
                while (i < availableIntervals.length && availableIntervals[i][0] <= uncover) {
                    nxtEnd = Math.max(nxtEnd, availableIntervals[i++][1]);
                }
                // 无法覆盖 uncover，或者无法继续向右延伸
                if (nxtEnd < uncover || nxtEnd == uncover && uncover < r) return -1;
                curEnd = nxtEnd;
                res++;
            }
        }
        return res;
    }

    private List<int[]> merge(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        List<int[]> q = new ArrayList<>();
        int st = -1, ed = -1;
        for (int[] x : intervals) {
            if (x[0] > ed) {
                if (st != -1) q.add(new int[]{st, ed});
                st = x[0];
                ed = x[1];
            } else ed = Math.max(ed, x[1]);
        }
        if (st != -1) q.add(new int[]{st, ed});
        return q;
    }
}
/**
 * Ref: LC45 + LC56
 * 在遍历区间的过程中，对于当前区间：
 * 1.如果只看左边的区间(是否与我相交)，那么按右端点排序是更合适的，方便区分(例如二分查找)左边哪些区间与我相交、不相交
 * 2. 如果只看右边的区间(是否与我相交)，那么按左端点排序
 */