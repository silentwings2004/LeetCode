package LC3601_3900;

public class LC3744_FindKthCharacterinExpandedString {
    /**
     * You are given a string s consisting of one or more words separated by single spaces. Each word in s consists of
     * lowercase English letters.
     *
     * We obtain the expanded string t from s as follows:
     *
     * For each word in s, repeat its first character once, then its second character twice, and so on.
     * For example, if s = "hello world", then t = "heelllllllooooo woorrrllllddddd".
     *
     * You are also given an integer k, representing a valid index of the string t.
     *
     * Return the kth character of the string t.
     *
     * Input: s = "hello world", k = 0
     * Output: "h"
     *
     * Input: s = "hello world", k = 15
     * Output: " "
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s contains only lowercase English letters and spaces ' '.
     * s does not contain any leading or trailing spaces.
     * All the words in s are separated by a single space.
     * 0 <= k < t.length. That is, k is a valid index of t.
     * @param s
     * @param k
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public char kthCharacter(String s, long k) {
        String[] strs = s.split(" ");
        int n = strs.length;
        k++;
        char res = ' ';
        for (int i = 0, t = 0; i < n; i++) {
            int m = strs[i].length();
            long tot = (1L + m) * m / 2;
            if (tot >= k) {
                int x = ((int)Math.sqrt(8 * k + 1) - 1) / 2;
                long y = (1L + x) * x / 2;
                if (y < k) res = strs[i].charAt(x);
                else res = strs[i].charAt(x - 1);
                break;
            } else {
                k -= tot;
                if (k == 1) break;
                k--;
            }
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public char kthCharacter2(String s, long k) {
        int n = s.length(), p = 0;
        char res = ' ';
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                if (k == 0) break;
                k--;
                p = 0;
            } else {
                int t = p + 1;
                if (k < t) {
                    res = c;
                    break;
                }
                k -= t;
                p++;
            }
        }
        return res;
    }
}