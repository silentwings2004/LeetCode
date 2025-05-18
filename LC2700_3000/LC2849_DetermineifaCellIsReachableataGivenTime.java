package LC2700_3000;

public class LC2849_DetermineifaCellIsReachableataGivenTime {
    /**
     * You are given four integers sx, sy, fx, fy, and a non-negative integer t.
     *
     * In an infinite 2D grid, you start at the cell (sx, sy). Each second, you must move to any of its adjacent cells.
     *
     * Return true if you can reach cell (fx, fy) after exactly t seconds, or false otherwise.
     *
     * A cell's adjacent cells are the 8 cells around it that share at least one corner with it. You can visit the same
     * cell several times.
     *
     * Input: sx = 2, sy = 4, fx = 7, fy = 7, t = 6
     * Output: true
     *
     * Input: sx = 3, sy = 1, fx = 7, fy = 3, t = 3
     * Output: false
     *
     * Constraints:
     *
     * 1 <= sx, sy, fx, fy <= 109
     * 0 <= t <= 10^9
     * @param sx
     * @param sy
     * @param fx
     * @param fy
     * @param t
     * @return
     */
    // time = O(1), space = O(1)
    public boolean isReachableAtTime(int sx, int sy, int fx, int fy, int t) {
        int dx = Math.abs(sx - fx), dy = Math.abs(sy - fy);
        int tmin = Math.max(dx, dy);
        if (tmin == 0) return t % 2 == 0 || t > 1;
        return t >= tmin;
    }
}