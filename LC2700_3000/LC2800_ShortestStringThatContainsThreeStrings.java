package LC2700_3000;
import java.util.*;
public class LC2800_ShortestStringThatContainsThreeStrings {
    /**
     * Given three strings a, b, and c, your task is to find a string that has the minimum length and contains all three
     * strings as substrings.
     * If there are multiple such strings, return the lexicographically smallest one.
     *
     * Return a string denoting the answer to the problem.
     *
     * Notes
     *
     * A string a is lexicographically smaller than a string b (of the same length) if in the first position where a and
     * b differ, string a has a letter that appears earlier in the alphabet than the corresponding letter in b.
     * A substring is a contiguous sequence of characters within a string.
     *
     * Input: a = "abc", b = "bca", c = "aaa"
     * Output: "aaabca"
     *
     * Input: a = "ab", b = "ba", c = "aba"
     * Output: "aba"
     *
     * Constraints:
     *
     * 1 <= a.length, b.length, c.length <= 100
     * a, b, c consist only of lowercase English letters.
     * @param a
     * @param b
     * @param c
     * @return
     */
    // time = O(n^2), space = O(n)
    public String minimumString(String a, String b, String c) {
        List<String> q = Arrays.asList(work(a, b, c), work(a, c, b), work(b, a, c), work(b, c, a), work(c, a, b), work(c, b, a));
        Collections.sort(q, (o1, o2) -> o1.length() != o2.length() ? o1.length() - o2.length() : o1.compareTo(o2));
        return q.get(0);
    }

    private String work(String a, String b, String c) {
        StringBuilder sb = new StringBuilder();
        sb.append(a);
        boolean canSkip = false;
        if (sb.length() > b.length() && sb.toString().contains(b)) canSkip = true;
        if (!canSkip) {
            boolean flag = false;
            for (int i = Math.min(sb.length(), b.length()); i > 0; i--) {
                String s = sb.toString().substring(sb.length() - i), t = b.substring(0, i);
                if (s.equals(t)) {
                    flag = true;
                    sb.append(b.substring(i));
                    break;
                }
            }
            if (!flag) sb.append(b);
        }
        canSkip = false;
        if (sb.length() > c.length() && sb.toString().contains(c)) canSkip = true;
        if (!canSkip) {
            boolean flag = false;
            for (int i = Math.min(sb.length(), c.length()); i > 0; i--) {
                String s = sb.toString().substring(sb.length() - i), t = c.substring(0, i);
                if (s.equals(t)) {
                    flag = true;
                    sb.append(c.substring(i));
                    break;
                }
            }
            if (!flag) sb.append(c);
        }
        return sb.toString();
    }
}