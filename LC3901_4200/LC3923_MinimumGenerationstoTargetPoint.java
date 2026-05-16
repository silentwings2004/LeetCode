package LC3901_4200;
import java.util.*;
public class LC3923_MinimumGenerationstoTargetPoint {
    /**
     * You are given a 2D integer array points where points[i] = [xi, yi, zi] represents a point in 3D space, and an
     * integer array target representing a target point.
     *
     * Define generation 0 as the initial list of points. For each integer k >= 1, form generation k as follows:
     *
     * Consider every pair of two distinct points a = [x1, y1, z1] and b = [x2, y2, z2] taken from all points produced
     * in generations 0 through k - 1.
     * For each such pair, compute c = [floor((x1 + x2) / 2), floor((y1 + y2) / 2), floor((z1 + z2) / 2)] and collect
     * every such c into a generation k.
     * All points in the generation k are produced simultaneously from points in generations 0 through k - 1.
     * After generation k is formed, the points in the generation k are considered available for forming later generations.
     * Return the smallest integer k such that the target appears in one of the generations 0 through k. If the target
     * is already in the initial points, return 0. If it is impossible to obtain the target, return -1.
     *
     * Notes:
     *
     * floor denotes rounding down to the nearest integer.
     * "Two distinct points" means the two chosen points must have different (x, y, z) coordinates. A point cannot be
     * paired with itself, and pairing two points with identical coordinates is not possible.
     *
     * Input: points = [[0,0,0],[6,6,6]], target = [3,3,3]
     * Output: 1
     *
     * Input: points = [[0,0,0],[5,5,5]], target = [1,1,1]
     * Output: 2
     *
     * Input: points = [[0,0,0],[2,2,2],[3,3,3]], target = [2,2,2]
     * Output: 0
     *
     * Input: points = [[1,2,3]], target = [5,5,5]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= points.length <= 20
     * points[i] = [xi, yi, zi]
     * 0 <= xi, yi, zi <= 6
     * target.length == 3
     * 0 <= target[i] <= 6
     * The initial set of points contains no duplicates.
     * @param points
     * @param target
     * @return
     */
    // time = O(U^9), space = O(U^3)  U <= 7
    public int minGenerations(int[][] points, int[] target) {
        HashSet<String> set = new HashSet<>();
        for (int[] p : points) set.add(get(p));
        String t = get(target);
        if (set.contains(t)) return 0;
        if (set.size() < 2) return -1;

        int res = 0;
        while (true) {
            res++;
            HashSet<String> set2 = new HashSet<>();
            List<int[]> q = new ArrayList<>();
            for (String s : set) {
                String[] strs = s.split("#");
                int x = Integer.parseInt(strs[0]);
                int y = Integer.parseInt(strs[1]);
                int z = Integer.parseInt(strs[2]);
                q.add(new int[]{x, y, z});
            }

            int n = q.size();
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    int[] a = q.get(i), b = q.get(j);
                    if (a[0] == b[0] && a[1] == b[1] && a[2] == b[2]) continue;
                    int x2 = (a[0] + b[0]) / 2;
                    int y2 = (a[1] + b[1]) / 2;
                    int z2 = (a[2] + b[2]) / 2;
                    int[] p = new int[]{x2, y2, z2};
                    String h = get(p);
                    if (!set.contains(h)) set2.add(h);
                }
            }
            if (set2.contains(t)) return res;
            if (set2.size() == 0) return -1;
            set.addAll(set2);
        }
    }

    private String get(int[] x) {
        return x[0] + "#" + x[1] + "#" + x[2];
    }
}