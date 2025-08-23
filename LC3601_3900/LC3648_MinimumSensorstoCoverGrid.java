package LC3601_3900;

public class LC3648_MinimumSensorstoCoverGrid {
    /**
     * You are given n × m grid and an integer k.
     *
     * A sensor placed on cell (r, c) covers all cells whose Chebyshev distance from (r, c) is at most k.
     *
     * The Chebyshev distance between two cells (r1, c1) and (r2, c2) is max(|r1 − r2|,|c1 − c2|).
     *
     * Your task is to return the minimum number of sensors required to cover every cell of the grid.
     *
     * Input: n = 5, m = 5, k = 1
     * Output: 4
     *
     * Input: n = 2, m = 2, k = 2
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= n <= 10^3
     * 1 <= m <= 10^3
     * 0 <= k <= 10^3
     * @param n
     * @param m
     * @param k
     * @return
     */
    // time = O(1), space = O(1)
    public int minSensors(int n, int m, int k) {
        k = k * 2 + 1;
        int v1 = (m + k - 1) / k, v2 = (n + k - 1) / k;
        return v1 * v2;
    }
}