package LC601_900;
import java.util.*;
public class LC722_RemoveComments {
    /**
     * Given a C++ program, remove comments from it. The program source is an array of strings source where source[i]
     * is the ith line of the source code. This represents the result of splitting the original source code string by
     * the newline character '\n'.
     *
     * If a certain line of code is empty after removing comments, you must not output that line: each string in the
     * answer list will be non-empty.
     *
     * There will be no control characters, single quote, or double quote characters.
     *
     * Also, nothing else such as defines or macros will interfere with the comments.
     *
     * It is guaranteed that every open block comment will eventually be closed, so "/*" outside of a line or block
     * comment always starts a new comment.
     *
     * Finally, implicit newline characters can be deleted by block comments. Please see the examples below for details.
     *
     * After removing the comments from the source code, return the source code in the same format.
     *
     * Constraints:
     *
     * 1 <= source.length <= 100
     * 0 <= source[i].length <= 80
     * source[i] consists of printable ASCII characters.
     * Every open block comment is eventually closed.
     * There are no single-quote or double-quote in the input.
     * @param source
     * @return
     */
    // time = O(n), space = O(n)
    public List<String> removeComments(String[] source) {
        StringBuilder sb = new StringBuilder();
        for (String s : source) sb.append(s).append('\n');
        String str = sb.toString();
        sb = new StringBuilder();
        List<String> res = new ArrayList<>();
        int n = str.length();

        for (int i = 0; i < n;) {
            if (i + 1 < n && str.charAt(i) == '/' && str.charAt(i + 1) == '/') {
                while (str.charAt(i) != '\n') i++;
            } else if (i + 1 < n && str.charAt(i) == '/' && str.charAt(i + 1) == '*') {
                i += 2;
                while (str.charAt(i) != '*' || str.charAt(i + 1) != '/') i++;
                i += 2;
            } else if (str.charAt(i) == '\n') {
                if (sb.length() > 0) {
                    res.add(sb.toString());
                    sb = new StringBuilder();
                }
                i++;
            } else sb.append(str.charAt(i++));
        }
        return res;
    }
}