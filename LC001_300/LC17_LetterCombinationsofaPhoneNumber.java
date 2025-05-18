package LC001_300;
import java.util.*;
public class LC17_LetterCombinationsofaPhoneNumber {
    /**
     * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number
     * could represent. Return the answer in any order.
     *
     * Input: digits = "23"
     * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
     *
     * Constraints:
     *
     * 0 <= digits.length <= 4
     * digits[i] is a digit in the range ['2', '9'].
     * @param digits
     * @return
     */
    // time = O(4^n * n), space = O(n)
    String[] strs = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    List<String> res;
    public List<String> letterCombinations(String digits) {
        res = new ArrayList<>();
        if (digits.length() == 0) return res;

        dfs(digits, 0, new StringBuilder());
        return res;
    }

    private void dfs(String s, int u, StringBuilder sb) {
        if (u == s.length()) {
            res.add(sb.toString());
            return;
        }

        int i = s.charAt(u) - '0';
        for (char c : strs[i].toCharArray()) {
            sb.append(c);
            dfs(s, u + 1, sb);
            sb.setLength(sb.length() - 1);
        }
    }
}
/**
 * 两两组合 -> DFS模板操作题
 */
