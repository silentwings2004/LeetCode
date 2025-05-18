package LC3301_3600;
import java.util.*;
public class LC3466_MaximumCoinCollection {
    /**
     * Mario drives on a two-lane freeway with coins every mile. You are given two integer arrays, lane1 and lane2,
     * where the value at the ith index represents the number of coins he gains or loses in the ith mile in that lane.
     *
     * If Mario is in lane 1 at mile i and lane1[i] > 0, Mario gains lane1[i] coins.
     * If Mario is in lane 1 at mile i and lane1[i] < 0, Mario pays a toll and loses abs(lane1[i]) coins.
     * The same rules apply for lane2.
     * Mario can enter the freeway anywhere and exit anytime after traveling at least one mile. Mario always enters the
     * freeway on lane 1 but can switch lanes at most 2 times.
     *
     * A lane switch is when Mario goes from lane 1 to lane 2 or vice versa.
     *
     * Return the maximum number of coins Mario can earn after performing at most 2 lane switches.
     *
     * Note: Mario can switch lanes immediately upon entering or just before exiting the freeway.
     *
     * Input: lane1 = [1,-2,-10,3], lane2 = [-5,10,0,1]
     * Output: 14
     *
     * Input: lane1 = [1,-1,-1,-1], lane2 = [0,3,4,-5]
     * Output: 8
     *
     * Input: lane1 = [-5,-4,-3], lane2 = [-1,2,3]
     * Output: 5
     *
     * Input: lane1 = [-3,-3,-3], lane2 = [9,-2,4]
     * Output: 11
     *
     * Input: lane1 = [-10], lane2 = [-2]
     * Output: -2
     *
     * Constraints:
     *
     * 1 <= lane1.length == lane2.length <= 10^5
     * -10^9 <= lane1[i], lane2[i] <= 10^9
     * @param lane1
     * @param lane2
     * @return
     */
    // time = O(n), space = O(n)
    public long maxCoins(int[] lane1, int[] lane2) {
        int n = lane1.length;
        long[][] f = new long[n][3];
        long inf = (long)1E18;
        for (int i = 0; i < n; i++) Arrays.fill(f[i], -inf);
        f[0][0] = lane1[0];
        f[0][1] = lane2[0];
        long res = Math.max(f[0][0], f[0][1]);
        for (int i = 1; i < n; i++) {
            f[i][0] = Math.max(0, f[i - 1][0]) + lane1[i];
            f[i][1] = Math.max(Math.max(0, f[i - 1][0]) + lane2[i], f[i - 1][1] + lane2[i]);
            f[i][2] = Math.max(f[i - 1][1] + lane1[i], f[i - 1][2] + lane1[i]);
            for (int j = 0; j < 3; j++) res = Math.max(res, f[i][j]);
        }
        return res;
    }
}