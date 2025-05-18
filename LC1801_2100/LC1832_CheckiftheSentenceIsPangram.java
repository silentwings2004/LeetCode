package LC1801_2100;

public class LC1832_CheckiftheSentenceIsPangram {
    /**
     * A pangram is a sentence where every letter of the English alphabet appears at least once.
     *
     * Given a string sentence containing only lowercase English letters, return true if sentence is a pangram, or false
     * otherwise.
     *
     * Input: sentence = "thequickbrownfoxjumpsoverthelazydog"
     * Output: true
     *
     * Input: sentence = "leetcode"
     * Output: false
     *
     * Constraints:
     *
     * 1 <= sentence.length <= 1000
     * sentence consists of lowercase English letters.
     * @param sentence
     * @return
     */
    // time = O(n), space = O(1)
    public boolean checkIfPangram(String sentence) {
        for (int i = 0; i < 26; i++) {
            char c = (char)(i + 'a');
            if (sentence.indexOf(c) == -1) return false;
        }
        return true;
    }
}