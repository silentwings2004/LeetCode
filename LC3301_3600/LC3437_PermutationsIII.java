package LC3301_3600;
import java.util.*;
public class LC3437_PermutationsIII {
    /**
     * Given an integer n, an alternating permutation is a permutation of the first n positive integers such that no
     * two adjacent elements are both odd or both even.
     *
     * Return all such alternating permutations sorted in lexicographical order.
     * Input: n = 4
     *
     * Output: [[1,2,3,4],[1,4,3,2],[2,1,4,3],[2,3,4,1],[3,2,1,4],[3,4,1,2],[4,1,2,3],[4,3,2,1]]
     * Input: n = 2
     *
     * Output: [[1,2],[2,1]]
     * Input: n = 3
     *
     * Output: [[1,2,3],[3,2,1]]
     *
     * Constraints:
     *
     * 1 <= n <= 10
     * @param n
     * @return
     */
    // time = O(2^n * n), space = O(n)
    public int[][] permute(int n) {
        List<int[]> res = new ArrayList<>();
        int[] path = new int[n];
        dfs(res, path, 0, -1);
        int[][] ans = new int[res.size()][n];
        for (int i = 0; i < res.size(); i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = res.get(i)[j];
            }
        }
        return ans;
    }

    private void dfs(List<int[]> res, int[] path, int state, int last) {
        int n = path.length;
        if (state == (1 << n) - 1) {
            res.add(path.clone());
            return;
        }

        int u = Integer.bitCount(state);
        for (int i = 0; i < n; i++) {
            if ((state >> i & 1) == 1) continue;
            if (last != -1 && (i + 1) % 2 == last % 2) continue;
            state |= 1 << i;
            path[u] = i + 1;
            dfs(res, path, state, i + 1);
            state ^= 1 << i;
        }
    }
}