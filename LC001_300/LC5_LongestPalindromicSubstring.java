package LC001_300;
import java.util.*;
public class LC5_LongestPalindromicSubstring {
    /**
     * Given a string s, return the longest palindromic substring in s.
     *
     * Input: s = "babad"
     * Output: "bab"
     *
     * Constraints:
     *
     * 1 <= s.length <= 1000
     * s consist of only digits and English letters (lower-case and/or upper-case)
     *
     * @param s
     * @return
     */
    // S1: DP
    // time = O(n^2), space = O(n^2)
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] g = new boolean[n + 1][n + 1];

        for (int len = 1; len <= n; len++) {
            for (int i = 1; i + len - 1 <= n; i++) {
                int j = i + len - 1;
                if (s.charAt(i - 1) == s.charAt(j - 1) && (len <= 2 || g[i + 1][j - 1])) {
                    g[i][j] = true;
                }
            }
        }

        String res = "";
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                if (g[i][j]) {
                    if (j - i + 1 > res.length()) {
                        res = s.substring(i - 1, j);
                    }
                }
            }
        }
        return res;
    }

    // S2: 中心扩散法
    // time = O(n^2), space = O(1)
    public String longestPalindrome2(String s) {
        String res = "";
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int l = i - 1, r = i + 1;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
            }
            if (res.length() < r - l - 1) res = s.substring(l + 1, r);

            l = i;
            r = i + 1;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
            }
            if (res.length() < r - l - 1) res = s.substring(l + 1, r);
        }
        return res;
    }

    // S3: Manacher (最优解！！！)
    // time = O(n), space = O(n)
    public String longestPalindrome3(String s) {
        // corner case
        if (s == null || s.length() == 0) return "";

        StringBuilder sb = new StringBuilder();
        sb.append("#");
        for (char c : s.toCharArray()) {
            sb.append(c);
            sb.append("#");
        }

        int n = sb.length();
        int[] p = new int[n];
        int maxCenter = -1, maxRight = -1;

        for (int i = 0; i < n; i++) {
            int r = 0;
            if (i <= maxRight) {
                int j = maxCenter * 2 - i;
                r = Math.min(p[j], maxRight - i);
            }
            while (i - r >= 0 && i + r < n && sb.charAt(i - r) == sb.charAt(i + r)) r++;
            p[i] = r - 1;
            if (i + p[i] > maxRight) {
                maxRight = i + p[i];
                maxCenter = i;
            }
        }

        int maxLen = -1, center = 0;
        for (int i = 0; i < n; i++) {
            if (p[i] > maxLen) {
                maxLen = p[i];
                center = i;
            }
        }
        int start = center / 2 - maxLen / 2; // 注意：这道题的一大坑点：一定要先算出start，然后end = start + maxLen
        return s.substring(start, start + maxLen); // 不能直接让end = center/2 + maxLen/2,因为由于除2，可能小数位舍去，结果可能最终差1.
    }
}
/**
 * d{acb|bca}e
 * 偶数，奇数
 * O(n^2)
 * Manacher: O(n)
 * 相比于KMP，Manacher相对更容易理解。
 * 只考虑长度为奇数的回文字符串
 * XXaXX
 * x x x x x x x x x x
 * 暴力算法：固定中心往两边扩展
 *      bab
 *       ^
 * P[i]: extended radius of the longest palindromic substring centered at i
 * 对于每个i而言，考虑它的P[i]
 * [0:i-1]
 * maxRight
 * maxCenter
 *            MC           MR
 * # & x & # x x x # & x & #
 *     j      ctr      i
 * 右边界超越i，对所有i-1个元素而言，我们找的是右边界最远的那个
 * 如果MR超过i，意味着求i的时候，可以看j
 * 以j为中心，其最长回文子串
 * 以i为中心，半径至少从2开始算 => 利用j的信息里的半径长度
 * 偶数怎么办?
 * 按下面所示改造，变奇数型，然后再映射回来
 * c b b d
 * # c [# b # b #] d #
 *          ^
 *        center
 * start: center/2 - maxLen/2
 * len: maxLen
 */