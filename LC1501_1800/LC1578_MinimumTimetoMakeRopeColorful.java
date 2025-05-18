package LC1501_1800;
import java.util.*;
public class LC1578_MinimumTimetoMakeRopeColorful {
    /**
     * Alice has n balloons arranged on a rope. You are given a 0-indexed string colors where colors[i] is the color of
     * the ith balloon.
     *
     * Alice wants the rope to be colorful. She does not want two consecutive balloons to be of the same color, so she
     * asks Bob for help. Bob can remove some balloons from the rope to make it colorful. You are given a 0-indexed
     * integer array neededTime where neededTime[i] is the time (in seconds) that Bob needs to remove the ith balloon
     * from the rope.
     *
     * Return the minimum time Bob needs to make the rope colorful.
     *
     * Input: colors = "abaac", neededTime = [1,2,3,4,5]
     * Output: 3
     *
     * Input: colors = "abc", neededTime = [1,2,3]
     * Output: 0
     *
     * Input: colors = "aabaa", neededTime = [1,2,3,4,1]
     * Output: 2
     *
     * Constraints:
     *
     * n == colors.length == neededTime.length
     * 1 <= n <= 10^5
     * 1 <= neededTime[i] <= 10^4
     * colors contains only lowercase English letters.
     * @param colors
     * @param neededTime
     * @return
     */
    // S1: DP
    // time = O(26 * n), space = O(26 * n)
    public int minCost(String colors, int[] neededTime) {
        int n = neededTime.length, inf = 0x3f3f3f3f;
        int[][] f = new int[n + 1][26];
        for (int i = 1; i <= n; i++) Arrays.fill(f[i], inf);
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 26; j++) f[i][j] = f[i - 1][j] + neededTime[i - 1];
            int u = colors.charAt(i - 1) - 'a';
            for (int j = 0; j < 26; j++) {
                if (u != j) f[i][u] = Math.min(f[i][u], f[i - 1][j]);
            }
        }
        int res = inf;
        for (int i = 0; i < 26; i++) res = Math.min(res, f[n][i]);
        return res;
    }

    // S2: Greedy
    // time = O(n), space = O(1)
    public int minCost2(String colors, int[] neededTime) {
        int prev = 0, res = 0, n = colors.length();
        for (int i = 1; i < n; i++) {
            if (colors.charAt(prev) != colors.charAt(i)) prev = i;
            else {
                if (neededTime[prev] < neededTime[i]) {
                    res += neededTime[prev];
                    prev = i;
                } else res += neededTime[i];
            }
        }
        return res;
    }
}
/**
 * dp
 * 1. 状态表示：
 * (1) 集合：所有从1~i中删且最后剩下的字母是j的删法的集合
 * (2) 属性：min
 * 2. 状态计算：集合划分
 * (1) 删掉第i个字母：f(i-1,j) + cost[i]
 * (2) 不删掉第i个字母: 分成27类，和这个字母相同的不能用 f(i-1,k) 枚举下k，取个min
 */
