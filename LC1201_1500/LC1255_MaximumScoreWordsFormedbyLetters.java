package LC1201_1500;
import java.util.*;
public class LC1255_MaximumScoreWordsFormedbyLetters {
    /**
     * Given a list of words, list of  single letters (might be repeating) and score of every character.
     *
     * Return the maximum score of any valid set of words formed by using the given letters (words[i] cannot be used two
     * or more times).
     *
     * It is not necessary to use all characters in letters and each letter can only be used once. Score of letters 'a',
     * 'b', 'c', ... ,'z' is given by score[0], score[1], ... , score[25] respectively.
     *
     * Input: words = ["dog","cat","dad","good"], letters = ["a","a","c","d","d","d","g","o","o"],
     * score = [1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0]
     * Output: 23
     *
     * Input: words = ["xxxz","ax","bx","cx"], letters = ["z","a","b","c","x","x","x"],
     * score = [4,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,10]
     * Output: 27
     *
     * Input: words = ["leetcode"], letters = ["l","e","t","c","o","d"],
     * score = [0,0,1,1,1,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,0,0,0,0,0]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= words.length <= 14
     * 1 <= words[i].length <= 15
     * 1 <= letters.length <= 100
     * letters[i].length == 1
     * score.length == 26
     * 0 <= score[i] <= 10
     * words[i], letters[i] contains only lower case English letters.
     * @param words
     * @param letters
     * @param score
     * @return
     */
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int m = words.length, n = letters.length;
        int[] t = new int[m];
        for (int i = 0; i < m; i++) {
            for (char c : words[i].toCharArray()) t[i] += score[c - 'a'];
        }

        int[] cnt = new int[26];
        for (char c : letters) cnt[c - 'a']++;

        int res = 0;
        for (int i = 0; i < 1 << m; i++) {
            int sum = 0;
            int[] b = cnt.clone();
            boolean success = true;
            for (int j = 0; j < m; j++) {
                if ((i >> j & 1) == 1) {
                    for (char c : words[j].toCharArray()) {
                        if (b[c - 'a'] == 0) {
                            success = false;
                            break;
                        } else b[c - 'a']--;
                    }
                    if (success) sum += t[j];
                    else {
                        sum = 0;
                        break;
                    }
                }
            }
            res = Math.max(res, sum);
        }
        return res;
    }
}