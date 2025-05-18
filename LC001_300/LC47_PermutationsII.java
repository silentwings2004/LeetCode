package LC001_300;
import java.util.*;
public class LC47_PermutationsII {
    /**
     * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in
     * any order.
     *
     * Input: nums = [1,1,2]
     * Output:
     * [[1,1,2],
     *  [1,2,1],
     *  [2,1,1]]
     *
     * Input: nums = [1,2,3]
     * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 8
     * -10 <= nums[i] <= 10
     * @param nums
     * @return
     */
    // S1: swap
    // time = O(n * n!), space = O(n)
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // corner case
        if (nums == null || nums.length == 0) return res;

        dfs(nums, 0, res);
        return res;
    }

    private void dfs(int[] nums, int idx, List<List<Integer>> res) {
        // base case
        if (idx == nums.length) {
            List<Integer> list = new ArrayList<>();
            for (int n : nums) list.add(n);
            res.add(list);
            return;
        }

        HashSet<Integer> set = new HashSet<>();
        for (int i = idx; i < nums.length; i++) {
            if (set.add(nums[i])) {
                swap(nums, idx, i);
                dfs(nums, idx + 1, res);
                swap(nums, idx, i);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // S2: dfs
    // time = O(n * n!), space = O(n)
    List<List<Integer>> res;
    List<Integer> path;
    boolean[] st;
    public List<List<Integer>> permuteUnique2(int[] nums) {
        res = new ArrayList<>();
        path = new ArrayList<>();
        st = new boolean[nums.length];
        Arrays.sort(nums);

        dfs(nums, 0);
        return res;
    }

    private void dfs(int[] nums, int u) {
        if (u == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!st[i]) {
                if (i > 0 && nums[i] == nums[i - 1] && !st[i - 1]) continue;
                st[i] = true;
                path.add(nums[i]);
                dfs(nums, u + 1);
                path.remove(path.size() - 1);
                st[i] = false;
            }
        }
    }
}