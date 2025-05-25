package LC3301_3600;
import java.util.*;
public class LC3563_LexicographicallySmallestStringAfterAdjacentRemovals {
    /**
     * You are given a string s consisting of lowercase English letters.
     *
     * You can perform the following operation any number of times (including zero):
     *
     * Remove any pair of adjacent characters in the string that are consecutive in the alphabet, in either order
     * (e.g., 'a' and 'b', or 'b' and 'a').
     * Shift the remaining characters to the left to fill the gap.
     * Return the lexicographically smallest string that can be obtained after performing the operations optimally.
     *
     * A string a is lexicographically smaller than a string b if in the first position where a and b differ, string a
     * has a letter that appears earlier in the alphabet than the corresponding letter in b.
     * If the first min(a.length, b.length) characters do not differ, then the shorter string is the lexicographically
     * smaller one.
     *
     * Note: Consider the alphabet as circular, thus 'a' and 'z' are consecutive.
     *
     * Input: s = "abc"
     * Output: "a"
     *
     * Input: s = "bcda"
     * Output: ""
     *
     * Input: s = "zdce"
     * Output: "zdce"
     *
     * Constraints:
     *
     * 1 <= s.length <= 250
     * s consists only of lowercase English letters.
     * @param s
     * @return
     */
    // time = O(n^3), space = O(n^2)
    public String lexicographicallySmallestString(String s) {
        int n = s.length();
        String[][] f = new String[n + 1][n + 1];
        for (int i = 0; i <= n; i++) Arrays.fill(f[i], "");
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                String t = s.charAt(i) + f[i + 1][j + 1];
                for (int k = i + 1; k <= j; k++) {
                    if (check(s.charAt(i), s.charAt(k)) && f[i + 1][k].equals("")) {
                        if (t.compareTo(f[k + 1][j + 1]) > 0) t = f[k + 1][j + 1];
                    }
                }
                f[i][j + 1] = t;
            }
        }
        return f[0][n];
    }

    private boolean check(char a, char b) {
        return Math.abs(a - b) == 1 || Math.abs(a - b) == 25;
    }

    // S2
    // time = O(n^3), space = O(n^2)
    int[][] f;
    String[] g;
    String s;
    public String lexicographicallySmallestString2(String s) {
        this.s = s;
        int n = s.length();
        f = new int[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(f[i], -1);
        g = new String[n];
        return dfs(0);
    }

    private boolean isConsecutive(char x, char y) {
        int d = Math.abs(x - y);
        return d == 1 || d == 25;
    }

    private int canBeEmpty(int i, int j) {
        if (i > j) return 1;
        if (f[i][j] != -1) return f[i][j];
        if (isConsecutive(s.charAt(i), s.charAt(j)) && canBeEmpty(i + 1, j - 1) > 0) {
            return f[i][j] = 1;
        }
        for (int k = i + 1; k < j; k += 2) {
            if (canBeEmpty(i, k) > 0 && canBeEmpty(k + 1, j) > 0) {
                return f[i][j] = 1;
            }
        }
        return f[i][j] = 0;
    }

    private String dfs(int u) {
        if (u == s.length()) return "";
        if (g[u] != null) return g[u];

        String t = s.charAt(u) + dfs(u + 1);
        for (int v = u + 1; v < s.length(); v += 2) {
            if (canBeEmpty(u, v) > 0) {
                String ns = dfs(v + 1);
                if (ns.compareTo(t) < 0) t = ns;
            }
        }
        return g[u] = t;
    }
}