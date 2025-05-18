package LC1201_1500;
import java.util.*;
public class LC1496_PathCrossing {
    /**
     * Given a string path, where path[i] = 'N', 'S', 'E' or 'W', each representing moving one unit north, south, east,
     * or west, respectively. You start at the origin (0, 0) on a 2D plane and walk on the path specified by path.
     *
     * Return true if the path crosses itself at any point, that is, if at any time you are on a location you have
     * previously visited. Return false otherwise.
     *
     * Input: path = "NES"
     * Output: false
     *
     * Input: path = "NESWW"
     * Output: true
     *
     * Constraints:
     *
     * 1 <= path.length <= 10^4
     * path[i] is either 'N', 'S', 'E', or 'W'.
     * @param path
     * @return
     */
    // time = O(n), space = O(n)
    public boolean isPathCrossing(String path) {
        HashSet<Integer> set = new HashSet<>();
        int x = 0, y = 0, P = 10010;
        set.add(x * P + y);
        for (char c : path.toCharArray()) {
            if (c == 'N') y++;
            else if (c == 'S') y--;
            else if (c == 'E') x++;
            else x--;
            if (!set.add(x * P + y)) return true;
        }
        return false;
    }
}