package LC1801_2100;
import java.util.*;
public class LC1807_EvaluatetheBracketPairsofaString {
    /**
     * You are given a string s that contains some bracket pairs, with each pair containing a non-empty key.
     *
     * For example, in the string "(name)is(age)yearsold", there are two bracket pairs that contain the keys "name" and
     * "age".
     * You know the values of a wide range of keys. This is represented by a 2D string array knowledge where each
     * knowledge[i] = [keyi, valuei] indicates that key keyi has a value of valuei.
     *
     * You are tasked to evaluate all of the bracket pairs. When you evaluate a bracket pair that contains some key
     * keyi, you will:
     *
     * Replace keyi and the bracket pair with the key's corresponding valuei.
     * If you do not know the value of the key, you will replace keyi and the bracket pair with a question mark "?"
     * (without the quotation marks).
     * Each key will appear at most once in your knowledge. There will not be any nested brackets in s.
     *
     * Return the resulting string after evaluating all of the bracket pairs.
     *
     * Input: s = "(name)is(age)yearsold", knowledge = [["name","bob"],["age","two"]]
     * Output: "bobistwoyearsold"
     *
     * Input: s = "hi(name)", knowledge = [["a","b"]]
     * Output: "hi?"
     *
     * Input: s = "(a)(a)(a)aaa", knowledge = [["a","yes"]]
     * Output: "yesyesyesaaa"
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * 0 <= knowledge.length <= 10^5
     * knowledge[i].length == 2
     * 1 <= keyi.length, valuei.length <= 10
     * s consists of lowercase English letters and round brackets '(' and ')'.
     * Every open bracket '(' in s will have a corresponding close bracket ')'.
     * The key in each bracket pair of s will be non-empty.
     * There will not be any nested bracket pairs in s.
     * keyi and valuei consist of lowercase English letters.
     * Each keyi in knowledge is unique.
     * @param s
     * @param knowledge
     * @return
     */
    // time = O(n), space = O(n)
    public String evaluate(String s, List<List<String>> knowledge) {
        HashMap<String, String> map = new HashMap<>();
        for (List<String> x : knowledge) {
            String key = x.get(0), val = x.get(1);
            map.put(key, val);
        }

        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c != '(') {
                sb.append(c);
                continue;
            } else {
                int j = i + 1;
                while (j < n && s.charAt(j) != ')') j++;
                String key = s.substring(i + 1, j);
                if (map.containsKey(key)) sb.append(map.get(key));
                else sb.append('?');
                i = j;
            }
        }
        return sb.toString();
    }
}