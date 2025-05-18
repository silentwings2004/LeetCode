package practice;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: GenerateParentheses
 * Creater: Silentwings
 * Date: Apr, 2020
 * Description: 22. Generate Parentheses
 */
public class LC22_GenerateParentheses {
    /**
     * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
     *
     * For example, given n = 3, a solution set is:
     *
     * [
     *   "((()))",
     *   "(()())",
     *   "(())()",
     *   "()(())",
     *   "()()()"
     * ]
     * @param n
     * @return
     */
    // time = O(2^2n * n) = O(4^n * n) 对于2^2n个序列中的每一个，我们用于建立和验证该序列的复杂度为 O(n)。
    // space = O(2n) = O(n) if 不考虑res list消耗不考虑 最大深度就是这棵树的高度2n(由于有backtracking，它不会一直append)
    // 对于时间复杂度而言，如果分析出来是指数级及以上，则表示计算机已经根本算不出来了，具体是多少已经doesn't matter了
    public List<String> generateParenthesis(int n) { // dfs要搞一个helper函数 --> 先写helper函数
        // corner case
        if (n <= 0) return null;
        List<String> res = new ArrayList<>();
        dfs(res, n, 0, new StringBuilder());
        return res;
    }

    private void dfs(List<String> res, int n, int delta, StringBuilder path) {
        // base case - success
        if (path.length() == 2 * n && delta == 0) {
            res.add(path.toString());
            return;
        }
        // base case - fail
        if (delta < 0 || path.length() >= 2 * n) { // path.length() == 2 * n且delta > 0也不是valid，所以这里要写>=
            return;
        }
        // string长度一直在增加，不可能会减小，所以不可能成环。同时只会不断一层一层树状发散，而不会出现汇聚 ==> 不需要查重

        // 分叉 --> for loop  两叉 拆开写就行
        // '('
        int len = path.length();
        path.append('(');
        dfs(res, n, delta + 1, path);
        path.setLength(len); // StringBuilder通过setLength来做backtracking
        path.append(')');
        dfs(res, n, delta - 1, path);
        path.setLength(len);
    }
}
