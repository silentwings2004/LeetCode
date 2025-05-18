package LC601_900;
import java.util.*;
public class LC709_ToLowerCase {
    /**
     * Given a string s, return the string after replacing every uppercase letter with the same lowercase letter.
     *
     * Input: s = "Hello"
     * Output: "hello"
     *
     * Constraints:
     *
     * 1 <= s.length <= 100
     * s consists of printable ASCII characters.
     * @param s
     * @return
     */
    // time = O(n), space = O(n)
    public String toLowerCase(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) sb.append(Character.toLowerCase(c));
        return sb.toString();
    }
}