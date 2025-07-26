package LC3601_3900;
import java.util.*;
public class LC3623_CountNumberofTrapezoidsI {
    /**
     * You are given a 2D integer array points, where points[i] = [xi, yi] represents the coordinates of the ith point
     * on the Cartesian plane.
     *
     * A horizontal trapezoid is a convex quadrilateral with at least one pair of horizontal sides (i.e. parallel to
     * the x-axis). Two lines are parallel if and only if they have the same slope.
     *
     * Return the number of unique horizontal trapezoids that can be formed by choosing any four distinct points from
     * points.
     *
     * Since the answer may be very large, return it modulo 10^9 + 7.
     *
     * Input: points = [[1,0],[2,0],[3,0],[2,2],[3,2]]
     * Output: 3
     *
     * Input: points = [[0,0],[1,0],[0,1],[2,1]]
     * Output: 1
     *
     * Constraints:
     *
     * 4 <= points.length <= 10^5
     * –108 <= xi, yi <= 10^8
     * All points are pairwise distinct.
     * @param points
     * @return
     */
    // time = O(n), space = O(n)
    public int countTrapezoids(int[][] points) {
        long mod = (long)(1e9 + 7);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int[] p : points) map.put(p[1], map.getOrDefault(p[1], 0) + 1);
        long res = 0, s = 0;
        for (int v : map.values()) {
            if (v < 2) continue;
            long t = 1L * v * (v - 1) / 2;
            res = (res + t * s % mod) % mod;
            s = (s + t) % mod;
        }
        return (int)res;
    }
}
/**
 * 枚举右，维护左
 */