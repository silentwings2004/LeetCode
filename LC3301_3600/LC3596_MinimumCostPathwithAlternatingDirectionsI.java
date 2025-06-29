package LC3301_3600;
import java.util.*;
public class LC3596_MinimumCostPathwithAlternatingDirectionsI {
    /**
     * You are given two integers m and n representing the number of rows and columns of a grid, respectively.
     *
     * The cost to enter cell (i, j) is defined as (i + 1) * (j + 1).
     *
     * You start at cell (0, 0) on move 1.
     *
     * At each step, you move to an adjacent cell, following an alternating pattern:
     *
     * On odd-numbered moves, you must move either right or down.
     * On even-numbered moves, you must move either left or up.
     * Return the minimum total cost required to reach (m - 1, n - 1). If it is impossible, return -1.
     *
     * Input: m = 1, n = 1
     * Output: 1
     *
     * Input: m = 2, n = 1
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= m, n <= 10^6
     * @param m
     * @param n
     * @return
     */
    // time = O(1), space = O(1)
    public int minCost(int m, int n) {
        if (m == 1 && n == 1) return 1;
        if (m == 2 && n == 1 || m == 1 && n == 2) return 3;
        return -1;
    }
}