package LC3301_3600;
import java.util.*;
public class LC3457_EatPizzas {
    /**
     * You are given an integer array pizzas of size n, where pizzas[i] represents the weight of the ith pizza. Every
     * day, you eat exactly 4 pizzas. Due to your incredible metabolism, when you eat pizzas of weights W, X, Y, and Z,
     * where W <= X <= Y <= Z, you gain the weight of only 1 pizza!
     *
     * On odd-numbered days (1-indexed), you gain a weight of Z.
     * On even-numbered days, you gain a weight of Y.
     * Find the maximum total weight you can gain by eating all pizzas optimally.
     *
     * Note: It is guaranteed that n is a multiple of 4, and each pizza can be eaten only once.
     *
     * Input: pizzas = [1,2,3,4,5,6,7,8]
     * Output: 14
     *
     * Input: pizzas = [2,1,1,1,1,1,1,1]
     * Output: 3
     *
     * Constraints:
     *
     * 4 <= n == pizzas.length <= 2 * 10^5
     * 1 <= pizzas[i] <= 10^5
     * n is a multiple of 4.
     * @param pizzas
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public long maxWeight(int[] pizzas) {
        Arrays.sort(pizzas);
        int n = pizzas.length, m = n / 4, r = n - 1;
        int odd = (m + 1) / 2, even = m - odd;
        long res = 0;
        while (odd-- > 0) res += pizzas[r--];
        while (even-- > 0) {
            r--;
            res += pizzas[r--];
        }
        return res;
    }
}