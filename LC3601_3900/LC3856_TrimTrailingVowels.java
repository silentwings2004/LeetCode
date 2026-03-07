package LC3601_3900;

public class LC3856_TrimTrailingVowels {
    /**
     * You are given a string s that consists of lowercase English letters.
     *
     * Return the string obtained by removing all trailing vowels from s.
     *
     * The vowels consist of the characters 'a', 'e', 'i', 'o', and 'u'.
     *
     * Input: s = "idea"
     * Output: "id"
     *
     * Input: s = "day"
     * Output: "day"
     *
     * Input: s = "aeiou"
     * Output: ""
     *
     * Constraints:
     *
     * 1 <= s.length <= 100
     * s consists of only lowercase English letters.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public String trimTrailingVowels(String s) {
        String vowel = "aeiou";
        int n = s.length();
        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (vowel.indexOf(c) == -1) return s.substring(0, i + 1);
        }
        return "";
    }
}