package LC3301_3600;

public class LC3502_MinimumCosttoReachEveryPosition {
    /**
     * You are given an integer array cost of size n. You are currently at position n (at the end of the line) in a
     * line of n + 1 people (numbered from 0 to n).
     *
     * You wish to move forward in the line, but each person in front of you charges a specific amount to swap places.
     * The cost to swap with person i is given by cost[i].
     *
     * You are allowed to swap places with people as follows:
     *
     * If they are in front of you, you must pay them cost[i] to swap with them.
     * If they are behind you, they can swap with you for free.
     * Return an array answer of size n, where answer[i] is the minimum total cost to reach each position i in the line.
     *
     * Input: cost = [5,3,4,1,3,2]
     * Output: [5,3,3,1,1,1]
     *
     * Input: cost = [1,2,4,6,7]
     * Output: [1,1,1,1,1]
     *
     * Constraints:
     *
     * 1 <= n == cost.length <= 100
     * 1 <= cost[i] <= 100
     * @param cost
     * @return
     */
    // time = O(n), space = O(1)
    public int[] minCosts(int[] cost) {
        int n = cost.length;
        for (int i = 1; i < n; i++) cost[i] = Math.min(cost[i - 1], cost[i]);
        return cost;
    }
}