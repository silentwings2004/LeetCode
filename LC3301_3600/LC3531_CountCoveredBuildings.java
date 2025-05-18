package LC3301_3600;
import java.util.*;
public class LC3531_CountCoveredBuildings {
    /**
     * You are given a positive integer n, representing an n x n city. You are also given a 2D grid buildings, where
     * buildings[i] = [x, y] denotes a unique building located at coordinates [x, y].
     *
     * A building is covered if there is at least one building in all four directions: left, right, above, and below.
     *
     * Return the number of covered buildings.
     *
     * Input: n = 3, buildings = [[1,2],[2,2],[3,2],[2,1],[2,3]]
     * Output: 1
     *
     * Input: n = 3, buildings = [[1,1],[1,2],[2,1],[2,2]]
     * Output: 0
     *
     * Input: n = 5, buildings = [[1,3],[3,2],[3,3],[3,5],[5,3]]
     * Output: 1
     *
     * Constraints:
     *
     * 2 <= n <= 10^5
     * 1 <= buildings.length <= 10^5
     * buildings[i] = [x, y]
     * 1 <= x, y <= n
     * All coordinates of buildings are unique.
     * @param n
     * @param buildings
     * @return
     */
    // S1: TreeSet
    // time = O(nlogn), space = O(n)
    public int countCoveredBuildings(int n, int[][] buildings) {
        HashMap<Integer, TreeSet<Integer>> xm = new HashMap<>();
        HashMap<Integer, TreeSet<Integer>> ym = new HashMap<>();
        for (int[] b : buildings) {
            int x = b[0], y = b[1];
            xm.putIfAbsent(x, new TreeSet<>());
            ym.putIfAbsent(y, new TreeSet<>());
            xm.get(x).add(y);
            ym.get(y).add(x);
        }
        int res = 0;
        for (int[] b : buildings) {
            int x = b[0], y = b[1];
            TreeSet<Integer> xs = xm.getOrDefault(x, new TreeSet<>());
            TreeSet<Integer> ys = ym.getOrDefault(y, new TreeSet<>());
            if (xs.size() == 0 || ys.size() == 0) continue;
            Integer ly = xs.lower(y), ry = xs.higher(y);
            Integer lx = ys.lower(x), rx = ys.higher(x);
            if (lx != null && rx != null && ly != null && ry != null) res++;
        }
        return res;
    }

    // S2: Min + Max
    // time = O(n + m), space = O(n)
    public int countCoveredBuildings2(int n, int[][] buildings) {
        int[] rmin = new int[n + 1];
        int[] rmax = new int[n + 1];
        int[] cmin = new int[n + 1];
        int[] cmax = new int[n + 1];
        Arrays.fill(rmin, n + 1);
        Arrays.fill(cmin, n + 1);

        for (int[] b : buildings) {
            int x = b[0], y = b[1];
            rmin[y] = Math.min(rmin[y], x);
            rmax[y] = Math.max(rmax[y], x);
            cmin[x] = Math.min(cmin[x], y);
            cmax[x] = Math.max(cmax[x], y);
        }

        int res = 0;
        for (int[] b : buildings) {
            int x = b[0], y = b[1];
            if (x > rmin[y] && x < rmax[y] && y > cmin[x] && y < cmax[x]) res++;
        }
        return res;
    }
}
/**
 * 记录同一行的最小横坐标和最大横坐标，同一列的最小纵坐标和最大纵坐标。
 * 对于每个建筑 (x,y)，如果 x 在这一行的最小值和最大值之间（不能相等），y 在这一列的最小值和最大值之间（不能相等），那么答案加一。
 */