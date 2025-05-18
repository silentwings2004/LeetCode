package LC3301_3600;

public class LC3471_FindtheLargestAlmostMissingInteger {
    /**
     * You are given an integer array nums and an integer k.
     *
     * An integer x is almost missing from nums if x appears in exactly one subarray of size k within nums.
     *
     * Return the largest almost missing integer from nums. If no such integer exists, return -1.
     *
     * A subarray is a contiguous sequence of elements within an array.
     *
     * Input: nums = [3,9,2,1,7], k = 3
     * Output: 7
     *
     * Input: nums = [3,9,7,2,1,7], k = 4
     * Output: 3
     *
     * Input: nums = [0,0], k = 1
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 50
     * 0 <= nums[i] <= 50
     * 1 <= k <= nums.length
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    public int largestInteger(int[] nums, int k) {
        int n = nums.length, res = -1;
        for (int x : nums) {
            int[] cnt = new int[60];
            int t = 0;
            boolean f = false;
            for (int i = 0, j = 0; i < n; i++) {
                cnt[nums[i]]++;
                if (i - j + 1 == k) {
                    if (cnt[x] > 0) {
                        if (t > 0) {
                            f = false;
                            break;
                        }
                        t++;
                        f = true;
                    }
                    cnt[nums[j++]]--;
                }
            }
            if (f && t == 1) res = Math.max(res, x);
        }
        return res;
    }
}