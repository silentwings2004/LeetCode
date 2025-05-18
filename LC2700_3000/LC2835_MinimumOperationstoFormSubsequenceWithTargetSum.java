package LC2700_3000;
import java.util.*;
public class LC2835_MinimumOperationstoFormSubsequenceWithTargetSum {
    /**
     * You are given a 0-indexed array nums consisting of non-negative powers of 2, and an integer target.
     *
     * In one operation, you must apply the following changes to the array:
     *
     * Choose any element of the array nums[i] such that nums[i] > 1.
     * Remove nums[i] from the array.
     * Add two occurrences of nums[i] / 2 to the end of nums.
     * Return the minimum number of operations you need to perform so that nums contains a subsequence whose elements
     * sum to target. If it is impossible to obtain such a subsequence, return -1.
     *
     * A subsequence is an array that can be derived from another array by deleting some or no elements without changing
     * the order of the remaining elements.
     *
     * Input: nums = [1,2,8], target = 7
     * Output: 1
     *
     * Input: nums = [1,32,1,2], target = 12
     * Output: 2
     *
     * Input: nums = [1,32,1], target = 35
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * 1 <= nums[i] <= 2^30
     * nums consists only of non-negative powers of two.
     * 1 <= target < 2^31
     * @param nums
     * @param target
     * @return
     */
    // time = O(30 * n), space = O(1)
    public int minOperations(List<Integer> nums, int target) {
        int[] c = new int[32], d = new int[32];
        for (int i = 0; i <= 30; i++) {
            if ((target >> i & 1) == 1) c[i]++;
        }

        for (int x : nums) {
            if (x == target) return 0;
            for (int i = 0; i <= 30; i++) {
                if ((x >> i & 1) == 1) d[i]++;
            }
        }

        int res = 0;
        for (int i = 0; i <= 30; i++) {
            if (d[i] >= c[i]) {
                int t = d[i] - c[i];
                d[i + 1] += t / 2;
            } else {
                int j = i + 1;
                while (j <= 30 && d[j] == 0) j++;
                if (j > 30) return -1;
                d[j]--;
                d[i] += 2;
                res += j - i;
                for (int k = i + 1; k < j; k++) d[k]++;
            }
        }
        return res;
    }
}