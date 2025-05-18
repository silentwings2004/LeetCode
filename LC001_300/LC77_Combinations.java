package LC001_300;
import java.util.*;
public class LC77_Combinations {
    /**
     * Given two integers n and k, return all possible combinations of k numbers out of the range [1, n].
     *
     * You may return the answer in any order.
     *
     * Input: n = 4, k = 2
     * Output:
     * [
     *   [2,4],
     *   [3,4],
     *   [2,3],
     *   [1,2],
     *   [1,3],
     *   [1,4],
     * ]
     *
     * Constraints:
     *
     * 1 <= n <= 20
     * 1 <= k <= n
     * @param n
     * @param k
     * @return
     */
    // time = O(k * C(n,k)), space = O(C(n,k))
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(n, k, 1, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int n, int k, int idx, List<Integer> path, List<List<Integer>> res) {
        // base case - success
        if (k == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = idx; i <= n; i++) {
            path.add(i);
            dfs(n, k - 1, i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }

    // S2: Math + Gosper's Hack
    // time = O(2^k * k), space = O(k)
    public List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();

        int state = (1 << k) - 1;
        while (state < (1 << n)) {
            List<Integer> path = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if ((state >> i & 1) == 1) path.add(i + 1);
            }
            res.add(path);

            int c = state & -state;
            int r = state + c;
            state = (((r ^ state) >> 2) / c) | r;
        }
        return res;
    }
}
/**
 * 人为规定一个顺序，从头往后选即可
 */