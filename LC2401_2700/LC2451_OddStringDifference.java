package LC2401_2700;
import java.util.*;
public class LC2451_OddStringDifference {
    /**
     * You are given an array of equal-length strings words. Assume that the length of each string is n.
     *
     * Each string words[i] can be converted into a difference integer array difference[i] of length n - 1 where
     * difference[i][j] = words[i][j+1] - words[i][j] where 0 <= j <= n - 2. Note that the difference between two
     * letters is the difference between their positions in the alphabet i.e. the position of 'a' is 0, 'b' is 1, and
     * 'z' is 25.
     *
     * For example, for the string "acb", the difference integer array is [2 - 0, 1 - 2] = [2, -1].
     * All the strings in words have the same difference integer array, except one. You should find that string.
     *
     * Return the string in words that has different difference integer array.
     *
     * Input: words = ["adc","wzy","abc"]
     * Output: "abc"
     *
     * Input: words = ["aaa","bob","ccc","ddd"]
     * Output: "bob"
     *
     * Constraints:
     *
     * 3 <= words.length <= 100
     * n == words[i].length
     * 2 <= n <= 20
     * words[i] consists of lowercase English letters.
     * @param words
     * @return
     */
    // time = O(n^2), space = O(n^2)
    public String oddString(String[] words) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String w : words) map.put(convert(w), map.getOrDefault(convert(w), 0) + 1);
        for (String w : words) {
            if (map.get(convert(w)) == 1) return w;
        }
        return "";
    }

    private String convert(String s) {
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        for (int i = 0; i + 1 < n; i++) sb.append(s.charAt(i + 1) - s.charAt(i)).append(",");
        return sb.toString();
    }
}
