package LC1501_1800;
import java.util.*;
public class LC1561_MaximumNumberofCoinsYouCanGet {
    /**
     * There are 3n piles of coins of varying size, you and your friends will take piles of coins as follows:
     *
     * In each step, you will choose any 3 piles of coins (not necessarily consecutive).
     * Of your choice, Alice will pick the pile with the maximum number of coins.
     * You will pick the next pile with the maximum number of coins.
     * Your friend Bob will pick the last pile.
     * Repeat until there are no more piles of coins.
     * Given an array of integers piles where piles[i] is the number of coins in the ith pile.
     *
     * Return the maximum number of coins that you can have.
     *
     * Input: piles = [2,4,1,2,7,8]
     * Output: 9
     *
     * Input: piles = [2,4,5]
     * Output: 4
     *
     * Input: piles = [9,8,7,6,5,1,2,3,4]
     * Output: 18
     *
     * Constraints:
     *
     * 3 <= piles.length <= 10^5
     * piles.length % 3 == 0
     * 1 <= piles[i] <= 10^4
     * @param piles
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int n = piles.length, res = 0;
        for (int i = n - 2, j = 0; j < n / 3; i -= 2, j++) res += piles[i];
        return res;
    }
}