package LC2401_2700;

public class LC2579_CountTotalNumberofColoredCells {
    /**
     * There exists an infinitely large two-dimensional grid of uncolored unit cells. You are given a positive
     * integer n, indicating that you must do the following routine for n minutes:
     *
     * At the first minute, color any arbitrary unit cell blue.
     * Every minute thereafter, color blue every uncolored cell that touches a blue cell.
     * Below is a pictorial representation of the state of the grid after minutes 1, 2, and 3.
     *
     * Return the number of colored cells at the end of n minutes.
     *
     * Input: n = 1
     * Output: 1
     *
     * Input: n = 2
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * @param n
     * @return
     */
    // time = O(n), space = O(1)
    public long coloredCells(int n) {
        if (n == 1) return 1;
        long t = 1;
        for (int i = 2; i <= n; i++) t += (i - 1) * 4;
        return t;
    }
}