package LC2401_2700;

public class LC2431_MaximizeTotalTastinessofPurchasedFruits {
    /**
     * You are given two non-negative integer arrays price and tastiness, both arrays have the same length n. You are
     * also given two non-negative integers maxAmount and maxCoupons.
     *
     * For every integer i in range [0, n - 1]:
     *
     * price[i] describes the price of ith fruit.
     * tastiness[i] describes the tastiness of ith fruit.
     * You want to purchase some fruits such that total tastiness is maximized and the total price does not exceed
     * maxAmount.
     *
     * Additionally, you can use a coupon to purchase fruit for half of its price (rounded down to the closest integer).
     * You can use at most maxCoupons of such coupons.
     *
     * Return the maximum total tastiness that can be purchased.
     *
     * Note that:
     *
     * You can purchase each fruit at most once.
     * You can use coupons on some fruit at most once.
     *
     * Input: price = [10,20,20], tastiness = [5,8,8], maxAmount = 20, maxCoupons = 1
     * Output: 13
     *
     * Input: price = [10,15,7], tastiness = [5,8,20], maxAmount = 10, maxCoupons = 2
     * Output: 28
     *
     * Constraints:
     *
     * n == price.length == tastiness.length
     * 1 <= n <= 100
     * 0 <= price[i], tastiness[i], maxAmount <= 1000
     * 0 <= maxCoupons <= 5
     * @param price
     * @param tastiness
     * @param maxAmount
     * @param maxCoupons
     * @return
     */
    // time = O(n * maxAmount * maxCoupon) = O(5*10^5), space = O(n * maxAmount * maxCoupon)
    public int maxTastiness(int[] price, int[] tastiness, int maxAmount, int maxCoupons) {
        int n = price.length;
        int[][][] f = new int[n + 1][maxAmount + 1][maxCoupons + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= maxAmount; j++) {
                for (int k = 0; k <= maxCoupons; k++) {
                    f[i][j][k] = f[i - 1][j][k];
                    if (j >= price[i - 1]) {
                        f[i][j][k] = Math.max(f[i][j][k], f[i - 1][j - price[i - 1]][k] + tastiness[i - 1]);
                    }
                    if (j >= price[i - 1] / 2 && k > 0) {
                        f[i][j][k] = Math.max(f[i][j][k], f[i - 1][j - price[i - 1] / 2][k - 1] + tastiness[i - 1]);
                    }
                }
            }
        }
        return f[n][maxAmount][maxCoupons];
    }
}
