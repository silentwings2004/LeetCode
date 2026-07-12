package LC3901_4200;

public class LC3981_CountDistinctWaystoFormTargetfromTwoStrings {
    /**
     * You are given three strings word1, word2, and target.
     *
     * Your task is to count the number of ways to form target by choosing characters from word1 and word2 under the
     * following conditions:
     *
     * For each character of target, choose one matching character from either word1 or word2.
     * The chosen indices from word1 must be strictly increasing.
     * The chosen indices from word2 must be strictly increasing.
     * At least one character must be chosen from both word1 and word2.
     * Two ways are considered different if, for at least one position in target, the chosen character comes from a
     * different string or a different index.
     *
     * Return the number of ways. Since the answer may be very large, return it modulo 109 + 7.
     *
     * Input: word1 = "abc", word2 = "bac", target = "abc"
     * Output: 5
     *
     * Input: word1 = "cd", word2 = "cd", target = "ccd"
     * Output: 4
     *
     * Input: word1 = "xy", word2 = "xy", target = "xyxy"
     * Output: 2
     *
     * Input: word1 = "ab", word2 = "cde", target = "ace"
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= word1.length, word2.length, target.length <= 100
     * word1, word2, and target consist of lowercase English letters only.
     * @param word1
     * @param word2
     * @param target
     * @return
     */
    // time = O(l * m^2 * n^2), space = O(l * m * n)
    final long mod = (long)(1e9 + 7);
    int[][][][][] memo;
    int m, n, len;
    public int interleaveCharacters(String word1, String word2, String target) {
        m = word1.length();
        n = word2.length();
        len = target.length();
        memo = new int[len + 1][m + 1][n + 1][2][2];
        for (int i = 0; i <= len; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    for (int u = 0; u < 2; u++) {
                        for (int v = 0; v < 2; v++) {
                            memo[i][j][k][u][v] = -1;
                        }
                    }
                }
            }
        }
        return dfs(word1, word2, target, 0, 0, 0, 0, 0);
    }

    private int dfs(String w1, String w2, String t, int u, int a, int b, int f, int g) {
        if (u == len) return f == 1 && g == 1 ? 1 : 0;
        if (memo[u][a][b][f][g] != -1) return memo[u][a][b][f][g];

        long res = 0;
        for (int i = a; i < m; i++) {
            if (w1.charAt(i) == t.charAt(u)) res = (res + dfs(w1, w2, t, u + 1, i + 1, b, 1, g)) % mod;
        }
        for (int i = b; i < n; i++) {
            if (w2.charAt(i) == t.charAt(u)) res = (res + dfs(w1, w2, t, u + 1, a, i + 1, f, 1)) % mod;
        }
        return memo[u][a][b][f][g] = (int)res;
    }
}
/**
 * t = abaab
 * w1 = aabb
 * w2 = baabc
 * dfs(i,j,k) 表示target[0,i]是由 word1[0,j]和 word2[0,k] 中的字母组成的，返回对应的方案数
 * word1[j] 和 word2[k] 是否在 target 中
 * 1. 都不在（都不选）
 * 2. word1[j] 在 target 中，word2[k] 不在
 * 3. word1[j] 不在 target 中，word2[k] 在
 * 4. word1[j] 和 word2[k] 都在 target 中
 *
 * 如果 word1[j] 不在 target 中，那么问题变成 target[0,i] 是由 word1[0,j-1] 和 word2[0,k] 中的字母组成的
 * 方案数为 dfs(i,j-1,k), 包含了情况 1 + 情况 3
 * 如果 word2[k] 不在 target 中，那么问题变成 target[0,i] 是由 word1[0,j] 和 word2[0,k-1] 中的字母组成的
 * 方案数为 dfs(i,j,k-1), 包含了情况 1 + 情况 2
 *
 * 情况 1 = target[0,i] 是由 word1[0,j-1] 和 word2[0,k-1] 中的字母组成的
 * 方案数为 dfs(i,j-1,k-1)
 * 情况 1 + 情况 2 + 情况 3 = dfs(i,j-1,k) + dfs(i,j,k-1) - dfs(i,j-1,k-1)
 *
 * 情况4，target[i] 一定要和 word1[j] 或者 word2[k] 中的一个匹配
 * 情况 4A
 * target[i] 和 word1[j] 匹配
 * 那么问题变成 target[0,i-1] 是由 word1[0,j-1] 和 word2[0,k] 中的字母组成的，并且 word2[k] 一定出现在 target[0,i-1] 中
 * dfs(i-1,j-1,k) - 不符合情况 4 的方案
 * = dfs(i-1,j-1,k) - (target[0,i-1] 是由 word1[0,j-1] 和 word2[0,k-1] 中的字母组成的)
 * = dfs(i-1,j-1,k) - dfs(i-1,j-1,k-1)
 */