package LC3601_3900;

public class LC3789_MinimumCosttoAcquireRequiredItems {
    /**
     * You are given five integers cost1, cost2, costBoth, need1, and need2.
     *
     * There are three types of items available:
     *
     * An item of type 1 costs cost1 and contributes 1 unit to the type 1 requirement only.
     * An item of type 2 costs cost2 and contributes 1 unit to the type 2 requirement only.
     * An item of type 3 costs costBoth and contributes 1 unit to both type 1 and type 2 requirements.
     * You must collect enough items so that the total contribution toward type 1 is at least need1 and the total
     * contribution toward type 2 is at least need2.
     *
     * Return an integer representing the minimum possible total cost to achieve these requirements.
     *
     * Input: cost1 = 3, cost2 = 2, costBoth = 1, need1 = 3, need2 = 2
     * Output: 3
     *
     * Input: cost1 = 5, cost2 = 4, costBoth = 15, need1 = 2, need2 = 3
     * Output: 22
     *
     * Input: cost1 = 5, cost2 = 4, costBoth = 15, need1 = 0, need2 = 0
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= cost1, cost2, costBoth <= 10^6
     * 0 <= need1, need2 <= 10^9
     * @param cost1
     * @param cost2
     * @param costBoth
     * @param need1
     * @param need2
     * @return
     */
    // time = O(1), space = O(1)
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long v1 = 1L * need1 * cost1 + 1L * need2 * cost2;
        int m = Math.min(need1, need2);
        long v2 = 1L * m * costBoth + 1L * (need1 - m) * cost1 + 1L * (need2 - m) * cost2;
        long v3 = 1L * Math.max(need1, need2) * costBoth;
        return Math.min(v1, Math.min(v2, v3));
    }
}