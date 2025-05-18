package LC1501_1800;
import java.util.*;
public class LC1503_LastMomentBeforeAllAntsFallOutofaPlank {
    /**
     * We have a wooden plank of the length n units. Some ants are walking on the plank, each ant moves with a speed of
     * 1 unit per second. Some of the ants move to the left, the other move to the right.
     *
     * When two ants moving in two different directions meet at some point, they change their directions and continue
     * moving again. Assume changing directions does not take any additional time.
     *
     * When an ant reaches one end of the plank at a time t, it falls out of the plank immediately.
     *
     * Given an integer n and two integer arrays left and right, the positions of the ants moving to the left and the
     * right, return the moment when the last ant(s) fall out of the plank.
     *
     * Input: n = 4, left = [4,3], right = [0,1]
     * Output: 4
     *
     * Input: n = 7, left = [], right = [0,1,2,3,4,5,6,7]
     * Output: 7
     *
     * Input: n = 7, left = [0,1,2,3,4,5,6,7], right = []
     * Output: 7
     *
     * Constraints:
     *
     * 1 <= n <= 10^4
     * 0 <= left.length <= n + 1
     * 0 <= left[i] <= n
     * 0 <= right.length <= n + 1
     * 0 <= right[i] <= n
     * 1 <= left.length + right.length <= n + 1
     * All values of left and right are unique, and each value can appear only in one of the two arrays.
     * @param n
     * @param left
     * @param right
     * @return
     */
    // time = O(n), space = O(1)
    public int getLastMoment(int n, int[] left, int[] right) {
        int res = 0;
        for (int x : left) res = Math.max(res, x);
        for (int x : right) res = Math.max(res, n - x);
        return res;
    }
}