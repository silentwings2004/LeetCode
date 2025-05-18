package LC601_900;
import java.util.*;
public class LC789_EscapeTheGhosts {
    /**
     * You are playing a simplified PAC-MAN game on an infinite 2-D grid. You start at the point [0, 0], and you are
     * given a destination point target = [xtarget, ytarget] that you are trying to get to. There are several ghosts on
     * the map with their starting positions given as a 2D array ghosts, where ghosts[i] = [xi, yi] represents the
     * starting position of the ith ghost. All inputs are integral coordinates.
     *
     * Each turn, you and all the ghosts may independently choose to either move 1 unit in any of the four cardinal
     * directions: north, east, south, or west, or stay still. All actions happen simultaneously.
     *
     * You escape if and only if you can reach the target before any ghost reaches you. If you reach any square
     * (including the target) at the same time as a ghost, it does not count as an escape.
     *
     * Return true if it is possible to escape regardless of how the ghosts move, otherwise return false.
     *
     * Input: ghosts = [[1,0],[0,3]], target = [0,1]
     * Output: true
     *
     * Input: ghosts = [[1,0]], target = [2,0]
     * Output: false
     *
     * Input: ghosts = [[2,0]], target = [1,0]
     * Output: false
     *
     * Constraints:
     *
     * 1 <= ghosts.length <= 100
     * ghosts[i].length == 2
     * -10^4 <= xi, yi <= 10^4
     * There can be multiple ghosts in the same location.
     * target.length == 2
     * -10^4 <= xtarget, ytarget <= 10^4
     * @param ghosts
     * @param target
     * @return
     */
    // time = O(n), space = O(1)
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        for (int[] g : ghosts) {
            if (Math.abs(g[0] - target[0]) + Math.abs(g[1] - target[1]) <= Math.abs(target[0]) + Math.abs(target[1])) {
                return false;
            }
        }
        return true;
    }

    private int get_dist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}