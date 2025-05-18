package LC901_1200;

public class LC1011_CapacityToShipPackagesWithinDDays {
    /**
     * A conveyor belt has packages that must be shipped from one port to another within days days.
     *
     * The ith package on the conveyor belt has a weight of weights[i]. Each day, we load the ship with packages on
     * the conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity
     * of the ship.
     *
     * Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being
     * shipped within days days.
     *
     * Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
     * Output: 15
     *
     * Constraints:
     *
     * 1 <= days <= weights.length <= 5 * 10^4
     * 1 <= weights[i] <= 500
     * @param weights
     * @param D
     * @return
     */
    // time = O(nlogn), space = O(1)
    public int shipWithinDays(int[] weights, int days) {
        int l = 0, r = 0;
        for (int x : weights) {
            l = Math.max(l, x);
            r += x;
        }

        while (l < r) {
            int mid = l + r >> 1;
            if (check(weights, days, mid)) r = mid;
            else l = mid + 1;
        }
        return r;
    }

    private boolean check(int[] w, int d, int mid) {
        int t = 1, sum = 0;
        for (int x : w) {
            if (sum + x > mid) {
                t++;
                sum = x;
            } else sum += x;
        }
        return t <= d;
    }
}
/**
 * ref: LC410
 * dp[i][j]: nums[0:i] divided into j groups
 * min{max{dp[k][j-1], sum[k:i]}} over k  前k个数分成j-1组
 * O(n^3)
 * BS 本质是"猜"，问啥猜啥，最多猜32次
 */