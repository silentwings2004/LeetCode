package LC001_300;
import java.util.*;
public class LC150_EvaluateReversePolishNotation {
    /**
     * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
     *
     * Valid operators are +, -, *, and /. Each operand may be an integer or another expression.
     *
     * Note that division between two integers should truncate toward zero.
     *
     * It is guaranteed that the given RPN expression is always valid. That means the expression would always evaluate
     * to a result, and there will not be any division by zero operation.
     *
     * Input: tokens = ["2","1","+","3","*"]
     * Output: 9
     *
     * Constraints:
     *
     * 1 <= tokens.length <= 10^4
     * tokens[i] is either an operator: "+", "-", "*", or "/", or an integer in the range [-200, 200].
     * @param tokens
     * @return
     */
    // time = O(n), space = O(n)
    public int evalRPN(String[] tokens) {
        Stack<Integer> stk = new Stack<>();
        for (String s : tokens) {
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
                int b = stk.pop();
                int a = stk.pop();
                if (s.equals("+")) a += b;
                else if (s.equals("-")) a -= b;
                else if (s.equals("*")) a *= b;
                else a /= b;
                stk.push(a);
            } else stk.push(Integer.parseInt(s));
        }
        return stk.peek();
    }
}