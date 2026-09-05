package LC3901_4200;

public class LC4039_SumofDecodedNumbers {
    /**
     * You are given an integer array nums.
     *
     * Each nums[i] is an encoded integer representing two positive integers xi and yi. To decode nums[i], define:
     *
     * widthi = nums[i] % 10.
     * di = floor(nums[i] / 10).
     * xi as the integer formed by the first widthi digits of the decimal representation of di.
     * yi as the integer formed by all remaining digits of the decimal representation of di.
     * It is guaranteed that the decimal representation of di contains more than widthi digits. Therefore, both xi and
     * yi contain at least one digit.
     *
     * The decoded value of nums[i] is xiyi.
     *
     * Return the sum of the decoded values of all elements in nums, modulo 10^9 + 7.
     *
     * The floor() function returns the integer part of the division.
     *
     * Input: nums = [231]
     * Output: 8
     *
     * Input: nums = [2522,2101]
     * Output: 1649
     *
     * Input: nums = [2301]
     * Output: 73741817
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 100 < nums[i] < 10^15
     * 1 <= widthi <= 9
     * 1 <= xi, yi < 10^9
     * The digit sequences used to form xi and yi do not have leading zeros.
     * It is guaranteed that every element in nums is a valid encoded integer.
     * @param nums
     * @return
     */
    // time = O(n * logU), space = O(1)  U: max(nums)
    long mod = (long)(1e9 + 7);
    public int sumDecoded(long[] nums) {
        long res = 0;
        for (long v : nums) {
            int w = (int)(v % 10);
            long d = v / 10;
            long x = d, y = 0;
            int n = 0;
            while (d > 0) {
                n++;
                d /= 10;
            }
            long p = 1;
            for (int i = 0; i < n - w; i++) {
                y = x % 10 * p + y;
                x /= 10;
                p *= 10;
            }
            res = (res + qmi(x, y)) % mod;
        }
        return (int)res;
    }

    private long qmi(long a, long k) {
        long res = 1;
        while (k > 0) {
            if ((k & 1) == 1) res = res * a % mod;
            a = a * a % mod;
            k >>= 1;
        }
        return res;
    }
}