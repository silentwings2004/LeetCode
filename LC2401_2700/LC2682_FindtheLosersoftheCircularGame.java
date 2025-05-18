package LC2401_2700;
import java.util.*;
public class LC2682_FindtheLosersoftheCircularGame {
    /**
     * There are n friends that are playing a game. The friends are sitting in a circle and are numbered from 1 to n in
     * clockwise order. More formally, moving clockwise from the ith friend brings you to the (i+1)th friend for
     * 1 <= i < n, and moving clockwise from the nth friend brings you to the 1st friend.
     *
     * The rules of the game are as follows:
     *
     * 1st friend receives the ball.
     *
     * After that, 1st friend passes it to the friend who is k steps away from them in the clockwise direction.
     * After that, the friend who receives the ball should pass it to the friend who is 2 * k steps away from them in
     * the clockwise direction.
     * After that, the friend who receives the ball should pass it to the friend who is 3 * k steps away from them in
     * the clockwise direction, and so on and so forth.
     * In other words, on the ith turn, the friend holding the ball should pass it to the friend who is i * k steps away
     * from them in the clockwise direction.
     *
     * The game is finished when some friend receives the ball for the second time.
     *
     * The losers of the game are friends who did not receive the ball in the entire game.
     *
     * Given the number of friends, n, and an integer k, return the array answer, which contains the losers of the game
     * in the ascending order.
     *
     * Input: n = 5, k = 2
     * Output: [4,5]
     *
     * Input: n = 4, k = 4
     * Output: [2,3,4]
     *
     * Constraints:
     *
     * 1 <= k <= n <= 50
     * @param n
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    public int[] circularGameLosers(int n, int k) {
        HashSet<Integer> set = new HashSet<>();
        set.add(0);
        int x = 0;
        for (int i = 1;; i++) {
            x = (x + i * k) % n;
            if (!set.add(x)) break;
        }
        int[] res = new int[n - set.size()];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (!set.contains(i)) res[idx++] = i + 1;
        }
        return res;
    }
}