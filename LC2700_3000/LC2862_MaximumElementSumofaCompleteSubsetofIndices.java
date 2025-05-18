package LC2700_3000;
import java.util.*;
public class LC2862_MaximumElementSumofaCompleteSubsetofIndices {
    /**
     * You are given a 1-indexed array nums of n integers.
     *
     * A set of numbers is complete if the product of every pair of its elements is a perfect square.
     *
     * For a subset of the indices set {1, 2, ..., n} represented as {i1, i2, ..., ik}, we define its element-sum as:
     * nums[i1] + nums[i2] + ... + nums[ik].
     *
     * Return the maximum element-sum of a complete subset of the indices set {1, 2, ..., n}.
     *
     * A perfect square is a number that can be expressed as the product of an integer by itself.
     *
     * Input: nums = [8,7,3,5,7,2,4,9]
     * Output: 16
     *
     * Input: nums = [5,10,3,10,1,13,7,9,4]
     * Output: 19
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 10^4
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public long maximumSum(List<Integer> nums) {
        long res = 0;
        for (int x : nums) res = Math.max(res, x);
        int n = nums.size();
        for (int k = 1; k <= n; k++) {
            long sum = 0;
            for (int i = 1; i * i * k <= n; i++) sum += nums.get(i * i * k - 1);
            res = Math.max(res, sum);
        }
        return res;
    }

    // S2
    // time = O(n * sqrt(n)), space = O(n)
    public long maximumSum2(List<Integer> nums) {
        long res = 0;
        int n = nums.size();
        long[] s = new long[n + 1];
        for (int i = 0; i < n; i++){
            int c = get(i + 1);
            s[c] += nums.get(i);
            res = Math.max(res, s[c]);
        }
        return res;
    }

    private int get(int n) {
        int res = 1;
        for (int i = 2; i <= n / i; i++) {
            int cnt = 0;
            while (n % i == 0) {
                cnt++;
                n /= i;
            }
            if (cnt % 2 == 1) res *= i;
        }
        if (n > 1) res *= n;
        return res;
    }
}