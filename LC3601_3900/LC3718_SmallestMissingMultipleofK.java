package LC3601_3900;

public class LC3718_SmallestMissingMultipleofK {
    /**
     * Given an integer array nums and an integer k, return the smallest positive multiple of k that is missing from nums.
     *
     * A multiple of k is any positive integer divisible by k.
     *
     * Input: nums = [8,2,3,4,6], k = 2
     * Output: 10
     *
     * Input: nums = [1,4,7,10,15], k = 5
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     * 1 <= k <= 100
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public int missingMultiple(int[] nums, int k) {
        boolean[] st = new boolean[102];
        for (int x : nums) {
            if (x % k == 0 && x / k <= 101) st[x / k] = true;
        }
        int res = 0;
        for (int i = 1; i <= 101; i++) {
            if (!st[i]) {
                res = i * k;
                break;
            }
        }
        return res;
    }
}