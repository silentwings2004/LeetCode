package LC3301_3600;
import java.util.*;
public class LC3481_ApplySubstitutions {
    /**
     * You are given a replacements mapping and a text string that may contain placeholders formatted as %var%, where
     * each var corresponds to a key in the replacements mapping. Each replacement value may itself contain one or more
     * such placeholders. Each placeholder is replaced by the value associated with its corresponding replacement key.
     *
     * Return the fully substituted text string which does not contain any placeholders.
     *
     * Input: replacements = [["A","abc"],["B","def"]], text = "%A%_%B%"
     *
     * Output: "abc_def"
     *
     * Input: replacements = [["A","bce"],["B","ace"],["C","abc%B%"]], text = "%A%_%B%_%C%"
     *
     * Output: "bce_ace_abcace"
     *
     * Constraints:
     *
     * 1 <= replacements.length <= 10
     * Each element of replacements is a two-element list [key, value], where:
     * key is a single uppercase English letter.
     * value is a non-empty string of at most 8 characters that may contain zero or more placeholders formatted as
     * %<key>%.
     * All replacement keys are unique.
     * The text string is formed by concatenating all key placeholders (formatted as %<key>%) randomly from the
     * replacements mapping, separated by underscores.
     * text.length == 4 * replacements.length - 1
     * Every placeholder in the text or in any replacement value corresponds to a key in the replacements mapping.
     * There are no cyclic dependencies between replacement keys.
     * @param replacements
     * @param text
     * @return
     */
    // time = O(n), space = O(n)
    HashMap<String, String> map;
    public String applySubstitutions(List<List<String>> replacements, String text) {
        map = new HashMap<>();
        for (List<String> r : replacements) {
            String x = r.get(0), y = r.get(1);
            map.put(x, y);
        }
        return dfs(text);
    }

    private String dfs(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != '%') sb.append(s.charAt(i));
            else {
                int j = i + 1;
                while (j < n && s.charAt(j) != '%') j++;
                String sub = s.substring(i + 1, j);
                sb.append(dfs(map.get(sub)));
                i = j;
            }
        }
        return sb.toString();
    }
}
