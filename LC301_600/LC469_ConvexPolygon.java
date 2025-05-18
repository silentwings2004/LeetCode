package LC301_600;
import java.util.*;
public class LC469_ConvexPolygon {
    /**
     * You are given an array of points on the X-Y plane points where points[i] = [xi, yi]. The points form a polygon
     * when joined sequentially.
     *
     * Return true if this polygon is convex and false otherwise.
     *
     * You may assume the polygon formed by given points is always a simple polygon. In other words, we ensure that
     * exactly two edges intersect at each vertex and that edges otherwise don't intersect each other.
     *
     * Input: points = [[0,0],[0,5],[5,5],[5,0]]
     * Output: true
     *
     * Input: points = [[0,0],[0,10],[10,10],[10,0],[5,5]]
     * Output: false
     *
     * Constraints:
     *
     * 3 <= points.length <= 10^4
     * points[i].length == 2
     * -10^4 <= xi, yi <= 10^4
     * All the given points are unique.
     * @param points
     * @return
     */
    // time = O(1), space = O(1)
    public boolean isConvex(List<List<Integer>> points) {
        int flag = 0, n = points.size();
        for (int i = 0; i < n; i++) {
            int angle = helper(points, i);
            if (angle == 0) continue;
            if (flag == 0) flag = angle > 0 ? 1 : -1;
            else if (flag > 0 != angle > 0) return false;
        }
        return true;
    }

    private int helper(List<List<Integer>> points, int i) {
        int n = points.size();
        List<Integer> c = points.get(i % n);
        List<Integer> b = points.get((i + 1) % n);
        List<Integer> a = points.get((i + 2) % n);
        return (a.get(1) - b.get(1)) * (b.get(0) - c.get(0)) - (b.get(1) - c.get(1)) * (a.get(0) - b.get(0));
    }
}
