package LC901_1200;
import java.util.*;
public class LC1181_BeforeandAfterPuzzle {
    /**
     * Given a list of phrases, generate a list of Before and After puzzles.
     *
     * A phrase is a string that consists of lowercase English letters and spaces only. No space appears in the start or
     * the end of a phrase. There are no consecutive spaces in a phrase.
     *
     * Before and After puzzles are phrases that are formed by merging two phrases where the last word of the first
     * phrase is the same as the first word of the second phrase.
     *
     * Return the Before and After puzzles that can be formed by every two phrases phrases[i] and phrases[j]
     * where i != j. Note that the order of matching two phrases matters, we want to consider both orders.
     *
     * You should return a list of distinct strings sorted lexicographically.
     *
     * Input: phrases = ["writing code","code rocks"]
     * Output: ["writing code rocks"]
     *
     * Input: phrases = ["mission statement",
     *                   "a quick bite to eat",
     *                   "a chip off the old block",
     *                   "chocolate bar",
     *                   "mission impossible",
     *                   "a man on a mission",
     *                   "block party",
     *                   "eat my words",
     *                   "bar of soap"]
     * Output: ["a chip off the old block party",
     *          "a man on a mission impossible",
     *          "a man on a mission statement",
     *          "a quick bite to eat my words",
     *          "chocolate bar of soap"]
     *
     * Input: phrases = ["a","b","a"]
     * Output: ["a"]
     *
     * Constraints:
     *
     * 1 <= phrases.length <= 100
     * 1 <= phrases[i].length <= 100
     * @param phrases
     * @return
     */
    // time = O(n^3 * logn), space = O(n^2)
    TreeSet<String> set;
    public List<String> beforeAndAfterPuzzles(String[] phrases) {
        set = new TreeSet<>();
        int n = phrases.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                helper(phrases[i], phrases[j]);
            }
        }
        return new ArrayList<>(set);
    }

    private void helper(String s, String t) {
        String[] strs1 = s.split(" ");
        String[] strs2 = t.split(" ");
        int m = strs1.length, n = strs2.length;
        if (strs1[m - 1].equals(strs2[0])) {
            StringBuilder sb = new StringBuilder();
            sb.append(s).append(' ');
            for (int i = 1; i < n; i++) sb.append(strs2[i]).append(' ');
            sb.setLength(sb.length() - 1);
            set.add(sb.toString());
        }
    }
}
