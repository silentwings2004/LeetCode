package LC001_300;
import java.util.*;
public class LC90_SubsetsII {
    /**
     * Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
     *
     * Note: The solution set must not contain duplicate subsets.
     *
     * Input: [1,2,2]
     * Output:
     * [
     *   [2],
     *   [1],
     *   [1,2,2],
     *   [2,2],
     *   [1,2],
     *   []
     * ]
     *
     * @param nums
     * @return
     */
    // time = O(n * 2^n), space = O(n)
    List<List<Integer>> res;
    List<Integer> path;
    int[] nums;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        res = new ArrayList<>();
        path = new ArrayList<>();
        this.nums = nums;
        Arrays.sort(nums);
        dfs(0);
        return res;
    }

    private void dfs(int u) {
        res.add(new ArrayList<>(path));

        for (int i = u; i < nums.length; i++) {
            if (i > u && nums[i] == nums[i - 1]) continue;
            path.add(nums[i]);
            dfs(i + 1);
            path.remove(path.size() - 1);
        }
    }
}
/**
 * 时间复杂度分析：不同子集的个数最多有 2^n 个，另外存储答案时还需要 O(n) 的计算量，所以时间复杂度是 O(n*2^n)。
 */