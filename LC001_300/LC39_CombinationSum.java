package LC001_300;
import java.util.*;
public class LC39_CombinationSum {
    /**
     * Given an array of distinct integers candidates and a target integer target, return a list of all unique
     * combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
     *
     * The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the
     * frequency of at least one of the chosen numbers is different.
     *
     * It is guaranteed that the number of unique combinations that sum up to target is less than 150 combinations for
     * the given input.
     *
     * Input: candidates = [2,3,6,7], target = 7
     * Output: [[2,2,3],[7]]
     *
     * Constraints:
     *
     * 1 <= candidates.length <= 30
     * 1 <= candidates[i] <= 200
     * All elements of candidates are distinct.
     * 1 <= target <= 500
     * @param candidates
     * @param target
     * @return
     */
    // time = O(n^(t/m + 1)), space = O(n^(t/m))  t: target, m: minimal val in candidates
    List<List<Integer>> res;
    List<Integer> path;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        res = new ArrayList<>();
        path = new ArrayList<>();
        dfs(candidates, 0, target);
        return res;
    }

    private void dfs(int[] a, int u, int t) {
        if (t == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        if (t < 0) return;

        for (int i = u; i < a.length; i++) {
            path.add(a[i]);
            dfs(a, i, t - a[i]);
            path.remove(path.size() - 1);
        }
    }
}
