package LC3601_3900;

import java.util.HashSet;

public class LC3810_MinimumOperationstoReachTargetArray {
    /**
     * You are given two integer arrays nums and target, each of length n, where nums[i] is the current value at index i
     * and target[i] is the desired value at index i.
     *
     * You may perform the following operation any number of times (including zero):
     *
     * Choose an integer value x
     * Find all maximal contiguous segments where nums[i] == x (a segment is maximal if it cannot be extended to the
     * left or right while keeping all values equal to x)
     * For each such segment [l, r], update simultaneously:
     * nums[l] = target[l], nums[l + 1] = target[l + 1], ..., nums[r] = target[r]
     * Return the minimum number of operations required to make nums equal to target.
     *
     * Input: nums = [1,2,3], target = [2,1,3]
     * Output: 2
     *
     * Input: nums = [4,1,4], target = [5,1,4]
     * Output: 1
     *
     * Input: nums = [7,3,7], target = [5,5,9]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= n == nums.length == target.length <= 10^5
     * 1 <= nums[i], target[i] <= 10^5
     * @param nums
     * @param target
     * @return
     */
    // time = O(n), space = O(n)
    public int minOperations(int[] nums, int[] target) {
        int n = nums.length, res = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] == target[i]) continue;
            int j = i + 1;
            while (j < n && nums[j] == nums[j - 1]) j++;
            if (set.add(nums[i])) res++;
            i = j - 1;
        }
        return res;
    }
}