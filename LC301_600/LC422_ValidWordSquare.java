package LC301_600;
import java.util.*;
public class LC422_ValidWordSquare {
    /**
     * Given an array of strings words, return true if it forms a valid word square.
     *
     * A sequence of strings forms a valid word square if the kth row and column read the same string, where
     * 0 <= k < max(numRows, numColumns).
     *
     * Input: words = ["abcd","bnrt","crmy","dtye"]
     * Output: true
     *
     * Constraints:
     *
     * 1 <= words.length <= 500
     * 1 <= words[i].length <= 500
     * words[i] consists of only lowercase English letters.
     * @param words
     * @return
     */
    // time = O(n * k), space = O(1)
    public boolean validWordSquare(List<String> words) {
        int n = words.size();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < words.get(i).length(); j++) {
                if (j >= n || words.get(j).length() <= i || words.get(j).charAt(i) != words.get(i).charAt(j)) return false;
            }
        }
        return true;
    }
}
