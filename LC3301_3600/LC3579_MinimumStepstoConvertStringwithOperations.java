package LC3301_3600;

public class LC3579_MinimumStepstoConvertStringwithOperations {
    /**
     * You are given two strings, word1 and word2, of equal length. You need to transform word1 into word2.
     *
     * For this, divide word1 into one or more contiguous substrings. For each substring substr you can perform the
     * following operations:
     *
     * Replace: Replace the character at any one index of substr with another lowercase English letter.
     *
     * Swap: Swap any two characters in substr.
     *
     * Reverse Substring: Reverse substr.
     *
     * Each of these counts as one operation and each character of each substring can be used in each type of operation
     * at most once (i.e. no single index may be involved in more than one replace, one swap, or one reverse).
     *
     * Return the minimum number of operations required to transform word1 into word2.
     *
     * Input: word1 = "abcdf", word2 = "dacbe"
     * Output: 4
     *
     * Input: word1 = "abceded", word2 = "baecfef"
     * Output: 4
     *
     * Input: word1 = "abcdef", word2 = "fedabc"
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= word1.length == word2.length <= 100
     * word1 and word2 consist only of lowercase English letters.
     * @param word1
     * @param word2
     * @return
     */
    // time = O(n^2),s pace = O(n^2)
    int[][] cnt;
    int op;
    public int minOperations(String word1, String word2) {
        char[] s = word1.toCharArray();
        char[] t = word2.toCharArray();
        int n = s.length;
        int[][] rev = new int[n][n];
        for (int i = 0; i < n * 2 - 1; i++) {
            cnt = new int[26][26];
            op = 1;
            int l = i / 2, r = (i + 1) / 2;
            while (l >= 0 && r < n) {
                update(s[l], t[r]);
                if (l != r) update(s[r], t[l]);
                rev[l][r] = op;
                l--;
                r++;
            }
        }

        int[] f = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int res = Integer.MAX_VALUE;
            cnt = new int[26][26];
            op = 0;
            for (int j = i; j >= 0; j--) {
                update(s[j], t[j]);
                res = Math.min(res, f[j] + Math.min(op, rev[j][i]));
            }
            f[i + 1] = res;
        }
        return f[n];
    }

    private void update(char x, char y) {
        if (x == y) return;
        int a = x - 'a', b = y - 'a';
        if (cnt[b][a] > 0) cnt[b][a]--;
        else {
            cnt[a][b]++;
            op++;
        }
    }
}
/**
 * 本题是标准的划分型 DP
 * 定义 f[i+1] 表示前缀 [0,i] 为了达成题目要求，需要的最少操作次数。这里 i+1 是为了把 f[0] 当作初始值
 * 枚举最后一个子串的左端点 j，那么问题变成前缀 [0,j−1] 为了达成题目要求，需要的最少操作次数，即 f[j]。取最小值
 * 初始值 f[0]=0，空串无需操作。
 * 答案为 f[n]。
 * 如何计算子串 [j,i] 的最小操作次数 op(j,i)
 * 先只考虑操作 1 和操作 2
 * 注意到，操作 2 等价两次操作 1，所以应该最大化操作 2 的次数
 * (sp, tp) = (tq, sq)
 */