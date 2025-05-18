package LC2401_2700;
import java.util.*;
public class LC2551_PutMarblesinBags {
    /**
     * You have k bags. You are given a 0-indexed integer array weights where weights[i] is the weight of the ith
     * marble. You are also given the integer k.
     *
     * Divide the marbles into the k bags according to the following rules:
     *
     * No bag is empty.
     * If the ith marble and jth marble are in a bag, then all marbles with an index between the ith and jth indices
     * should also be in that same bag.
     * If a bag consists of all the marbles with an index from i to j inclusively, then the cost of the bag is
     * weights[i] + weights[j].
     * The score after distributing the marbles is the sum of the costs of all the k bags.
     *
     * Return the difference between the maximum and minimum scores among marble distributions.
     *
     * Input: weights = [1,3,5,1], k = 2
     * Output: 4
     *
     * Input: weights = [1, 3], k = 2
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= k <= weights.length <= 10^5
     * 1 <= weights[i] <= 10^9
     * @param weights
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(n)
    public long putMarbles(int[] weights, int k) {
        int n = weights.length;
        int[] arr = new int[n - 1];
        for (int i = 0; i < n - 1; i++) arr[i] = weights[i] + weights[i + 1];
        Arrays.sort(arr);

        long res = 0;
        for (int i = 0; i < k - 1; i++) {
            res += arr[arr.length - 1 - i];
            res -= arr[i];
        }
        return res;
    }
}
/**
 * 显然，无论怎么分组，weights[0]和weights[n-1]是必然会计入score。接下来我们需要在n个元素中间插入k-1块隔板。
 * 每块隔板引入的score就是隔板两边的相邻元素weights之和。
 * 显然要使score最大，我们只需要选取最大的k-1个“相邻元素weight之和”即可。
 * 同理，要使score最小，我们只需要选取最小的k-1个“相邻元素weight之和”即可。
 *
 */