package LC2700_3000;
import java.util.*;
public class LC2706_BuyTwoChocolates {
    /**
     * You are given an integer array prices representing the prices of various chocolates in a store. You are also
     * given a single integer money, which represents your initial amount of money.
     *
     * You must buy exactly two chocolates in such a way that you still have some non-negative leftover money. You would
     * like to minimize the sum of the prices of the two chocolates you buy.
     *
     * Return the amount of money you will have leftover after buying the two chocolates. If there is no way for you to
     * buy two chocolates without ending up in debt, return money. Note that the leftover must be non-negative.
     *
     * Input: prices = [1,2,2], money = 3
     * Output: 0
     *
     * Input: prices = [3,2,3], money = 3
     * Output: 3
     *
     * Constraints:
     *
     * 2 <= prices.length <= 50
     * 1 <= prices[i] <= 100
     * 1 <= money <= 100
     * @param prices
     * @param money
     * @return
     */
    // time = O(n), space = O(1)
    public int buyChoco(int[] prices, int money) {
        int a = 110, b = 110;
        for (int x : prices) {
            if (x < a) {
                b = a;
                a = x;
            } else if (x < b) b = x;
        }
        int sum = a + b;
        return money < sum ? money : money - sum;
    }
}