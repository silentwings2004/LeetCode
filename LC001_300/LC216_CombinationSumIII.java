package LC001_300;
import java.util.*;
public class LC216_CombinationSumIII {
    /**
     * Find all valid combinations of k numbers that sum up to n such that the following conditions are true:
     *
     * Only numbers 1 through 9 are used.
     * Each number is used at most once.
     * Return a list of all possible valid combinations. The list must not contain the same combination twice, and the
     * combinations may be returned in any order.
     *
     * Input: k = 3, n = 7
     * Output: [[1,2,4]]
     *
     * Constraints:
     *
     * 2 <= k <= 9
     * 1 <= n <= 60
     * @param k
     * @param n
     * @return
     */
    // time = O(C(9,k)) * k), space = O(k)
    List<List<Integer>> res;
    List<Integer> path;
    public List<List<Integer>> combinationSum3(int k, int n) {
        res = new ArrayList<>();
        path = new ArrayList<>();
        dfs(1, k, n);
        return res;
    }

    private void dfs(int u, int k, int n) {
        if (n == 0) {
            if (k == 0) res.add(new ArrayList<>(path));
            return;
        }
        if (k == 0) return;

        for (int i = u; i <= 9; i++) {
            path.add(i);
            dfs(i + 1, k - 1, n - i);
            path.remove(path.size() - 1);
        }
    }
}
