package LC2700_3000;
import java.util.*;
public class LC2751_RobotCollisions {
    /**
     * There are n 1-indexed robots, each having a position on a line, health, and movement direction.
     *
     * You are given 0-indexed integer arrays positions, healths, and a string directions (directions[i] is either 'L'
     * for left or 'R' for right). All integers in positions are unique.
     *
     * All robots start moving on the line simultaneously at the same speed in their given directions. If two robots
     * ever share the same position while moving, they will collide.
     *
     * If two robots collide, the robot with lower health is removed from the line, and the health of the other robot
     * decreases by one. The surviving robot continues in the same direction it was going. If both robots have the same
     * health, they are both removed from the line.
     *
     * Your task is to determine the health of the robots that survive the collisions, in the same order that the robots
     * were given, i.e. final heath of robot 1 (if survived), final health of robot 2 (if survived), and so on. If there
     * are no survivors, return an empty array.
     *
     * Return an array containing the health of the remaining robots (in the order they were given in the input), after
     * no further collisions can occur.
     *
     * Note: The positions may be unsorted.
     *
     * Input: positions = [5,4,3,2,1], healths = [2,17,9,15,10], directions = "RRRRR"
     * Output: [2,17,9,15,10]
     *
     * Input: positions = [3,5,2,6], healths = [10,10,15,12], directions = "RLRL"
     * Output: [14]
     *
     * Input: positions = [1,2,5,6], healths = [10,10,11,11], directions = "RLRL"
     * Output: []
     *
     * Constraints:
     *
     * 1 <= positions.length == healths.length == directions.length == n <= 10^5
     * 1 <= positions[i], healths[i] <= 10^9
     * directions[i] == 'L' or directions[i] == 'R'
     * All values in positions are distinct
     * @param positions
     * @param healths
     * @param directions
     * @return
     */
    // time = O(nlogn), space = O(n)
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        TreeSet<int[]> hl = new TreeSet<>((o1, o2) -> o1[0] - o2[0]);
        TreeSet<int[]> hr = new TreeSet<>((o1, o2) -> o1[0] - o2[0]);

        int n = positions.length;
        for (int i = 0; i < n; i++) {
            if (directions.charAt(i) == 'L') hl.add(new int[]{positions[i], healths[i], i});
            else hr.add(new int[]{positions[i], healths[i], i});
        }
        int[] res = new int[n];
        while (!hl.isEmpty() && !hr.isEmpty()) {
            int[] t1 = hr.last();
            int[] t2 = hl.higher(t1);
            if (t2 == null) {
                res[t1[2]] = t1[1];
                hr.remove(t1);
            } else {
                if (t1[1] > t2[1]) {
                    t1[1]--;
                    hl.remove(t2);
                } else if (t1[1] < t2[1]) {
                    t2[1]--;
                    hr.remove(t1);
                } else {
                    hr.remove(t1);
                    hl.remove(t2);
                }
            }
        }

        while (!hl.isEmpty()) {
            int[] t = hl.last();
            res[t[2]] = t[1];
            hl.remove(t);
        }

        while (!hr.isEmpty()) {
            int[] t = hr.last();
            res[t[2]] = t[1];
            hr.remove(t);
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (res[i] > 0) ans.add(res[i]);
        }
        return ans;
    }
}