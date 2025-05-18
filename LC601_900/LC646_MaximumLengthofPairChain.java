package LC601_900;
import java.util.*;
public class LC646_MaximumLengthofPairChain {
    /**
     * You are given an array of n pairs pairs where pairs[i] = [lefti, righti] and lefti < righti.
     *
     * A pair p2 = [c, d] follows a pair p1 = [a, b] if b < c. A chain of pairs can be formed in this fashion.
     *
     * Return the length longest chain which can be formed.
     *
     * You do not need to use up all the given intervals. You can select pairs in any order.
     *
     * Input: pairs = [[1,2],[2,3],[3,4]]
     * Output: 2
     *
     * Constraints:
     *
     * n == pairs.length
     * 1 <= n <= 1000
     * -1000 <= lefti < righti <= 1000
     * @param pairs
     * @return
     */
    // time = O(nlogn), space = O(1)
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (o1, o2) -> o1[1] - o2[1]);
        int res = 1, ed = pairs[0][1];
        for (int[] p : pairs) {
            if (p[0] > ed) {
                res++;
                ed = p[1];
            }
        }
        return res;
    }
}
/**
 * ref: LC435 almost same problem
 * (a,b) (c,d)
 * maximum number of non-overlapping intervals
 * sort by ending point
 * dp: n^2  LIS
 * dp[i] => dp[j] + 1
 * sort by starting points: LIS
 *
 * 按照右端点排序
 * 第一个区间必选
 * 贪心解 = 最优解
 * 1. 贪心解 <= 最优解
 * 2. 贪心解 >= 最优解
 */
