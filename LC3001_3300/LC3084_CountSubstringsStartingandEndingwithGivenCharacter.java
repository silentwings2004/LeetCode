package LC3001_3300;
import java.util.*;
public class LC3084_CountSubstringsStartingandEndingwithGivenCharacter {
    /**
     * You are given a string s and a character c. Return the total number of substrings of s that start and end with c.
     *
     * Input: s = "abada", c = "a"
     * Output: 6
     *
     * Input: s = "zzz", c = "z"
     * Output: 6
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s and c consist only of lowercase English letters.
     * @param s
     * @param c
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public long countSubstrings(String s, char c) {
        List<Integer>[] cnt = new List[26];
        for (int i = 0; i < 26; i++) cnt[i] = new ArrayList<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            cnt[u].add(i);
        }
        int u = c - 'a', m = cnt[u].size();
        if (m == 0) return 0;
        long res = 0;
        for (int i = 0; i < m; i++) res += m - i;
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public long countSubstrings2(String s, char c) {
        int k = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) k++;
        }
        return (long)k * (k + 1) / 2;
    }
}