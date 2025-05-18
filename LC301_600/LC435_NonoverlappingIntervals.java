package LC301_600;
import java.util.*;
public class LC435_NonoverlappingIntervals {
    /**
     * Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals
     * you need to remove to make the rest of the intervals non-overlapping.
     *
     * Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= intervals.length <= 10^5
     * intervals[i].length == 2
     * -5 * 10^4 <= starti < endi <= 5 * 10^4
     * @param intervals
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[1] - o2[1]);
        int n = intervals.length, res = 1, r = intervals[0][1];
        for (int i = 1; i < n; i++) {
            if (intervals[i][0] >= r) {
                res++;
                r = intervals[i][1];
            }
        }
        return n - res;
    }
}
/**
 * 区间类问题2大方法：Greedy  手动设计一种最优策略
 * 1. sweeping line / diff -> 擅长解决多个区间互相overlap,不需要从中选取区间，只是考察其中overlap的情况。
 * 区间里去选一些区间来保留某个性质，扫描线没什么帮助，用扫描线的话眼中不再有区间，只有点
 * 2. sort
 * (1) sort by starting point => minimum number of intervals to cover the whole range
 * (2) sort by ending point => maximum number of intervals that are non-overlapping
 * 区间型dp
 * 按照ending point排序的话，永远保留第一个，因为它的ending point最靠前，它对后面这些区间的干扰就会越小
 * 这样就可以保证所选的这些区间都不会overlap
 * 在这里是从里面选区间，必须保证区间是完整的，而用扫描线则是把starting point ,end point 都打散了。
 *
 * 按区间右端点从小到大排序
 * 从左到右选择每个区间
 * 贪心解 = 最优解
 * 贪心解 <= 最优解 => 贪心解必然是一个有效解，而最优解是所有有效解离最大值，所以必然有最优解 >= 贪心解
 * 贪心解 >= 最优解 => [调整法] 将最优解的合法区间按照右端点从小到大排序，找到第一个和贪心解不一样的区间，当不一样的时候，由于贪心解从前往后选，
 * 贪心解选的必然是右端点最靠左边的一个，最优解的右端点一定>=贪心解的右端点，两者调换后仍然是一个最优解，依次类推可以将任何一个最优解的区间
 * 换成贪心解的区间，所以两者相等
 */
