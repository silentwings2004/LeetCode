package LC3301_3600;

public class LC3576_TransformArraytoAllEqualElements {
    /**
     * You are given an integer array nums of size n containing only 1 and -1, and an integer k.
     *
     * You can perform the following operation at most k times:
     *
     * Choose an index i (0 <= i < n - 1), and multiply both nums[i] and nums[i + 1] by -1.
     *
     * Note that you can choose the same index i more than once in different operations.
     *
     * Return true if it is possible to make all elements of the array equal after at most k operations, and false
     * otherwise.
     *
     * Input: nums = [1,-1,1,-1,1], k = 3
     * Output: true
     *
     * Input: nums = [-1,-1,-1,1,1,1], k = 5
     * Output: false
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 10^5
     * nums[i] is either -1 or 1.
     * 1 <= k <= n
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public boolean canMakeEqual(int[] nums, int k) {
        return helper(nums, k, 1) || helper(nums, k, -1);
    }

    private boolean helper(int[] nums, int k, int v) {
        int n = nums.length, cnt = 0, mul = 1;
        for (int i = 0; i < n; i++) {
            if (nums[i] * mul == v) {
                mul = 1;
                continue;
            }
            if (cnt == k || i == n - 1) return false;
            cnt++;
            mul = -1;
        }
        return true;
    }
}