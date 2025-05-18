package LC2101_2400;

public class LC2369_CheckifThereisaValidPartitionForTheArray {
    /**
     * You are given a 0-indexed integer array nums. You have to partition the array into one or more contiguous
     * subarrays.
     *
     * We call a partition of the array valid if each of the obtained subarrays satisfies one of the following conditions:
     *
     * The subarray consists of exactly 2 equal elements. For example, the subarray [2,2] is good.
     * The subarray consists of exactly 3 equal elements. For example, the subarray [4,4,4] is good.
     * The subarray consists of exactly 3 consecutive increasing elements, that is, the difference between adjacent
     * elements is 1. For example, the subarray [3,4,5] is good, but the subarray [1,3,5] is not.
     * Return true if the array has at least one valid partition. Otherwise, return false.
     *
     * Input: nums = [4,4,4,5,6]
     * Output: true
     *
     * Input: nums = [1,1,1,2]
     * Output: false
     *
     * Constraints:
     *
     * 2 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^6
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public boolean validPartition(int[] nums) {
        int n = nums.length;
        boolean[] f = new boolean[n + 1];
        f[0] = true;
        f[1] = false;
        for (int i = 2; i <= n; i++) {
            f[i] = f[i - 2] && (nums[i - 2] == nums[i - 1]);
            if (i >= 3) {
                f[i] |= f[i - 3] && (nums[i - 3] == nums[i - 2] && nums[i - 2] == nums[i - 1]);
                f[i] |= f[i - 3] && (nums[i - 3] + 1 == nums[i - 2] && nums[i - 2] + 1 == nums[i - 1]);
            }
        }
        return f[n];
    }
}
