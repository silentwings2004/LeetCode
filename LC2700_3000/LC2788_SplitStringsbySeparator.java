package LC2700_3000;
import java.util.*;
public class LC2788_SplitStringsbySeparator {
    /**
     * Given an array of strings words and a character separator, split each string in words by separator.
     *
     * Return an array of strings containing the new strings formed after the splits, excluding empty strings.
     *
     * Notes
     *
     * separator is used to determine where the split should occur, but it is not included as part of the resulting
     * strings.
     * A split may result in more than two strings.
     * The resulting strings must maintain the same order as they were initially given.
     *
     * Input: words = ["one.two.three","four.five","six"], separator = "."
     * Output: ["one","two","three","four","five","six"]
     *
     * Input: words = ["$easy$","$problem$"], separator = "$"
     * Output: ["easy","problem"]
     *
     * Input: words = ["|||"], separator = "|"
     * Output: []
     *
     * Constraints:
     *
     * 1 <= words.length <= 100
     * 1 <= words[i].length <= 20
     * characters in words[i] are either lowercase English letters or characters from the
     * string ".,|$#@" (excluding the quotes)
     * separator is a character from the string ".,|$#@" (excluding the quotes)
     * @param words
     * @param separator
     * @return
     */
    // time = O(n), space = O(n)
    public List<String> splitWordsBySeparator(List<String> words, char separator) {
        List<String> res = new ArrayList<>();
        for (String w : words) {
            String[] strs = w.split(get(separator));
            for (String str : strs) {
                if (str.length() > 0) res.add(str);
            }
        }
        return res;
    }

    private String get(char c) {
        if (c == '.') return "\\.";
        if (c == ',') return "\\,";
        if (c == '|') return "\\|";
        if (c == '$') return "\\$";
        if (c == '#') return "\\#";
        return "\\@";
    }
}