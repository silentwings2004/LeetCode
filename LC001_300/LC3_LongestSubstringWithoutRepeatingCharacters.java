package LC001_300;
import java.util.*;
public class LC3_LongestSubstringWithoutRepeatingCharacters {
    /**
     * Given a string s, find the length of the longest substring without repeating characters.
     *
     * Input: s = "abcabcbb"
     * Output: 3
     *
     * Constraints:
     *
     * 0 <= s.length <= 5 * 10^4
     * s consists of English letters, digits, symbols and spaces.
     *
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public int lengthOfLongestSubstring(String s) {
        int[] cnt = new int[128];

        int n = s.length(), res = 0;
        for (int i = 0, j = 0; i < n; i++) {
            int c = s.charAt(i);
            cnt[c]++;
            while (cnt[c] > 1) cnt[s.charAt(j++)]--;
            res = Math.max(res, i - j + 1);
        }
        return res;
    }
}