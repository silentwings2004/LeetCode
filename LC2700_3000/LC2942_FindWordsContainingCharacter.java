package LC2700_3000;
import java.util.*;
public class LC2942_FindWordsContainingCharacter {
    /**
     * You are given a 0-indexed array of strings words and a character x.
     *
     * Return an array of indices representing the words that contain the character x.
     *
     * Note that the returned array may be in any order.
     *
     * Input: words = ["leet","code"], x = "e"
     * Output: [0,1]
     *
     * Input: words = ["abc","bcd","aaaa","cbc"], x = "a"
     * Output: [0,2]
     *
     * Input: words = ["abc","bcd","aaaa","cbc"], x = "z"
     * Output: []
     *
     * Constraints:
     *
     * 1 <= words.length <= 50
     * 1 <= words[i].length <= 50
     * x is a lowercase English letter.
     * words[i] consists only of lowercase English letters.
     * @param words
     * @param x
     * @return
     */
    // time = O(n * k), space = O(1)
    public List<Integer> findWordsContaining(String[] words, char x) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].indexOf(x) != -1) res.add(i);
        }
        return res;
    }
}