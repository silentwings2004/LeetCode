package LC301_600;
import java.util.*;
public class LC455_AssignCookies {
    /**
     * Assume you are an awesome parent and want to give your children some cookies. But, you should give each child at
     * most one cookie.
     *
     * Each child i has a greed factor g[i], which is the minimum size of a cookie that the child will be content with;
     * and each cookie j has a size s[j]. If s[j] >= g[i], we can assign the cookie j to the child i, and the child i
     * will be content. Your goal is to maximize the number of your content children and output the maximum number.
     *
     * Input: g = [1,2,3], s = [1,1]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= g.length <= 3 * 10^4
     * 0 <= s.length <= 3 * 10^4
     * 1 <= g[i], s[j] <= 2^31 - 1
     * @param g
     * @param s
     * @return
     */
    // time = O(mlogm + nlogn), space = O(logm + logn)
    public int findContentChildren(int[] g, int[] s) {
        if (s == null || s.length == 0) return 0;

        Arrays.sort(g);
        Arrays.sort(s);
        int m = g.length, n = s.length, res = 0, j = 0;
        for (int i = 0; i < m; i++) {
             while (j < n && s[j] < g[i]) j++;
             if (j < n) {
                 res++;
                 j++;
             } else break;
        }
        return res;
    }
}