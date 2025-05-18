package LC2401_2700;
import java.util.*;
public class LC2434_UsingaRobottoPrinttheLexicographicallySmallestString {
    /**
     * You are given a string s and a robot that currently holds an empty string t. Apply one of the following
     * operations until s and t are both empty:
     *
     * Remove the first character of a string s and give it to the robot. The robot will append this character to the
     * string t.
     * Remove the last character of a string t and give it to the robot. The robot will write this character on paper.
     * Return the lexicographically smallest string that can be written on the paper.
     *
     * Input: s = "zza"
     * Output: "azz"
     *
     * Input: s = "bac"
     * Output: "abc"
     *
     * Input: s = "bdda"
     * Output: "addb"
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s consists of only English lowercase letters.
     * @param s
     * @return
     */
    // S1: stack
    // time = O(n), space = O(n)
    public String robotWithString(String s) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> stk = new Stack<>();
        int[] pos = new int[26];
        Arrays.fill(pos, -1);
        int n = s.length();
        for (int i = 0; i < n; i++) pos[s.charAt(i) - 'a'] = i;

        for (int i = 0, k = 0; i < 26 && k < n; i++) {
            char c = (char)(i + 'a');
            while (!stk.isEmpty() && stk.peek() <= c) sb.append(stk.pop());
            while (k <= pos[i]) {
                if (s.charAt(k) == c) sb.append(c);
                else stk.push(s.charAt(k));
                k++;
            }
        }
        while (!stk.isEmpty()) sb.append(stk.pop());
        return sb.toString();
    }

    // S2: stack
    // time = O(n), space = O(n)
    public String robotWithString2(String s) {
        int n = s.length();
        Stack<Character> stk = new Stack<>();
        char[] nextSmaller = new char[n];
        char cur = s.charAt(n - 1);
        for (int i = n - 1; i >= 0; i--) {
            cur = (char) Math.min(cur, s.charAt(i));
            nextSmaller[i] = cur;
        }

        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < n) {
            if (stk.isEmpty() || nextSmaller[i] < stk.peek()) stk.push(s.charAt(i++));
            else sb.append(stk.pop());
        }
        while (!stk.isEmpty()) sb.append(stk.pop());
        return sb.toString();
    }
}
/**
 * t本质上就是一个栈
 * 借助于栈能够得到的最小字典序是多少
 * 10^5 => 贪心 or 单调栈
 * 最多只有一共26种字母 => 先把所有的a先输出
 * 按照字母来枚举
 * 先把最后一个a之前的所有字母操作到t之中
 * 将后面所有的b输出，以及t的末尾有没有b，将b输出，然后再去看c
 */
