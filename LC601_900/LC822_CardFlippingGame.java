package LC601_900;
import java.util.*;
public class LC822_CardFlippingGame {
    /**
     * You are given two 0-indexed integer arrays fronts and backs of length n, where the ith card has the positive
     * integer fronts[i] printed on the front and backs[i] printed on the back. Initially, each card is placed on a
     * table such that the front number is facing up and the other is facing down. You may flip over any number of cards
     * (possibly zero).
     *
     * After flipping the cards, an integer is considered good if it is facing down on some card and not facing up on
     * any card.
     *
     * Return the minimum possible good integer after flipping the cards. If there are no good integers, return 0.
     *
     * Input: fronts = [1,2,4,4,7], backs = [1,3,4,1,3]
     * Output: 2
     *
     * Input: fronts = [1], backs = [1]
     * Output: 0
     *
     * Constraints:
     *
     * n == fronts.length == backs.length
     * 1 <= n <= 1000
     * 1 <= fronts[i], backs[i] <= 2000
     * @param fronts
     * @param backs
     * @return
     */
    // time = O(n), space = O(n)
    public int flipgame(int[] fronts, int[] backs) {
        HashSet<Integer> set = new HashSet<>();
        int n = fronts.length;
        for (int i = 0; i < n; i++) {
            if (fronts[i] == backs[i]) set.add(fronts[i]);
        }

        int res = Integer.MAX_VALUE;
        for (int x : fronts) {
            if (!set.contains(x)) res = Math.min(res, x);
        }
        for (int x : backs) {
            if (!set.contains(x)) res = Math.min(res, x);
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}