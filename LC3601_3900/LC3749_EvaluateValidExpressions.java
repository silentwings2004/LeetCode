package LC3601_3900;

public class LC3749_EvaluateValidExpressions {
    /**
     * You are given a string expression that represents a nested mathematical expression in a simplified form.
     *
     * A valid expression is either an integer literal or follows the format op(a,b), where:
     *
     * op is one of "add", "sub", "mul", or "div".
     * a and b are each valid expressions.
     * The operations are defined as follows:
     *
     * add(a,b) = a + b
     * sub(a,b) = a - b
     * mul(a,b) = a * b
     * div(a,b) = a / b
     * Return an integer representing the result after fully evaluating the expression.
     *
     * Input: expression = "add(2,3)"
     * Output: 5
     *
     * Input: expression = "-42"
     * Output: -42
     *
     * Input: expression = "div(mul(4,sub(9,5)),add(1,1))"
     * Output: 8
     *
     * Constraints:
     *
     * 1 <= expression.length <= 10^5
     * expression is valid and consists of digits, commas, parentheses, the minus sign '-', and the lowercase strings
     * "add", "sub", "mul", "div".
     * All intermediate results fit within the range of a long integer.
     * All divisions result in integer values.
     * @param expression
     * @return
     */
    // time = O(n), space = O(n)
    String s;
    int idx;
    public long evaluateExpression(String expression) {
        s = expression;
        idx = 0;
        return dfs();
    }

    private long dfs() {
        if (s.charAt(idx) == '-' || Character.isDigit(s.charAt(idx))) return eval();
        char c = s.charAt(idx);
        idx += 3; // skip "add", "sub", "mul" or "div"
        idx++; // skip '('
        long l = dfs();
        idx++; // skip ','
        long r = dfs();
        idx++; // skip ')'
        long res = 0;
        if (c == 'a') res = l + r;
        else if (c == 's') res = l - r;
        else if (c == 'm') res = l * r;
        else res = l / r;
        return res;
    }

    private long eval() {
        int sign = 1;
        if (s.charAt(idx) == '-') {
            sign = -1;
            idx++;
        }
        long v = 0;
        int n = s.length();
        while (idx < n && Character.isDigit(s.charAt(idx))) {
            v = v * 10 + (s.charAt(idx) - '0');
            idx++;
        }
        return v * sign;
    }
}