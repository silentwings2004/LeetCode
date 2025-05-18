package LC3001_3300;
import java.util.*;
public class LC3111_MinimumRectanglestoCoverPoints {
    /**
     * You are given a 2D integer array points, where points[i] = [xi, yi]. You are also given an integer w. Your task
     * is to cover all the given points with rectangles.
     *
     * Each rectangle has its lower end at some point (x1, 0) and its upper end at some point (x2, y2), where x1 <= x2,
     * y2 >= 0, and the condition x2 - x1 <= w must be satisfied for each rectangle.
     *
     * A point is considered covered by a rectangle if it lies within or on the boundary of the rectangle.
     *
     * Return an integer denoting the minimum number of rectangles needed so that each point is covered by at least one
     * rectangle.
     *
     * Note: A point may be covered by more than one rectangle.
     *
     * Input: points = [[2,1],[1,0],[1,4],[1,8],[3,5],[4,6]], w = 1
     *
     * Output: 2
     *
     * Input: points = [[0,0],[1,1],[2,2],[3,3],[4,4],[5,5],[6,6]], w = 2
     *
     * Output: 3
     *
     * Input: points = [[2,3],[1,2]], w = 0
     *
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= points.length <= 10^5
     * points[i].length == 2
     * 0 <= xi == points[i][0] <= 10^9
     * 0 <= yi == points[i][1] <= 10^9
     * 0 <= w <= 10^9
     * All pairs (xi, yi) are distinct.
     * @param points
     * @param w
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public int minRectanglesToCoverPoints(int[][] points, int w) {
        Arrays.sort(points, (o1, o2) -> o1[0] - o2[0]);
        int n = points.length, res = 0;
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && points[j][0] - points[i][0] <= w) j++;
            res++;
            i = j - 1;
        }
        return res;
    }
}