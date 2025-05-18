package LC2700_3000;
import java.util.*;
public class LC2746_DecrementalStringConcatenation {
    /**
     * You are given a 0-indexed array words containing n strings.
     *
     * Let's define a join operation join(x, y) between two strings x and y as concatenating them into xy. However,
     * if the last character of x is equal to the first character of y, one of them is deleted.
     *
     * For example join("ab", "ba") = "aba" and join("ab", "cde") = "abcde".
     *
     * You are to perform n - 1 join operations. Let str0 = words[0]. Starting from i = 1 up to i = n - 1, for the ith
     * operation, you can do one of the following:
     *
     * Make stri = join(stri - 1, words[i])
     * Make stri = join(words[i], stri - 1)
     * Your task is to minimize the length of strn - 1.
     *
     * Return an integer denoting the minimum possible length of strn - 1.
     *
     * Input: words = ["aa","ab","bc"]
     * Output: 4
     *
     * Input: words = ["ab","b"]
     * Output: 2
     *
     * Input: words = ["aaa","c","aba"]
     * Output: 6
     *
     * Constraints:
     *
     * 1 <= words.length <= 1000
     * 1 <= words[i].length <= 50
     * Each character in words[i] is an English lowercase letter
     * @param words
     * @return
     */
    // time = O(n^2), space = O(n^2)
    public int minimizeConcatenatedLength(String[] words) {
        final int INF = 0x3f3f3f3f;
        int n = words.length;
        int[][] match = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (words[i].charAt(words[i].length() - 1) == words[j].charAt(0)) match[i][j] = 1;
                if (words[j].charAt(words[j].length() - 1) == words[i].charAt(0)) match[j][i] = 1;
            }
        }

        int[][] f = new int[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(f[i], INF);
        f[0][0] = words[0].length();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i >= j && i + 1 < n) {
                    f[i + 1][j] = Math.min(f[i + 1][j], f[i][j] + words[i + 1].length() - match[i + 1][i]);
                    f[i][i + 1] = Math.min(f[i][i + 1], f[i][j] + words[i + 1].length() - match[j][i + 1]);
                } else if (i < j && j + 1 < n) {
                    f[j + 1][j] = Math.min(f[j + 1][j], f[i][j] + words[j + 1].length() - match[j + 1][i]);
                    f[i][j + 1] = Math.min(f[i][j + 1], f[i][j] + words[j + 1].length() - match[j][j + 1]);
                }
            }
        }
        int res = INF;
        for (int i = 0; i < n; i++) res = Math.min(res, Math.min(f[i][n - 1], f[n - 1][i]));
        return res;
    }
}