package LC3601_3900;
import java.util.*;
public class LC3873_MaximumPointsActivatedwithOneAddition {
    /**
     * You are given a 2D integer array points, where points[i] = [xi, yi] represents the coordinates of the ith point.
     * All coordinates in points are distinct.
     *
     * If a point is activated, then all points that have the same x-coordinate or y-coordinate become activated as well.
     *
     * Activation continues until no additional points can be activated.
     *
     * You may add one additional point at any integer coordinate (x, y) not already present in points. Activation
     * begins by activating this newly added point.
     *
     * Return an integer denoting the maximum number of points that can be activated, including the newly added point.
     *
     * Input: points = [[1,1],[1,2],[2,2]]
     * Output: 4
     *
     * Input: points = [[2,2],[1,1],[3,3]]
     * Output: 3
     *
     * Input: points = [[2,3],[2,2],[1,1],[4,5]]
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= points.length <= 10^5
     * points[i] = [xi, yi]
     * -10^9 <= xi, yi <= 10^9
     * points contains all distinct coordinates.
     * @param points
     * @return
     */
    // time = O(nlogn), space = O(n)
    int[] p, sz;
    public int maxActivated(int[][] points) {
        HashMap<Integer, Integer> xm = new HashMap<>();
        HashMap<Integer, Integer> ym = new HashMap<>();
        int n = 0;
        for (int[] x : points) {
            if (!xm.containsKey(x[0])) xm.put(x[0], n++);
            if (!ym.containsKey(x[1])) ym.put(x[1], n++);
        }

        p = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; i++) p[i] = i;
        for (int[] x : points) {
            int u = find(xm.get(x[0])), v = find(ym.get(x[1]));
            p[u] = v;
        }
        for (int[] x : points) {
            int u = find(xm.get(x[0]));
            sz[u]++;
        }

        int mx1 = 0, mx2 = 0;
        for (int i = 0; i < n; i++) {
            if (i == find(i)) {
                if (sz[i] > mx1) {
                    mx2 = mx1;
                    mx1 = sz[i];
                } else if (sz[i] > mx2) mx2 = sz[i];
            }
        }
        return mx1 + mx2 + 1;
    }

    private int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }
}
