package LC3601_3900;
import java.util.*;
public class LC3853_MergeCloseCharacters {
    /**
     * You are given a string s consisting of lowercase English letters and an integer k.
     *
     * Two equal characters in the current string s are considered close if the distance between their indices is at
     * most k.
     *
     * When two characters are close, the right one merges into the left. Merges happen one at a time, and after each
     * merge, the string updates until no more merges are possible.
     *
     * Return the resulting string after performing all possible merges.
     *
     * Note: If multiple merges are possible, always merge the pair with the smallest left index. If multiple pairs
     * share the smallest left index, choose the pair with the smallest right index.
     *
     * Input: s = "abca", k = 3
     * Output: "abc"
     *
     * Input: s = "aabca", k = 2
     * Output: "abca"
     *
     * Input: s = "yybyzybz", k = 2
     * Output: "ybzybz"
     *
     * Constraints:
     *
     * 1 <= s.length <= 100
     * 1 <= k <= s.length
     * s consists of lowercase English letters.
     * @param s
     * @param k
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public String mergeCharacters(String s, int k) {
        int n = s.length();
        int[] stk = new int[n + 1];
        int[] pos = new int[26];
        Arrays.fill(pos, -1);
        int tt = 0;
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            if (pos[u] == -1 || tt + 1 - pos[u] > k) {
                stk[++tt] = i;
                pos[u] = tt;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= tt; i++) sb.append(s.charAt(stk[i]));
        return sb.toString();
    }

    // S2
    // time = O(n), space = O(n)
    public String mergeCharacters2(String s, int k) {
        int[] pos = new int[26];
        Arrays.fill(pos, -k - 1);
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            if (sb.length() - pos[u] > k) {
                pos[u] = sb.length();
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}