package LC3001_3300;
import java.util.*;
public class LC3102_MinimizeManhattanDistances {
    /**
     * You are given a 0-indexed array points representing integer coordinates of some points on a 2D plane, where
     * points[i] = [xi, yi].
     *
     * The distance between two points is defined as their Manhattan distance.
     *
     * Return the minimum possible value for maximum distance between any two points by removing exactly one point.
     *
     * Input: points = [[3,10],[5,15],[10,2],[4,4]]
     * Output: 12
     *
     * Input: points = [[1,1],[1,1],[1,1]]
     * Output: 0
     *
     * Constraints:
     *
     * 3 <= points.length <= 10^5
     * points[i].length == 2
     * 1 <= points[i][0], points[i][1] <= 10^8
     * @param points
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public int minimumDistance(int[][] points) {
        int n = points.length;
        int[][][] w = new int[4][n][2];
        for (int i = 0; i < n; i++) {
            int x = points[i][0], y = points[i][1];
            w[0][i] = new int[]{x + y, i};
            w[1][i] = new int[]{-x + y, i};
            w[2][i] = new int[]{x - y, i};
            w[3][i] = new int[]{-x - y, i};
        }
        for (int i = 0; i < 4; i++) Arrays.sort(w[i], (o1, o2) -> o1[0] - o2[0]);

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int t = 0;
            for (int j = 0; j < 4; j++) {
                int[] a = w[j][n - 1], b = w[j][0];
                int idx1 = a[1], idx2 = b[1];
                if (idx1 == i) a = w[j][n - 2];
                else if (idx2 == i) b = w[j][1];
                t = Math.max(t, a[0] - b[0]);
            }
            res = Math.min(res, t);
        }
        return res;
    }

    // S2
    // time = O(nlogn), space = O(n)
    public int minimumDistance2(int[][] points) {
        TreeMap<Integer, Integer>[] mp = new TreeMap[4];
        for (int i = 0; i < 4; i++) mp[i] = new TreeMap<>();
        for (int[] p : points) {
            int x = p[0], y = p[1];
            add(mp[0], x + y);
            add(mp[1], x - y);
            add(mp[2], -x + y);
            add(mp[3], -x - y);
        }

        int res = 0x3f3f3f3f;
        for (int[] p : points) {
            int x = p[0], y = p[1];
            remove(mp[0], x + y);
            remove(mp[1], x - y);
            remove(mp[2], -x + y);
            remove(mp[3], -x - y);

            int ans = 0;
            ans = Math.max(ans, mp[0].lastKey() - mp[0].firstKey());
            ans = Math.max(ans, mp[1].lastKey() - mp[1].firstKey());
            ans = Math.max(ans, mp[2].lastKey() - mp[2].firstKey());
            ans = Math.max(ans, mp[3].lastKey() - mp[3].firstKey());
            res = Math.min(res, ans);

            add(mp[0], x + y);
            add(mp[1], x - y);
            add(mp[2], -x + y);
            add(mp[3], -x - y);
        }
        return res;
    }

    private void add(TreeMap<Integer, Integer> map, int x) {
        map.put(x, map.getOrDefault(x, 0) + 1);
    }

    private void remove(TreeMap<Integer, Integer> map, int x) {
        map.put(x, map.get(x) - 1);
        if (map.get(x) == 0) map.remove(x);
    }
}
/**
 * 切比雪夫距离
 * |x1 - x2| + |y1 - y2| = max(|x1' - x2'|, |y1' - y2'|)
 */