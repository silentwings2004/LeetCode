package LC3001_3300;

public class LC3136_ValidWord {
    /**
     * A word is considered valid if:
     *
     * It contains a minimum of 3 characters.
     * It consists of the digits 0-9, and the uppercase and lowercase English letters. (Not necessary to have all of
     * them.)
     * It includes at least one vowel.
     * It includes at least one consonant.
     * You are given a string word.
     *
     * Return true if word is valid, otherwise, return false.
     *
     * Notes:
     *
     * 'a', 'e', 'i', 'o', 'u', and their uppercases are vowels.
     * A consonant is an English letter that is not a vowel.
     *
     * Input: word = "234Adas"
     * Output: true
     *
     * Input: word = "b3"
     * Output: false
     *
     * Input: word = "a3$e"
     * Output: false
     *
     * Constraints:
     *
     * 1 <= word.length <= 20
     * word consists of English uppercase and lowercase letters, digits, '@', '#', and '$'.
     * @param word
     * @return
     */
    // time = O(n), space = O(1)
    public boolean isValid(String word) {
        String vowel = "aeiouAEIOU";
        boolean f1 = false, f2 = false;
        int n = word.length();
        if (n < 3) return false;
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            if (!Character.isDigit(c) && !Character.isLetter(c)) return false;
            if (Character.isLetter(c)) {
                if (vowel.indexOf(c) != -1) f1 = true;
                else f2 = true;
            }
        }
        return f1 && f2;
    }
}