package LC2700_3000;

public class LC2909_MinimumSumofMountainTripletsII {
    /**
     * You are given a 0-indexed array nums of integers.
     *
     * A triplet of indices (i, j, k) is a mountain if:
     *
     * i < j < k
     * nums[i] < nums[j] and nums[k] < nums[j]
     * Return the minimum possible sum of a mountain triplet of nums. If no such triplet exists, return -1.
     *
     * Input: nums = [8,6,1,5,3]
     * Output: 9
     *
     * Input: nums = [5,4,8,7,10,2]
     * Output: 13
     *
     * Input: nums = [6,5,4,3,4,5]
     * Output: -1
     *
     * Constraints:
     *
     * 3 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^8
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public int minimumSum(int[] nums) {
        int n = nums.length, INF = (int)1e9;
        int[] l = new int[n], r = new int[n];
        l[0] = INF;
        for (int i = 1; i < n; i++) l[i] = Math.min(l[i - 1], nums[i - 1]);
        r[n - 1] = INF;
        for (int i = n - 2; i >= 0; i--) r[i] = Math.min(r[i + 1], nums[i + 1]);
        int res = INF;
        for (int i = 1; i < n - 1; i++) {
            if (nums[i] > l[i] && nums[i] > r[i]) res = Math.min(res, l[i] + nums[i] + r[i]);
        }
        return res == INF ? -1 : res;
    }
}