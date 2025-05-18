package LC3301_3600;

public class LC3411_MaximumSubarrayWithEqualProducts {
    /**
     * You are given an array of positive integers nums.
     *
     * An array arr is called product equivalent if prod(arr) == lcm(arr) * gcd(arr), where:
     *
     * prod(arr) is the product of all elements of arr.
     * gcd(arr) is the GCD of all elements of arr.
     * lcm(arr) is the LCM of all elements of arr.
     * Return the length of the longest product equivalent subarray of nums.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * The term gcd(a, b) denotes the greatest common divisor of a and b.
     *
     * The term lcm(a, b) denotes the least common multiple of a and b.
     *
     * nput: nums = [1,2,1,2,1,1,1]
     * Output: 5
     *
     * Input: nums = [2,3,4,5,6]
     * Output: 3
     *
     * Input: nums = [1,2,3,1,4,5,1]
     * Output: 5
     *
     * Constraints:
     *
     * 2 <= nums.length <= 100
     * 1 <= nums[i] <= 10
     * @param nums
     * @return
     */
    // time = O(n^2 * logU), space = O(1)  U = max(nums)
    public int maxLength(int[] nums) {
        int n = nums.length, res = 0;
        for (int i = 0; i < n; i++) {
            int a = 1, b = 0, c = 1;
            for (int j = i; j < n; j++) {
                a *= nums[j];
                b = gcd(b, nums[j]);
                c = c * nums[j] / gcd(c, nums[j]);
                if (a == b * c) res = Math.max(res, j - i + 1);
            }
        }
        return res;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // S2
    // time = O(nlogU), space = O(1)
    public int maxLength2(int[] nums) {
        int n = nums.length, res = 2, mul = 1, j = 0;
        for (int i = 0; i < n; i++) {
            while (gcd(mul, nums[i]) > 1) mul /= nums[j++];
            mul *= nums[i];
            res = Math.max(res, i - j + 1);
        }
        return res;
    }
}
/**
 * if k >= 3 => 那么这 k 个数必须两两互质
 */