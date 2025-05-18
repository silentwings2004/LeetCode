package LC901_1200;
import java.util.*;
public class LC1078_OccurrencesAfterBigram {
    /**
     * Given two strings first and second, consider occurrences in some text of the form "first second third", where
     * second comes immediately after first, and third comes immediately after second.
     *
     * Return an array of all the words third for each occurrence of "first second third".
     *
     * Input: text = "alice is a good girl she is a good student", first = "a", second = "good"
     * Output: ["girl","student"]
     *
     * Constraints:
     *
     * 1 <= text.length <= 1000
     * text consists of lowercase English letters and spaces.
     * All the words in text a separated by a single space.
     * 1 <= first.length, second.length <= 10
     * first and second consist of lowercase English letters.
     * @param text
     * @param first
     * @param second
     * @return
     */
    // time = O(n), space = O(n)
    public String[] findOcurrences(String text, String first, String second) {
        List<String> res = new ArrayList<>();
        String[] strs = text.split(" ");
        for (int i = 0; i + 2 < strs.length; i++) {
            if (strs[i].equals(first) && strs[i + 1].equals(second)) {
                res.add(strs[i + 2]);
            }
        }
        String[] ans = new String[res.size()];
        for (int i = 0; i < res.size(); i++) ans[i] = res.get(i);
        return ans;
    }
}
