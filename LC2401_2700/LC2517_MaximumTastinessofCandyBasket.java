package LC2401_2700;
import java.util.*;
public class LC2517_MaximumTastinessofCandyBasket {
    /**
     * You are given an array of positive integers price where price[i] denotes the price of the ith candy and a
     * positive integer k.
     *
     * The store sells baskets of k distinct candies. The tastiness of a candy basket is the smallest absolute
     * difference of the prices of any two candies in the basket.
     *
     * Return the maximum tastiness of a candy basket.
     *
     * Input: price = [13,5,1,8,21,2], k = 3
     * Output: 8
     *
     * Input: price = [1,3,1], k = 2
     * Output: 2
     *
     * Input: price = [7,7,7,7], k = 2
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= price.length <= 10^5
     * 1 <= price[i] <= 10^9
     * 2 <= k <= price.length
     * @param price
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(1)
    public int maximumTastiness(int[] price, int k) {
        int n = price.length;
        Arrays.sort(price);
        int l = 0, r = price[n - 1] - price[0];
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (check(price, k, mid)) l = mid;
            else r = mid - 1;
        }
        return r;
    }

    private boolean check(int[] price, int k, int mid) {
        int n = price.length, last = price[0], cnt = 1;
        for (int i = 1; i < n; i++) {
            if (price[i] - last >= mid) {
                last = price[i];
                cnt++;
            }
        }
        return cnt >= k;
    }
}