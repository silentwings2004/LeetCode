package LC3601_3900;

public class LC3775_ReverseWordsWithSameVowelCount {
    /**
     * You are given a string s consisting of lowercase English words, each separated by a single space.
     *
     * Determine how many vowels appear in the first word. Then, reverse each following word that has the same vowel
     * count. Leave all remaining words unchanged.
     *
     * Return the resulting string.
     *
     * Vowels are 'a', 'e', 'i', 'o', and 'u'.
     *
     * Input: s = "cat and mice"
     * Output: "cat dna mice"
     *
     * Input: s = "book is nice"
     * Output: "book is ecin"
     *
     * Input: s = "banana healthy"
     * Output: "banana healthy"
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s consists of lowercase English letters and spaces.
     * Words in s are separated by a single space.
     * s does not contain leading or trailing spaces.
     * @param s
     * @return
     */
    // time = O(n), space = O(n)
    public String reverseWords(String s) {
        String[] strs = s.split(" ");
        int n = strs.length, cnt = 0;
        String vowel = "aeiou";
        for (int i = 0; i < n; i++) {
            int m = strs[i].length();
            int t = 0;
            for (int j = 0; j < m; j++) {
                char c = strs[i].charAt(j);
                if (vowel.indexOf(c) != -1) t++;
            }
            if (i == 0) cnt = t;
            else if (t == cnt) strs[i] = new StringBuilder(strs[i]).reverse().toString();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append(strs[i]).append(' ');
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}