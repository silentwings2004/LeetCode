package LC001_300;
import java.util.*;
public class LC149_MaxPointsonaLine {
    /**
     * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane, return the maximum
     * number of points that lie on the same straight line.
     *
     * Input: points = [[1,1],[2,2],[3,3]]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= points.length <= 300
     * points[i].length == 2
     * -10^4 <= xi, yi <= 10^4
     * All the points are unique.
     * @param points
     * @return
     */
    // time = O(n^2), space = O(n)
    public int maxPoints(int[][] points) {
        int n = points.length, res = 0;
        for (int i = 0; i < n; i++) {
            HashMap<String, Integer> map = new HashMap<>();
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                int x1 = points[i][0], y1 = points[i][1];
                int x2 = points[j][0], y2 = points[j][1];
                int dx = x2 - x1, dy = y2 - y1;
                int g = gcd(dx, dy);
                int a = dx / g, b = dy / g;
                String h = a + "#" + b;
                map.put(h, map.getOrDefault(h, 0) + 1);
                res = Math.max(res, map.get(h));
            }
        }
        return res + 1;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // S2
    // time = O(n^2), space = O(n)
    public int maxPoints2(int[][] points) {
        int res = 0;
        for (int[] p : points) {
            int ss = 0, vs = 0;
            HashMap<Double, Integer> map = new HashMap<>();
            for (int[] q : points) {
                if (p[0] == q[0] && p[1] == q[1]) ss++;
                else if (p[0] == q[0]) vs++;
                else {
                    double k = (double)(q[1] - p[1]) / (q[0] - p[0]);
                    map.put(k, map.getOrDefault(k, 0) + 1);
                }
            }
            int c = vs;
            for (double key : map.keySet()) c = Math.max(c, map.get(key));
            res = Math.max(res, c + ss);
        }
        return res;
    }
}
/**
 * o(n^2) 根据斜率去看哪些点在一条直线上 => 是0怎么办，90度怎么办
 * dy/dx => a/b 化简成一个最简分数
 * double slope = dy * 1.0 / dx; // 可能精度会不够，非常危险！！！ -> 不提倡
 *
 * 1. 先枚举所有直线 => O(n^2)
 * 2. 判断直线上有多少个点 => O(n)
 * 优化：先枚举中心点，再枚举其他所有点，求下这个点和中心点的斜率
 * 可以用斜率来表示直线，单独拿个变量记录下垂直的直线
 */