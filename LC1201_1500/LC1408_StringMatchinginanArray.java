package LC1201_1500;
import java.util.*;
public class LC1408_StringMatchinginanArray {
    /**
     * Given an array of string words. Return all strings in words which is substring of another word in any order.
     *
     * String words[i] is substring of words[j], if can be obtained removing some characters to left and/or right side
     * of words[j].
     *
     * Input: words = ["mass","as","hero","superhero"]
     * Output: ["as","hero"]
     *
     * Constraints:
     *
     * 1 <= words.length <= 100
     * 1 <= words[i].length <= 30
     * words[i] contains only lowercase English letters.
     * It's guaranteed that words[i] will be unique.
     * @param words
     * @return
     */
    // time = O(n), space = O(n)
    public List<String> stringMatching(String[] words) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (String s : words) sb.append(s).append('#');

        for (String word : words) {
            if (sb.indexOf(word) != sb.lastIndexOf(word)) res.add(word);
        }
        return res;
    }
}
