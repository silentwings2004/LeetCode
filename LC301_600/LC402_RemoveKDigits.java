package LC301_600;
import java.util.*;
public class LC402_RemoveKDigits {
    /**
     * Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer
     * after removing k digits from num.
     *
     * Input: num = "1432219", k = 3
     * Output: "1219"
     *
     * Constraints:
     *
     * 1 <= k <= num.length <= 10^5
     * num consists of only digits.
     * num does not have any leading zeros except for the zero itself.
     * @param num
     * @param k
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public String removeKdigits(String num, int k) {
        // corner case
        if (num == null || num.length() == 0 || k < 0) return "";

        Stack<Integer> stack = new Stack<>();
        int count = 0;

        for (char c : num.toCharArray()) {
            int digit = c - '0';
            if (stack.isEmpty() || stack.peek() <= digit) stack.push(digit);
            else {
                while (!stack.isEmpty() && stack.peek() > digit && count < k) {  // "112", k = 1 -> "11", 所以这里是 >，不是>=
                    stack.pop();
                    count++;
                }
                stack.push(digit);
            }
        }
        for (int i = 0; i < k - count; i++) stack.pop();

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) sb.append(stack.pop());

        int i = sb.length() - 1;
        while (i > 0 && sb.charAt(i) == '0') i--;
        sb.setLength(i + 1);
        return sb.length() == 0 ? "0" : sb.reverse().toString();
    }

    // S2
    // time = O(n), space = O(n)
    public String removeKdigits2(String num, int k) {
        int n = num.length();
        StringBuilder sb = new StringBuilder();
        for (char c : num.toCharArray()) {
            while (k > 0 && sb.length() > 0 && sb.charAt(sb.length() - 1) > c) {
                k--;
                sb.setLength(sb.length() - 1);
            }
            sb.append(c);
        }

        while (k-- > 0) sb.setLength(sb.length() - 1);
        k = 0;
        while (k < sb.length() && sb.charAt(k) == '0') k++;
        if (k == sb.length()) sb.append(0);
        return sb.toString().substring(k);
    }

    // S3: stack
    // time = O(n), space = O(n)
    final int N = 100010;
    public String removeKdigits3(String num, int k) {
        char[] stk = new char[N];
        int tt = 0;
        int n = num.length();

        for (char c : num.toCharArray()) {
            while (k > 0 && tt > 0 && stk[tt] > c) {
                k--;
                tt--;
            }
            stk[++tt] = c;
        }

        while (k-- > 0) tt--;
        int hh = 1;
        while (hh <= tt && stk[hh] == '0') hh++;
        if (hh > tt) stk[++tt] = '0';
        return new String(stk, hh, tt - hh + 1);
    }
}
/**
 * 1 4 3 -> 把4扔掉，破坏了递增序列这个性质
 * 名额没有用完，一路走到底，那就把最后几个删掉
 * 暴搜
 * 1. Si-1 > Si => 必删Si-1
 * 2. Si-1 < Si => 不能删Si-1
 * 3. Si-1 = Si => 不删Si-1，选择机会留给后面
 * 每次的决策都是唯一确定的
 * 得到的一定是递增的，还有剩余就删最后的即可
 */