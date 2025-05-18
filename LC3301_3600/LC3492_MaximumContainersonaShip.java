package LC3301_3600;

public class LC3492_MaximumContainersonaShip {
    /**
     * You are given a positive integer n representing an n x n cargo deck on a ship. Each cell on the deck can hold
     * one container with a weight of exactly w.
     *
     * However, the total weight of all containers, if loaded onto the deck, must not exceed the ship's maximum weight
     * capacity, maxWeight.
     *
     * Return the maximum number of containers that can be loaded onto the ship.
     *
     * Input: n = 2, w = 3, maxWeight = 15
     * Output: 4
     *
     * Input: n = 3, w = 5, maxWeight = 20
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= n <= 1000
     * 1 <= w <= 1000
     * 1 <= maxWeight <= 10^9
     * @param n
     * @param w
     * @param maxWeight
     * @return
     */
    // time = O(1), space = O(1)
    public int maxContainers(int n, int w, int maxWeight) {
        return Math.min(n * n, maxWeight / w);
    }
}