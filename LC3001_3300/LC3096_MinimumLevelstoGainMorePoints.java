package LC3001_3300;

public class LC3096_MinimumLevelstoGainMorePoints {
    /**
     * You are given a binary array possible of length n.
     *
     * Danielchandg and Bob are playing a game that consists of n levels. Some of the levels in the game are impossible
     * to clear while others can always be cleared. In particular, if possible[i] == 0, then the ith level is impossible
     * to clear for both the players. A player gains 1 point on clearing a level and loses 1 point if the player fails
     * to clear it.
     *
     * At the start of the game, Danielchandg will play some levels in the given order starting from the 0th level,
     * after which Bob will play for the rest of the levels.
     *
     * Danielchandg wants to know the minimum number of levels he should play to gain more points than Bob, if both
     * players play optimally to maximize their points.
     *
     * Return the minimum number of levels danielchandg should play to gain more points. If this is not possible, return
     * -1.
     *
     * Note that each player must play at least 1 level.
     *
     * Input: possible = [1,0,1,0]
     * Output: 1
     *
     * Input: possible = [1,1,1,1,1]
     * Output: 3
     *
     * Input: possible = [0,0]
     * Output: -1
     *
     * Constraints:
     *
     * 2 <= n == possible.length <= 10^5
     * possible[i] is either 0 or 1.
     * @param possible
     * @return
     */
    // time = O(n), space = O(1)
    public int minimumLevels(int[] possible) {
        int n = possible.length;
        int s = 0;
        for (int i = 0; i < n; i++) s += possible[i] == 1 ? 1 : -1;
        for (int i = 0, t = 0; i < n - 1; i++) {
            t += possible[i] == 1 ? 1 : -1;
            if (t > s - t) return i + 1;
        }
        return -1;
    }
}