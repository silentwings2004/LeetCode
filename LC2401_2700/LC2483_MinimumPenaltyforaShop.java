package LC2401_2700;

public class LC2483_MinimumPenaltyforaShop {
    /**
     * You are given the customer visit log of a shop represented by a 0-indexed string customers consisting only of
     * characters 'N' and 'Y':
     *
     * if the ith character is 'Y', it means that customers come at the ith hour
     * whereas 'N' indicates that no customers come at the ith hour.
     * If the shop closes at the jth hour (0 <= j <= n), the penalty is calculated as follows:
     *
     * For every hour when the shop is open and no customers come, the penalty increases by 1.
     * For every hour when the shop is closed and customers come, the penalty increases by 1.
     * Return the earliest hour at which the shop must be closed to incur a minimum penalty.
     *
     * Note that if a shop closes at the jth hour, it means the shop is closed at the hour j.
     *
     * Input: customers = "YYNY"
     * Output: 2
     *
     * Input: customers = "NNNNN"
     * Output: 0
     *
     * Input: customers = "YYYY"
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= customers.length <= 10^5
     * customers consists only of characters 'Y' and 'N'.
     * @param customers
     * @return
     */
    // time = O(n), space = O(n)
    public int bestClosingTime(String customers) {
        int n = customers.length();
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + (customers.charAt(i - 1) == 'Y' ? 1 : 0);

        int min = n, res = 0;
        for (int i = 0; i <= n; i++) {
            int cost = i - s[i] + s[n] - s[i];
            if (min > cost) {
                min = cost;
                res = i;
            }
        }
        return res;
    }
}
