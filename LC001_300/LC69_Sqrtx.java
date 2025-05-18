package LC001_300;
import java.util.*;
public class LC69_Sqrtx {
    /**
     * Given a non-negative integer x, compute and return the square root of x.
     *
     * Since the return type is an integer, the decimal digits are truncated, and only the integer part of the result
     * is returned.
     *
     * Input: x = 4
     * Output: 2
     *
     * Constraints:
     *
     * 0 <= x <= 2^31 - 1
     * @param x
     * @return
     */
    // S1: 整数二分
    // time = O(logn), space = O(1)
    public int mySqrt(int x) {
        int l = 0, r = x;
        while (l < r) {
            int mid = r - (r - l) / 2;
            if (mid <= x / mid) l = mid;
            else r = mid - 1;
        }
        return l;
    }

    // S2: 浮点数二分
    final double eps = 1e-6;
    public int mySqrt2(int x) {
        double y = x;
        double l = 0, r = y;
        while (r - l > eps) {
            double mid = (l + r) / 2;
            if (mid * mid < y) l = mid;
            else r = mid;
        }
        return (int) r;
    }
}