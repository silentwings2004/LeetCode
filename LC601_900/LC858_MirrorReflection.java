package LC601_900;

public class LC858_MirrorReflection {
    /**
     * There is a special square room with mirrors on each of the four walls. Except for the southwest corner, there
     * are receptors on each of the remaining corners, numbered 0, 1, and 2.
     *
     * The square room has walls of length p and a laser ray from the southwest corner first meets the east wall at a
     * distance q from the 0th receptor.
     *
     * Given the two integers p and q, return the number of the receptor that the ray meets first.
     *
     * The test cases are guaranteed so that the ray will meet a receptor eventually.
     *
     * Input: p = 2, q = 1
     * Output: 2
     *
     * Input: p = 3, q = 1
     * Output: 1
     *
     *
     Constraints:

     1 <= q <= p <= 1000
     * @param p
     * @param q
     * @return
     */
    // time = O(logP), space = O(1)
    public int mirrorReflection(int p, int q) {
        int Y = p * q / gcd(p, q);
        int x = Y / q, y = Y / p;
        if (x % 2 == 1) {
            if (y % 2 == 1) return 1;
            return 0;
        }
        return 2;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
/**
 * a * q = b * p => gcd(p, q) = Y
 * y = Y / p, x = Y / q
 */