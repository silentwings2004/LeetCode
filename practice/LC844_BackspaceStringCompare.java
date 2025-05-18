package practice;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: BackspaceStringCompare
 * Creater: Silentwings
 * Date: Apr, 2020
 * Description: 844. Backspace String Compare
 */
public class LC844_BackspaceStringCompare {
    /**
     * Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a
     * backspace character.
     *
     * Example 1:
     *
     * Input: S = "ab#c", T = "ad#c"
     * Output: true
     * Explanation: Both S and T become "ac".
     * Example 2:
     *
     * Input: S = "ab##", T = "c#d#"
     * Output: true
     * Explanation: Both S and T become "".
     * Example 3:
     *
     * Input: S = "a##c", T = "#a#c"
     * Output: true
     * Explanation: Both S and T become "c".
     * Example 4:
     *
     * Input: S = "a#c", T = "b"
     * Output: false
     * Explanation: S becomes "c" while T becomes "b".
     * Note:
     *
     * 1 <= S.length <= 200
     * 1 <= T.length <= 200
     * S and T only contain lowercase letters and '#' characters.
     * Follow up:
     *
     * Can you solve it in O(N) time and O(1) space?
     * @param S
     * @param T
     * @return
     */
    // S1: Stack
    // time = O(m + n), space = O(m + n)
    public boolean backspaceCompare(String S, String T) {
        return helper(S).equals(helper(T));
    }

    private String helper(String str) {
        char[] chars = str.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : chars) {
            if (c != '#') {
                stack.push(c);
            } else if (!stack.isEmpty()) {
                stack.pop();
            }
        }
        return String.valueOf(stack);
    }

    // S2: 逆序遍历 --> 双指针
    // time = O(m + n), space = O(1)
    public boolean backspaceCompare2(String S, String T) {
        int sp = S.length() - 1;
        int tp = T.length() - 1;

        while (sp >= 0 || tp >= 0) {
            sp = backTraverse(sp, S);
            tp = backTraverse(tp, T);
            if (sp >= 0 && tp >= 0 && S.charAt(sp) != T.charAt(tp)) return false; // 字符不匹配
            if ((sp >= 0) != (tp >= 0)) return false; // 两字符串长度不等
            sp--;
            tp--;
        }
        return true;
    }

    private int backTraverse(int pointer, String str) {
        int skip = 0; // 记录skip的字符数
        while (pointer >= 0) {
            if (str.charAt(pointer) == '#') {
                pointer--;   // 遇到'#'指针后移一位，同时意味着skip的字符要多1个
                skip++;
            } else if (skip > 0) {  // 如果遇到的不是'#'，但是记录的要skip的字符数不为0，那么意味着当前字符要skip掉，即继续后移1位
                pointer--;
                skip--;
            } else break; // 遇到的不是'#'且skip的字符数已经为0，那就表明当前所指字符为有效字符，直接跳出返回当前的指针，
                          // 进入主函数进行对比
        }
        return pointer;
     }
}
