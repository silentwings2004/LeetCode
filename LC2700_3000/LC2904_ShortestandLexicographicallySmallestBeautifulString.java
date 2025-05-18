package LC2700_3000;

public class LC2904_ShortestandLexicographicallySmallestBeautifulString {
    /**
     * You are given a binary string s and a positive integer k.
     *
     * A substring of s is beautiful if the number of 1's in it is exactly k.
     *
     * Let len be the length of the shortest beautiful substring.
     *
     * Return the lexicographically smallest beautiful substring of string s with length equal to len. If s doesn't
     * contain a beautiful substring, return an empty string.
     *
     * A string a is lexicographically larger than a string b (of the same length) if in the first position where a and
     * b differ, a has a character strictly larger than the corresponding character in b.
     *
     * For example, "abcd" is lexicographically larger than "abcc" because the first position they differ is at the
     * fourth character, and d is greater than c.
     *
     * Input: s = "100011001", k = 3
     * Output: "11001"
     *
     * Input: s = "1011", k = 2
     * Output: "11"
     *
     * Input: s = "000", k = 1
     * Output: ""
     *
     * Constraints:
     *
     * 1 <= s.length <= 100
     * 1 <= k <= s.length
     * @param s
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length(), maxLen = n + 1;
        int cnt = 0;
        String res = "";
        for (int i = 0, j = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '1') cnt++;
            while (cnt > k) {
                if (s.charAt(j++) == '1') cnt--;
            }
            if (cnt == k) {
                while (j < i && s.charAt(j) == '0') j++;
                int len = i - j + 1;
                String t = s.substring(j, i + 1);
                if (len < maxLen) {
                    maxLen = len;
                    res = s.substring(j, i + 1);
                } else if (len == maxLen && res.compareTo(t) > 0) res = t;
            }
        }
        return res;
    }
}