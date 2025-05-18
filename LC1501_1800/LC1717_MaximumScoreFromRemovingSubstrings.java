package LC1501_1800;
import java.util.*;
public class LC1717_MaximumScoreFromRemovingSubstrings {
    /**
     * You are given a string s and two integers x and y. You can perform two types of operations any number of times.
     *
     * Remove substring "ab" and gain x points.
     * For example, when removing "ab" from "cabxbae" it becomes "cxbae".
     * Remove substring "ba" and gain y points.
     * For example, when removing "ba" from "cabxbae" it becomes "cabxe".
     * Return the maximum points you can gain after applying the above operations on s.
     *
     * Input: s = "cdbcbbaaabab", x = 4, y = 5
     * Output: 19
     *
     * Input: s = "aabbaaxybbaabb", x = 5, y = 4
     * Output: 20
     *
     * Constraints:
     *
     * 1 <= s.length <= 105
     * 1 <= x, y <= 104
     * s consists of lowercase English letters.
     *
     * @param s
     * @param x
     * @param y
     * @return
     */
    // S1: StringBuilder
    // time = O(n), space = O(n)
    public int maximumGain(String s, int x, int y) {
        if (x < y) {
            s = reverse(s);
            int t = x;
            x = y;
            y = t;
        }

        int n = s.length();
        char[] stk = new char[n + 1];
        int tt = 0, res = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'b' && tt > 0 && stk[tt] == 'a') {
                res += x;
                tt--;
                continue;
            }
            stk[++tt] = s.charAt(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= tt; i++) sb.append(stk[i]);
        s = sb.toString();
        n = s.length();
        stk = new char[n + 1];
        tt = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'a' && tt > 0 && stk[tt] == 'b') {
                res += y;
                tt--;
                continue;
            }
            stk[++tt] = s.charAt(i);
        }
        return res;
    }

    private String reverse(String s) {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }

    // S2: Two Stacks
    // time = O(n), space = O(n)
    public int maximumGain2(String s, int x, int y) {
        // corner case
        if (s == null || s.length() == 0) return 0;

        Stack<Character> stack1 = new Stack<>();
        Stack<Character> stack2 = new Stack<>();
        int res = 0;
        int max = Math.max(x, y), min = Math.min(x, y);
        char first = x > y ? 'a' : 'b', second = x > y ? 'b' : 'a'; // if x > y -> "ab"; y > x -> "ba"

        for (char c : s.toCharArray()) {
            if (!stack1.isEmpty() && stack1.peek() == first && c == second) {
                stack1.pop();
                res += max;
            } else stack1.push(c);
        }

        while (!stack1.isEmpty()) {
            char c = stack1.pop();
            if (!stack2.isEmpty() && stack2.peek() == first && c == second) {
                stack2.pop();
                res += min;
            } else stack2.push(c);
        }
        return res;
    }
}

/**
 * x ababbabaa y ababab x -> 删到最后肯定只有要么都是a，要么都是b
 * aaaaaaaaa / bbbbbbbb
 * 完全取决于a的个数与b的个数之差 -> m个a，n个b -> m - n -> 删除k次是一定的 -> 优先删除值较大的那个组合 -> bbbaaaaa -> aa
 * 朴素的贪心思想
 * 2 pass -> 第一遍删ab，第二遍删ba -> 简单粗暴一些
 */

