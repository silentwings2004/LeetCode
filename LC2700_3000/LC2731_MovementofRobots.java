package LC2700_3000;
import java.util.*;
public class LC2731_MovementofRobots {
    /**
     * Some robots are standing on an infinite number line with their initial coordinates given by a 0-indexed integer
     * array nums and will start moving once given the command to move. The robots will move a unit distance each second.
     *
     * You are given a string s denoting the direction in which robots will move on command. 'L' means the robot will
     * move towards the left side or negative side of the number line, whereas 'R' means the robot will move towards
     * the right side or positive side of the number line.
     *
     * If two robots collide, they will start moving in opposite directions.
     *
     * Return the sum of distances between all the pairs of robots d seconds after the command. Since the sum can be
     * very large, return it modulo 109 + 7.
     *
     * Note:
     *
     * For two robots at the index i and j, pair (i,j) and pair (j,i) are considered the same pair.
     * When robots collide, they instantly change their directions without wasting any time.
     * Collision happens when two robots share the same place in a moment.
     * For example, if a robot is positioned in 0 going to the right and another is positioned in 2 going to the left,
     * the next second they'll be both in 1 and they will change direction and the next second the first one will be in
     * 0, heading left, and another will be in 2, heading right.
     * For example, if a robot is positioned in 0 going to the right and another is positioned in 1 going to the left,
     * the next second the first one will be in 0, heading left, and another will be in 1, heading right.
     *
     * Input: nums = [-2,0,2], s = "RLL", d = 3
     * Output: 8
     *
     * Input: nums = [1,0], s = "RL", d = 2
     * Output: 5
     *
     * Constraints:
     *
     * 2 <= nums.length <= 10^5
     * -2 * 10^9 <= nums[i] <= 2 * 10^9
     * 0 <= d <= 10^9
     * nums.length == s.length
     * s consists of 'L' and 'R' only
     * nums[i] will be unique.
     * @param nums
     * @param s
     * @param d
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int sumDistance(int[] nums, String s, int d) {
        int n = nums.length;
        long[] w = new long[n];
        for (int i = 0; i < n; i++) w[i] = nums[i] + (s.charAt(i) == 'L' ? -1 : 1) * d;
        Arrays.sort(w);
        long[] c = new long[n - 1];
        for (int i = 1; i < n; i++) c[i - 1] = w[i] - w[i - 1];
        long res = 0, mod = (long)1e9 + 7;
        for (int i = 0; i < n - 1; i++) res = (res + c[i] * (n - (i + 1)) % mod * (i + 1)) % mod;
        return (int)res;
    }
}