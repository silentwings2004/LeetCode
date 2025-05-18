package LC2101_2400;

public class LC2367_NumberofArithmeticTriplets {
    /**
     * You are given a 0-indexed, strictly increasing integer array nums and a positive integer diff. A triplet
     * (i, j, k) is an arithmetic triplet if the following conditions are met:
     *
     * i < j < k,
     * nums[j] - nums[i] == diff, and
     * nums[k] - nums[j] == diff.
     * Return the number of unique arithmetic triplets.
     *
     * Input: nums = [0,1,4,6,7,10], diff = 3
     * Output: 2
     *
     * Constraints:
     *
     * 3 <= nums.length <= 200
     * 0 <= nums[i] <= 200
     * 1 <= diff <= 50
     * nums is strictly increasing.
     * @param nums
     * @param diff
     * @return
     */
    // S1: brute-force
    // time = O(n^3), space = O(1)
    public int arithmeticTriplets(int[] nums, int diff) {
        int n = nums.length, res = 0;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[j] - nums[i] == diff && nums[k] - nums[j] == diff) {
                        res++;
                    }
                }
            }
        }
        return res;
    }

    // S2: count
    // time = O(n), space = O(1)
    public int arithmeticTriplets2(int[] nums, int diff) {
        boolean[] cnt = new boolean[201];
        int res = 0;

        for (int x : nums) {
            if (x >= 2 * diff) res += cnt[x - diff] && cnt[x - 2 * diff] ? 1 : 0;
            cnt[x] = true;
        }
        return res;
    }
}
