package LC901_1200;
import java.util.*;
public class LC1177_CanMakePalindromefromSubstring {
    /**
     * You are given a string s and array queries where queries[i] = [lefti, righti, ki]. We may rearrange the substring
     * s[lefti...righti] for each query and then choose up to ki of them to replace with any lowercase English letter.
     *
     * If the substring is possible to be a palindrome string after the operations above, the result of the query is
     * true. Otherwise, the result is false.
     *
     * Return a boolean array answer where answer[i] is the result of the ith query queries[i].
     *
     * Note that each letter is counted individually for replacement, so if, for example s[lefti...righti] = "aaa", and
     * ki = 2, we can only replace two of the letters. Also, note that no query modifies the initial string s.
     *
     * Input: s = "abcda", queries = [[3,3,0],[1,2,0],[0,3,1],[0,3,2],[0,4,1]]
     * Output: [true,false,false,true,true]
     *
     * Input: s = "lyb", queries = [[0,1,0],[2,2,1]]
     * Output: [false,true]
     *
     * Constraints:
     *
     * 1 <= s.length, queries.length <= 10^5
     * 0 <= lefti <= righti < s.length
     * 0 <= ki <= s.length
     * s consists of lowercase English letters.
     * @param s
     * @param queries
     * @return
     */
    // time = O(26n), space = O(26n)
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        List<Boolean> res = new ArrayList<>();
        int n = s.length();
        int[][] cnt = new int[26][n + 1];
        for (int i = 0; i < 26; i++) {
            for (int j = 1; j <= n; j++) {
                cnt[i][j] = cnt[i][j - 1];
                if ((char)(i + 'a') == s.charAt(j - 1)) cnt[i][j]++;
            }
        }

        for (int[] q : queries) {
            int l = q[0] + 1, r = q[1] + 1, k = q[2];
            int t = 0;
            for (int i = 0; i < 26; i++) t += (cnt[i][r] - cnt[i][l - 1]) % 2 == 1 ? 1 : 0;
            res.add(t / 2 <= k);
        }
        return res;
    }
}
/**
 * 最多只有一个字符出现奇数次
 */