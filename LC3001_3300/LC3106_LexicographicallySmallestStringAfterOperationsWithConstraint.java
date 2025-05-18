package LC3001_3300;

public class LC3106_LexicographicallySmallestStringAfterOperationsWithConstraint {
    /**
     * You are given a string s and an integer k.
     *
     * Define a function distance(s1, s2) between two strings s1 and s2 of the same length n as:
     *
     * The sum of the minimum distance between s1[i] and s2[i] when the characters from 'a' to 'z' are placed in a
     * cyclic order, for all i in the range [0, n - 1].
     *
     * For example, distance("ab", "cd") == 4, and distance("a", "z") == 1.
     * You can change any letter of s to any other lowercase English letter, any number of times.
     * Return a string denoting the lexicographically smallest string t you can get after some changes, such that
     * distance(s, t) <= k.
     *
     * Input: s = "zbbz", k = 3
     * Output: "aaaz"
     *
     * Input: s = "xaxcd", k = 4
     * Output: "aawcd"
     *
     * Input: s = "lol", k = 0
     * Output: "lol"
     *
     * Constraints:
     *
     * 1 <= s.length <= 100
     * 0 <= k <= 2000
     * s consists only of lowercase English letters.
     * @param s
     * @param k
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public String getSmallestString(String s, int k) {
        if (k == 0) return s;
        char[] chars = s.toCharArray();
        int n = chars.length;
        for (int i = 0; i < n; i++) {
            int x = chars[i] - 'a', y = ('a' - chars[i] + 26) % 26;
            int t = Math.min(x, y);
            if (k >= t) {
                chars[i] = 'a';
                k -= t;
            } else {
                int c1 = (chars[i] - 'a' - k + 26) % 26, c2 = (chars[i] - 'a' + k) % 26;
                int ch = Math.min(c1, c2);
                if (ch < chars[i] - 'a') chars[i] = (char)(ch + 'a');
                break;
            }
        }
        return String.valueOf(chars);
    }

    // S2
    // time = O(n), space = O(n)
    public String getSmallestString2(String s, int k) {
        char[] t = s.toCharArray();
        int n = t.length;
        for (int i = 0; i < n; i++) {
            int d = Math.min(t[i] - 'a', 'z' - t[i] + 1);
            if (k >= d) {
                t[i] = 'a';
                k -= d;
            } else {
                t[i] -= k;
                break;
            }
        }
        return String.valueOf(t);
    }
}
/**
 * k = 0 => 答案就是 s
 * k = 1 => 如果s[0] 不是 'a'， 那么肯定要改 s[0]
 * 在 s 的基础上调整
 * 每次操作，可以把 s[i] + 1 or -1
 * 至多操作 k 次，得到的最小字典序是多少
 */