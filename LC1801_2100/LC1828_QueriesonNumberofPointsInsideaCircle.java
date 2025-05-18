package LC1801_2100;

public class LC1828_QueriesonNumberofPointsInsideaCircle {
    /**
     * You are given an array points where points[i] = [xi, yi] is the coordinates of the ith point on a 2D plane.
     * Multiple points can have the same coordinates.
     *
     * You are also given an array queries where queries[j] = [xj, yj, rj] describes a circle centered at (xj, yj) with
     * a radius of rj.
     *
     * For each query queries[j], compute the number of points inside the jth circle. Points on the border of the
     * circle are considered inside.
     *
     * Return an array answer, where answer[j] is the answer to the jth query.
     *
     * Input: points = [[1,3],[3,3],[5,3],[2,2]], queries = [[2,3,1],[4,3,1],[1,1,2]]
     * Output: [3,2,2]
     *
     * Input: points = [[1,1],[2,2],[3,3],[4,4],[5,5]], queries = [[1,2,2],[2,2,2],[4,3,2],[4,3,3]]
     * Output: [2,3,2,4]
     *
     * Constraints:
     *
     * 1 <= points.length <= 500
     * points[i].length == 2
     * 0 <= xi, yi <= 500
     * 1 <= queries.length <= 500
     * queries[j].length == 3
     * 0 <= xj, yj <= 500
     * 1 <= rj <= 500
     * All coordinates are integers.
     *
     * Follow up: Could you find the answer for each query in better complexity than O(n)?
     * @param points
     * @param queries
     * @return
     */
    // time = O(n * m), space = O(1)
    public int[] countPoints(int[][] points, int[][] queries) {
        int n = queries.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int x1 = queries[i][0], y1 = queries[i][1], r = queries[i][2];
            for (int[] p : points) {
                int x2 = p[0], y2 = p[1];
                int dx = x2 - x1, dy = y2 - y1;
                if (dx * dx + dy * dy <= r * r) res[i]++;
            }
        }
        return res;
    }
}
