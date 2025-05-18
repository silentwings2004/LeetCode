package LC1201_1500;
import java.util.*;
public class LC1453_MaximumofDartsInsideofaCircularDartboard {
    /**
     * Alice is throwing n darts on a very large wall. You are given an array darts where darts[i] = [xi, yi] is the
     * position of the ith dart that Alice threw on the wall.
     *
     * Bob knows the positions of the n darts on the wall. He wants to place a dartboard of radius r on the wall so
     * that the maximum number of darts that Alice throws lie on the dartboard.
     *
     * Given the integer r, return the maximum number of darts that can lie on the dartboard.
     *
     * Input: darts = [[-2,0],[2,0],[0,2],[0,-2]], r = 2
     * Output: 4
     *
     * Input: darts = [[-3,0],[3,0],[2,6],[5,4],[0,9],[7,8]], r = 5
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= darts.length <= 100
     * darts[i].length == 2
     * -10^4 <= xi, yi <= 10^4
     * All the darts are unique
     * 1 <= r <= 5000
     * @param darts
     * @param r
     * @return
     */
    // time = O(n^3), space = O(1)
    public int numPoints(int[][] darts, int r) {
        int n = darts.length, res = 1;
        final double eps = 1e-8;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                double[][] centers = get_centers(darts[i], darts[j], r);
                for (double[] center : centers) {
                    int cnt = 0;
                    double cx = center[0], cy = center[1];
                    for (int k = 0; k < n; k++) {
                        double dx = darts[k][0] - cx, dy = darts[k][1] - cy;
                        if (f(dx) + f(dy) <= f(r) + eps) cnt++;
                    }
                    res = Math.max(res, cnt);
                }
            }
        }
        return res;
    }

    private double f(double x) {
        return x * x;
    }

    private double[][] get_centers(int[] a, int[] b, double r) {
        double x0 = a[0], y0 = a[1];
        double x1 = b[0], y1 = b[1];
        double cx = (x0 + x1) / 2, cy = (y0 + y1) / 2;
        double d = Math.sqrt(f(x0 - x1) + f(y0 - y1));
        d /= 2;
        double[][] centers = new double[2][2];
        if (d > r) return centers;

        double h = Math.sqrt(f(r) - f(d));
        d *= 2;
        double vx = (x1 - x0) / d, vy = (y1 - y0) / d;
        double hx = -vy, hy = vx;
        centers[0] = new double[]{cx + h * hx, cy + h * hy};
        centers[1] = new double[]{cx - h * hx, cy - h * hy};
        return centers;
    }
}
/**
 * 2点确定一个圆 => 只有2种位置，再分别枚举这2个圆，判断有多少个点在圆内
 * 求圆心
 */