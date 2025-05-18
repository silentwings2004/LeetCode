package LC901_1200;
import java.util.*;
public class LC1006_ClumsyFactorial {
    /**
     * The factorial of a positive integer n is the product of all positive integers less than or equal to n.
     *
     * For example, factorial(10) = 10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1.
     * We make a clumsy factorial using the integers in decreasing order by swapping out the multiply operations for a
     * fixed rotation of operations with multiply '*', divide '/', add '+', and subtract '-' in this order.
     *
     * For example, clumsy(10) = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1.
     * However, these operations are still applied using the usual order of operations of arithmetic. We do all
     * multiplication and division steps before any addition or subtraction steps, and multiplication and division
     * steps are processed left to right.
     *
     * Additionally, the division that we use is floor division such that 10 * 9 / 8 = 90 / 8 = 11.
     *
     * Given an integer n, return the clumsy factorial of n.
     *
     * Input: n = 4
     * Output: 7
     *
     * Input: n = 10
     * Output: 12
     *
     * Constraints:
     *
     * 1 <= n <= 10^4
     * @param n
     * @return
     */
    // S1: 表达式求值
    // time = O(n), space = O(n)
    Stack<Integer> num;
    Stack<Character> op;
    public int clumsy(int n) {
        char[] op = new char[]{'*', '/', '+', '-'};
        StringBuilder sb = new StringBuilder();
        for (int i = n, j = 0; i > 0; i--, j++) {
            sb.append(i);
            if (i > 1) sb.append(op[j % 4]);
        }
        return calc(sb.toString());
    }

    private int calc(String s) {
        num = new Stack<>();
        op = new Stack<>();

        int n = s.length();
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('+', 1);
        map.put('-', 1);
        map.put('*', 2);
        map.put('/', 2);

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int j = i;
                while (j < n && Character.isDigit(s.charAt(j))) j++;
                int val = Integer.parseInt(s.substring(i, j));
                num.push(val);
                i = j - 1;
            } else {
                while (!op.isEmpty() && map.get(op.peek()) >= map.get(c)) eval();
                op.push(c);
            }
        }
        while (!op.isEmpty()) eval();
        return num.peek();
    }

    private void eval() {
        int b = num.pop();
        int a = num.pop();
        char c = op.pop();
        int res = 0;
        if (c == '+') res = a + b;
        else if (c == '-') res = a - b;
        else if (c == '*') res = a * b;
        else res = a / b;
        num.push(res);
    }

    // S2: Math
    // time = O(1), space = O(1)
    public int clumsy2(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (n == 3) return 6;
        if (n == 4) return 7;
        if (n % 4 == 0) return n + 1;
        if (n % 4 == 1) return n + 2;
        if (n % 4 == 2) return n + 2;
        return n - 1;
    }
}