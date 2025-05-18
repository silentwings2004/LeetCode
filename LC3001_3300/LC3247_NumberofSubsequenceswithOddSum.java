package LC3001_3300;

public class LC3247_NumberofSubsequenceswithOddSum {
    /**
     * Given an array nums, return the number of subsequences with an odd sum of elements.
     *
     * Since the answer may be very large, return it modulo 10^9 + 7.
     *
     * Input: nums = [1,1,1]
     * Output: 4
     *
     * Input: nums = [1,2,2]
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= nums.lnegth <= 10^5
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int subsequenceCount(int[] nums) {
        int n = nums.length;
        long mod = (long)(1e9 + 7);
        long odd = 0, even = 0;

        for (int i = 0; i < n; i++) {
            long x = odd, y = even;
            if (nums[i] % 2 == 1) {
                odd = (x + y + 1) % mod;
                even = (y + x) % mod;
            } else {
                odd = x * 2 % mod;
                even = (y * 2 + 1) % mod;
            }
        }
        return (int)odd;
    }
}