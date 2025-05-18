package LC3001_3300;
import java.util.*;
public class LC3143_MaximumPointsInsidetheSquare {
    /**
     * You are given a 2D array points and a string s where, points[i] represents the coordinates of point i, and s[i]
     * represents the tag of point i.
     *
     * A valid square is a square centered at the origin (0, 0), has edges parallel to the axes, and does not contain
     * two points with the same tag.
     *
     * Return the maximum number of points contained in a valid square.
     *
     * Note:
     *
     * A point is considered to be inside the square if it lies on or within the square's boundaries.
     * The side length of the square can be zero.
     *
     * Input: points = [[2,2],[-1,-2],[-4,4],[-3,1],[3,-3]], s = "abdca"
     * Output: 2
     *
     * Input: points = [[1,1],[-2,-2],[-2,2]], s = "abb"
     * Output: 1
     *
     * Input: points = [[1,1],[-1,-1],[2,-2]], s = "ccd"
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= s.length, points.length <= 10^5
     * points[i].length == 2
     * -10^9 <= points[i][0], points[i][1] <= 10^9
     * s.length == points.length
     * points consists of distinct coordinates.
     * s consists only of lowercase English letters.
     * @param points
     * @param s
     * @return
     */
    // S1
    // time = O(nlogn), space = O(1)
    int res;
    public int maxPointsInsideSquare(int[][] points, String s) {
        int n = s.length();
        int l = 0, r = (int)1e9;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (check(points, s, mid)) l = mid;
            else r = mid - 1;
        }
        check(points, s, r);
        return res;
    }

    private boolean check(int[][] p, String s, int mid) {
        int n = s.length();
        int state = 0;
        for (int i = 0; i < n; i++) {
            int x = p[i][0], y = p[i][1];
            if (Math.abs(x) > mid || Math.abs(y) > mid) continue;
            int u = s.charAt(i) - 'a';
            if ((state >> u & 1) == 1) return false;
            state |= 1 << u;
        }
        res = Integer.bitCount(state);
        return true;
    }

    // S2
    // time = O(1), space = O(n)
    public int maxPointsInsideSquare2(int[][] points, String s) {
        int[] p = new int[26];
        Arrays.fill(p, Integer.MAX_VALUE);
        int min2 = Integer.MAX_VALUE;
        int n = points.length;
        for (int i = 0; i < n; i++) {
            int x = points[i][0], y = points[i][1];
            int d = Math.max(Math.abs(x), Math.abs(y));
            int u = s.charAt(i) - 'a';
            if (d < p[u]) {
                min2 = Math.min(min2, p[u]);
                p[u] = d;
            } else min2 = Math.min(min2, d);
        }
        int res = 0;
        for (int x : p) {
            if (x < min2) res++;
        }
        return res;
    }
}