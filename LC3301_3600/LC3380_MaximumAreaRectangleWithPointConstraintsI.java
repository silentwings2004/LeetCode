package LC3301_3600;
import java.util.*;
public class LC3380_MaximumAreaRectangleWithPointConstraintsI {
    /**
     * You are given an array points where points[i] = [xi, yi] represents the coordinates of a point on an infinite
     * plane.
     *
     * Your task is to find the maximum area of a rectangle that:
     *
     * Can be formed using four of these points as its corners.
     * Does not contain any other point inside or on its border.
     * Has its edges parallel to the axes.
     * Return the maximum area that you can obtain or -1 if no such rectangle is possible.
     *
     * Input: points = [[1,1],[1,3],[3,1],[3,3]]
     * Output: 4
     *
     * Input: points = [[1,1],[1,3],[3,1],[3,3],[2,2]]
     * Output: -1
     *
     * Input: points = [[1,1],[1,3],[3,1],[3,3],[1,2],[3,2]]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= points.length <= 10
     * points[i].length == 2
     * 0 <= xi, yi <= 100
     * All the given points are unique.
     * @param points
     * @return
     */
    // time = O(n^4), space = O(n)
    public int maxRectangleArea(int[][] points) {
        int n = points.length, res = -1;
        HashSet<String> set = new HashSet<>();
        for (int[] p : points) set.add(p[0] + "#" + p[1]);
        for (int i = 0; i < n; i++) {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = 0; j < n; j++) {
                if (j != i && points[j][0] == x1) {
                    int x2 = points[j][0], y2 = points[j][1];
                    for (int k = 0; k < n; k++) {
                        if (k != i && k != j && points[k][1] == y1) {
                            int x3 = points[k][0], y3 = points[k][1];
                            // p4: (x3, y2)
                            if (set.contains(x3 + "#" + y2)) {
                                if (check(points, x1, y1, x3, y2)) {
                                    res = Math.max(res, Math.abs(x3 - x1) * Math.abs(y2 - y1));
                                }
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

    private boolean check(int[][] points, int x1, int y1, int x2, int y2) {
        int cnt = 0;
        for (int[] p : points) {
            int x = p[0], y = p[1];
            if (x >= Math.min(x1, x2) && x <= Math.max(x1, x2) && y >= Math.min(y1, y2) && y <= Math.max(y1, y2)) {
                cnt++;
                if (cnt > 4) return false;
            }
        }
        return cnt == 4;
    }
}