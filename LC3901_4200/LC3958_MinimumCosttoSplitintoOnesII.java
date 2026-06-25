package LC3901_4200;

public class LC3958_MinimumCosttoSplitintoOnesII {
    /**
     * You are given an integer n.
     *
     * In one operation, you may split an integer x into two positive integers a and b such that a + b = x.
     *
     * The cost of this operation is a * b.
     *
     * Return the minimum total cost required to split the integer n into n ones.
     *
     * Input: n = 3
     * Output: 3
     *
     * Input: n = 4
     * Output: 6
     *
     * Constraints:
     *
     * 1 <= n <= 5 * 10^7
     * @param n
     * @return
     */
    // time = O(1), space = O(1)
    public long minCost(int n) {
        return 1L * n * (n - 1) / 2;
    }
}