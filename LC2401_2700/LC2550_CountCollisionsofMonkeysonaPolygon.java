package LC2401_2700;

public class LC2550_CountCollisionsofMonkeysonaPolygon {
    /**
     * There is a regular convex polygon with n vertices. The vertices are labeled from 0 to n - 1 in a clockwise
     * direction, and each vertex has exactly one monkey. The following figure shows a convex polygon of 6 vertices.
     *
     * Each monkey moves simultaneously to a neighboring vertex. A neighboring vertex for a vertex i can be:
     *
     * the vertex (i + 1) % n in the clockwise direction, or
     * the vertex (i - 1 + n) % n in the counter-clockwise direction.
     * A collision happens if at least two monkeys reside on the same vertex after the movement.
     *
     * Return the number of ways the monkeys can move so that at least one collision happens. Since the answer may be
     * very large, return it modulo 10^9 + 7.
     *
     * Note that each monkey can only move once.
     *
     * Input: n = 3
     * Output: 6
     *
     * Input: n = 4
     * Output: 14
     *
     * Constraints:
     *
     * 3 <= n <= 10^9
     * @param n
     * @return
     */
    // time = O(logn), space = O(1)
    long mod = (long)(1e9 + 7);
    public int monkeyMove(int n) {
        return (int)((qmi(2, n, mod) - 2 + mod) % mod);
    }

    private long qmi(long a, long k, long p) {
        long res = 1;
        while (k > 0) {
            if ((k & 1) == 1) res = res * a % mod;
            a = a * a % mod;
            k >>= 1;
        }
        return res;
    }
}