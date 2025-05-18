package LC1501_1800;
import java.util.*;
public class LC1535_FindtheWinnerofanArrayGame {
    /**
     * Given an integer array arr of distinct integers and an integer k.
     *
     * A game will be played between the first two elements of the array (i.e. arr[0] and arr[1]). In each round of the
     * game, we compare arr[0] with arr[1], the larger integer wins and remains at position 0, and the smaller integer
     * moves to the end of the array. The game ends when an integer wins k consecutive rounds.
     *
     * Return the integer which will win the game.
     *
     * It is guaranteed that there will be a winner of the game.
     *
     * Input: arr = [2,1,3,5,4,6,7], k = 2
     * Output: 5
     *
     * Input: arr = [3,2,1], k = 10
     * Output: 3
     *
     * Constraints:
     *
     * 2 <= arr.length <= 10^5
     * 1 <= arr[i] <= 10^6
     * arr contains distinct integers.
     * 1 <= k <= 10^9
     * @param arr
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public int getWinner(int[] arr, int k) {
        int n = arr.length, maxv = arr[0], cnt = 0;
        for (int i = 1; i < n; i++) {
            if (maxv < arr[i]) {
                maxv = arr[i];
                cnt = 1;
            } else cnt++;
            if (cnt == k) return maxv;
        }
        return maxv;
    }
}
