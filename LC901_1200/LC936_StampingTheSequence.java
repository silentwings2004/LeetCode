package LC901_1200;
import java.util.*;
public class LC936_StampingTheSequence {
    /**
     * You are given two strings stamp and target. Initially, there is a string s of length target.length with all
     * s[i] == '?'.
     *
     * In one turn, you can place stamp over s and replace every letter in the s with the corresponding letter from
     * stamp.
     *
     * For example, if stamp = "abc" and target = "abcba", then s is "?????" initially. In one turn you can:
     * place stamp at index 0 of s to obtain "abc??",
     * place stamp at index 1 of s to obtain "?abc?", or
     * place stamp at index 2 of s to obtain "??abc".
     * Note that stamp must be fully contained in the boundaries of s in order to stamp (i.e., you cannot place stamp at
     * index 3 of s).
     * We want to convert s to target using at most 10 * target.length turns.
     *
     * Return an array of the index of the left-most letter being stamped at each turn. If we cannot obtain target from
     * s within 10 * target.length turns, return an empty array.
     *
     * Input: stamp = "abc", target = "ababc"
     * Output: [0,2]
     *
     * Input: stamp = "abca", target = "aabcaca"
     * Output: [3,0,1]
     *
     * Constraints:
     *
     * 1 <= stamp.length <= target.length <= 1000
     * stamp and target consist of lowercase English letters.
     * @param stamp
     * @param target
     * @return
     */
    // time = O(m * (m − n)), space = O(m * (m − n))
    char[] chars;
    public int[] movesToStamp(String stamp, String target) {
        List<Integer> res = new ArrayList<>();
        boolean flag = true;
        int m = stamp.length(), n = target.length();
        chars = target.toCharArray();

        while (flag) {
            flag = false;
            for (int i = 0; i < n; i++) {
                if (check(stamp, target, i)) {
                    for (int k = 0; k < m; k++) chars[i + k] = '*';
                    res.add(i);
                    flag = true;
                    break;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (chars[i] != '*') return new int[0];
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) ans[i] = res.get(res.size() - 1 - i);
        return ans;
    }

    private boolean check(String s, String t, int pos) {
        boolean flag = false;
        int m = s.length(), n = t.length();
        for (int i = 0; i < m; i++) {
            if (pos + i >= n) return false;
            if (chars[pos + i] == '*') continue;
            if (chars[pos + i] != s.charAt(i)) return false;
            flag = true;
        }
        return flag;
    }

    // S2
    // time = O(n * (n - m)), space = O(n * (n - m))
    public int[] movesToStamp2(String stamp, String target) {
        List<Integer> res = new ArrayList<>();
        char[] chars = target.toCharArray();
        int m = stamp.length(), n = chars.length;
        while (true) {
            boolean flag = true;
            for (int i = 0; i + m <= n; i++) {
                if (work(stamp, chars, i)) {
                    res.add(i);
                    flag = false;
                }
            }
            if (flag) break;
        }

        target = String.valueOf(chars);
        String t = "?".repeat(n);
        if (!target.equals(t)) return new int[0];
        Collections.reverse(res);
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) ans[i] = res.get(i);
        return ans;
    }

    private boolean work(String s, char[] chars, int k) {
        String target = String.valueOf(chars);
        int m = s.length();
        if (target.substring(k, k + m).equals("?".repeat(m))) return false;
        for (int i = 0; i < m; i++) {
            if (chars[k + i] != '?' && chars[k + i] != s.charAt(i)) return false;
        }
        for (int i = 0; i < m; i++) chars[k + i] = '?';
        return true;
    }
}
/**
 * 从后往前看，找到一段和刷子完全匹配的一段 => 变成？
 */
