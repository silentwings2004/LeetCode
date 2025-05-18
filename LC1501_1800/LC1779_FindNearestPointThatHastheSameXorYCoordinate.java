package LC1501_1800;

public class LC1779_FindNearestPointThatHastheSameXorYCoordinate {
    /**
     * You are given two integers, x and y, which represent your current location on a Cartesian grid: (x, y). You are
     * also given an array points where each points[i] = [ai, bi] represents that a point exists at (ai, bi). A point is
     * valid if it shares the same x-coordinate or the same y-coordinate as your location.
     *
     * Return the index (0-indexed) of the valid point with the smallest Manhattan distance from your current location.
     * If there are multiple, return the valid point with the smallest index. If there are no valid points, return -1.
     *
     * The Manhattan distance between two points (x1, y1) and (x2, y2) is abs(x1 - x2) + abs(y1 - y2).
     *
     * Input: x = 3, y = 4, points = [[1,2],[3,1],[2,4],[2,3],[4,4]]
     * Output: 2
     *
     * Input: x = 3, y = 4, points = [[3,4]]
     * Output: 0
     *
     * Input: x = 3, y = 4, points = [[2,3]]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= points.length <= 10^4
     * points[i].length == 2
     * 1 <= x, y, ai, bi <= 10^4
     * @param x
     * @param y
     * @param points
     * @return
     */
    // time = O(n), space = O(1)
    public int nearestValidPoint(int x, int y, int[][] points) {
        int n = points.length, d = (int) 1e8, res = -1;
        for (int i = 0; i < n; i++) {
            int a = points[i][0], b = points[i][1];
            if (a == x || b == y) {
                int dist = Math.abs(x - a) + Math.abs(y - b);
                if (dist < d) {
                    d = dist;
                    res = i;
                }
            }
        }
        return res;
    }
}
