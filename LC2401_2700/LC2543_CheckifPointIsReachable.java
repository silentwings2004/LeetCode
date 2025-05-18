package LC2401_2700;
import java.math.*;
public class LC2543_CheckifPointIsReachable {
    /**
     * There exists an infinitely large grid. You are currently at point (1, 1), and you need to reach the point
     * (targetX, targetY) using a finite number of steps.
     *
     * In one step, you can move from point (x, y) to any one of the following points:
     *
     * (x, y - x)
     * (x - y, y)
     * (2 * x, y)
     * (x, 2 * y)
     * Given two integers targetX and targetY representing the X-coordinate and Y-coordinate of your final position,
     * return true if you can reach the point from (1, 1) using some number of steps, and false otherwise.
     *
     * Input: targetX = 6, targetY = 9
     * Output: false
     *
     * Input: targetX = 4, targetY = 7
     * Output: true
     *
     * Constraints:
     *
     * 1 <= targetX, targetY <= 10^9
     * @param targetX
     * @param targetY
     * @return
     */
    // S1
    // time = O(logx + logy), space = O(1)
    public boolean isReachable(int targetX, int targetY) {
        int x = targetX, y = targetY;
        while (x > 0 && y > 0) {
            while (x % 2 == 0) x /= 2;
            while (y % 2 == 0) y /= 2;
            if (x == 1 && y == 1) return true;
            if (x > y) x -= y;
            else y -= x;
        }
        return false;
    }

    // S2
    // time = O(logx + logy), space = O(1)
    public boolean isReachable2(int targetX, int targetY) {
        int g = gcd(targetX, targetY);
        while (g % 2 == 0) g /= 2;
        return g == 1;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
/**
 * (x,y) -> (1,1)
 *
 * (x, y) -> (x, x + y)
 * (x, y) -> (x + y, x)
 * (x, y) -> (x / 2, y) if (x % 2 == 0)
 * (x, y) -> (x, y / 2) if (y % 2 == 0)
 * => (mx + ny, mmx + nny)
 * mx + ny = 1 <=> gcd(x, y) = 1  x,y互质
 * 凡是2的倍数都可以约掉
 * gcd(x,y) = 2^k
 * if (x or y is even) => (x/2, y), (x, y/2)
 * if (x and y  are odd) => ((x+y)/2, y), (x, (x+y)/2)
 * => (x == y == 1)
 */