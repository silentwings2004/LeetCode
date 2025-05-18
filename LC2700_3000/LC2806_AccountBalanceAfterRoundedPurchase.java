package LC2700_3000;

public class LC2806_AccountBalanceAfterRoundedPurchase {
    /**
     * Initially, you have a bank account balance of 100 dollars.
     *
     * You are given an integer purchaseAmount representing the amount you will spend on a purchase in dollars.
     *
     * At the store where you will make the purchase, the purchase amount is rounded to the nearest multiple of 10. In
     * other words, you pay a non-negative amount, roundedAmount, such that roundedAmount is a multiple of 10 and
     * abs(roundedAmount - purchaseAmount) is minimized.
     *
     * If there is more than one nearest multiple of 10, the largest multiple is chosen.
     *
     * Return an integer denoting your account balance after making a purchase worth purchaseAmount dollars from the
     * store.
     *
     * Note: 0 is considered to be a multiple of 10 in this problem.
     *
     * Input: purchaseAmount = 9
     * Output: 90
     *
     * Input: purchaseAmount = 15
     * Output: 80
     *
     * Constraints:
     *
     * 0 <= purchaseAmount <= 100
     * @param purchaseAmount
     * @return
     */
    // S1
    // time = O(1), space = O(1)
    public int accountBalanceAfterPurchase(int purchaseAmount) {
        int minv = 100, t = 0;
        for (int i = 0; i <= 10; i++) {
            if (Math.abs(i * 10 - purchaseAmount) <= minv) {
                minv = Math.abs(i * 10 - purchaseAmount);
                t = i * 10;
            }
        }
        return 100 - t;
    }

    // S2
    // time = O(1), space = O(1)
    public int accountBalanceAfterPurchase2(int purchaseAmount) {
        int a = purchaseAmount / 10 * 10, b = (purchaseAmount + 9) / 10 * 10;
        if (purchaseAmount - a < b - purchaseAmount) return 100 - a;
        return 100 - b;
    }
}