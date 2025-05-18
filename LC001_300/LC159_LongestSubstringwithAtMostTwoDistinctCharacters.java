package LC001_300;
import java.util.*;
public class LC159_LongestSubstringwithAtMostTwoDistinctCharacters {
    /**
     * Given a string s, return the length of the longest substring that contains at most two distinct characters.
     *
     * Input: s = "eceba"
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^4
     * s consists of English letters.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int[] cnt = new int[128];
        int n = s.length();

        int res = 0, tot = 0;
        for (int i = 0, j = 0; i < n; i++) {
            char c = s.charAt(i);
            if (cnt[c]++ == 0) tot++;
            while (tot > 2) if (--cnt[s.charAt(j++)] == 0) tot--;
            res = Math.max(res, i - j + 1);
        }
        return res;
    }
}