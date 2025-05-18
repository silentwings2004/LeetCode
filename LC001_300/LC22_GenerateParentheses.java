package LC001_300;
import java.util.*;
public class LC22_GenerateParentheses {
    /**
     * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
     *
     * Input: n = 3
     * Output: ["((()))","(()())","(())()","()(())","()()()"]
     *
     * Constraints:
     *
     * 1 <= n <= 8
     * @param n
     * @return
     */
    // S1
    // time = O(n * 4^n), space = O(n)
    List<String> res;
    int n;
    public List<String> generateParenthesis(int n) {
        res = new ArrayList<>();
        this.n = n;

        dfs(0, 0, new StringBuilder());
        return res;
    }

    private void dfs(int u, int delta, StringBuilder sb) {
        if (u == 2 * n) {
            if (delta == 0) res.add(sb.toString());
            return;
        }

        if (delta < 0) return;

        // "("
        sb.append('(');
        dfs(u + 1, delta + 1, sb);
        sb.setLength(sb.length() - 1);

        // ')'
        sb.append(')');
        dfs(u + 1, delta - 1, sb);
        sb.setLength(sb.length() - 1);
    }

    // S2: state compression + Ghosper's hack
    public List<String> generateParenthesis2(int n) {
        List<String> res = new ArrayList<>();
        int k = n;
        n *= 2;
        int state = (1 << k) - 1;
        while (state < (1 << n)) {
            // do something
            int delta = 0;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if ((state >> i & 1) == 1) {
                    sb.append('(');
                    delta++;
                }
                else {
                    sb.append(")");
                    delta--;
                    if (delta < 0) break;
                }
            }
            if (delta == 0) res.add(sb.toString());

            int c = state & -state;
            int r = state + c;
            state = (((r ^ state) >> 2) / c) | r;
        }
        return res;
    }

    // S3: dfs
    // time = O(C(2n, n)), space = O(n)
    List<String> ans;
    public List<String> generateParenthesis3(int n) {
        ans = new ArrayList<>();
        dfs(n, 0, 0, "");
        return ans;
    }

    private void dfs(int n, int lc, int rc, String path) {
        if (lc == n && rc == n) ans.add(path);
        else {
            if (lc < n) dfs(n, lc + 1, rc, path + "(");
            if (rc < n && rc < lc) dfs(n, lc, rc + 1, path + ")");
        }
    }
}
/**
 * 1. 任意前缀中, "(" >= ")" 数量
 * 2. 左右括号数量相等
 * 如果只要求数量的话，是个卡特兰数 = C(m,n) / (n + 1)
 * ref: AC889
 */