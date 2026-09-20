package LC3901_4200;

public class LC4054_CountShadowPairsI {
    /**
     * You are given an integer array nums of length n.
     *
     * A pair of indices (i, j) is called a shadow pair if all of the following conditions are satisfied:
     *
     * 0 <= i < j < n
     * nums[i] < nums[j]
     * There does not exist an index k such that i < k < j and nums[k] < nums[i] < nums[j].
     * Return the total number of shadow pairs.
     *
     * Input: nums = [3,1,4,1,5]
     * Output: 3
     *
     * Input: nums = [6,7,6,6,7]
     * Output: 4
     *
     * Input: nums = [1,2,3,4]
     * Output: 6
     *
     * Constraints:
     *
     * 3 <= n == nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(n)
    public long shadowPairs(int[] nums) {
        int n = nums.length;
        int[] stk = new int[n + 1];
        int tt = 0;
        long res = 0;

        for (int x : nums) {
            int l = 1, r = tt;
            while (l < r) {
                int mid = l + r >> 1;
                if (stk[mid] >= x) r = mid;
                else l = mid + 1;
            }
            int idx = stk[r] >= x ? r : r + 1;
            res += idx - 1;
            while (tt > 0 && stk[tt] > x) tt--;
            stk[++tt] = x;
        }
        return res;
    }
}