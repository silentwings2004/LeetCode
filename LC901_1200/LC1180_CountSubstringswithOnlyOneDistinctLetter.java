package LC901_1200;

public class LC1180_CountSubstringswithOnlyOneDistinctLetter {
    /**
     * Given a string s, return the number of substrings that have only one distinct letter.
     *
     * Input: s = "aaaba"
     * Output: 8
     *
     * Input: s = "aaaaaaaaaa"
     * Output: 55
     *
     * Constraints:
     *
     * 1 <= s.length <= 1000
     * s[i] consists of only lowercase English letters.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public int countLetters(String s) {
        int n = s.length(), res = 0;
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && s.charAt(j) == s.charAt(i)) j++;
            int len = j - i;
            res += (len + 1) * len / 2;
            i = j - 1;
        }
        return res;
    }
}
