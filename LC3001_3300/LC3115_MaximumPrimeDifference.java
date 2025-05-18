package LC3001_3300;

public class LC3115_MaximumPrimeDifference {
    /**
     * You are given an integer array nums.
     *
     * Return an integer that is the maximum distance between the indices of two (not necessarily different) prime
     * numbers in nums.
     *
     * Input: nums = [4,2,9,5,3]
     *
     * Output: 3
     *
     * Input: nums = [4,8,2,8]
     *
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 3 * 10^5
     * 1 <= nums[i] <= 100
     * The input is generated such that the number of prime numbers in the nums is at least one.
     * @param nums
     * @return
     */
    // time = O(n*sqrt(k)), space = O(1)
    public int maximumPrimeDifference(int[] nums) {
        int n = nums.length, res = 0, last = -1;
        for (int i = 0; i < n; i++) {
            if (isPrime(nums[i])) {
                if (last == -1) last = i;
                else res = i - last;
            }
        }
        return res;
    }

    private boolean isPrime(int x) {
        if (x < 2) return false;
        for (int i = 2; i <= x / i; i++) {
            if (x % i == 0) return false;
        }
        return true;
    }
}