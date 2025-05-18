package LC2101_2400;
import java.util.*;
public class LC2350_ShortestImpossibleSequenceofRolls {
    /**
     * You are given an integer array rolls of length n and an integer k. You roll a k sided dice numbered from 1 to k,
     * n times, where the result of the ith roll is rolls[i].
     *
     * Return the length of the shortest sequence of rolls that cannot be taken from rolls.
     *
     * A sequence of rolls of length len is the result of rolling a k sided dice len times.
     *
     * Note that the sequence taken does not have to be consecutive as long as it is in order.
     *
     * Input: rolls = [4,2,1,2,3,3,2,4,1], k = 4
     * Output: 3
     *
     * Input: rolls = [1,1,2,2], k = 2
     * Output: 2
     *
     * Input: rolls = [1,1,3,2,2,2,3,3], k = 4
     * Output: 1
     *
     * Constraints:
     *
     * n == rolls.length
     * 1 <= n <= 10^5
     * 1 <= rolls[i] <= k <= 10^5
     * @param rolls
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    public int shortestSequence(int[] rolls, int k) {
        HashSet<Integer> set = new HashSet<>();
        int cnt = 0;
        for (int x : rolls) {
            set.add(x);
            if (set.size() == k) {
                set.clear();
                cnt++;
            }
        }
        return cnt + 1;
    }
}
/**
 *  * * * * * * k^L
 *  * *
 *  * * *
 *  3 *
 */