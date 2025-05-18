package LC301_600;
import java.util.*;
public class LC452_MinimumNumberofArrowstoBurstBalloons {
    /**
     * There are some spherical balloons taped onto a flat wall that represents the XY-plane. The balloons are
     * represented as a 2D integer array points where points[i] = [xstart, xend] denotes a balloon whose horizontal
     * diameter stretches between xstart and xend. You do not know the exact y-coordinates of the balloons.
     *
     * Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis.
     * A balloon with xstart and xend is burst by an arrow shot at x if xstart <= x <= xend. There is no limit to the
     * number of arrows that can be shot. A shot arrow keeps traveling up infinitely, bursting any balloons in its path.
     *
     * Given the array points, return the minimum number of arrows that must be shot to burst all balloons.
     *
     * Input: points = [[10,16],[2,8],[1,6],[7,12]]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= points.length <= 10^5
     * points[i].length == 2
     * -2^31 <= xstart < xend <= 2^31 - 1
     * @param points
     * @return
     */
    // time = O(nlogn), space = O(1)
    public int findMinArrowShots(int[][] points) {
        // 注意：这里不能直接用o1[1] - o2[1],因为会越界
        // 或者也可以写成 Arrays.sort(points, (o1, o2) -> Long.compare(o1[1], o2[1]));
        Arrays.sort(points, (o1, o2) -> {
            if (o1[1] < o2[1]) return -1;
            if (o1[1] > o2[1]) return 1;
            return 0;
        }); // 注意：这里不能直接用o1[1] - o2[1],因为会越界

        int res = 1, r = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > r) {
                res++;
                r = points[i][1];
            }
        }
        return res;
    }

    // S2: 手写quick_sort(最优解)
    // time = O(nlogn), space = O(1)
    public int findMinArrowShots2(int[][] points) {
        int n = points.length;
        quick_sort(points, 0, n - 1);
        int res = 1, r = points[0][1];
        for (int i = 1; i < n; i++) {
            if (points[i][0] > r) {
                res++;
                r = points[i][1];
            }
        }
        return res;
    }

    private void quick_sort(int[][] q, int l, int r) {
        if (l >= r) return;

        int x = q[l + r >> 1][1], i = l - 1, j = r + 1;
        while (i < j) {
            while (q[++i][1] < x);
            while (q[--j][1] > x);
            if (i < j) {
                int[] t = q[i];
                q[i] = q[j];
                q[j] = t;
            }
        }
        quick_sort(q, l, j);
        quick_sort(q, j + 1, r);
    }
}
/**
 * 区间型贪心法，通过区间排序得到意想不到的效果
 * sort by starting points: the minimum number of intervals to cover the whole range
 * sort by ending point: the maximum number of non-overlapping intervals
 * 如果倒过来看，ending point 与 starting point等价的话，两者都可以做
 * 6个non-overlapping intervals -> 至少要6根箭  ans >= m
 * 只有选中某些特定的m个non-overlapping intervals才能把其他overlapping的intervals射穿
 * 射在区间的右端点，目的是等更多的区间出现来射穿，一箭多雕
 * ref: LC435，解法是一模一样的
 * ref: AC905 区间选点
 * 1. 先将所有区间按照右端点来排序
 * 2. 排完之后从左往右扫描，每次选当前区间的右端点,看下个区间是否和当前这个区间有交集
 * 有交集就跳过
 *
 * 等价于给我们一堆线段，问选多少个点，使得每个线段都包含一个点
 * ref: AC905 区间选点
 * 先将所有区间按照右端点来排序，从左往右扫描，每次选当前区间的右端点
 * 贪心解 >= 最优解
 * 贪心解 <= 最优解  => 至少需要k个点才能cover这k个互不相交的区间
 */
