package LC1501_1800;

public class LC1599_MaximumProfitofOperatingaCentennialWheel {
    /**
     * You are the operator of a Centennial Wheel that has four gondolas, and each gondola has room for up to four
     * people. You have the ability to rotate the gondolas counterclockwise, which costs you runningCost dollars.
     *
     * You are given an array customers of length n where customers[i] is the number of new customers arriving just
     * before the ith rotation (0-indexed). This means you must rotate the wheel i times before the customers[i]
     * customers arrive. You cannot make customers wait if there is room in the gondola. Each customer pays
     * boardingCost dollars when they board on the gondola closest to the ground and will exit once that gondola
     * reaches the ground again.
     *
     * You can stop the wheel at any time, including before serving all customers. If you decide to stop serving
     * customers, all subsequent rotations are free in order to get all the customers down safely. Note that if there
     * are currently more than four customers waiting at the wheel, only four will board the gondola, and the rest will
     * wait for the next rotation.
     *
     * Return the minimum number of rotations you need to perform to maximize your profit. If there is no scenario
     * where the profit is positive, return -1.
     *
     * Input: customers = [8,3], boardingCost = 5, runningCost = 6
     * Output: 3
     *
     * Input: customers = [10,9,6], boardingCost = 6, runningCost = 4
     * Output: 7
     *
     * Input: customers = [3,4,0,5,1], boardingCost = 1, runningCost = 92
     * Output: -1
     *
     * Constraints:
     *
     * n == customers.length
     * 1 <= n <= 10^5
     * 0 <= customers[i] <= 50
     * 1 <= boardingCost, runningCost <= 100
     * @param customers
     * @param boardingCost
     * @param runningCost
     * @return
     */
    // time = O(n * k), space = O(1)
    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        int n = customers.length, profit = 0, maxv = 0, res = -1, t = 0, round = 0;
        for (int i = 0; i < n || t > 0; i++) {
            if (i < n) t += customers[i];
            int m = Math.min(t, 4);
            t -= m;
            round++;
            profit += m * boardingCost - runningCost;
            if (profit > maxv) {
                maxv = profit;
                res = round;
            }
        }
        return res;
    }
}