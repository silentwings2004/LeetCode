package LC601_900;
import java.util.*;
public class LC784_LetterCasePermutation {
    /**
     * Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.
     *
     * Return a list of all possible strings we could create. You can return the output in any order.
     *
     * Input: S = "a1b2"
     * Output: ["a1b2","a1B2","A1b2","A1B2"]
     *
     * Input: s = "3z4"
     * Output: ["3z4","3Z4"]
     *
     * Constraints:
     *
     * 1 <= s.length <= 12
     * s consists of lowercase English letters, uppercase English letters, and digits.
     *
     * @param S
     * @return
     */
    // S1: dfs
    // time = O(2^n * n), space = O(2^n * n)
    List<String> res;
    public List<String> letterCasePermutation(String s) {
        res = new ArrayList<>();
        char[] chars = s.toCharArray();
        dfs(chars, 0);
        return res;
    }

    private void dfs(char[] chars, int u) {
        if (u == chars.length) res.add(String.valueOf(chars));
        else {
            dfs(chars, u + 1);
            if (!Character.isDigit(chars[u])) {
                chars[u] ^= 32;
                dfs(chars, u + 1);
                chars[u] ^= 32;
            }
        }
    }

    // S2: state compression
    // time = O(n * 2^n), space = O(n)
    public List<String> letterCasePermutation2(String s) {
        HashSet<String> set = new HashSet<>();
        int n = s.length();
        for (int state = 0; state < 1 << n; state++) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if ((state >> i & 1) == 1) {
                    if (Character.isDigit(c)) sb.append(c);
                    else sb.append((char)(c ^ 32));
                } else sb.append(c);
            }
            set.add(sb.toString());
        }
        return new ArrayList<>(set);
    }
}