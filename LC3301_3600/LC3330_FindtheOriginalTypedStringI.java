package LC3301_3600;

public class LC3330_FindtheOriginalTypedStringI {
    /**
     * Alice is attempting to type a specific string on her computer. However, she tends to be clumsy and may press a
     * key for too long, resulting in a character being typed multiple times.
     *
     * Although Alice tried to focus on her typing, she is aware that she may still have done this at most once.
     *
     * You are given a string word, which represents the final output displayed on Alice's screen.
     *
     * Return the total number of possible original strings that Alice might have intended to type.
     *
     * Input: word = "abbcccc"
     * Output: 5
     *
     * Input: word = "abcd"
     * Output: 1
     *
     * Input: word = "aaaa"
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= word.length <= 100
     * word consists only of lowercase English letters.
     * @param word
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public int possibleStringCount(String word) {
        int n = word.length(), res = 0;
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && word.charAt(j) == word.charAt(i)) j++;
            res += j - i - 1;
            i = j - 1;
        }
        return res + 1;
    }

    // S2
    // time = O(n), space = O(1)
    public int possibleStringCount2(String word) {
        int n = word.length(), res = 1;
        for (int i = 1; i < n; i++) {
            if (word.charAt(i) == word.charAt(i - 1)) res++;
        }
        return res;
    }
}