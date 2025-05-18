package LC1501_1800;
import java.util.*;
public class LC1798_MaximumNumberofConsecutiveValuesYouCanMake {
    /**
     * You are given an integer array coins of length n which represents the n coins that you own. The value of the ith
     * coin is coins[i]. You can make some value x if you can choose some of your n coins such that their values sum up
     * to x.
     *
     * Return the maximum number of consecutive integer values that you can make with your coins starting from and
     * including 0.
     *
     * Note that you may have multiple coins of the same value.
     *
     * Input: coins = [1,3]
     * Output: 2
     *
     * Constraints:
     *
     * coins.length == n
     * 1 <= n <= 4 * 10^4
     * 1 <= coins[i] <= 4 * 10^4
     *
     * @param coins
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public int getMaximumConsecutive(int[] coins) {
        Arrays.sort(coins);
        int sum = 0;
        for (int x : coins) {
            if (x > sum + 1) return sum + 1;
            sum += x;
        }
        return sum + 1;
    }
}
/**
 * [0, 1, 2, 3, ..., max], max + 1
 * Set = {0, ...}
 * [X X X X] c X X X
 * for (int x : set) set.add(c + x);
 * if (curMax + 1) < c
 * return curMax + 1
 * [0, curMax]  [c, curMax + c] => [0, curMax + c]
 * curMax + 1 < c  => 是个连续区间，不需要用一个set
 *
 * Greedy: similar to LC55
 * ai > Si-1 + 1 => Si-1 + 1 一定无法凑出
 * ai <= Si-1 + 1 一定可以凑出
 * [Si-1 + 1 - ai, Si] => [Si-1 + 1, Si-1 + ai]
 */
