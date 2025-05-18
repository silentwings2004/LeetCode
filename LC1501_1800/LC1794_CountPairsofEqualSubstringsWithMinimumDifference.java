package LC1501_1800;
import java.util.*;
public class LC1794_CountPairsofEqualSubstringsWithMinimumDifference {
    /**
     * You are given two strings firstString and secondString that are 0-indexed and consist only of lowercase English
     * letters. Count the number of index quadruples (i,j,a,b) that satisfy the following conditions:
     *
     * 0 <= i <= j < firstString.length
     * 0 <= a <= b < secondString.length
     * The substring of firstString that starts at the ith character and ends at the jth character (inclusive) is equal
     * to the substring of secondString that starts at the ath character and ends at the bth character (inclusive).
     * j - a is the minimum possible value among all quadruples that satisfy the previous conditions.
     * Return the number of such quadruples.
     *
     * Input: firstString = "abcd", secondString = "bccda"
     * Output: 1
     *
     * Input: firstString = "ab", secondString = "cd"
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= firstString.length, secondString.length <= 2 * 10^5
     * Both strings consist only of lowercase English letters.
     * @param firstString
     * @param secondString
     * @return
     */
    // time = O(m + n), space = O(1)
    public int countQuadruples(String firstString, String secondString) {
        int m = firstString.length(), n = secondString.length();
        int[] cnt = new int[26];
        Arrays.fill(cnt, -1);
        for (int i = 0; i < n; i++) {
            char c = secondString.charAt(i);
            cnt[c - 'a'] = i;
        }

        int res = 0, min = (int) 1e8;
        for (int i = 0; i < m; i++) {
            char c = firstString.charAt(i);
            if (cnt[c - 'a'] == -1) continue;
            int a = cnt[c - 'a'];
            if (min >= i - a) {
                if (min > i - a) {
                    min = i - a;
                    res = 1;
                } else res++;
            }
        }
        return res;
    }
}
