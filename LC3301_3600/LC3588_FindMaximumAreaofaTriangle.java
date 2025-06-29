package LC3301_3600;
import java.util.*;
public class LC3588_FindMaximumAreaofaTriangle {
    /**
     * You are given a 2D array coords of size n x 2, representing the coordinates of n points in an infinite Cartesian
     * plane.
     *
     * Find twice the maximum area of a triangle with its corners at any three elements from coords, such that at least
     * one side of this triangle is parallel to the x-axis or y-axis. Formally, if the maximum area of such a triangle
     * is A, return 2 * A.
     *
     * If no such triangle exists, return -1.
     *
     * Note that a triangle cannot have zero area.
     *
     * Input: coords = [[1,1],[1,2],[3,2],[3,3]]
     * Output: 2
     *
     * Input: coords = [[1,1],[2,2],[3,3]]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= n == coords.length <= 10^5
     * 1 <= coords[i][0], coords[i][1] <= 10^6
     * All coords[i] are unique.
     * @param coords
     * @return
     */
    // time = O(nlogn), space = O(n)
    public long maxArea(int[][] coords) {
        HashMap<Integer, List<Integer>> xMap = new HashMap<>();
        HashMap<Integer, List<Integer>> yMap = new HashMap<>();
        int xmin = Integer.MAX_VALUE, xmax = Integer.MIN_VALUE;
        int ymin = Integer.MAX_VALUE, ymax = Integer.MIN_VALUE;
        for (int[] c : coords) {
            int x = c[0], y = c[1];
            xMap.putIfAbsent(x, new ArrayList<>());
            xMap.get(x).add(y);
            yMap.putIfAbsent(y, new ArrayList<>());
            yMap.get(y).add(x);
            xmin = Math.min(xmin, x);
            xmax = Math.max(xmax, x);
            ymin = Math.min(ymin, y);
            ymax = Math.max(ymax, y);
        }
        for (List<Integer> v : xMap.values()) Collections.sort(v);
        for (List<Integer> v : yMap.values()) Collections.sort(v);

        long res = -1;
        for (int x : xMap.keySet()) {
            List<Integer> ys = xMap.get(x);
            int m = ys.size();
            if (m < 2) continue;
            int d = ys.get(m - 1) - ys.get(0);
            int h = Math.max(x - xmin, xmax - x);
            res = Math.max(res, 1L * d * h);
        }

        for (int y : yMap.keySet()) {
            List<Integer> xs = yMap.get(y);
            int m = xs.size();
            if (m < 2) continue;
            int d = xs.get(m - 1) - xs.get(0);
            int h = Math.max(y - ymin, ymax - y);
            res = Math.max(res, 1L * d * h);
        }
        return res == 0 ? -1 : res;
    }

    // s2
    // time = O(n), space = O(n)
    long res;
    public long maxArea2(int[][] coords) {
        cal(coords);
        for (int[] c : coords) {
            int t = c[0];
            c[0] = c[1];
            c[1] = t;
        }
        cal(coords);
        return res > 0 ? res : -1;
    }

    private void cal(int[][] coords) {
        int minx = Integer.MAX_VALUE, maxx = 0;
        HashMap<Integer, Integer> miny = new HashMap<>();
        HashMap<Integer, Integer> maxy = new HashMap<>();
        for (int[] c : coords) {
            int x = c[0], y = c[1];
            minx = Math.min(minx, x);
            maxx = Math.max(maxx, x);
            maxy.put(x, Math.max(maxy.getOrDefault(x, 0), y));
            miny.put(x, Math.min(miny.getOrDefault(x, y), y));
        }

        for (int x : miny.keySet()) {
            int y1 = miny.get(x), y2 = maxy.get(x);
            int w = Math.max(maxx - x, x - minx), h = y2 - y1;
            res = Math.max(res, 1L * w * h);
        }
    }
}