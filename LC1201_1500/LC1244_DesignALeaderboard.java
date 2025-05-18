package LC1201_1500;
import java.util.*;
public class LC1244_DesignALeaderboard {
    /**
     * Design a Leaderboard class, which has 3 functions:
     *
     * addScore(playerId, score): Update the leaderboard by adding score to the given player's score. If there is no
     * player with such id in the leaderboard, add him to the leaderboard with the given score.
     * top(K): Return the score sum of the top K players.
     * reset(playerId): Reset the score of the player with the given id to 0 (in other words erase it from the
     * leaderboard). It is guaranteed that the player was added to the leaderboard before calling this function.
     * Initially, the leaderboard is empty.
     *
     * Input:
     * ["Leaderboard","addScore","addScore","addScore","addScore","addScore","top","reset","reset","addScore","top"]
     * [[],[1,73],[2,56],[3,39],[4,51],[5,4],[1],[1],[2],[2,51],[3]]
     * Output:
     * [null,null,null,null,null,null,73,null,null,null,141]
     *
     * Constraints:
     *
     * 1 <= playerId, K <= 10000
     * It's guaranteed that K is less than or equal to the current number of players.
     * 1 <= score <= 100
     * There will be at most 1000 function calls.
     */
    HashMap<Integer, Integer> map;
    TreeMap<Integer, HashSet<Integer>> cnt;
    public LC1244_DesignALeaderboard() {
        map = new HashMap<>();
        cnt = new TreeMap<>();
    }
    // time = O(logn), space = O(n)
    public void addScore(int playerId, int score) {
        if (!map.containsKey(playerId)) {
            map.put(playerId, score);
            cnt.putIfAbsent(score, new HashSet<>());
            cnt.get(score).add(playerId);
        } else {
            int t = map.get(playerId);
            cnt.get(t).remove(playerId);
            if (cnt.get(t).size() == 0) cnt.remove(t);
            map.put(playerId, map.get(playerId) + score);
            cnt.putIfAbsent(map.get(playerId), new HashSet<>());
            cnt.get(map.get(playerId)).add(playerId);
        }
    }
    // time = O(klogn), space = O(n)
    public int top(int K) {
        int s = 0;
        Integer lk = cnt.lastKey();
        while (K > 0) {
            int sz = cnt.get(lk).size();
            if (sz < K) {
                s += lk * sz;
                K -= sz;
            } else {
                s += lk * K;
                K = 0;
            }
            lk = cnt.lowerKey(lk);
        }
        return s;
    }
    // time = O(logn), space = O(n)
    public void reset(int playerId) {
        int t = map.get(playerId);
        cnt.get(t).remove(playerId);
        if (cnt.get(t).size() == 0) cnt.remove(t);
        map.remove(playerId);
    }
}