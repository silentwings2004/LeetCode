package LC3601_3900;

public class LC3618_SplitArraybyPrimeIndices {
    /**
     * You are given an integer array nums.
     *
     * Split nums into two arrays A and B using the following rule:
     *
     * Elements at prime indices in nums must go into array A.
     * All other elements must go into array B.
     * Return the absolute difference between the sums of the two arrays: |sum(A) - sum(B)|.
     *
     * A prime number is a natural number greater than 1 with only two factors, 1 and itself.
     *
     * Note: An empty array has a sum of 0.
     *
     * Input: nums = [2,3,4]
     * Output: 1
     *
     * Input: nums = [-1,5,7,0]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * -10^9 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(nlogk), space = O(1)
    public long splitArray(int[] nums) {
        int n = nums.length;
        long s1 = 0, s2 = 0;
        for (int i = 0; i < n; i++) {
            if (check(i)) s1 += nums[i];
            else s2 += nums[i];
        }
        return Math.abs(s1 - s2);
    }

    private boolean check(int x) {
        if (x < 2) return false;
        for (int i = 2; i <= x / i; i++) {
            if (x % i == 0) return false;
        }
        return true;
    }
}