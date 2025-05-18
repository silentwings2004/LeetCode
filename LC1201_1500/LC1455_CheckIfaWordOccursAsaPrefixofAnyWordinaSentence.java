package LC1201_1500;

public class LC1455_CheckIfaWordOccursAsaPrefixofAnyWordinaSentence {
    /**
     * Given a sentence that consists of some words separated by a single space, and a searchWord, check if searchWord
     * is a prefix of any word in sentence.
     *
     * Return the index of the word in sentence (1-indexed) where searchWord is a prefix of this word. If searchWord is
     * a prefix of more than one word, return the index of the first word (minimum index). If there is no such word
     * return -1.
     *
     * A prefix of a string s is any leading contiguous substring of s.
     *
     * Input: sentence = "i love eating burger", searchWord = "burg"
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= sentence.length <= 100
     * 1 <= searchWord.length <= 10
     * sentence consists of lowercase English letters and spaces.
     * searchWord consists of lowercase English letters.
     * @param sentence
     * @param searchWord
     * @return
     */
    // time = O(n * k), space = O(n * k)
    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] strs = sentence.split(" ");
        int n = strs.length;
        for (int i = 0; i < n; i++) {
            if (strs[i].indexOf(searchWord) == 0) return i + 1;
        }
        return -1;
    }
}
