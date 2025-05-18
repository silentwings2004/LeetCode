package LC3001_3300;

public class LC3120_CounttheNumberofSpecialCharactersI {
    /**
     * You are given a string word. A letter is called special if it appears both in lowercase and uppercase in word.
     *
     * Return the number of special letters in word.
     *
     * Input: word = "aaAbcBC"
     * Output: 3
     *
     * Input: word = "abc"
     * Output: 0
     *
     * Input: word = "abBCab"
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= word.length <= 50
     * word consists of only lowercase and uppercase English letters.
     * @param word
     * @return
     */
    // time = O(n), space = O(1)
    public int numberOfSpecialChars(String word) {
        int n = word.length(), upper = 0, lower = 0, res = 0;
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            if (Character.isLowerCase(c)) lower |= 1 << (c - 'a');
            else upper |= 1 << (c - 'A');
        }
        return Integer.bitCount(lower & upper);
    }
}