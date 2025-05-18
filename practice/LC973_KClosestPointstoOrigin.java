package practice;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: KClosestPointstoOrigin
 * Creater: Silentwings
 * Date: May, 2020
 * Description: 973. K Closest Points to Origin
 */
public class LC973_KClosestPointstoOrigin {
    /**
     * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
     *
     * (Here, the distance between two points on a plane is the Euclidean distance.)
     *
     * You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is
     * in.)
     *
     *
     *
     * Example 1:
     *
     * Input: points = [[1,3],[-2,2]], K = 1
     * Output: [[-2,2]]
     * Explanation:
     * The distance between (1, 3) and the origin is sqrt(10).
     * The distance between (-2, 2) and the origin is sqrt(8).
     * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
     * We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
     * Example 2:
     *
     * Input: points = [[3,3],[5,-1],[-2,4]], K = 2
     * Output: [[3,3],[-2,4]]
     * (The answer [[-2,4],[3,3]] would also be accepted.)
     *
     *
     * Note:
     *
     * 1 <= K <= points.length <= 10000
     * -10000 < points[i][0] < 10000
     * -10000 < points[i][1] < 10000
     * @param points
     * @param K
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int[][] kClosest(int[][] points, int K) {
        int[][] res = new int[K][2];
        // corner case
        if (points == null || points.length == 0 || points[0] == null || points[0].length == 0) {
            return res;
        }
        if (K >= points.length) return points;

        int len = points.length;
        int[] dist = new int[len];
        for (int i = 0; i < len; i++) {
            dist[i] = calDist(points[i]);
        }
        Arrays.sort(dist);
        int idx = 0;
        for (int i = 0; i < len; i++) {
            if (calDist(points[i]) <= dist[K - 1]) {
                res[idx++] = points[i];
            }
        }
        return res;
    }

    private int calDist(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
}
