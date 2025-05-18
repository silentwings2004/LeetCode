package LC301_600;
import java.util.*;
public class LC491_IncreasingSubsequences {
    /**
     * Given an integer array nums, return all the different possible increasing subsequences of the given array with
     * at least two elements. You may return the answer in any order.
     *
     * The given array may contain duplicates, and two equal integers should also be considered a special case of
     * increasing sequence.
     *
     * Input: nums = [4,6,7,7]
     * Output: [[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 15
     * -100 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n * 2^n), space = O(2^n)
    List<List<Integer>> res;
    List<Integer> path;
    public List<List<Integer>> findSubsequences(int[] nums) {
        res = new ArrayList<>();
        path = new ArrayList<>();
        dfs(nums, 0);
        return res;
    }

    private void dfs(int[] nums, int u) {
        if (path.size() >= 2) res.add(new ArrayList<>(path));
        if (u == nums.length) return;

        HashSet<Integer> set = new HashSet<>();
        for (int i = u; i < nums.length; i++) {
            if (!set.add(nums[i])) continue;
            if (path.size() > 0 && nums[i] < path.get(path.size() - 1)) continue;
            path.add(nums[i]);
            dfs(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }
}