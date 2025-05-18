package LC1201_1500;

public class LC1266_MinimumTimeVisitingAllPoints {
    /**
     * On a 2D plane, there are n points with integer coordinates points[i] = [xi, yi]. Return the minimum time in
     * seconds to visit all the points in the order given by points.
     *
     * You can move according to these rules:
     *
     * In 1 second, you can either:
     * move vertically by one unit,
     * move horizontally by one unit, or
     * move diagonally sqrt(2) units (in other words, move one unit vertically then one unit horizontally in 1 second).
     * You have to visit the points in the same order as they appear in the array.
     * You are allowed to pass through points that appear later in the order, but these do not count as visits.
     *
     * Input: points = [[1,1],[3,4],[-1,0]]
     * Output: 7
     *
     * Input: points = [[3,2],[-2,2]]
     * Output: 5
     *
     * Constraints:
     *
     * points.length == n
     * 1 <= n <= 100
     * points[i].length == 2
     * -1000 <= points[i][0], points[i][1] <= 1000
     * @param points
     * @return
     */
    // time = O(n), space = O(1)
    public int minTimeToVisitAllPoints(int[][] points) {
        int n = points.length, res = 0;
        for (int i = 1; i < n; i++) {
            int x1 = points[i - 1][0], y1 = points[i - 1][1];
            int x2 = points[i][0], y2 = points[i][1];
            int a = Math.abs(x1 - x2), b = Math.abs(y1 - y2);
            res += Math.max(a, b);
        }
        return res;
    }
}