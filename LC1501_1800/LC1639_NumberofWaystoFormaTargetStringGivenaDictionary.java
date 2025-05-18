package LC1501_1800;

public class LC1639_NumberofWaystoFormaTargetStringGivenaDictionary {
    /**
     * You are given a list of strings of the same length words and a string target.
     *
     * Your task is to form target using the given words under the following rules:
     *
     * target should be formed from left to right.
     * To form the ith character (0-indexed) of target, you can choose the kth character of the jth string in words if
     * target[i] = words[j][k].
     * Once you use the kth character of the jth string of words, you can no longer use the xth character of any string
     * in words where x <= k. In other words, all characters to the left of or at index k become unusuable for every
     * string.
     * Repeat the process until you form the string target.
     * Notice that you can use multiple characters from the same string in words provided the conditions above are met.
     *
     * Return the number of ways to form target from words. Since the answer may be too large, return it modulo 10^9 + 7.
     *
     * Input: words = ["acca","bbbb","caca"], target = "aba"
     * Output: 6
     *
     * Input: words = ["abba","baab"], target = "bab"
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= words.length <= 1000
     * 1 <= words[i].length <= 1000
     * All strings in words have the same length.
     * 1 <= target.length <= 1000
     * words[i] and target contain only lowercase English letters.
     * @param words
     * @param target
     * @return
     */
    // time = O(m * n), space = O(m * n)
    public int numWays(String[] words, String target) {
        int m = words[0].length(), n = target.length();
        long[][] f = new long[n + 1][m + 1];
        long mod = (long)(1e9 + 7);

        for (int k = 0; k <= m; k++) f[0][k] = 1;

        int[][] count = new int[m + 1][26];
        for (String word : words) {
            for (int i = 1; i <= m; i++) {
                count[i][word.charAt(i - 1) - 'a']++;
            }
        }

        target = "#" + target;
        for (int i = 1; i <= n; i++) {
            for (int k = 1; k <= m; k++) {
                f[i][k] = f[i][k - 1];
                f[i][k] = (f[i][k] + f[i - 1][k - 1] * count[k][target.charAt(i) - 'a'] % mod) % mod;
            }
        }
        return (int) f[n][m];
    }
}
/**
 * 主要是i和k的关系，跟j关系不大
 * 把words都拍扁
 * 每个位置可以提供哪些字符
 * dp[i][k]: how many ways to form target[0:i] using word[0:k]
 * 1. we do not use word[k] to form target[i]
 * dp[i][k] = dp[i][k-1]
 * 2. we use word[k] to from target[i]
 * if (can do that)
 *      dp[i][k] = dp[i-1][k-1] * count (how many word[k] == target[i])
 * n = target.size()
 * m = word.size()k
 * return dp[n][m]
 * ref: LC1621
 * dp[i][k]: how many ways to form valid target[0:i] and target[i] must use word[k]   (x)
 */