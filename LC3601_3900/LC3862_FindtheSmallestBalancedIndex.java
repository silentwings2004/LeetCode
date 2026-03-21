package LC3601_3900;

public class LC3862_FindtheSmallestBalancedIndex {
    /**
     * You are given an integer array nums.
     *
     * An index i is balanced if the sum of elements strictly to the left of i equals the product of elements strictly
     * to the right of i.
     *
     * If there are no elements to the left, the sum is considered as 0. Similarly, if there are no elements to the
     * right, the product is considered as 1.
     *
     * Return an integer denoting the smallest balanced index. If no balanced index exists, return -1.
     *
     * Input: nums = [2,1,2]
     * Output: 1
     *
     * Input: nums = [2,8,2,2,5]
     * Output: 2
     *
     * Input: nums = [1]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int smallestBalancedIndex(int[] nums) {
        long s = 0, t = 1;
        for (int x : nums) s += x;
        int n = nums.length, res = -1;
        for (int i = n - 1; i >= 0; i--) {
            s -= nums[i];
            if (i + 1 < n) {
                if (t > Long.MAX_VALUE / nums[i + 1]) break;
                t *= nums[i + 1];
            }
            if (s == t) res = i;
            if (t > s) break;
        }
        return res;
    }
}