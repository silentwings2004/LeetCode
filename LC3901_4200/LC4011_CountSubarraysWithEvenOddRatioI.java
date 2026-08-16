package LC3901_4200;

public class LC4011_CountSubarraysWithEvenOddRatioI {
    /**
     * You are given an integer array nums and two integers a and b.
     *
     * For a subarray, let:
     *
     * x be the number of even elements.
     * y be the number of odd elements.
     * The ratio of even to odd numbers in a subarray is defined as x / y, where the ratio is compared by its exact
     * rational value.
     *
     * A subarray is considered valid if:
     *
     * y > 0, and
     * x / y <= a / b.
     * Return the number of valid subarrays in nums.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [1,2,1,2], a = 3, b = 2
     * Output: 7
     *
     * Input: nums = [2,2,1], a = 2, b = 1
     * Output: 3
     *
     * Input: nums = [2,2,2], a = 1, b = 1
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * 1 <= nums[i] <= 1000
     * 1 <= a, b <= 1000
     * @param nums
     * @param a
     * @param b
     * @return
     */
    // time = O(n^2), space = O(n)
    public int countRatioSubarrays(int[] nums, int a, int b) {
        int n = nums.length;
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + (nums[i - 1] % 2 == 0 ? 1 : 0);

        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int x = s[j + 1] - s[i], y = j - i + 1 - x;
                if (1.0 * x / y <= 1.0 * a / b) res++;
            }
        }
        return res;
    }
}