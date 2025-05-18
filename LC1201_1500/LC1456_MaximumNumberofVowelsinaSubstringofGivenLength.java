package LC1201_1500;

public class LC1456_MaximumNumberofVowelsinaSubstringofGivenLength {
    /**
     * Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.
     *
     * Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
     *
     * Input: s = "abciiidef", k = 3
     * Output: 3
     *
     * Input: s = "aeiou", k = 2
     * Output: 2
     *
     * Input: s = "leetcode", k = 3
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s consists of lowercase English letters.
     * 1 <= k <= s.length
     * @param s
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public int maxVowels(String s, int k) {
        String vowel = "aeiou";
        int n = s.length(), res = 0;
        for (int i = 0, j = 0, cnt = 0; i < n; i++) {
            char c = s.charAt(i);
            if (vowel.indexOf(c) != -1) cnt++;
            if (i - j + 1 == k) {
                res = Math.max(res, cnt);
                if (vowel.indexOf(s.charAt(j++)) != -1) cnt--;
            }
        }
        return res;
    }
}