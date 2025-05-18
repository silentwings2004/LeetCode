package LC3001_3300;
import java.util.*;
public class LC3075_MaximizeHappinessofSelectedChildren {
    /**
     * You are given an array happiness of length n, and a positive integer k.
     *
     * There are n children standing in a queue, where the ith child has happiness value happiness[i]. You want to
     * select k children from these n children in k turns.
     *
     * In each turn, when you select a child, the happiness value of all the children that have not been selected till
     * now decreases by 1. Note that the happiness value cannot become negative and gets decremented only if it is
     * positive.
     *
     * Return the maximum sum of the happiness values of the selected children you can achieve by selecting k children.
     *
     * Input: happiness = [1,2,3], k = 2
     * Output: 4
     *
     * Input: happiness = [1,1,1,1], k = 2
     * Output: 1
     *
     * Input: happiness = [2,3,4,5], k = 1
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= n == happiness.length <= 2 * 10^5
     * 1 <= happiness[i] <= 10^8
     * 1 <= k <= n
     * @param happiness
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public long maximumHappinessSum(int[] happiness, int k) {
        Arrays.sort(happiness);
        int n = happiness.length;
        long res = 0, t = 0;
        for (int i = n - 1; i >= 0; i--) {
            res += Math.max(0, happiness[i] - t);
            t++;
            k--;
            if (k == 0) break;
        }
        return res;
    }
}