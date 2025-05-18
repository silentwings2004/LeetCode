package practice;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: RemoveKDigits
 * Creater: Silentwings
 * Date: May, 2020
 * Description: 402. Remove K Digits
 */
public class LC402_RemoveKDigits {
    /**
     * Given a non-negative integer num represented as a string, remove k digits from the number so that the new number
     * is the smallest possible.
     *
     * Note:
     * The length of num is less than 10002 and will be ≥ k.
     * The given num does not contain any leading zero.
     * Example 1:
     *
     * Input: num = "1432219", k = 3
     * Output: "1219"
     * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
     *
     * Example 2:
     *
     * Input: num = "10200", k = 1
     * Output: "200"
     * Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
     *
     * Example 3:
     *
     * Input: num = "10", k = 2
     * Output: "0"
     * Explanation: Remove all the digits from the number and it is left with nothing which is 0.
     *
     * 1 2 3 4 5 2 6 4, k = 4 ==> 1 2 (3) (4) (5) 2 --> 1 2 2 --> 1 2 2 6 --> 1 2 2 (6) 4 --> 1 2 2 4
     *
     * @param num
     * @param k
     * @return
     */
    // S1: Stack
    // time = O(n), space = O(n)
    public String removeKdigits(String num, int k) {
        // corner case
        if (num == null || num.length() == 0 || num.length() < k) return "0";
        if (k <= 0) return num;

        Stack<Character> stack = new Stack<>();
        char[] chars = num.toCharArray();
        stack.push(chars[0]);
        int count = 0;
        StringBuilder sb = new StringBuilder();
        int res = 0;

        for (int i = 1; i < chars.length; i++) {
            while (!stack.isEmpty() && chars[i] < stack.peek() && count < k) {
                stack.pop();
                count++;
            }
            stack.push(chars[i]);
        }

        if (count < k) {
            if (num.length() > k) {
                int diff = k - count;
                while (diff > 0 && !stack.isEmpty()) {
                    stack.pop();
                    diff--;
                }
            } else return "0";
        }
        while (!stack.isEmpty()) sb.append(stack.pop());
        sb.reverse();
        char[] ch = sb.toString().toCharArray();
        int mark = -1;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] != '0') {
                mark = i;
                break;
            }
        }
        return mark == -1 ? "0" : String.valueOf(ch).substring(mark, ch.length);
    }

    // S2: Use StringBuilder as Stack <-- Stack (部分排序) by 算法哥
    public String removeKdigits2(String num, int k) {
        // corner case
        if (num == null || num.length() < k) return "0";

        int len = num.length();
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < len; i++) {
            char c = num.charAt(i);
            while (sb.length() > 0 && sb.charAt(sb.length() - 1) > c && count < k) {
                sb.setLength(sb.length() - 1);
                count++;
            }
            // remove leading zeros
            if (sb.length() > 0 || c != '0') sb.append(c);
        }
        while (sb.length() > 0 && count++ < k) sb.setLength(sb.length() - 1);
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
