package LC1501_1800;
import java.util.*;
public class LC1544_MakeTheStringGreat {
    /**
     * Given a string s of lower and upper case English letters.
     *
     * A good string is a string which doesn't have two adjacent characters s[i] and s[i + 1] where:
     *
     * 0 <= i <= s.length - 2
     * s[i] is a lower-case letter and s[i + 1] is the same letter but in upper-case or vice-versa.
     * To make the string good, you can choose two adjacent characters that make the string bad and remove them. You
     * can keep doing this until the string becomes good.
     *
     * Return the string after making it good. The answer is guaranteed to be unique under the given constraints.
     *
     * Notice that an empty string is also good.
     *
     * Input: s = "leEeetcode"
     * Output: "leetcode"
     *
     * Input: s = "abBAcC"
     * Output: ""
     *
     * Input: s = "s"
     * Output: "s"
     *
     * Constraints:
     *
     * 1 <= s.length <= 100
     * s contains only lower and upper case English letters.
     * @param s
     * @return
     */
    // time = O(n), space = O(n)
    public String makeGood(String s) {
        Stack<Character> stk = new Stack<>();
        for (char c : s.toCharArray()) {
            if (!stk.isEmpty() && Math.abs(c - stk.peek()) == 32) stk.pop();
            else stk.push(c);
        }
        StringBuilder sb = new StringBuilder();
        while (!stk.isEmpty()) sb.append(stk.pop());
        return sb.reverse().toString();
    }
}
