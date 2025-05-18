package LC001_300;
import java.util.*;
public class LC78_Subsets {
    /**
     * Given an integer array nums, return all possible subsets (the power set).
     *
     * The solution set must not contain duplicate subsets.
     *
     * Input: nums = [1,2,3]
     * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10
     * -10 <= nums[i] <= 10
     * All the numbers of nums are unique.
     *
     * @param nums
     * @return
     */
    // S1: dfs
    // time = O(n * 2^n), space = O(n)
    List<List<Integer>> res;
    List<Integer> path;
    int[] nums;
    public List<List<Integer>> subsets(int[] nums) {
        this.nums = nums;
        res = new ArrayList<>();
        path = new ArrayList<>();
        dfs(0);
        return res;
    }

    private void dfs(int u) {
        res.add(new ArrayList<>(path));

        for (int i = u; i < nums.length; i++) {
            path.add(nums[i]);
            dfs(i + 1);
            path.remove(path.size() - 1);
        }
    }

    // S2: state compression
    // time = O(2^n * n), space = O(n)
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        for (int state = 0; state < (1 << n); state++) {
            List<Integer> path = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if ((state >> i & 1) == 1) path.add(nums[i]);
            }
            res.add(path);
        }
        return res;
    }
}

/**
 * 时间复杂度： O(n * 2^n)
 *                     { }
 *                    / |  \
 *                 {1} {2} {3}     --> O(n)
 *                /  \
 *           2取/不取  3取/不取      --> O(2^(n - 1))
 *      {1, 2, 3}, {1, 3}, {1, 2}, {1}  ==> O(2^n)
 *      but we also need to do deep copy for each possible subset and add it to the res list,
 *      and in worst case the cost of deep copy is O(n)
 *      ==> time = O(n * 2^n)
 *
 * 空间复杂度：O(n) 递归时的栈空间消耗，也就是压栈的深度，如果考虑output的space，那么space = O(n * 2^n)
 */
