package LC901_1200;
import java.util.*;
public class LC1065_IndexPairsofaString {
    /**
     * Given a string text and an array of strings words, return an array of all index pairs [i, j] so that the
     * substring text[i...j] is in words.
     *
     * Return the pairs [i, j] in sorted order (i.e., sort them by their first coordinate, and in case of ties sort
     * them by their second coordinate).
     *
     * Input: text = "thestoryofleetcodeandme", words = ["story","fleet","leetcode"]
     * Output: [[3,7],[9,13],[10,17]]
     *
     * Input: text = "ababa", words = ["aba","ab"]
     * Output: [[0,1],[0,2],[2,3],[2,4]]
     *
     * Constraints:
     *
     * 1 <= text.length <= 100
     * 1 <= words.length <= 20
     * 1 <= words[i].length <= 50
     * text and words[i] consist of lowercase English letters.
     * All the strings of words are unique.
     * @param text
     * @param words
     * @return
     */
    // time = O(n * s + m^3), space = O(n * s)
    public int[][] indexPairs(String text, String[] words) {
        List<int[]> res = new ArrayList<>();
        for (String word : words) {
            int idx = text.indexOf(word);
            while (idx != -1) {
                res.add(new int[]{idx, idx + word.length() - 1});
                idx = text.indexOf(word, idx + 1);
            }
        }
        Collections.sort(res, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        return res.toArray(new int[res.size()][]);
    }
}