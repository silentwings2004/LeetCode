package LC3301_3600;
import java.util.*;
public class LC3499_MaximizeActiveSectionwithTradeI {
    /**
     * You are given a binary string s of length n, where:
     *
     * '1' represents an active section.
     * '0' represents an inactive section.
     * You can perform at most one trade to maximize the number of active sections in s. In a trade, you:
     *
     * Convert a contiguous block of '1's that is surrounded by '0's to all '0's.
     * Afterward, convert a contiguous block of '0's that is surrounded by '1's to all '1's.
     * Return the maximum number of active sections in s after making the optimal trade.
     *
     * Note: Treat s as if it is augmented with a '1' at both ends, forming t = '1' + s + '1'. The augmented '1's do
     * not contribute to the final count.
     *
     * Input: s = "01"
     * Output: 1
     *
     * Input: s = "0100"
     * Output: 4
     *
     * Input: s = "1000100"
     * Output: 7
     *
     * Input: s = "01010"
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= n == s.length <= 10^5
     * s[i] is either '0' or '1'
     * @param s
     * @return
     */
    // time = O(n), space = O(n)
    public int maxActiveSectionsAfterTrade(String s) {
        int n = s.length(), sum = 0;
        List<Integer> q = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                int j = i + 1;
                while (j < n && s.charAt(j) == '0') j++;
                q.add(j - i);
                i = j - 1;
            } else sum++;
        }

        int m = q.size(), res = 0;
        if (m < 2) res = sum;
        else {
            for (int i = 0; i + 1 < m; i++) {
                res = Math.max(res, q.get(i) + q.get(i + 1) + sum);
            }
        }
        return res;
    }
}