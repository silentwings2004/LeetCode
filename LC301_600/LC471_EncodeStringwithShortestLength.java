package LC301_600;

public class LC471_EncodeStringwithShortestLength {
    /**
     * Given a string s, encode the string such that its encoded length is the shortest.
     *
     * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated
     * exactly k times. k should be a positive integer.
     *
     * If an encoding process does not make the string shorter, then do not encode it. If there are several solutions,
     * return any of them.
     *
     * Input: s = "aaa"
     * Output: "aaa"
     *
     * Input: s = "aaaaa"
     * Output: "5[a]"
     *
     * Input: s = "aaaaaaaaaa"
     * Output: "10[a]"
     *
     * Constraints:
     *
     * 1 <= s.length <= 150
     * s consists of only lowercase English letters.
     * @param s
     * @return
     */
    // time = O(n^4), space = O(n^2)
    String[][] f;
    public String encode(String s) {
        int n = s.length();
        f = new String[n][n];
        for (int i = 0; i < n; i++) f[i][i] = s.substring(i, i + 1); // case 1

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                f[i][j] = helper(s, i, j); // case 2
                for (int k = i; k < j; k++) { // case 3
                    if (f[i][k].length() + f[k + 1][j].length() < f[i][j].length()) {
                        f[i][j] = f[i][k] + f[k + 1][j];
                    }
                }
            }
        }
        return f[0][n - 1];
    }

    private String helper(String str, int a, int b) {
        String s = str.substring(a, b + 1);
        String res = s;
        int n = s.length();
        for (int len = 1; len < n; len++) {
            if (n % len != 0) continue;
            boolean flag = true;
            for (int i = len; i < n; i += len) {
                if (!s.substring(i, i + len).equals(s.substring(0, len))) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                String t = (n / len) + "[" + f[a][a + len - 1] + "]"; // 注意这里是f[a][a + len - 1] 而不是s.substring(0, len)!
                if (t.length() < res.length()) res = t;
            }
        }
        return res;
    }
}
/**
 * 令dp[i][j]表示区间s[i:j]可以encode的最短字符串。
 * 我们需要知道，对于任意区间s[i:j]，high level的encode方式有三类:
 * 1. dp[i][j]就是s[i:j].
 * 2. s[i:j]是若干个相同子串t的k次重复，每个子串长度是len，那么dp[i][j]可以写成k[t]的形式，注意t应该是dp[i][i+len-1]而不是原始的子串。
 *    另外，我们需要对k进行遍历得到最优的encode。
 * 3. 我们还可以探索合适的切割点将dp[i][j]分为前后两部分，得到长度最优的dp[i][j] = dp[i][k]+dp[k+1][j]。
 * 这是第二类区间型DP，动态规划的顺序是先小区间、后大区间。最终的答案就是dp[0][n-1].
 */
