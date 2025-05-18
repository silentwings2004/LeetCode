package LC601_900;
import java.util.*;
public class LC649_Dota2Senate {
    /**
     * In the world of Dota2, there are two parties: the Radiant and the Dire.
     *
     * The Dota2 senate consists of senators coming from two parties. Now the Senate wants to decide on a change in the
     * Dota2 game. The voting for this change is a round-based procedure. In each round, each senator can exercise one
     * of the two rights:
     *
     * Ban one senator's right: A senator can make another senator lose all his rights in this and all the following
     * rounds.
     * Announce the victory: If this senator found the senators who still have rights to vote are all from the same
     * party, he can announce the victory and decide on the change in the game.
     * Given a string senate representing each senator's party belonging. The character 'R' and 'D' represent the
     * Radiant party and the Dire party. Then if there are n senators, the size of the given string will be n.
     *
     * The round-based procedure starts from the first senator to the last senator in the given order. This procedure
     * will last until the end of voting. All the senators who have lost their rights will be skipped during the procedure.
     *
     * Suppose every senator is smart enough and will play the best strategy for his own party. Predict which party
     * will finally announce the victory and change the Dota2 game. The output should be "Radiant" or "Dire".
     *
     * Input: senate = "RD"
     * Output: "Radiant"
     *
     * Input: senate = "RDD"
     * Output: "Dire"
     *
     * Constraints:
     *
     * n == senate.length
     * 1 <= n <= 10^4
     * senate[i] is either 'R' or 'D'.
     * @param senate
     * @return
     */
    // time = O(n), space = O(n)
    public String predictPartyVictory(String senate) {
        Queue<Integer> r = new LinkedList<>();
        Queue<Integer> d = new LinkedList<>();

        int n = senate.length();
        for (int i = 0; i < n; i++) {
            if (senate.charAt(i) == 'R') r.offer(i);
            else d.offer(i);
        }

        while (!r.isEmpty() && !d.isEmpty()) {
            if (r.peek() < d.peek()) r.offer(r.peek() + n);
            else d.offer(d.peek() + n);
            r.poll();
            d.poll();
        }
        if (!r.isEmpty()) return "Radiant";
        return "Dire";
    }
}
/**
 * 踢除一个议员
 * 宣布胜利
 * 每个议员的决策都是唯一确定的
 * 但可以选踢哪个议员 -> 哪个议员伤害更大
 * 踢权利更大的议员 -> 后面的第一个对方党派的议员
 * 整个过程是确定性的过程
 */
