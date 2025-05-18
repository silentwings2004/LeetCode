package LC1501_1800;
import java.util.*;
public class LC1781_SumofBeautyofAllSubstrings {
    /**
     * The beauty of a string is the difference in frequencies between the most frequent and least frequent characters.
     *
     * For example, the beauty of "abaacc" is 3 - 1 = 2.
     * Given a string s, return the sum of beauty of all of its substrings.
     *
     * Input: s = "aabcb"
     * Output: 5
     *
     * Input: s = "aabcbaa"
     * Output: 17
     *
     * Constraints:
     *
     * 1 <= s.length <= 500
     * s consists of only lowercase English letters.
     * @param s
     * @return
     */
    // S1
    // time = O(n^2), space = O(n)
    public int beautySum(String s) {
        int n = s.length();
        int[][] cnt = new int[n + 1][26];
        for (int i = 1; i <= n; i++) {
            int u = s.charAt(i - 1) - 'a';
            cnt[i] = cnt[i - 1].clone();
            cnt[i][u]++;
        }

        int res = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                int[] diff = new int[26];
                int min = n + 1, max = -1;
                for (int k = 0; k < 26; k++) {
                    diff[k] = cnt[j][k] - cnt[i - 1][k];
                    if (diff[k] > 0) min = Math.min(min, diff[k]);
                    max = Math.max(max, diff[k]);
                }
                res += max - min;
            }
        }
        return res;
    }

    // S2
    // time = O(n^2), space = O(n)
    public int beautySum2(String s) {
        int[] cnt = new int[26];
        int n = s.length(), res = 0;
        for (int i = 0; i < n; i++) {
            Arrays.fill(cnt, 0);
            for (int j = i; j < n; j++) {
                cnt[s.charAt(j) - 'a']++;
                int minv = n + 1, maxv = -1;
                for (int k = 0; k < 26; k++) {
                    if (cnt[k] > 0) {
                        minv = Math.min(minv, cnt[k]);
                        maxv = Math.max(maxv, cnt[k]);
                    }
                }
                res += maxv - minv;
            }
        }
        return res;
    }
}
