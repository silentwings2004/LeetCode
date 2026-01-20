package LC3601_3900;
import java.util.*;
public class LC3809_BestReachableTower {
    /**
     * You are given a 2D integer array towers, where towers[i] = [xi, yi, qi] represents the coordinates (xi, yi) and
     * quality factor qi of the ith tower.
     *
     * You are also given an integer array center = [cx, cy] representing your location, and
     * an integer radius.
     *
     * A tower is reachable if its Manhattan distance from center is less than or equal to radius.
     *
     * Among all reachable towers:
     *
     * Return the coordinates of the tower with the maximum quality factor.
     * If there is a tie, return the tower with the lexicographically smallest coordinate. If no tower is reachable,
     * return [-1, -1].
     * The Manhattan Distance between two cells (xi, yi) and (xj, yj) is |xi - xj| + |yi - yj|.
     * A coordinate [xi, yi] is lexicographically smaller than [xj, yj] if xi < xj, or xi == xj and yi < yj.
     *
     * |x| denotes the absolute value of x.
     *
     * Input: towers = [[1,2,5], [2,1,7], [3,1,9]], center = [1,1], radius = 2
     * Output: [3,1]
     *
     * Input: towers = [[1,3,4], [2,2,4], [4,4,7]], center = [0,0], radius = 5
     * Output: [1,3]
     *
     * Input: towers = [[5,6,8], [0,3,5]], center = [1,2], radius = 1
     * Output: [-1,-1]
     *
     * Constraints:
     *
     * 1 <= towers.length <= 10^5
     * towers[i] = [xi, yi, qi]
     * center = [cx, cy]
     * 0 <= xi, yi, qi, cx, cy <= 10^5
     * 0 <= radius <= 10^5
     * @param towers
     * @param center
     * @param radius
     * @return
     */
    // time = O(n), space = O(1)
    public int[] bestTower(int[][] towers, int[] center, int radius) {
        int mx = -1;
        int[] res = new int[]{-1, -1};
        for (int[] t : towers) {
            if (Math.abs(t[0] - center[0]) + Math.abs(t[1] - center[1]) <= radius) {
                if (t[2] > mx) {
                    mx = t[2];
                    res = new int[]{t[0], t[1]};
                } else if (t[2] == mx && (res[0] > t[0] || res[0] == t[0] && res[1] > t[1])) {
                    res = new int[]{t[0], t[1]};
                }
            }
        }
        return res;
    }
}