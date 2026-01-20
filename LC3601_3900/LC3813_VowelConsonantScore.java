package LC3601_3900;

public class LC3813_VowelConsonantScore {
    /**
     * You are given a string s consisting of lowercase English letters, spaces, and digits.
     *
     * Let v be the number of vowels in s and c be the number of consonants in s.
     *
     * A vowel is one of the letters 'a', 'e', 'i', 'o', or 'u', while any other letter in the English alphabet is
     * considered a consonant.
     *
     * The score of the string s is defined as follows:
     *
     * If c > 0, the score = floor(v / c) where floor denotes rounding down to the nearest integer.
     * Otherwise, the score = 0.
     * Return an integer denoting the score of the string.
     *
     * Input: s = "cooear"
     * Output: 2
     *
     * Input: s = "axeyizou"
     * Output: 1
     *
     * Input: s = "au 123"
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= s.length <= 100
     * s consists of lowercase English letters, spaces and digits.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public int vowelConsonantScore(String s) {
        String vowel = "aeiou";
        int n = s.length(), x = 0, y = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isLowerCase(c)) {
                if (vowel.indexOf(c) != -1) x++;
                else y++;
            }
        }
        return y == 0 ? 0 : x / y;
    }
}