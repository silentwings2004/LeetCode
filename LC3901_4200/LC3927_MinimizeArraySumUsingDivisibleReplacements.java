package LC3901_4200;

public class LC3927_MinimizeArraySumUsingDivisibleReplacements {
    /**
     * ou are given an integer array nums.
     *
     * You can perform the following operation any number of times:
     *
     * Choose two indices a and b such that nums[a] % nums[b] == 0.
     * Replace nums[a] with nums[b].
     * Return the minimum possible sum of the array after performing any number of operations.
     *
     * Input: nums = [3,6,2]
     * Output: 7
     *
     * Input: nums = [4,2,8,3]
     * Output: 9
     *
     * Input: nums = [7,5,9]
     * Output: 21
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // time = O(n + mlogm), space = O(m)  m = max(nums)
    public long minArraySum(int[] nums) {
        int mx = nums[0];
        for (int x : nums) mx = Math.max(mx, x);
        boolean[] st = new boolean[mx + 1];
        for (int x : nums) st[x] = true;
        int[] mind = new int[mx + 1];
        for (int i = 1; i <= mx; i++) {
            if (!st[i]) continue;
            for (int j = i; j <= mx; j += i) {
                if (st[j] && mind[j] == 0) mind[j] = i;
            }
        }
        long res = 0;
        for (int x : nums) res += mind[x];
        return res;
    }
}