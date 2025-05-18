package LC001_300;
import java.util.*;
public class LC241_DifferentWaystoAddParentheses {
    /**
     * Given a string expression of numbers and operators, return all possible results from computing all the different
     * possible ways to group numbers and operators. You may return the answer in any order.
     *
     * Input: expression = "2-1-1"
     * Output: [0,2]
     *
     * Constraints:
     *
     * 1 <= expression.length <= 20
     * expression consists of digits and the operator '+', '-', and '*'.
     * @param expression
     * @return
     */
    // S1: dfs
    // time = O(2^n), space = O(2^n)
    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> res = new ArrayList<>();
        int n = expression.length();
        for (int i = 0; i < n; i++) {
            char c = expression.charAt(i);
            if (c == '+' || c == '-' || c == '*') { // 枚举表达式树的根节点
                List<Integer> left = diffWaysToCompute(expression.substring(0, i));
                List<Integer> right = diffWaysToCompute(expression.substring(i + 1));
                for (int x : left) {
                    for (int y : right) {
                        if (c == '+') res.add(x + y);
                        else if (c == '-') res.add(x - y);
                        else res.add(x * y);
                    }
                }
            }
        }
        if (res.size() == 0) res.add(Integer.valueOf(expression));
        return res;
    }

    // S2
    // time = O(2^n), space = O(2^n)
    List<String> expr;
    public List<Integer> diffWaysToCompute2(String expression) {
        expr = new ArrayList<>();
        int n = expression.length();
        for (int i = 0; i < n; i++) {
            if (Character.isDigit(expression.charAt(i))) {
                int j = i, x = 0;
                while (j < n && Character.isDigit(expression.charAt(j))) x = x * 10 + (expression.charAt(j++) - '0');
                i = j - 1;
                expr.add(String.valueOf(x));
            } else expr.add(expression.substring(i, i + 1));
        }
        return dfs(0, expr.size() - 1);
    }

    private List<Integer> dfs(int l, int r) {
        if (l == r) return Arrays.asList(Integer.parseInt(expr.get(r)));

        List<Integer> res = new ArrayList<>();
        for (int i = l + 1; i < r; i += 2) {
            List<Integer> left = dfs(l, i - 1), right = dfs(i + 1, r);
            for (int x : left) {
                for (int y : right) {
                    int z = 0;
                    if (expr.get(i).equals("+")) z = x + y;
                    else if (expr.get(i).equals("-")) z = x - y;
                    else z = x * y;
                    res.add(z);
                }
            }
        }
        return res;
    }
}
/**
 * ref: LC894 for time and space complexity, actually the same problem in essence
 * 遍历最后一步的运算是在哪一位发生
 * (x x x x) - (x x x x x)
 * {3 4 2 1} - {2 4 3 1 2 4 5}
 * (x ) + (x x x)
 * (x x) * (x x x)
 * (x x x) - x
 *
 * (A B x) x => (A B) x
 * (A B) (x x) => (A B)
 *  0 1 2 3   4 5 6 7 8
 * (x x x x) (x x x x x)
 *   * - +  -  * * + -
 */
