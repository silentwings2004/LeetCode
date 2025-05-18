package LC2401_2700;
import java.util.*;
public class LC2557_MaximumNumberofIntegerstoChooseFromaRangeII {
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
     * Input: banned = [1,4,6], n = 6, maxSum = 4
     * Output: 1
     *
     * Input: banned = [4,3,5,6], n = 7, maxSum = 18
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= banned.length <= 10^5
     * 1 <= banned[i] <= n <= 10^9
     * 1 <= maxSum <= 10^15
     * @param banned
     * @param n
     * @param maxSum
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int maxCount(int[] banned, int n, long maxSum) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int x : banned) set.add(x);
        set.add(n + 1);
        long sum = 0, res = 0;
        int a = 1;
        for (int x : set) {
            int b = x - 1;
            if (a > b) {
                a = x + 1;
                continue;
            }
            long t = (long) (a + b) * (b - a + 1) / 2;
            if (sum + t <= maxSum) {
                sum += t;
                res += b - a + 1;
            } else {
                int l = a, r = b;
                while (l < r) {
                    int mid = l + r + 1 >> 1;
                    t = (long) (a + mid) * (mid - a + 1) / 2;
                    if (sum + t <= maxSum) l = mid;
                    else r = mid - 1;
                }
                t = (long) (a + r) * (r - a + 1) / 2;
                if (sum + t <= maxSum) {
                    sum += t;
                    res += r - a + 1;
                }
            }
            a = x + 1;
        }
        return (int) res;
    }
}