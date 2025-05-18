package LC2401_2700;
import java.util.*;
public class LC2554_MaximumNumberofIntegerstoChooseFromaRangeI {
    /**
     * You are given an integer array banned and two integers n and maxSum. You are choosing some number of integers
     * following the below rules:
     *
     * The chosen integers have to be in the range [1, n].
     * Each integer can be chosen at most once.
     * The chosen integers should not be in the array banned.
     * The sum of the chosen integers should not exceed maxSum.
     * Return the maximum number of integers you can choose following the mentioned rules.
     *
     * Input: banned = [1,6,5], n = 5, maxSum = 6
     * Output: 2
     *
     * Input: banned = [1,2,3,4,5,6,7], n = 8, maxSum = 1
     * Output: 0
     *
     * Input: banned = [11], n = 7, maxSum = 50
     * Output: 7
     *
     * Constraints:
     *
     * 1 <= banned.length <= 10^4
     * 1 <= banned[i], n <= 10^4
     * 1 <= maxSum <= 10^9
     * @param banned
     * @param n
     * @param maxSum
     * @return
     */
    // time = O(n), space = O(n)
    public int maxCount(int[] banned, int n, int maxSum) {
        HashSet<Integer> set = new HashSet<>();
        for (int x : banned) set.add(x);
        int sum = 0, res = 0;
        for (int i = 1; i <= n; i++) {
            if (set.contains(i)) continue;
            sum += i;
            res++;
            if (sum > maxSum) {
                res--;
                break;
            }
        }
        return res;
    }
}