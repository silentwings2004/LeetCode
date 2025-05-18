package LC3301_3600;
import java.util.*;
public class LC3545_MinimumDeletionsforAtMostKDistinctCharacters {
    /**
     * You are given a string s consisting of lowercase English letters, and an integer k.
     *
     * Your task is to delete some (possibly none) of the characters in the string so that the number of distinct
     * characters in the resulting string is at most k.
     *
     * Return the minimum number of deletions required to achieve this.
     *
     * Input: s = "abc", k = 2
     * Output: 1
     *
     * Input: s = "aabb", k = 2
     * Output: 0
     *
     * Input: s = "yyyzz", k = 1
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= s.length <= 16
     * 1 <= k <= 16
     * s consists only of lowercase English letters.
     * @param s
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public int minDeletion(String s, int k) {
        int n = s.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            cnt[u]++;
        }
        Arrays.sort(cnt);
        int res = 0;
        for (int i = 0; i < 26 - k; i++) res += cnt[i];
        return res;
    }
}