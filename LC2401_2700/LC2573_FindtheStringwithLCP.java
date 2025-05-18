package LC2401_2700;
import java.util.*;
public class LC2573_FindtheStringwithLCP {
    /**
     * We define the lcp matrix of any 0-indexed string word of n lowercase English letters as an n x n grid such that:
     *
     * lcp[i][j] is equal to the length of the longest common prefix between the substrings word[i,n-1] and word[j,n-1].
     * Given an n x n matrix lcp, return the alphabetically smallest string word that corresponds to lcp. If there is no
     * such string, return an empty string.
     *
     * A string a is lexicographically smaller than a string b (of the same length) if in the first position where a
     * and b differ, string a has a letter that appears earlier in the alphabet than the corresponding letter in b.
     * For example, "aabd" is lexicographically smaller than "aaca" because the first position they differ is at the
     * third letter, and 'b' comes before 'c'.
     *
     * Input: lcp = [[4,0,2,0],[0,3,0,1],[2,0,2,0],[0,1,0,1]]
     * Output: "abab"
     *
     * Input: lcp = [[4,3,2,1],[3,3,2,1],[2,2,2,1],[1,1,1,1]]
     * Output: "aaaa"
     *
     * Input: lcp = [[4,3,2,1],[3,3,2,1],[2,2,2,1],[1,1,1,3]]
     * Output: ""
     *
     * Constraints:
     *
     * 1 <= n == lcp.length == lcp[i].length <= 1000
     * 0 <= lcp[i][j] <= n
     * @param lcp
     * @return
     */
    // time = O(n^2), space = O(n)
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        for (int i = 0; i < n; i++) {
            if (lcp[i][i] != n - i) return "";
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (lcp[i][j] != lcp[j][i]) return "";
            }
        }

        char[] chars = new char[n];
        char c = 'a';
        Arrays.fill(chars, '#');
        for (int i = 0; i < n; i++) {
            if (chars[i] == '#') {
                if (c > 'z') return "";
                for (int j = i; j < n; j++) {
                    if (lcp[i][j] > 0) {
                        if (chars[j] != '#') return "";
                        chars[j] = c;
                    }
                }
                c++;
            } else {
                for (int j = i + 1; j < n; j++) {
                    if (lcp[i][j] > 0 && chars[i] != chars[j]) return "";
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (lcp[i][j] > 0) {
                    int k = 0;
                    while (i + k < n && j + k < n && chars[i + k] == chars[j + k]) k++;
                    if (k != lcp[i][j]) return "";
                }
            }
        }
        return String.valueOf(chars);
    }
}
