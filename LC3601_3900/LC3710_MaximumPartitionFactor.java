package LC3601_3900;

public class LC3710_MaximumPartitionFactor {
    /**
     * You are given a 2D integer array points, where points[i] = [xi, yi] represents the coordinates of the ith point
     * on the Cartesian plane.
     *
     * The Manhattan distance between two points points[i] = [xi, yi] and points[j] = [xj, yj] is |xi - xj| + |yi - yj|.
     *
     * Split the n points into exactly two non-empty groups. The partition factor of a split is the minimum Manhattan
     * distance among all unordered pairs of points that lie in the same group.
     *
     * Return the maximum possible partition factor over all valid splits.
     *
     * Note: A group of size 1 contributes no intra-group pairs. When n = 2 (both groups size 1), there are no
     * intra-group pairs, so define the partition factor as 0.
     *
     * Input: points = [[0,0],[0,2],[2,0],[2,2]]
     *
     * Output: 4
     *
     * Input: points = [[0,0],[0,1],[10,0]]
     *
     * Output: 11
     *
     * Constraints:
     *
     * 2 <= points.length <= 500
     * points[i] = [xi, yi]
     * -10^8 <= xi, yi <= 10^8
     * @param points
     * @return
     */
    // time = O(n^2 * log(maxDist)), space = O(n)
    public int maxPartitionFactor(int[][] points) {
        int n = points.length;
        if (n == 2) return 0;
        int maxD = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int d = getDist(points[i], points[j]);
                maxD = Math.max(maxD, d);
            }
        }

        int l = 0, r = maxD + 10;
        while (l < r) {
            int mid = l + r >> 1;
            if (check(points, mid)) l = mid + 1;
            else r = mid;
        }
        return check(points, r) ? r : r - 1;
    }

    private int getDist(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }

    private boolean check(int[][] points, int mid) {
        int n = points.length;
        int[] color = new int[n];
        for (int i = 0; i < n; i++) {
            if (color[i] == 0) {
                if (!dfs(points, mid, color, i, 1)) return false;
            }
        }
        return true;
    }

    private boolean dfs(int[][] points, int mid, int[] color, int u, int c) {
        color[u] = c;
        for (int i = 0; i < points.length; i++) {
            if (i == u) continue;
            if (getDist(points[u], points[i]) < mid) {
                if (color[i] == c) return false;
                if (color[i] == 0 && !dfs(points, mid, color, i, 3 - c)) return false;
            }
        }
        return true;
    }
}