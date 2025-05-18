package LC1501_1800;
import java.util.*;
public class LC1624_LargestSubstringBetweenTwoEqualCharacters {
    /**
     * Given a string s, return the length of the longest substring between two equal characters, excluding the two
     * characters. If there is no such substring return -1.
     *
     * A substring is a contiguous sequence of characters within a string.
     *
     * Input: s = "aa"
     * Output: 0
     *
     * Input: s = "abca"
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= s.length <= 300
     * s contains only lowercase English letters.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public int maxLengthBetweenEqualCharacters(String s) {
        int n = s.length(), res = -1;
        int[] pos = new int[26];
        Arrays.fill(pos, -1);
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            if (pos[u] == -1) pos[u] = i;
            else res = Math.max(res, i - pos[u] - 1);
        }
        return res;
    }
}