package LC301_600;
import java.util.*;
public class LC593_ValidSquare {
    /**
     * Given the coordinates of four points in 2D space p1, p2, p3 and p4, return true if the four points construct a
     * square.
     *
     * The coordinate of a point pi is represented as [xi, yi]. The input is not given in any order.
     *
     * A valid square has four equal sides with positive length and four equal angles (90-degree angles).
     *
     * Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
     * Output: true
     *
     * Constraints:
     *
     * p1.length == p2.length == p3.length == p4.length == 2
     * -10^4 <= xi, yi <= 10^4
     * @param p1
     * @param p2
     * @param p3
     * @param p4
     * @return
     */
    // S1
    // time = O(1), space = O(1)
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        HashSet<Integer> set = new HashSet<>();
        int[][] p = new int[][]{p1, p2, p3, p4};
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                set.add(get_dist(p[i], p[j]));
            }
        }
        return !set.contains(0) && set.size() == 2;
    }

    private int get_dist(int[] a, int[] b) {
        int dx = a[0] - b[0], dy = a[1] - b[1];
        return dx * dx + dy * dy;
    }

    // S2
    // time = O(1), space = O(1)
    public boolean validSquare2(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] p = new int[][]{p1, p2, p3, p4};
        int[] d = new int[6];
        for (int i = 0, k = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++, k++) {
                d[k] = get_dist(p[i], p[j]);
            }
        }
        Arrays.sort(d);
        if (d[0] == 0) return false;
        return d[0] == d[1] && d[0] == d[2] && d[0] == d[3] && d[4] == d[5];
    }
}
/**
 * 计算这四个点两两之间的距离，共6条边。把这六条边放进一个set里，如果这个集合只有两个元素，且没有零元素，则说明这六条边构成的是正方形。
 */