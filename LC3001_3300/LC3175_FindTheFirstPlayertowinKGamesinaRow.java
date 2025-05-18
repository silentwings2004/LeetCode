package LC3001_3300;
import java.util.*;
public class LC3175_FindTheFirstPlayertowinKGamesinaRow {
    /**
     * A competition consists of n players numbered from 0 to n - 1.
     *
     * You are given an integer array skills of size n and a positive integer k, where skills[i] is the skill level of
     * player i. All integers in skills are unique.
     *
     * All players are standing in a queue in order from player 0 to player n - 1.
     *
     * The competition process is as follows:
     *
     * The first two players in the queue play a game, and the player with the higher skill level wins.
     * After the game, the winner stays at the beginning of the queue, and the loser goes to the end of it.
     * The winner of the competition is the first player who wins k games in a row.
     *
     * Return the initial index of the winning player.
     *
     * Input: skills = [4,2,6,3,9], k = 2
     *
     * Output: 2
     *
     * Input: skills = [2,5,4], k = 3
     *
     * Output: 1
     *
     * Constraints:
     *
     * n == skills.length
     * 2 <= n <= 10^5
     * 1 <= k <= 10^9
     * 1 <= skills[i] <= 10^6
     * All integers in skills are unique.
     * @param skills
     * @param k
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public int findWinningPlayer(int[] skills, int k) {
        int n = skills.length, mx = skills[0];
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            q.offer(i);
            mx = Math.max(mx, skills[i]);
        }
        int cnt = 0, res = 0;
        for (int i = 0; i < n; i++) {
            if (cnt >= k) return q.peek();
            int t = q.poll();
            if (skills[t] == mx) {
                res = t;
                break;
            }
            while (skills[t] > skills[q.peek()]) {
                q.offer(q.poll());
                cnt++;
                if (cnt >= k) return t;
            }
            q.offer(t);
            cnt = 1;
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public int findWinningPlayer2(int[] skills, int k) {
        int mx_i = 0, win = -1, n = skills.length;
        for (int i = 0; i < n; i++) {
            if (skills[i] > skills[mx_i]) {
                mx_i = i;
                win = 0;
            }
            win++;
            if (win == k) break;
        }
        return mx_i;
    }
}