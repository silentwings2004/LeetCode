package LC2700_3000;

public class LC2778_SumofSquaresofSpecialElements {
    /**
     * You are given a 1-indexed integer array nums of length n.
     *
     * An element nums[i] of nums is called special if i divides n, i.e. n % i == 0.
     *
     * Return the sum of the squares of all special elements of nums.
     *
     * Input: nums = [1,2,3,4]
     * Output: 21
     *
     * Input: nums = [2,7,1,19,18,3]
     * Output: 63
     *
     * Constraints:
     *
     * 1 <= nums.length == n <= 50
     * 1 <= nums[i] <= 50
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int sumOfSquares(int[] nums) {
        int n = nums.length, res = 0;
        for (int i = 0; i < n; i++) {
            if (n % (i + 1) == 0) res += nums[i] * nums[i];
        }
        return res;
    }
}