package LC301_600;
import java.util.*;
public class LC394_DecodeString {
    /**
     * Given an encoded string, return its decoded string.
     *
     * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated
     * exactly k times. Note that k is guaranteed to be a positive integer.
     *
     * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
     *
     * Furthermore, you may assume that the original data does not contain any digits and that digits are only for
     * those repeat numbers, k. For example, there won't be input like 3a or 2[4].
     *
     * Input: s = "3[a]2[bc]"
     * Output: "aaabcbc"
     *
     * Constraints:
     *
     * 1 <= s.length <= 30
     * s consists of lowercase English letters, digits, and square brackets '[]'.
     * s is guaranteed to be a valid input.
     * All the integers in s are in the range [1, 300].
     * @param s
     * @return
     */
    // S1: two stacks
    // time = O(n), space = O(n)
    public String decodeString(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        strStack.push(new StringBuilder());

        int val = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) val = val * 10 + c - '0';
            else if (c == '[') {
                numStack.push(val);
                val = 0;
                strStack.push(new StringBuilder());
            }  else if (c == ']') {
                int k = numStack.pop();
                String str = strStack.pop().toString();
                StringBuilder sb = new StringBuilder();
                while (k-- > 0) sb.append(str);
                strStack.peek().append(sb);
            } else strStack.peek().append(c);
        }
        return strStack.peek().toString();
    }

    // S2: dfs
    // time = O(maxK * n), space = O(n)
    int u = 0;
    public String decodeString2(String s) {
        return dfs(s);
    }

    private String dfs(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        while (u < n && s.charAt(u) != ']') {
            if (Character.isLowerCase(s.charAt(u))) sb.append(s.charAt(u++));
            else if (Character.isDigit(s.charAt(u))) {
                int k = u;
                while (k < n && Character.isDigit(s.charAt(k))) k++;
                int x = Integer.valueOf(s.substring(u, k));
                u = k + 1;
                String y = dfs(s);
                while (x-- > 0) sb.append(y);
                u++; // 过滤掉右括号
            }
        }
        return sb.toString();
    }

    // S3: stack
    // time = O(n), space = O(n)
    public String decodeString3(String s) {
        Stack<String> stk = new Stack<>();
        String res = "";
        for (char c : s.toCharArray()) {
            if (c != '[' && c != ']') res += c;
            else if (c == '[') {
                stk.push(res);
                res = "";
            } else {
                String t = stk.pop();
                res = eval(t, res);
            }
        }
        return res;
    }

    private String eval(String s, String t) {
        int i = s.length() - 1;
        while (i >= 0 && Character.isDigit(s.charAt(i))) i--;
        int val = Integer.parseInt(s.substring(i + 1));
        return s.substring(0, i + 1) + t.repeat(val);
    }
}